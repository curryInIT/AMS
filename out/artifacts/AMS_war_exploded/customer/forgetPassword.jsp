<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--
  Created by IntelliJ IDEA.
  User: jingjing
  Date: 17-3-16
  Time: 上午9:06
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="${pageContext.request.contextPath}/customer/js/jquery/2.0.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/customer/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/customer/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>忘记密码</title>
    <style type="text/css">
        html{height:100%}
        body{height:100%}
        #login_div{height:100%}
    </style>

    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        var countdown=60;
        function settime(obj) {
            if (countdown == 0) {
                obj.removeAttribute("disabled");
                obj.value="获取验证码";
                countdown = 60;
                return;
            } else {
                obj.setAttribute("disabled", true);
                obj.value="重新发送(" + countdown + ")";
                countdown--;
            }
            setTimeout(function() {
                        settime(obj) }
                    ,1000)
        }
    </script>

</head>
<body>
<div class="jumbotron" id="login_div">
    <div class="container" align="left">
        <p class="text-info" style="font-weight:bold;font-size:40px">网上政务大厅</p>
    </div>
    <hr>
    <div class="container" align="center">
        <div style="height:400px;width:400px">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#login" data-toggle="tab">找回密码</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="login">
                    <s:form method="post" action="customer_forgetPasswd" namespace="/" name="passwdForgetForm">
                        <br/>
                        <input  name="email" class="form-control" placeholder="请输入注册时的邮箱" required><br />
                        <input  name="account" class="form-control" placeholder="请输入要找回的账号" required ><br />
                        <br><br>
                        <input type="submit" id="btn" class="btn btn-info" value="获取验证码" onclick="settime(this)" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-info" onclick="JavaScript:window.location.href='${pageContext.request.contextPath}/customer/login.jsp'" >取消</button>
                    </s:form>
                </div>
            </div>
        </div>
        <div class="bottom">
            <br /><br />
            @2017-2018  <a href="${pageContext.request.contextPath}/customer/index.jsp">网上政务大厅</a>— All Rights Reserved. 蜀ICP备52000000号
        </div>
    </div>
</div>
<script>
    var info="${requestScope.accountOrEmail}";
    if(info!= ""){
        alert(info);
    }
</script>
</body>
</html>
