<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-14
  Time: 下午10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>公告发布</title>
</head>
<body>
<h3>添加公告</h3>
<s:form action="announce_save" method="post" namespace="/">
<form action="" method="post">
    <table width="70%" border="1" cellpadding="5" cellspacing="0" bgcolor="#CCCCCC">
        <tr>
            <td align="right">公告标题</td>
            <td><input type="text" name="contentTitle"/></td>
        </tr>
        <tr>
            <td align="right">公告内容</td>
            <td><textarea col="20" rows="10" name="content" placeholder="请输入公告内容"></textarea></td>
        </tr>
        <tr>
            <td colspan="2"><center><input type="submit" name="submit" value="提交"></center></td>
        </tr>
    </table>
</form>
</s:form>
<script>
    var info = "${requestScope.announceInfo}";
    if(info != ""){
        alert(info);
    }
</script>
</body>
</html>