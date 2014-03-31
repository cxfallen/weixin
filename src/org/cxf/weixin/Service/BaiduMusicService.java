package org.cxf.weixin.Service;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.cxf.weixin.message.resp.Music;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BaiduMusicService {
//	public static String musicstatic;
	public static Music searchMusic(String musicTitle, String musicAuthor) {
		String requestUrl = "http://box.zhangmen.baidu.com/x?op=12&count=1&title={TITLE}$${AUTHOR}$$$$";
		requestUrl = requestUrl.replace("{TITLE}", urlEncodeUTF8(musicTitle)).replace("{AUTHOR}", urlEncodeUTF8(musicAuthor));
//		requestUrl = requestUrl.replaceAll("\\+", "%20");  
		
		InputStream inputStream = httpRequest(requestUrl);
		
		Music music = parseMusic(inputStream);
		if (null != music) {  
            music.setTitle(musicTitle);  
            // 如果作者不为""，将描述设置为作者  
            if (!"".equals(musicAuthor))  
                music.setDescription(musicAuthor);  
            else  
                music.setDescription("来自百度音乐");  
        }  
//		BaiduMusicService.musicstatic = "description:" + music.getDescription() + "title:" +
//        music.getTitle() + "url:" + music.getMusicUrl() + "hqurl:" + music.getHQMusicUrl();
		return music;
	}

	private static Music parseMusic(InputStream inputStream) {
		Music music = null;
		try {
			// 使用dom4j解析xml字符串  
            SAXReader reader = new SAXReader();  
            Document document = reader.read(inputStream);  
            // 得到xml根元素  
            Element root = document.getRootElement();  
            // count表示搜索到的歌曲数  
            String count = root.element("count").getText();  
            // 当搜索到的歌曲数大于0时  
            if (!"0".equals(count)) {  
                // 普通品质  
                List<Element> urlList = root.elements("url");  
                // 高品质  
                List<Element> durlList = root.elements("durl");  
  
                // 普通品质的encode、decode  
                String urlEncode = urlList.get(0).element("encode").getText();  
                String urlDecode = urlList.get(0).element("decode").getText();  
                // 普通品质音乐的URL  
                String url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode;  
                if (-1 != urlDecode.lastIndexOf("&"))  
                    url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode.substring(0, urlDecode.lastIndexOf("&"));  
  
                // 默认情况下，高音质音乐的URL 等于 普通品质音乐的URL  
                String durl = url;  
  
                // 判断高品质节点是否存在  
                Element durlElement = durlList.get(0).element("encode");  
                if (null != durlElement) {  
                    // 高品质的encode、decode  
                    String durlEncode = durlList.get(0).element("encode").getText();  
                    String durlDecode = durlList.get(0).element("decode").getText();  
                    // 高品质音乐的URL  
                    durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode;  
                    if (-1 != durlDecode.lastIndexOf("&"))  
                        durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode.substring(0, durlDecode.lastIndexOf("&"));  
                }  
                music = new Music();  
                // 设置普通品质音乐链接  
                music.setMusicUrl(url);  
                // 设置高品质音乐链接  
                music.setHQMusicUrl(durl);
//                music.setThumbMediaId("");
            }
            else {
				System.out.println("no such music");
				music = new Music();
				music.setMusicUrl("");
				music.setHQMusicUrl("");
				
				return music;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return music;
	}

	private static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println(result);
		return result;
	}

	private static InputStream httpRequest(String requestUrl) {	
		InputStream inputStream = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
            
            inputStream = httpUrlConn.getInputStream();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputStream;
	}

	public static String getMusicUsage() {
		  StringBuffer buffer = new StringBuffer();  
	        buffer.append("\ue03c").append("歌曲点播操作指南").append("\n\n");  
	        buffer.append("回复：歌曲+歌名").append("\n");  
	        buffer.append("例如：歌曲存在").append("\n");  
	        buffer.append("或者：歌曲存在@汪峰").append("\n\n");  
	        buffer.append("回复“?”显示主菜单");  
	        return buffer.toString();  
	}
//
//	public static void main(String[] args) {  
//        Music music = searchMusic("相信自己", "零点乐队");  
//        System.out.println("音乐名称：" + music.getTitle());  
//        System.out.println("音乐描述：" + music.getDescription());  
//        System.out.println("普通品质链接：" + music.getMusicUrl());  
//        System.out.println("高品质链接：" + music.getHQMusicUrl());  
//    }  
}

