<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加员工</title>
</head>
<body>
<h3>添加员工</h3>
<s:form action="employee_save" method="post" namespace="/">
<form action="" method="post">
    <table width="70%" border="1" cellpadding="5" cellspacing="0" bgcolor="#CCCCCC">
        <tr>
            <td align="right">员工名称</td>
            <td><input type="text" name="name" palceholder="请输入员工名称"/></td>
        </tr>
        <tr>
            <td align="right">员工账号</td>
            <td><input type="text" name="account" palceholder="请输入员工账号"/></td>
        </tr>
        <tr>
            <td align="right">员工密码</td>
            <td><input type="password" name="password" palceholder="请输入员工密码"/></td>
        </tr>
        <tr>
            <td align="right">员工性别</td>
            <td>
			<input type="radio" name="sex" value="1"/>男
			<input type="radio" name="sex" value="0"/>女
			</td>
        </tr>
		<tr>
            <td align="right">员工年龄</td>
            <td><input type="text" name="age" palceholder="请输入员工年龄"/></td>
        </tr>
		<tr>
            <td align="right">员工手机号</td>
            <td><input type="text" name="phone" palceholder="请输入员工电话"/></td>
        </tr>
		<tr>
            <td align="right">员工邮箱</td>
            <td><input type="text" name="email" palceholder="请输入员工邮箱"/></td>
        </tr>
        <tr>
            <td align="right">员工地址</td>
            <td><textarea rows="5" cols="10" name="address" id="address" placeholder="请输入地址"></textarea> </td>
        </tr>
        <tr>
            <td colspan="2"><center><input type="submit" value="添加"/>&nbsp;&nbsp;&nbsp;<input type="button" value="退出" onclick="/employee_findAll"/></center></td>
        </tr>
    </table>
</form>
</s:form>
</body>
</html>