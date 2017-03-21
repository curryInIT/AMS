<%@ page import="cn.scu.ams.domain.Suggestion" %>
<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-16
  Time: 上午11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
    Suggestion suggestion= (Suggestion)session.getAttribute("curSuggestion");
%>

<!doctype html>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<html>
<head>
    <title>回复问题</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/styles/backstage.css">
</head>


<body>
<form action="suggestion_reply?suggestionId=<%=suggestion.getSuggestionId()%>&employeeId=<s:property value="#session.employee.employeeId" />" method="post">
<center>
    <h2>回复问题</h2><br><br>
    <div id="reference" style="color:#666666;">
        <table width="70%">
            <tr>
                <td style="width:20%;">问题类型：<%=suggestion.getContentType()%></td>
                <td style="width:20%">投稿时间：<%=suggestion.getPubTime()%></td>
                <td style="width:20%">处理状态：尚未处理</td>
            </tr>
            <tr>
                <td>问题详情：<%=suggestion.getContent()%></td>
            </tr>
        </table><br><br>
    </div>
</center>
<center>
    <textarea class="form-control" name="feedback" style="height:150px;width:800px" placeholder="请输入回复内容"></textarea><br><br><br>
    <button class="btn btn-danger" type="submit" style="color:#cc0000">提交</button>
</center>
</form>
<script>
    var info = "${requestScope.replySuggestionInfo}";
    if(info != ""){
        alert(info);
    }
</script>
</body>
</html>
