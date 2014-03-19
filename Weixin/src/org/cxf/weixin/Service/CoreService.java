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
            // Ĭ�Ϸ��ص��ı���Ϣ����  
            String respContent = "�������쳣�����Ժ��ԣ�";  
  
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
                if (contentString.equals("?") || contentString.equals("��")) {
					respContent = HelpUtil.getMainMenu();
				}
                else {
                	respContent = QQFaceUtil.multiQQFace(contentString);
//                    respContent = "�����͵���text��Ϣ��";  
//                	  respContent = "<a href=\"www.baidu.com\">�����͵���text��Ϣ������鿴���ࡣ</a>";
				}
//            	respContent = requestMap.get("Content")  +
//            			requestMap.get("Content").length() + ";" + requestMap.get("Content").compareTo("?");
            }  
            // ͼƬ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "�����͵���ͼƬ��Ϣ��";  

            }  
            // ����λ����Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "�����͵��ǵ���λ����Ϣ��";  
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
                    respContent = "лл���Ĺ�ע��";  
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
