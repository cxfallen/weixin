package org.cxf.weixin.util;

public class HelpUtil {
	
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("���ã�����Сq����ظ�����ѡ�����").append("\n\n");  
	    buffer.append("1  ����Ԥ��").append("\ue04a").append("\n");  
	    buffer.append("2  ������ѯ").append("\n");  
	    buffer.append("3  �ܱ�����").append("\ue114").append("\n");  
	    buffer.append("4  �����㲥").append("\n");  
	    buffer.append("5  ��ʷ����").append("\ue315").append("\n");  
//	    buffer.append("6  ��Ů��̨").append(QQFaceUtil.emoji(0x1F6B9)).append("\n"); 
	    buffer.append("6  ���ܷ���").append("\ue534").append("\n"); 
	    buffer.append("7  ����ʶ��").append("\ue138").append("\n");  
	    buffer.append("8  �������").append("\n");
	    buffer.append("9  У����¼").append("\ue148").append("\n");
	    buffer.append("�ظ���?����ʾ�˰����˵�");  
	    return buffer.toString();  
	}  
}
