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
	    buffer.append("6  校友名录").append("\ue148").append("\n");
	    buffer.append("回复“?”显示此帮助菜单");  
	    return buffer.toString();  
	}  
	
	public static String getWhetherUsage() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("\ue04a").append("天气查询操作指南").append("\n\n");  
        buffer.append("回复：天气+城市名").append("\n");  
        buffer.append("例如：天气北京").append("\n");  
        buffer.append("或者：天气@北京").append("\n\n");  
        buffer.append("回复“?”显示主菜单");  
        return buffer.toString();  
	}  
	
	public static String getAlumniUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\ue148").append("校友名录操作指南").append("\n\n");
		buffer.append("<a href = \"http://cxffirsttest.duapp.com/jsp/login.jsp\">点击此处验证身份</a>").append("\n");
		
		return buffer.toString() + "\n" + "回复“?”显示主菜单";
	}

	public static String getLocationHelp() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\ue114").append("周边搜索操作指南").append("\n\n");
		buffer.append("请先打开定位服务，上传你此时的地理位置").append("\n");
		
		return buffer.toString() + "\n" + "回复“?”显示主菜单";
	}
}
