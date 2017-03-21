<%@ page import="cn.scu.ams.domain.Suggestion" %>
<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-15
  Time: 下午3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
    Suggestion suggestion= (Suggestion)session.getAttribute("curSuggestion");
%>

<!doctype html>
<html>
<head>
    <title>问题详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/styles/backstage.css">
</head>


<body>

<center>
    <h2>问题详情</h2><br><br>
    <div id="reference" style="color:#666666">
        <table width="70%">

            <tr>
                <td style="width:20%;">问题编号：<%=suggestion.getSuggestionId()%>号</td>
                <td style="width:20%;">问题类型：<%=suggestion.getContentType()%></td>
                <td style="width:20%">投稿时间：<%=suggestion.getPubTime()%></td>
            </tr>
            <%
                if(suggestion.getDealStatus() == 1){
            %>
            <tr>
                <td style="width:20%;">处理状态：已处理</td>
                <td style="width:20%;">处理人员：<%=suggestion.getEmployeeId()%>号</td>
                <td style="width:20%;">处理时间：<%=suggestion.getReTime()%></td>
            </tr>
            <%
                }else{
            %>
            <tr>
                <td style="width:20%;">处理状态：尚未处理</td>
                <td style="width:20%;">处理人员：无</td>
                <td style="width:20%;">处理时间：无</td>
            </tr>
            <%
                }
            %>
        </table><br><br>
    </div>
</center>

<div id="detail" style="margin-left:100px;">
    <table>
        <tr>
            <td>问题详情：<%=suggestion.getContent()%></td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr><td>&nbsp;</td></tr>
        <%
            if(suggestion.getDealStatus() == 1){
        %>
        <tr>
            <td>问题回复：<%=suggestion.getFeedback()%></td>
        </tr>
        <%
        }
        %>
    </table>
</div>

</body>
</html>
