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
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！Try again later!";  
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
//            textMessage.setFuncFlag(0);  
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	String contentString = requestMap.get("Content");
            	
            	NewsMessage newsMessage = new NewsMessage();
				newsMessage.setFromUserName(toUserName);
				newsMessage.setToUserName(fromUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				
				List<Article> articleList = new ArrayList<Article>();
				
                if (contentString.equals("？") || contentString.equals("?")) {
					respContent = HelpUtil.getMainMenu();
				}
                else if (contentString.equals("1")) {
					respContent = HelpUtil.getWhetherUsage();
				}
                else if (contentString.equals("最新") || contentString.equals("新")) {
										
					Article article = new Article();
					article.setTitle("欢迎回到南昌外国语学校");
					article.setDescription("身在异乡的你，或许已多年未见母校，那么现在，让我们再次领略昌外的魅力!");
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
                else if (contentString.startsWith("翻译")) {
                	String keyWord = contentString.replaceAll("^翻译", "").trim();
                    respContent = "结果：" + BaiduTranslateService.translate(keyWord);                 
				} 
                else if (contentString.startsWith("天气")||contentString.endsWith("天气")) {
					String city = contentString.replaceAll("^天气", "").trim();
					respContent = BaiduWeatherService.showWeather(city);
				}
                else if (contentString.startsWith("附近")) {
					String keyword = contentString.replaceAll("^附近", "").trim();
					respContent = BaiduSearchService.search(keyword);
				}
                else if (contentString.startsWith("歌曲")) {
                	String keyWord = contentString.replaceAll("^歌曲[\\+ ~!@#%^-_=]?", "");
                	if ("".equals(keyWord)) {  
                        respContent = BaiduMusicService.getMusicUsage();  
                    } else {  
//                    	statickeyword = keyWord;
                        String[] kwArr = keyWord.split("@");  
                        // 歌曲名称  
                        String musicTitle = kwArr[0];
//                        title = musicTitle;
                        // 演唱者默认为空  
                        String musicAuthor = "";  
                        if (2 == kwArr.length)  
                            musicAuthor = kwArr[1]; 
//                        author = musicAuthor;
  
                        // 搜索音乐  
                        Music music = BaiduMusicService.searchMusic(musicTitle, musicAuthor);  
                        // 未搜索到音乐  
                        if (null == music) {  
                            respContent = "对不起，没有找到你想听的歌曲<" + musicTitle + ">。";  
                        } else {  
                            // 音乐消息  
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
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
            	locationX = requestMap.get("Location_X");
            	locationY = requestMap.get("Location_Y");
            	scale = requestMap.get("Scale");
            	label = requestMap.get("Label");
            	
                respContent = "已接受到您此刻的地理位置消息！" + "\n" + "\ue114 周边搜索使用说明" + "\n\n" 
                + "格式：附近+关键词" + "\n" + "例如：附近ATM,附近KTV,附近公交车站" + "\n" + "回复“?”显示主菜单";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "感谢您的关注，功能开发中，若您有任何建议意见欢迎留言。(持续内测中...[Grin])";
                    respContent = respContent + "\n" + "回复“?”显示主菜单";
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    respContent = "你有种！";
                    // 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
                }  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                	
					respContent = "您发送的是您当前地理位置。";
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
