<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<head>
  <title>登陆页面</title>
</head>
<body>
  <form name="loginForm" method="post" action="judgeUser.jsp">
    <table>
      <tr>
        <td>用户名: <input type="text" name="userName" id="userName"></td>
      </tr>
      <tr>
        <td>密码: <input type="password" name="password" id="password"></td>
      </tr>
      <tr>
        <td><input type="submit" value="登陆" style="background-color:pink">  <input type="reset" value="重置" style="background-color:red"></td>
        <td><input type="button" value="登记个人信息" style= "background-clor:pink" onclick="location.href='http://cxffirsttest.duapp.com/jsp/register.jsp'">    
      </tr>
    </table>
  </form>
</body>
</html>




