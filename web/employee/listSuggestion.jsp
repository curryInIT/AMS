<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-14
  Time: 下午10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>问题和意见列表</title>
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
            <th width="20%">问题类型</th>
            <th width="20%">投稿时间</th>
            <th width="15%">处理状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="list" var="s">
        <tr>
            <!--这里的id和for里面的c1 需要循环出来-->
            <td><s:property value="#s.suggestionId"/></td>
            <td><s:property value="#s.contentType"/></td>
            <td><s:property value="#s.pubTime"/></td>
            <s:if test="#s.dealStatus == 0">
                <td>尚未处理</td>
                <td align="center">
                    <input type="button" value="详情" class="btn" onClick="javascript:window.location.href='./suggestion_detail?suggestionId=<s:property value="#s.suggestionId"/>'">
                    <input type="button" value="回复" class="btn" onclick="javascript:window.location.href='./suggestion_replyDetail?suggestionId=<s:property value="#s.suggestionId"/>'">
                </td>
            </s:if>
            <s:else>
                <td>已处理</td>
                <td align="center">
                    <input type="button" value="详情" class="btn" onClick="javascript:window.location.href='./suggestion_detail?suggestionId=<s:property value="#s.suggestionId"/>'">
                </td>
            </s:else>
        </tr>
        </s:iterator>
        </tbody>
    </table>

</div>
<script>
    var info = "${requestScope.replySuggestionInfo}";
    if(info != ""){
        alert(info);
        window.location.href='./suggestion_findAll2';
    }
</script>
</body>
</html>