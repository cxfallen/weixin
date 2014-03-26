package org.cxf.weixin.message.req;

public class ImageMessage extends BaseMessage{
	// Í¼Æ¬Á´½Ó  
    private String PicUrl;  
    
    private long MediaId;
  
    public long getMediaId() {
		return MediaId;
	}

	public void setMediaId(long mediaId) {
		MediaId = mediaId;
	}

	public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}
