<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*,com.baidu.cloudservice.conf.Config,com.mysql.jdbc.Driver" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<head>
  <title>信息记录中</title>
</head>
<body>
  <h1>登记信息记录中</h1>
  <%
  	request.setCharacterEncoding("UTF-8");
  	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
    String sql = null; 
    
    String databaseName = Config.MYSQLNAME;
	String host = Config.MYSQLHOST;
	String port = Config.MYSQLPORT;
	String username = Config.USER;
	String sqlpassword = Config.PWD;
	String driverName = "com.mysql.jdbc.Driver";
	String dbUrl = "jdbc:mysql://";
	String serverName = host + ":" + port + "/";
  	String connName = dbUrl + serverName + databaseName;
  	
  	String userNameR = "";
  	String passwordR = "";
  	String realNameR = "";
  	String sexR = "";
  	String birthDateR = "";
  	String companyR = "";
  	String gAndClassR = "";
  	
    userNameR = request.getParameter("userName");
    passwordR = request.getParameter("password");
    realNameR = request.getParameter("realName");
    sexR = request.getParameter("sex");
    birthDateR = request.getParameter("birthDate");
    companyR = request.getParameter("company");
    gAndClassR = request.getParameter("in") + " - " + request.getParameter("out");
    
    
    Class.forName(driverName);
	connection = DriverManager.getConnection(connName, username, sqlpassword);
    stmt = connection.createStatement();
	try {
		sql = "insert into AlumniInfo(userName,password,realName,sex,birthDate,company,verify,gradeAndClass) values('" + userNameR + "','" + passwordR + "','"
				+ realNameR + "','" + sexR + "'," + birthDateR + ",'" + companyR + "','" + "f','" + gAndClassR + "')";
		stmt.execute(sql);
	} catch (Exception e) {%>
		  <jsp:forward page="reRegister.jsp"/>
<% 
	}
	
%>
 <jsp:forward page="waitingForVerify.jsp">
 	<jsp:param name="userName" value="<%=userNameR%>"/>
 </jsp:forward>
</body>
</html>