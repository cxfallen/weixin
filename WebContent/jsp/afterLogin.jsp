<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<head>
 <title>登录成功</title>
</head>
<body>
  <%
   request.setCharacterEncoding("UTF-8");
   String name = request.getParameter("userName");

   out.println("欢迎你:" + name);
   out.println("\n" + "<a href = \"showList.jsp\">点击查看校友录</a>");
   %>
  
</body>
</html> 