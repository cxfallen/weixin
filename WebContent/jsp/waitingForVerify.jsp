<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<head>
<title>等待审核</title>
</head>
<body>
	<%
	String name = request.getParameter("userName");
	out.println("欢迎你，" + name);
	%>
	<h3>感谢您的注册，我们会尽快审核您的身份。</h3>
	<h3>审核通过后，可查看校友录。</h3>
</body>
