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
			
			//将返回的输入流转为字符串
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
            dst = "翻译系统异常，请稍候尝试！";  
        return dst;  
	}
	
	public static String getTranslateUsage() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append(QQFaceUtil.emoji(0xe148)).append("Q译通使用指南").append("\n\n");  
	    buffer.append("Q译通为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");  
	    buffer.append("    中 -> 英").append("\n");  
	    buffer.append("    英 -> 中").append("\n");  
	    buffer.append("    日 -> 中").append("\n\n");  
	    buffer.append("使用示例：").append("\n");  
	    buffer.append("    翻译我是中国人").append("\n");  
	    buffer.append("    翻译dream").append("\n");  
	    buffer.append("    翻译さようなら").append("\n\n");  
	    buffer.append("回复“?”显示主菜单");  
	    return buffer.toString();  
	}  
//	
//	public static void main(String[] args) {
//		System.out.println(translate("网络真强大"));
//	}
}
