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
            // ������߲�Ϊ""������������Ϊ����  
            if (!"".equals(musicAuthor))  
                music.setDescription(musicAuthor);  
            else  
                music.setDescription("���԰ٶ�����");  
        }  
//		BaiduMusicService.musicstatic = "description:" + music.getDescription() + "title:" +
//        music.getTitle() + "url:" + music.getMusicUrl() + "hqurl:" + music.getHQMusicUrl();
		return music;
	}

	private static Music parseMusic(InputStream inputStream) {
		Music music = null;
		try {
			// ʹ��dom4j����xml�ַ���  
            SAXReader reader = new SAXReader();  
            Document document = reader.read(inputStream);  
            // �õ�xml��Ԫ��  
            Element root = document.getRootElement();  
            // count��ʾ�������ĸ�����  
            String count = root.element("count").getText();  
            // ���������ĸ���������0ʱ  
            if (!"0".equals(count)) {  
                // ��ͨƷ��  
                List<Element> urlList = root.elements("url");  
                // ��Ʒ��  
                List<Element> durlList = root.elements("durl");  
  
                // ��ͨƷ�ʵ�encode��decode  
                String urlEncode = urlList.get(0).element("encode").getText();  
                String urlDecode = urlList.get(0).element("decode").getText();  
                // ��ͨƷ�����ֵ�URL  
                String url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode;  
                if (-1 != urlDecode.lastIndexOf("&"))  
                    url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode.substring(0, urlDecode.lastIndexOf("&"));  
  
                // Ĭ������£����������ֵ�URL ���� ��ͨƷ�����ֵ�URL  
                String durl = url;  
  
                // �жϸ�Ʒ�ʽڵ��Ƿ����  
                Element durlElement = durlList.get(0).element("encode");  
                if (null != durlElement) {  
                    // ��Ʒ�ʵ�encode��decode  
                    String durlEncode = durlList.get(0).element("encode").getText();  
                    String durlDecode = durlList.get(0).element("decode").getText();  
                    // ��Ʒ�����ֵ�URL  
                    durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode;  
                    if (-1 != durlDecode.lastIndexOf("&"))  
                        durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode.substring(0, durlDecode.lastIndexOf("&"));  
                }  
                music = new Music();  
                // ������ͨƷ����������  
                music.setMusicUrl(url);  
                // ���ø�Ʒ����������  
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
	        buffer.append("\ue03c").append("�����㲥����ָ��").append("\n\n");  
	        buffer.append("�ظ�������+����").append("\n");  
	        buffer.append("���磺��������").append("\n");  
	        buffer.append("���ߣ���������@����").append("\n\n");  
	        buffer.append("�ظ���?����ʾ���˵�");  
	        return buffer.toString();  
	}
//
//	public static void main(String[] args) {  
//        Music music = searchMusic("�����Լ�", "����ֶ�");  
//        System.out.println("�������ƣ�" + music.getTitle());  
//        System.out.println("����������" + music.getDescription());  
//        System.out.println("��ͨƷ�����ӣ�" + music.getMusicUrl());  
//        System.out.println("��Ʒ�����ӣ�" + music.getHQMusicUrl());  
//    }  
}

