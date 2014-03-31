<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<head>
  <title>登记页面</title>
</head>
<body>
  <form name="registerForm" method="post" action="submitToDB.jsp">
    <table>
      <tr>
        <td>用户名: <input type="text" name="userName" id="userName"> *</td>
      </tr>
      <tr>
        <td>密码: <input type="password" name="password" id="password"> *</td>
      </tr>
      <tr>
        <td>真实姓名: <input type="text" name="realName" id="realName"> *</td>
      </tr>
      <tr>
        <td>性别: <select name="sex" id="sex">
        			<option value="male">男</option>
        			<option value="female">女</option>
        		</select> *
        </td>
      </tr>
      <tr>
        <td>出生年月: (例：199101)<input type="text" name="birthDate" id="birthDate"> *</td>
      </tr>
      <tr>
        <td>在校年份: (例：2005至2008)<input type="text" name="in" id="in"> - <input type="text" name="out" id="out"> *</td>
      </tr>
      <tr>
        <td>现学校/工作单位/职业: <input type="text" name="company" id="company"> *</td>
      </tr>
      <tr>
        <td><input type="submit" value="提交" style="background-color:pink">  <input type="reset" value="重置" style="background-color:red">  * 为必填项</td>
      </tr>
    </table>
  </form>
</body>
</html>




