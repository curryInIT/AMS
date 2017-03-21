<%--
  Created by IntelliJ IDEA.
  User: 18346
  Date: 17-3-15
  Time: 上午10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>员工详情</title>
</head>
<body>
<h2>员工详情</h2>
<s:form action="" method="post" namespace="/" theme="simple">
<table width="50%" border="1" cellpadding="5" cellspacing="0" bgcolor="#CCCCCC">
    <tr>
        <td>员工名字</td>
        <td><s:property value="%{model.name}"/></td>
    </tr>
    <tr>
        <td>员工账号</td>
        <td><s:property value="%{model.account}"/></td>
    </tr>
    <tr>
        <td>员工密码</td>
        <td><s:property value="%{model.password}"/></td>
    </tr>
    <tr>
        <td>员工性别</td>
        <td>
            <s:if test="%{model.sex == 0}">
                男
            </s:if>
            <s:if test="%{model.sex == 1}">
                女
            </s:if>
        </td>
    </tr>
    <tr>
        <td>员工年龄</td>
        <td><s:property value="%{model.age}" /></td>
    </tr>
    <tr>
        <td>员工手机号</td>
        <td><s:property value="%{model.phone}" /></td>
    </tr>
    <tr>
        <td>员工邮箱</td>
        <td><s:property value="%{model.email}" /></td>
    </tr>
    <tr>
        <td>员工地址</td>
        <td colspan="2"><s:property value="%{model.address}" /></td>
    </tr>
</table>
</s:form>
</body>
</html>