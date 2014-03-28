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
			//建立链接
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			
			//获取输入流
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			//读取返回结果
			stringBuffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				stringBuffer.append(str);
			}
			
			//释放资源
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
			
			buffer.append("===").append("历史上的").append(dateTag).append("===").append("\n\n");
			
			for (String info : m.group(3).split("  ")) {  
                info = info.replace(dateTag, "").replace("（图）", "").replace("&nbsp;&nbsp;", "").replaceAll("</?[^>]+>", "").trim();  
                // 在每行末尾追加2个换行符  
                if (!"".equals(info)) {  
                    buffer.append(info).append("\n\n");  
                }
			}
		}
		return (null == buffer) ? null : buffer.substring(0, buffer.lastIndexOf("\n\n"));
	}
	
	private static String getMonthDay(int amount) {
		DateFormat df = new SimpleDateFormat("M月d日");
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_YEAR, amount);
		return df.format(calendar.getTime());
	}
	public static String getTodayInHistoryInfo() {
		String html = httpRequest("http://www.rijiben.com/");
		String result = extract(html);
		message = result;
		return result + "\n\n" + "回复“?”显示主菜单";
	}

}
