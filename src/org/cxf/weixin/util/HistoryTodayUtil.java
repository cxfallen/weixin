package org.cxf.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HistoryTodayUtil {
	public static String message;
	
	private static String httpRequest(String requestUrl) {
		StringBuffer stringBuffer = null;
		
		try {
			//��������
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			
			//��ȡ������
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			//��ȡ���ؽ��
			stringBuffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				stringBuffer.append(str);
			}
			
			//�ͷ���Դ
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			httpUrlConn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	private static String extract(String html) {
		StringBuffer buffer = null;
		
		String dateTag = getMonthDay(0);
		
		Pattern p = Pattern.compile("(.*)(<div class=\"listren\">)(.*?)(</div>)(.*)"); 
		Matcher m = p.matcher(html);
		
		if (m.matches()) {
			buffer = new StringBuffer();
			
			if (m.group(3).contains(getMonthDay(-1)))  
                dateTag = getMonthDay(-1);  
			
			buffer.append("===").append("��ʷ�ϵ�").append(dateTag).append("===").append("\n\n");
			
			for (String info : m.group(3).split("  ")) {  
                info = info.replace(dateTag, "").replace("��ͼ��", "").replace("&nbsp;&nbsp;", "").replaceAll("</?[^>]+>", "").trim();  
                // ��ÿ��ĩβ׷��2�����з�  
                if (!"".equals(info)) {  
                    buffer.append(info).append("\n\n");  
                }
			}
		}
		return (null == buffer) ? null : buffer.substring(0, buffer.lastIndexOf("\n\n"));
	}
	
	private static String getMonthDay(int amount) {
		DateFormat df = new SimpleDateFormat("M��d��");
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_YEAR, amount);
		return df.format(calendar.getTime());
	}
	public static String getTodayInHistoryInfo() {
		String html = httpRequest("http://www.rijiben.com/");
		String result = extract(html);
		message = result;
		return result + "\n\n" + "�ظ���?����ʾ���˵�";
	}

}
