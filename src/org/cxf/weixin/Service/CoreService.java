package org.cxf.weixin.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cxf.weixin.message.resp.Article;
import org.cxf.weixin.message.resp.Music;
import org.cxf.weixin.message.resp.MusicMessage;
import org.cxf.weixin.message.resp.NewsMessage;
import org.cxf.weixin.message.resp.TextMessage;
import org.cxf.weixin.util.HelpUtil;
import org.cxf.weixin.util.HistoryTodayUtil;
import org.cxf.weixin.util.MessageUtil;
import org.cxf.weixin.util.QQFaceUtil;

public class CoreService {
	public static String message = "";
//	public static String statickeyword = "";
//	public static String author = "";
//	public static String title = "";
//	public static String musicInCServiceString = "";
	public static String result = "";
	public static String locationX;
	public static String locationY;
	public static String scale;
	public static String label;
	
	public static String processRequest(HttpServletRequest request) {

		String respMessage = null;
        try {  
            // Ĭ�Ϸ��ص��ı���Ϣ����  
            String respContent = "�������쳣�����Ժ��ԣ�Try again later!";  
  
            // xml�������  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // ���ͷ��ʺţ�open_id��  
            String fromUserName = requestMap.get("FromUserName");  
            // �����ʺ�  
            String toUserName = requestMap.get("ToUserName");  
            // ��Ϣ����  
            String msgType = requestMap.get("MsgType");  
  
            // �ظ��ı���Ϣ  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
//            textMessage.setFuncFlag(0);  
  
            // �ı���Ϣ  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	String contentString = requestMap.get("Content");
            	
            	NewsMessage newsMessage = new NewsMessage();
				newsMessage.setFromUserName(toUserName);
				newsMessage.setToUserName(fromUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				
				List<Article> articleList = new ArrayList<Article>();
				
                if (contentString.equals("��") || contentString.equals("?")) {
					respContent = HelpUtil.getMainMenu();
				}
                else if (contentString.equals("1")) {
					respContent = HelpUtil.getWhetherUsage();
				}
                else if (contentString.equals("����") || contentString.equals("��")) {
										
					Article article = new Article();
					article.setTitle("��ӭ�ص��ϲ������ѧУ");
					article.setDescription("����������㣬�����Ѷ���δ��ĸУ����ô���ڣ��������ٴ����Բ��������!");
					article.setPicUrl("http://cxffirsttest.duapp.com/images/latest.png");
					article.setUrl("http://baike.baidu.com/link?url=Uz_yLIhf53ZTYeynFMGAhK7FcA4ERYf0pJCiCtSIeFTk6P2h7cKUl4M3Z4WSC2ka");					
					articleList.add(article);
					
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
					
					return respMessage;
					
				}
                else if (contentString.equals("2")) {
					respContent = HelpUtil.getLocationHelp();
				}
                else if (contentString.equals("3")) {
					respContent = BaiduMusicService.getMusicUsage();
				}
                else if (contentString.equals("4")) {
					respContent = HistoryTodayUtil.getTodayInHistoryInfo(); 
				}
                else if (contentString.equals("5")) {
                	respContent = BaiduTranslateService.getTranslateUsage();
				}
                else if (contentString.equals("6")) {
					respContent = HelpUtil.getAlumniUsage();
				}
                else if (contentString.startsWith("����")) {
                	String keyWord = contentString.replaceAll("^����", "").trim();
                    respContent = "�����" + BaiduTranslateService.translate(keyWord);                 
				} 
                else if (contentString.startsWith("����")||contentString.endsWith("����")) {
					String city = contentString.replaceAll("^����", "").trim();
					respContent = BaiduWeatherService.showWeather(city);
				}
                else if (contentString.startsWith("����")) {
					String keyword = contentString.replaceAll("^����", "").trim();
					respContent = BaiduSearchService.search(keyword);
				}
                else if (contentString.startsWith("����")) {
                	String keyWord = contentString.replaceAll("^����[\\+ ~!@#%^-_=]?", "");
                	if ("".equals(keyWord)) {  
                        respContent = BaiduMusicService.getMusicUsage();  
                    } else {  
//                    	statickeyword = keyWord;
                        String[] kwArr = keyWord.split("@");  
                        // ��������  
                        String musicTitle = kwArr[0];
//                        title = musicTitle;
                        // �ݳ���Ĭ��Ϊ��  
                        String musicAuthor = "";  
                        if (2 == kwArr.length)  
                            musicAuthor = kwArr[1]; 
//                        author = musicAuthor;
  
                        // ��������  
                        Music music = BaiduMusicService.searchMusic(musicTitle, musicAuthor);  
                        // δ����������  
                        if (null == music) {  
                            respContent = "�Բ���û���ҵ��������ĸ���<" + musicTitle + ">��";  
                        } else {  
                            // ������Ϣ  
//                        	musicInCServiceString = "description:" + music.getDescription() + "title:" +
//                        	        music.getTitle() + "url:" + music.getMusicUrl() + "hqurl:" + music.getHQMusicUrl();
                            MusicMessage musicMessage = new MusicMessage();  
                            musicMessage.setToUserName(fromUserName);  
                            musicMessage.setFromUserName(toUserName);  
                            musicMessage.setCreateTime(new Date().getTime());  
                            musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);  
                            musicMessage.setMusic(music);
                            
                            message = musicMessage.getFromUserName() + " \n" + musicMessage.getToUserName()
                            		+ " \n" + musicMessage.getMsgType() + " \n" + musicMessage.getCreateTime()
                            		+ " \n" + musicMessage
                            		.getMusic().getDescription() + " \n" + musicMessage.getMusic().getTitle() + " \n" + 
                            		musicMessage.getMusic().getMusicUrl() + " \n" + musicMessage.getMusic().getHQMusicUrl();
                            respMessage = MessageUtil.musicMessageToXml(musicMessage); 
                            result = respMessage;
                            return respMessage;
                        }  
                    }  
                	if (null == respMessage) {  
                        if (null == respContent)  
                            respContent = BaiduMusicService.getMusicUsage();  
                        textMessage.setContent(respContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);  
                    }  
				}
                else {
                	respContent = QQFaceUtil.multiQQFace(contentString);
				}
            }  
            // ͼƬ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "�����͵���ͼƬ��Ϣ��";  
            }  
            // ����λ����Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
            	locationX = requestMap.get("Location_X");
            	locationY = requestMap.get("Location_Y");
            	scale = requestMap.get("Scale");
            	label = requestMap.get("Label");
            	
                respContent = "�ѽ��ܵ����˿̵ĵ���λ����Ϣ��" + "\n" + "\ue114 �ܱ�����ʹ��˵��" + "\n\n" 
                + "��ʽ������+�ؼ���" + "\n" + "���磺����ATM,����KTV,����������վ" + "\n" + "�ظ���?����ʾ���˵�";  
            }  
            // ������Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "�����͵���������Ϣ��";  
            }  
            // ��Ƶ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "�����͵�����Ƶ��Ϣ��";  
            }  
            // �¼�����  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // �¼�����  
                String eventType = requestMap.get("Event");  
                // ����  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "��л���Ĺ�ע�����ܿ����У��������κν��������ӭ���ԡ�(�����ڲ���...[Grin])";
                    respContent = respContent + "\n" + "�ظ���?����ʾ���˵�";
                }  
                // ȡ������  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    respContent = "�����֣�";
                    // ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ  
                }  
                // �Զ���˵�����¼�  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ  
                }  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                	
					respContent = "�����͵�������ǰ����λ�á�";
				}
                else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
					respContent = "";
				}
            }  
  
            textMessage.setContent(respContent);  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }        
        return respMessage; 
	}

}
