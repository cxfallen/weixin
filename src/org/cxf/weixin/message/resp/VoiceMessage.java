package org.cxf.weixin.message.resp;

public class VoiceMessage extends BaseMessage{
	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

	private Voice Voice;
}
