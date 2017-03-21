<%--
  Created by IntelliJ IDEA.
  User: hello~
  Date: 17-3-15
  Time: 下午7:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="cn.scu.ams.domain.Customer"%>
<%@ page import="cn.scu.ams.domain.IdCard" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String error;
    Customer customer =(Customer) ActionContext.getContext().getSession().get("customer");
    if(customer == null){
        error="非法访问！";
        request.setAttribute("errorInfo", error);
        request.getRequestDispatcher("${pageContext.request.contextPath}/customer/index.jsp").forward(request,response);
    }else{
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/customer/css/calendar.css">
    <title>身份证申请</title>
    <script src="${pageContext.request.contextPath}/customer/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/customer/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/customer/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- bootstrap -->
    <link href="${pageContext.request.contextPath}/customer/css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/customer/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/customer/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/customer/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
    <!-- libraries -->
    <link href="${pageContext.request.contextPath}/customer/css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/customer/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/css/layout.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- scripts -->
    <script src="${pageContext.request.contextPath}/customer/js/jquery-latest.js"></script>
    <script src="${pageContext.request.contextPath}/customer/js/theme.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/css/cardApply.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/css/all.css" />

</head>
<body>
<!--qq聊天代码部分begin-->
<div class="izl-rmenu">
    <a class="consult" target="_blank"><div class="phone" style="display:none;">139-80777044</div></a>
    <a class="cart"><div class="pic"></div></a>
    <a href="javascript:void(0)" class="btn_top" style="display: block;"></a>
</div>
<a target="_blank"  href="http://wpa.qq.com/msgrd?v=3&uin=2873553410&site=qq&menu=yes" id="udesk-feedback-tab" class="udesk-feedback-tab-left" style="display: block; background-color: black;"></a>
<!--qq聊天代码部分end-->
<!-- 顶部 -->
<div id="top">
    <div id="title">
        <img src="${pageContext.request.contextPath}/customer/images/logo.png">
    </div>
    <div id="login_info">
        <span class="glyphicon glyphicon-user" style="font-size:22px;color:#FFD2D2;"></span>
        <a href="${pageContext.request.contextPath}/customer/modifyInfo.jsp" style="font-family:宋体;font-weight:bold;font-size:15px" title="点击修改个人信息">
            <%=customer.getAccount()%>
        </a>
        <a href="#" onclick="javascript:if(confirm('确认注销吗？')) window.location.href='/customer_exit';" style="font-family:宋体;font-weight:bold;font-size:15px" title="点击退出登录">注销</a>
    </div>
</div>
<!-- 顶部结束 -->
<!-- 左边 -->
<div id="sidebar-nav">
    <ul id="dashboard-menu">
        <li>
            <a href="${pageContext.request.contextPath}/customer/index.jsp">
                <i class="icon-home"></i>
                <span>主页</span>
            </a>
        </li>

        <li  class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
            <a class="dropdown-toggle" href="#">
                <i class="icon-group"></i>
                <span>身份证办理</span>
                <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu">
                <li><a href="#" onclick="window.location.href='/idCard_beforeApply'">身份证申请</a></li>
                <li><a href="#" onclick="window.location.href='/idCard_beforeAbolish'">身份证挂失</a></li>
            </ul>
        </li>

        <li>
            <a class="dropdown-toggle" href="#">
                <i class="icon-edit"></i>
                <span>公司业务</span>
                <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu">
                <li><a href="#" onclick="window.location.href='/company_beforeApply'">公司注册</a></li>

                <li><a href="#" onclick="window.location.href='/company_beforeDelete'">公司注销</a></li>
            </ul>
        </li>
        <li>
            <a href="#" onclick="window.location.href='/vehicle_beforeApply'">
                <i class="icon-picture"></i>
                <span>机动车指标</span>
            </a>
        </li>
        <li>
            <a href="#" onclick="window.location.href='/business_allBusiness'">
                <i class="icon-signal"></i>
                <span>查询业务进度</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/suggestion_findAll">
                <i class="icon-calendar-empty"></i>
                <span>留言板</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/announce_findAll">
                <i class="glyphicon glyphicon-bullhorn"></i>
                <span>公告栏</span>
            </a>
        </li>
    </ul>
</div>
<!--左边结束 -->
<!-- 中间 -->
<%
    IdCard idCard = (IdCard)request.getAttribute("idCard2");
    if(idCard == null){
%>
<div class="content">
    <div id="yourposition" >
        <p class="text-info" style="font-size:16px; font-family:宋体">你现在的位置：身份证办理 > 身份证申请</p>
    </div>

    <div id="main">
        <div id="left">
            <center>
                <div style="width:400px;">
                    <s:form action="idCard_apply" method="post" namespace="/" enctype="multipart/form-data">
                        <form>
                            <br><br><br>
                            <input name="applyName" class="form-control" placeholder="请输入姓名" required>
                            <br>
                            <select name="sex" class="form-control">
                                <option value="0">男</option>
                                <option value="1">女</option>
                            </select>
                            <br>
                            <input  name="idNumber" class="form-control" placeholder="请输入身份证号" required>
                            <br>
                            <input  name="age" class="form-control" id="dt" placeholder="选择出生日期" required>
                            <br>
                            <input  name="race" class="form-control" placeholder="请输入民族" required>
                            <br>
                            <input  name="customerPhone" class="form-control" placeholder="请输入电话号码" required>
                            <br>
                            <input  name="customerEmail" class="form-control" placeholder="请输入电子邮箱" required>
                            <br>
                            <textarea  name="customerAddress" class="form-control" placeholder="请输入地址" required></textarea>
                            <br>请选择本人正面照上传
                            <input type="file" name="upload" />
                            <br>
                            <button type="submit" class="btn btn-lg btn-info">确定</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="reset" class="btn btn-lg btn-info">取消</button>
                        </form>
                    </s:form>
                </div>
            </center>
        </div>
        <div id="right">
		  <pre>
		  申请须知
  一、申请对象及条件：本省户籍年满16周岁
     的公民。

  二、需提交材料：居民户口簿

  三、办理程序及时限：户籍所在地派出所受
      理，20个工作日（加急证10个工作日）。

  四、注意事项：

  1、采集人像信息，不着制式服装或浅色上衣,常戴眼镜的公民应配戴眼镜框，不化浓妆。

  2、采集指纹信息，请保持手指表面清洁无污物。

  3、领取证件时须由公民到户籍窗口进行证件核验，无法到场的由同户成年直系亲属持《居民身份证领取凭证》、居民户口簿、委托书、代领人居民身份证到派出所领取,公民在收到证件1个月内，携带证件到省内就近派出所户籍窗口进行。
		  </pre>
        </div>
    </div>
</div>
<!--中间结束-->


<!--日历-->
<div id="dd"></div>
<script src="${pageContext.request.contextPath}/customer/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/customer/js/calendar.js"></script>
<script>
    $('#dd').calendar({
        trigger: '#dt',
        zIndex: 999,
        format: 'yyyy-mm-dd',
        onSelected: function (view, date, data) {
            console.log('event: onSelected')
        },
        onClose: function (view, date, data) {
            console.log('event: onClose')
            console.log('view:' + view)
            console.log('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });
</script>

<%
}else{
%>

<div class="content">
    <div id="yourposition" >
        <p class="text-info" style="font-size:16px; font-family:宋体">你现在的位置：身份证办理 > 身份证申请</p>
    </div>
<br><br>
<center><div>已存在身份证，不可再申请！</div></center>
 </div>
<%
    }
%>

</body>
</html>
<%
    }
%>