package org.cxf.weixin.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

public class BaiduWeatherService {
	
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
			// TODO: handle exception
		}
		return buffer.toString();
	}

	public static String showWeather(String city) {
		String rslt = null;
		
		String requestUrl = "http://api.map.baidu.com/telematics/v3/"
				+ "weather?location=CITY&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ"
				.replace("CITY", urlEncodeUTF8(city));
		try {
			String json = httpRequest(requestUrl);
			WeatherResult wr = new Gson().fromJson(json, WeatherResult.class);
			int err = Integer.parseInt(wr.getError());
			if (err == 0) {
				rslt = wr.getResults().get(0).getCurrentCity() + ":" + "\n" + wr.getResults().get(0).getWeather_Data()
						.get(0).getDate() + "\n" + wr.getResults().get(0).getWeather_Data().get(0).getWeather() + 
						"\n" + wr.getResults().get(0).getWeather_Data().get(0).getWind()
						+ "\n" + wr.getResults().get(0).getWeather_Data().get(0).getTemperature() + "\n\n" + 
						wr.getResults().get(0).getWeather_Data()
						.get(1).getDate() + "\n" + wr.getResults().get(0).getWeather_Data().get(1).getWeather() + 
						"\n" + wr.getResults().get(0).getWeather_Data().get(1).getWind()
						+ "\n" + wr.getResults().get(0).getWeather_Data().get(1).getTemperature();
			} else {
				rslt = wr.getStatus() + "\n" + "回复“?”显示主菜单";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (rslt.equals("")) {
			rslt = "抱歉，未查询到您所需的信息.";
		}
		return rslt;
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
	
	public static void main(String[] args) {
		String cityString = "天气北京";
		System.out.println(showWeather(cityString));
	}
}
