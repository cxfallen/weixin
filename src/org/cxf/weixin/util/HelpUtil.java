package org.cxf.weixin.util;

public class HelpUtil {
	
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");  
	    buffer.append("1  天气预报").append("\ue04a").append("\n");  
	    buffer.append("2  公交查询").append("\n");  
	    buffer.append("3  周边搜索").append("\ue114").append("\n");  
	    buffer.append("4  歌曲点播").append("\n");  
	    buffer.append("5  历史今日").append("\ue315").append("\n");  
//	    buffer.append("6  美女电台").append(QQFaceUtil.emoji(0x1F6B9)).append("\n"); 
	    buffer.append("6  智能翻译").append("\ue534").append("\n"); 
	    buffer.append("7  人脸识别").append("\ue138").append("\n");  
	    buffer.append("8  聊天唠嗑").append("\n");
	    buffer.append("9  校友名录").append("\ue148").append("\n");
	    buffer.append("回复“?”显示此帮助菜单");  
	    return buffer.toString();  
	}  
}
