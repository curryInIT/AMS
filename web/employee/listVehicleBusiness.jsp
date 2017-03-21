<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-14
  Time: 下午10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
    Object object = request.getAttribute("vehicleBusinessInfo");
    String error = "  ";
    if(object != null){
        error = "没有要处理的业务";
        request.removeAttribute("vehicleBusinessInfo");
    }
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>机动车业务列表</title>
    <link rel="stylesheet" href="styles/backstage.css">
</head>

<body>
<div class="details">
    <div class="details_operation clearfix">
     <h4><%=error %></h4>
    </div>
    <%
        if(!error.equals("没有要处理的业务")){
    %>
    <!--表格-->
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="20%">编号</th>
            <th width="25%">业务类型</th>
            <th width="30%">申请时间</th>
            <th width="25%">处理状态</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="vehicleList" var="vl">
            <tr>
                <!--这里的id和for里面的c1 需要循环出来-->
                <td><s:property value="#vl.businessId"/></td>
                <td>机动车指标申请</td>
                <td><s:property value="#vl.applyTime"/></td>
                <td>正在等待摇号</td>
            </tr>
        </s:iterator>
        </tbody>
        <center><input type="button" value="开始摇号" class="btn" onClick="window.location.href = 'business_rand'"></center><br>

    </table>
    <%
        }
    %>
</div>
</body>
</html>