<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<head>
  <title>记录中</title>
</head>
<body>
  <h1>登记信息</h1>
  <%
  	request.setCharacterEncoding("UTF-8");
  	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
    String sql = null; 
    
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    String realName = request.getParameter("realName");
    String sex = request.getParameter("sex");
    String birthDate = request.getParameter("birthDate");
    String company = request.getParameter("company");
    
   %>
</body>
</html>