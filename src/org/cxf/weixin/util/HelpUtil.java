package org.cxf.weixin.util;

public class HelpUtil {
	
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("您好，我是小外[Grin]，请回复数字选择服务：").append("\n\n");  
	    buffer.append("1  天气预报").append("\ue04a").append("\n");    
	    buffer.append("2  周边搜索").append("\ue114").append("\n");  
	    buffer.append("3  歌曲点播").append("\ue03c").append("\n");  
	    buffer.append("4  历史今日").append("\ue315").append("\n");  
	    buffer.append("5  智能翻译").append("\ue534").append("\n");  
	    buffer.append("6  聊天唠嗑").append("\n");
	    buffer.append("7  校友名录").append("\ue148").append("\n");
	    buffer.append("回复“?”显示此帮助菜单");  
	    return buffer.toString();  
	}  
	
	public static String getWhetherUsage() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("天气查询操作指南").append("\n\n");  
        buffer.append("回复：天气+城市名").append("\n");  
        buffer.append("例如：天气北京").append("\n");  
        buffer.append("或者：天气@北京").append("\n\n");  
        buffer.append("回复“?”显示主菜单");  
        return buffer.toString();  
	}  
	
	public static String getAlumniUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("校友名录操作指南").append("\n\n");
		buffer.append("<a href = \"http://cxffirsttest.duapp.com/jsp/login.jsp\">点击此处验证身份</a>").append("\n");
		buffer.append("<a href = \"http://www.baidu.com\">百度</a>");
		
		return buffer.toString() + "\n\n" + "回复“?”显示主菜单";
	}
}
