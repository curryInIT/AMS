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
    <title>业务列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/styles/backstage.css">
</head>

<body>
<div class="details">
  <div class="details_operation clearfix"></div>
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
            <s:iterator value="list" var="b">
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
    <br>
    <table table border="0" cellspacing="0" cellpadding="0"  width="900px">
        <tr>
            <td align="right">
                <span>第<s:property value="currPage"/>/<s:property value="totalPage"/>页</span>&nbsp;&nbsp;
                <span>总记录数：<s:property value="totalCount"/>&nbsp;&nbsp;每页显示：<s:property value="pageSize"/></span>
   <span>
   		<s:if test="currPage != 1">
            <a href="${pageContext.request.contextPath}/business_findAll.action?currentPage=1">[首页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/business_findAll.action?currentPage=<s:property value="currPage-1"/>">[上一页]</a>&nbsp;&nbsp;
        </s:if>
       <s:if test="currPage != totalPage">
           <a href="${pageContext.request.contextPath}/business_findAll.action?currentPage=<s:property value="currPage+1"/>">[下一页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath}/business_findAll.action?currentPage=<s:property value="totalPage" />">[尾页]</a>&nbsp;&nbsp;
       </s:if>
   </span>
            </td>
        </tr>
    </table>
	
</div>

</body>
</html>