package org.cxf.weixin.message.req;

/** 
 * �ı���Ϣ 
 *  
 * @author cxf 
 * @date 2014-03-18
 */  
public class TextMessage extends BaseMessage {  
    // ��Ϣ����  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
} 
