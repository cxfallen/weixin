package org.cxf.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cxf.weixin.Service.CoreService;
import org.cxf.weixin.util.SignUtil;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) ȷ����������΢�ŷ����� signature,timestamp,nonce,echostr 1.
	 *      ��token��timestamp��nonce�������������ֵ������� 2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1���� 3.
	 *      �����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();

		// ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) ����΢�ŷ�������������ϢdoPost����������������request�з�װ��������ص��������ݣ����Դ�request��ȡ��΢�ŷ�������������Ϣ��
	 *      ��ͨ��response���ǿ��ԶԽ��յ�����Ϣ������Ӧ����������Ϣ�� 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// ���ú���ҵ���������Ϣ��������Ϣ 
		String respMessage = CoreService.processRequest(request);
		
		// ��Ӧ��Ϣ
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}

}
