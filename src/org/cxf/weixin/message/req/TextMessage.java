package org.cxf.weixin.message.req;

/** 
 * 文本消息 
 *  
 * @author cxf 
 * @date 2014-03-18
 */  
public class TextMessage extends BaseMessage {  
    // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
} 
