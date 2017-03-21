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
    <title>修改员工信息</title>
</head>
<body>
<h3>修改员工信息</h3>
<s:form action="employee_update" method="post" namespace="/" theme="simple">
    <form action="" method="post">
        <table width="70%" border="1" cellpadding="5" cellspacing="0" bgcolor="#CCCCCC">
            <s:hidden name="employeeId" value="%{model.employeeId}"/>
            <tr>
                <td align="right">员工名称</td>
                <td><s:textfield name="name" value="%{model.name}"/></td>
            </tr>
            <tr>
                <td align="right">员工账号</td>
                <td><s:textfield name="account" value="%{model.account}"/></td>
            </tr>
            <tr>
                <td align="right">员工密码</td>
                <td><s:password showPassword="true" name="password" value="%{model.password}" disabled="false"/></td>
            </tr>
            <tr>
                <td align="right">员工性别</td>
                <td>
                    <s:radio name="sex" list="#{'0':'男','1':'女'}" value="%{model.sex}" />
                </td>
            </tr>
            <tr>
                <td align="right">员工年龄</td>
                <td><s:textfield name="age" value="%{model.age}" /></td>
            </tr>
            <tr>
                <td align="right">员工手机号</td>
                <td><s:textfield name="phone" value="%{model.phone}" /></td>
            </tr>
            <tr>
                <td align="right">员工邮箱</td>
                <td><s:textfield name="email" value="%{model.email}" /></td>
            </tr>
            <tr>
                <td align="right">员工地址</td>
                <td><s:textarea name="address" label="家庭地址" cols="10" rows="5" value="%{model.address}" /></td>
            </tr>
            <tr>
                <td colspan="2"><center><input type="submit" value="保存"/>&nbsp;&nbsp;&nbsp;<input type="button" value="退出" onclick="javascript:window.location.href = '/employee_findAll'"/></center></td>
            </tr>
        </table>
    </form>
</s:form>
</body>
</html>