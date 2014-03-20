package org.cxf.weixin.message.resp;

public class ImageMessage extends BaseMessage{
	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	private Image Image;
}
