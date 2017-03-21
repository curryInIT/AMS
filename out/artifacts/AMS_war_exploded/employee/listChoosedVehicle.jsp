<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-18
  Time: 上午9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>机动车指标申请成功列表</title>
    <link rel="stylesheet" href="styles/backstage.css">
</head>

<body>
<div class="details">
    <div class="details_operation clearfix">

    </div>
    <!--表格-->
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="10%">编号</th>
            <th width="15%">业务类型</th>
            <th width="20%">申请时间</th>
            <th width="20%">通过时间</th>
            <th width="20%">处理状态</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="choosedVehicleList" var="cvl">
        <tr>
            <!--这里的id和for里面的c1 需要循环出来-->
            <td><s:property value="#cvl.businessId"/></td>
            <td>机动车指标申请</td>
            <td><s:property value="#cvl.applyTime"/></td>
            <td><s:property value="#cvl.passTime"/></td>
            <td>机动车指标申请成功</td>
        </tr>
        </s:iterator>
        </tbody>
    </table>
    <br>
    <input type="button" value="发送电子邮件" class="btn" onClick="window.location.href = '/business_sendMail'">
</div>
</body>
</html>