<%@ page language="java" pageEncoding="UTF-8"%>
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
    if(name.equals("张三")&& password.equals("123")) {
      
  %>
  <jsp:forward page="afterLogin.jsp">
     <jsp:param name="userName" value="<%=name%>"/>
  </jsp:forward>
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