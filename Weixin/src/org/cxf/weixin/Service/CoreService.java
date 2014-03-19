package org.cxf.weixin.Service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cxf.weixin.message.resp.TextMessage;
import org.cxf.weixin.util.HelpUtil;
import org.cxf.weixin.util.MessageUtil;
import org.cxf.weixin.util.QQFaceUtil;

public class CoreService {

	public static String processRequest(HttpServletRequest request) {

		String respMessage = null;
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
  
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
                if (contentString.equals("?") || contentString.equals("？")) {
					respContent = HelpUtil.getMainMenu();
				}
                else {
                	respContent = QQFaceUtil.multiQQFace(contentString);
//                    respContent = "您发送的是text消息！";  
//                	  respContent = "<a href=\"www.baidu.com\">您发送的是text消息，点击查看更多。</a>";
				}
//            	respContent = requestMap.get("Content")  +
//            			requestMap.get("Content").length() + ";" + requestMap.get("Content").compareTo("?");
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";  

            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
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
                    respContent = "谢谢您的关注！";  
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
