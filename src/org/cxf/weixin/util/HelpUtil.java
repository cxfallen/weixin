package org.cxf.weixin.util;

public class HelpUtil {
	
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("���ã�����С��[Grin]����ظ�����ѡ�����").append("\n\n");  
	    buffer.append("1  ����Ԥ��").append("\ue04a").append("\n");    
	    buffer.append("2  �ܱ�����").append("\ue114").append("\n");  
	    buffer.append("3  �����㲥").append("\ue03c").append("\n");  
	    buffer.append("4  ��ʷ����").append("\ue315").append("\n");  
	    buffer.append("5  ���ܷ���").append("\ue534").append("\n");  
	    buffer.append("6  �������").append("\n");
	    buffer.append("7  У����¼").append("\ue148").append("\n");
	    buffer.append("�ظ���?����ʾ�˰����˵�");  
	    return buffer.toString();  
	}  
	
	public static String getWhetherUsage() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("������ѯ����ָ��").append("\n\n");  
        buffer.append("�ظ�������+������").append("\n");  
        buffer.append("���磺��������").append("\n");  
        buffer.append("���ߣ�����@����").append("\n\n");  
        buffer.append("�ظ���?����ʾ���˵�");  
        return buffer.toString();  
	}  
	
	public static String getAlumniUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("У����¼����ָ��").append("\n\n");
		buffer.append("<a href = \"http://cxffirsttest.duapp.com/jsp/login.jsp\">����˴���֤���</a>").append("\n");
		buffer.append("<a href = \"http://www.baidu.com\">�ٶ�</a>");
		
		return buffer.toString() + "\n\n" + "�ظ���?����ʾ���˵�";
	}
}
