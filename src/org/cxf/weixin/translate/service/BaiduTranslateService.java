package org.cxf.weixin.translate.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.cxf.weixin.util.QQFaceUtil;

import com.google.gson.Gson;

public class BaiduTranslateService {
	public static String httpRequest(String requestUrl) {

		StringBuffer buffer = new StringBuffer();
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
			
			httpUrlConn.setDoInput(true);
			httpUrlConn.setDoOutput(false);
			httpUrlConn.setUseCaches(false);
			
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			
			//�����ص�������תΪ�ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			
			String str = null;  
            while ((str = reader.readLine()) != null) {  
                buffer.append(str);  
            }  
            
            reader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String translate(String source) {
		String dst = null;
		
		String requestUrl = "http://openapi.baidu.com/public/2.0/bmt/translate?client_"
				+ "id=XKGWiGQiWaZOsiHPWCHTnrsw&q={keyWord}&from=auto&to=auto";
		requestUrl = requestUrl.replace("{keyWord}", urlEncodeUTF8(source));
		
		try {
			String json = httpRequest(requestUrl);
			TranslateResult tr = new Gson().fromJson(json,TranslateResult.class);
			dst = tr.getTrans_result().get(0).getDst();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		if (null == dst)  
            dst = "����ϵͳ�쳣�����Ժ��ԣ�";  
        return dst;  
	}
	
	public static String getTranslateUsage() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append(QQFaceUtil.emoji(0xe148)).append("Q��ͨʹ��ָ��").append("\n\n");  
	    buffer.append("Q��ͨΪ�û��ṩרҵ�Ķ����Է������Ŀǰ֧�����·��뷽��").append("\n");  
	    buffer.append("    �� -> Ӣ").append("\n");  
	    buffer.append("    Ӣ -> ��").append("\n");  
	    buffer.append("    �� -> ��").append("\n\n");  
	    buffer.append("ʹ��ʾ����").append("\n");  
	    buffer.append("    ���������й���").append("\n");  
	    buffer.append("    ����dream").append("\n");  
	    buffer.append("    ���뤵�褦�ʤ�").append("\n\n");  
	    buffer.append("�ظ���?����ʾ���˵�");  
	    return buffer.toString();  
	}  
//	
//	public static void main(String[] args) {
//		System.out.println(translate("������ǿ��"));
//	}
}
