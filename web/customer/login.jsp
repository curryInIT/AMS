<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%--
  Created by IntelliJ IDEA.
  User: hello~
  Date: 17-3-14
  Time: 下午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="${pageContext.request.contextPath}/customer/js/jquery/2.0.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/customer/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/customer/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>登录注册</title>
    <style type="text/css">
        html{height:100%}
        body{height:100%}
        #login_div{height:100%}
    </style>
</head>
<body>
<div class="jumbotron" id="login_div">
    <div class="container" align="left">
        <p class="text-info" style="font-family:宋体;font-weight:bold;font-size:40px">网上政务大厅</p>
    </div>
    <hr>
    <div class="container" align="center">

        <div style="height:400px;width:400px">

            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#login" data-toggle="tab">登录</a></li>
                <li><a href="#register" data-toggle="tab">注册</a></li>
            </ul>

            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="login">
                    <form method="post" action="customer_login" name="loginform">
                        <br/>
                        <input  name="account" class="form-control" placeholder="请输入账号" required >
                        <br/>
                        <input type="password" name="password" class="form-control" placeholder="请输入密码" required >
                        <br/>
                        <a href="${pageContext.request.contextPath}/customer/forgetPassword.jsp" style="text-align:left">忘记密码</a><br><br>
                        <button class="btn btn-info" type="submit">登录</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-info" onclick="JavaScript:window.location.href='${pageContext.request.contextPath}/customer/index.jsp'">取消</button>

                    </form>

                </div>
                <div class="tab-pane fade" id="register">
                    <s:form action="customer_register" method="post" namespace="/">
                    <form  method="post" action="" name="registerform">
                        <br/>
                        <input type="text" name="email"  class="form-control" placeholder="请输入电子邮箱地址" required >
                        <br/>
                        <input type="text" name="account" class="form-control" placeholder="账号不超过6位" required>
                        <br/>
                        <input type="password" name="password" class="form-control" placeholder="密码 6-12位,区分大小写" required>
                        <br/>
                        <input type="password" name="password2" class="form-control" placeholder="再次输入密码，进行确认" required>
                        <br/>
                        <button class="btn btn-info" type="submit">注册</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-info" onclick="JavaScript:window.location.href='${pageContext.request.contextPath}/customer/index.jsp'">取消</button>
                    </form>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var info="${requestScope.loginOrRegisterInfo}";
    if(info!=""){
          alert(info);
    }
</script>
</body>
</html>