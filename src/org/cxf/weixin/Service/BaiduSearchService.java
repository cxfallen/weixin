package org.cxf.weixin.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BaiduSearchService {
	
	public static String httpRequest(String requestUrl) {

		StringBuffer buffer = new StringBuffer();
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
			
			httpUrlConn.setDoInput(true);
			httpUrlConn.setDoOutput(true);
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
			result = URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String search(String keyWord) {
		try {
			String requestUrl = "http://api.map.baidu.com/place/search?query={keyword}"
					+ "&location={locationX},{locationY}&radius=1000&region=beijing&output=html";
//			String requestUrl = "http://api.map.baidu.com/place/search?query={keyword}"
//					+ "&location=39.914895,116.490757&radius=1000&region=北京&output=html";
//			requestUrl = requestUrl.replace("{keyWord}", urlEncodeUTF8(keyword));
//			
			requestUrl = requestUrl.replace("{keyword}", urlEncodeUTF8(keyWord));
			requestUrl = requestUrl.replace("{locationX}", CoreService.locationX);
			requestUrl = requestUrl.replace("{locationY}", urlEncodeUTF8(CoreService.locationY));
			
//			rslt = httpRequest(requestUrl);
			return requestUrl;
		} catch (Exception e) {
			return "请先上报地理位置！";
		}		
//		rslt = httpRequest(requestUrl);
	}
	public static void main(String[] args) {
		System.out.println(search("atm"));
	}
}
