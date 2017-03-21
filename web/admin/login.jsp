<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/admin/styles/reset.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/admin/styles/main.css">
    <!--[if IE 6]>
    <script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a-min.js"></script>
    <script type="text/javascript" src="../js/ie6Fixpng.js"></script>
    <![endif]-->
</head>

<body>
<div class="headerBar">
    <div class="logoBar login_logo">
        <h3 class="welcome_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎登录&nbsp;&nbsp;&nbsp;&nbsp;行政管理系统</h3>
    </div>
</div>
<h3 align="center" class="error"><s:actionerror/></h3>
<div class="loginBox">
    <div class="login_cont">
        <s:form action="admin_login" method="post" namespace="/">
        <form action="" method="post">
            <ul class="login">
                <br>
                <li class="l_tit">帐号</li>
                <li class="mb_10"><input type="text"  name="account" placeholder="请输入您的帐号" class="login_input user_icon"></li>
                <br>
                <li class="l_tit">密码</li>
                <li class="mb_10"><input type="password"  name="password" placeholder="请输入您的密码" class="login_input password_icon"></li>
                <li class="autoLogin"><input type="checkbox" id="a1" class="checked" name="autoFlag" value="1"><label for="a1">自动登陆(一周内自动登陆)</label></li>
                <li><input type="submit" value="" class="login_btn"></li>
            </ul>
        </form>
        </s:form>
    </div>
</div>
<div class="hr_25"></div>
</body>
</html>
