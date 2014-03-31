<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*,com.baidu.cloudservice.conf.Config,com.mysql.jdbc.Driver" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>校友录</title>
</head>
<body>
	<h1>信息仅供参考</h1>
	<%
	
	if(session.getAttribute("Username") == null) {%>
		请先登录！
	<% 	
	} else {
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
		
	  	Class.forName(driverName);
	 	connection = DriverManager.getConnection(connName, username, sqlpassword);
	    stmt = connection.createStatement();
	    
	    sql = "select realName,sex,birthDate,company,gradeAndClass from AlumniInfo";
	  	rs = stmt.executeQuery(sql);
	  	ResultSetMetaData rsmd = rs.getMetaData();		//表的字段属性变量
		
	  	
	  	 out.print("<table border=3 style=\"font-size:30px,overflow-x:auto\">");
	     out.print("<tr>");
	     out.print("<th width=100>"+"姓名");
	     out.print("<th width=100>"+"性别");
	     out.print("<th width=100>"+"出生年月");
	     out.print("<th width=100>"+"在校年份");
	     out.print("<th width=100>"+"现所在学校/单位/职业");
	     out.print("</tr>");
	     while(rs.next()){
	         out.print("<tr>");
	         out.print("<td>"+rs.getString("realName")+"</td>");
	         out.print("<td>"+rs.getString("sex")+"</td>");
	         out.print("<td>"+rs.getString("birthDate")+"</td>");
	         out.print("<td>"+rs.getString("gradeAndClass")+"</td>");
	         out.print("<td>"+rs.getString("company")+"</td>");
	         out.print("</tr>");
	     }
	     out.print("</table>");
    %>

     <% 
	connection.close();
	stmt.close();
	rs.close();
	}
	%>
</body>
</html>