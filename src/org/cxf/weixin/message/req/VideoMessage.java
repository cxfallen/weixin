package org.cxf.weixin.message.req;

public class VideoMessage extends BaseMessage{
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	private String MediaId;
	private String ThumbMediaId;
	
}
