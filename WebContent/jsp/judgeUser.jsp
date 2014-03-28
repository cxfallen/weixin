<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*,org.cxf.weixin.util.SQLUtil,com.baidu.cloudservice.conf.Config,com.mysql.jdbc.Driver" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<head>
  <title>身份验证</title>
</head>
<body>
  <%
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("userName");
    String password = request.getParameter("password");
    if(SQLUtil.checkPasswordFromAI(name, password)) {
    	if(SQLUtil.verifyUser(name)) {
			session.setAttribute("Username",name);
    	%>
    		  <jsp:forward page="afterLogin.jsp">
     			<jsp:param name="userName" value="<%=name%>"/>
 			 </jsp:forward>
    	<% 
    	}else {
    	%>
    		 <jsp:forward page="waitingForVerify.jsp">
  			<jsp:param name="userName" value="<%=name%>"/>
			 </jsp:forward>

    	<% 	
    	}
  %>
  

  <%
   }
   else {
  %>
  <jsp:forward page="relogin.jsp"/>
  <%
   }
  %>
</body>
</html>