<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*,com.baidu.cloudservice.conf.Config,com.mysql.jdbc.Driver" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
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
	    
	    sql = "select realName,sex,birthDate,company from AlumniInfo";
	  	rs = stmt.executeQuery(sql);
	  	ResultSetMetaData rsmd = rs.getMetaData();		//表的字段属性变量
	
	
    %>
     <ul style="width: 75%">
            <li style="font-weight: bold;">
                <span style="width: 10%;">姓名</span>
                <span style="width: 40%;">性别</span>
                <span style="width: 10%;">出生日期</span>
                <span style="width: 10%;">所在学校/单位</span>
            </li>
            <%
            	for (int i = 1;i <= rsmd.getColumnCount();i ++){
            		 /** 打印结果 **/
                    while (rs.next()) {
                    %>
                    <li>
                    <span style="width: 10%;"><%=rs.getString("realName")%></span>
                    <span style="width: 40%;"><%=rs.getString("sex")%></span>
                    <span style="width: 10%;"><%=rs.getString("birthDate")%></span>
                    <span style="width: 10%;"><%=rs.getString("company") %></span>
                    </li>
                <%
            	}
               
            
                }
            %>
        </ul>
     <% 
	connection.close();
	stmt.close();
	rs.close();
	}
	%>
</body>
</html>