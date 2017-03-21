<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<script src="${pageContext.request.contextPath}/admin/scripts/jquery/2.0.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/admin/styles/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/admin/scripts/bootstrap/3.3.6/bootstrap.min.js"></script>

<html>
<head>
    <meta charset="utf-8">
    <title>查询业务</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/styles/backstage.css">
</head>

<body>
<div class="details">
    <div class="details_operation clearfix">
        <s:form action="business_query" method="post">
	  <div class="input-group">
        <input type="text" class="form-control" placeholder="请输入办理人姓名" name="employeeName">
        <span class="input-group-btn">
          <button class="btn btn-default" type="submit">查询</button>
        </span>
      </div>
        </s:form>
    </div>
    <!--表格-->
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="15%">业务编号</th>
            <th width="20%">业务种类</th>
			<th width="15%">申请人</th>
			<th width="20%">申请时间</th>
			<th width="15%">业务状态</th>
			<th width="15%">办理人</th>
        </tr>
        </thead>
        <tbody>

        <s:iterator value="businessInfos" var="b">
            <tr>
                <!--这里的id和for里面的c1 需要循环出来-->
                <td><s:property value="#b.businessId"/></td>
                <td>
                    <s:property value="#b.businessType"/>
                </td>
                <td><s:property value="#b.customerName"/></td>
                <td><s:property value="#b.applyTime"/></td>
                <td><s:property value="#b.status"/></td>
                <td><s:property value="#b.employeeName"/></td>
            </tr>
        </s:iterator>
        
        </tbody>
    </table>
	
</div>
<h4><s:property value="#request.error"/></h4>
</body>
</html>