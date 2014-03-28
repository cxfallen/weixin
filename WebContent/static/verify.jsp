<%@ page language="java" import="java.util.*,java.net.URL,com.baidu.cloudservice.conf.Config,com.baidu.bae.api.factory.BaeFactory,com.baidu.bae.api.memcache.BaeCache" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
  <HEAD>
    <TITLE>Hello World</TITLE>
    <META http-equiv="Pragma" content="no-cache">
    <META http-equiv="Cache-Control" content="no-cache,no-store">
    <META http-equiv="Content-Type" content="text/html;charset=utf-8"/>
  </HEAD>
<BODY>
  <table border="1" height="100" width="80%">
    <caption><h3>验证码功能</h3></caption>
  	<form method="GET" action="/image/verifyvcode">
      <tr>
      <td>
      input：<input type="text" name="input"/>
      </td>
      
      <td>
        <%
			
  		%>
      </td>
      </tr>
		<%
			
  		%>
      <tr align= "center">
        <td>
      <input type="submit" align="center"/>
  		</td>
      </tr>
    </form>
  </table>

</BODY>
</HTML>
