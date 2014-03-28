package org.cxf.weixin.message.resp;

public class VideoMessage extends BaseMessage{
	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

	private Video Video;
}
