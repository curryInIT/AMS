<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>员工列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/styles/backstage.css">
    <script src="${pageContext.request.contextPath}/admin/scripts/jquery/2.0.0/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/admin/styles/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/admin/styles/bootstrap/3.3.6/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/admin/scripts/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>

<body>
<div class="details">
    <div class="details_operation clearfix">
        <div class="bui_select">
            <s:form action="employee_query" method="post">
            <input type="button" value="添&nbsp;&nbsp;加" class="add"  onclick="javascript:window.location.href = '/admin/addEmployee.jsp'">
            <span>
                <input type="text" class="form-control" placeholder="输入员工名称查询……" name="name">
                <button class="btn btn-default" type="submit">查询</button>
            </span>
            </s:form>
        </div>

    </div>
    <!--表格-->
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="15%">编号</th>
            <th width="25%">员工名称</th>
            <th width="30%">员工邮箱</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="list" var="e">
            <tr>
                <!--这里的id和for里面的c1 需要循环出来-->
                <td><s:property value="#e.employeeId"/></td>
                <td><s:property value="#e.name" /></td>
                <td><s:property value="#e.email"/> </td>
                <td align="center">
				<input type="button" value="详情" class="btn" onclick="javascript:window.location.href = 'employee_details.action?employeeId=<s:property value="#e.employeeId"/>'">
				<input type="button" value="修改" class="btn" onclick="javascript:window.location.href = 'employee_editPage.action?employeeId=<s:property value="#e.employeeId"/>'">
				<input type="button" value="删除" class="btn"  onclick="javascript:window.location.href = 'employee_delete.action?employeeId=<s:property value="#e.employeeId"/>'">
				</td>
            </tr>
         </s:iterator>
        </tbody>
    </table>

    <table table border="0" cellspacing="0" cellpadding="0"  width="900px">
        <tr>
            <td align="right">
                <span>第<s:property value="currPage"/>/<s:property value="totalPage"/>页</span>&nbsp;&nbsp;
                <span>总记录数：<s:property value="totalCount"/>&nbsp;&nbsp;每页显示：<s:property value="pageSize"/></span>
   <span>
   		<s:if test="currPage != 1">
            <a href="${pageContext.request.contextPath}/employee_findAll.action?currentPage=1">[首页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/employee_findAll.action?currentPage=<s:property value="currPage-1"/>">[上一页]</a>&nbsp;&nbsp;
        </s:if>
       <s:if test="currPage != totalPage">
           <a href="${pageContext.request.contextPath}/employee_findAll.action?currentPage=<s:property value="currPage+1"/>">[下一页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath}/employee_findAll.action?currentPage=<s:property value="totalPage" />">[尾页]</a>&nbsp;&nbsp;
       </s:if>
   </span>
            </td>
        </tr>
    </table>
</div>
</body>
</html>