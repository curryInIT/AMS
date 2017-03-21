<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-14
  Time: 下午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
    Object obj1 = session.getAttribute("passComInfo");
    if(obj1 != null){
        session.removeAttribute("passComInfo");
        out.write("<script type='text/javascript'>alert('业务处理已完成,该公司申请已通过');window.location.href = '/business_findCompanyBusiness';</script>");
    }

    Object obj2 = session.getAttribute("unpassComInfo");
    if(obj2 != null){
        session.removeAttribute("unpassComInfo");
        out.write("<script type='text/javascript'>alert('业务处理已完成,该公司申请未通过');window.location.href = '/business_findCompanyBusiness';</script>");
    }

    Object obj3 = session.getAttribute("passOffInfo");
    if(obj3 != null){
        session.removeAttribute("passOffInfo");
        out.write("<script type='text/javascript'>alert('业务处理已完成，该公司注销已通过');window.location.href = '/business_findCompanyBusiness';</script>");
    }

    Object obj4 = session.getAttribute("unpassOffInfo");
    if(obj4 != null){
        session.removeAttribute("unpassOffInfo");
        out.write("<script type='text/javascript'>alert('业务处理已完成，该公司注销未通过');window.location.href = '/business_findCompanyBusiness';</script>");
    }
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>公司业务列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/styles/backstage.css">
</head>

<body>
<div class="details">
    <div class="details_operation clearfix">

    </div>
    <!--表格-->
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="15%">编号</th>
            <th width="20%">业务类型</th>
            <th width="20%">申请时间</th>
            <th width="15%">处理状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="list" var="fcb">
        <tr>
            <!--这里的id和for里面的c1 需要循环出来-->
            <td><s:property value="#fcb.businessId"/></td>
            <s:if test="#fcb.businessType == 2">
                <td>公司申请业务</td>
            </s:if>
            <s:else>
                <td>公司注销业务</td>
            </s:else>
            <td><s:property value="#fcb.applyTime"/></td>
            <td>待处理</td>
            <td align="center"><input type="button" value="处理业务" class="btn" onClick="javascript:window.location.href='./business_detail?businessId=<s:property value="#fcb.businessId"/>'"></td>
        </tr>
        </s:iterator>
        </tbody>
    </table>

</div>
</body>
</html>