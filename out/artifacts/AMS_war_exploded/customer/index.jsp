<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%--
  Created by IntelliJ IDEA.
  User: hello~
  Date: 17-3-14
  Time: 下午9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.scu.ams.domain.Customer"%>

<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
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
    <script src="${pageContext.request.contextPath}/customer/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/customer/js/jquery-ui-1.10.2.custom.min.js"></script>
    <!-- knob -->
    <script src="${pageContext.request.contextPath}/customer/js/jquery.knob.js"></script>
    <!-- flot charts -->
    <script src="${pageContext.request.contextPath}/customer/js/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/customer/js/jquery.flot.stack.js"></script>
    <script src="${pageContext.request.contextPath}/customer/js/jquery.flot.resize.js"></script>
    <script src="${pageContext.request.contextPath}/customer/js/theme.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/css/index.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/css/all.css" />
</head>
<body>
<!--qq聊天代码部分begin-->
<div class="izl-rmenu">
    <a class="consult" target="_blank"><div class="phone" style="display:none;">139-8077-7044</div></a>
    <a class="cart"><div class="pic"></div></a>
    <a href="javascript:void(0)" class="btn_top" style="display: block;"></a>
</div>
<a target="_blank"  href="http://wpa.qq.com/msgrd?v=3&uin=2873553410&site=qq&menu=yes" id="udesk-feedback-tab" class="udesk-feedback-tab-left" style="display: block; background-color: black;"></a>
<!--qq聊天代码部分end-->
<%
    Customer customer =(Customer) ActionContext.getContext().getSession().get("customer");
    if(customer == null){
%>
<!--没有登录！！-->
<!-- 顶部 -->
<div id="top">
    <div id="title">
        <img src="${pageContext.request.contextPath}/customer/images/logo.png">
    </div>
    <div id="login_info">
        <span class="glyphicon glyphicon-user" style="font-size:22px;color:#FFD2D2;"></span>
        <a href="${pageContext.request.contextPath}/customer/login.jsp" style="font-family:宋体;font-weight:bold;font-size:20px">
            登录/注册
        </a>
    </div>

</div>
<!-- 顶部结束 -->

<!-- 左边 -->
<div id="sidebar-nav">
    <ul id="dashboard-menu">

        <li class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
            <a href="${pageContext.request.contextPath}/customer/index.jsp">
                <i class="icon-home"></i>
                <span>主页</span>
            </a>
        </li>

        <li>
            <a class="dropdown-toggle" href="#">
                <i class="icon-group"></i>
                <span>身份证办理</span>
                <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu">
                <li><a href="#"  onclick="fun1()">身份证申请</a></li>
                <li><a href="#"  onclick="fun1()">身份证挂失</a></li>
            </ul>
        </li>

        <li>
            <a class="dropdown-toggle" href="#">
                <i class="icon-edit"></i>
                <span>公司业务</span>
                <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu">
                <li><a href="#"  onclick="fun1()">公司注册</a></li>

                <li><a href="#"  onclick="fun1()">公司注销</a></li>
            </ul>
        </li>
        <li>
            <a href="#" onclick="fun1()">
                <i class="icon-picture"></i>
                <span>机动车指标</span>
            </a>
        </li>
        <li>
            <a href="#" onclick="fun1()">
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
<script>
    function fun1(){
        alert("请先登录！");
    }
</script>
<%
    }else{
%>
<!--已经登录！！-->
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

        <li class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
            <a href="${pageContext.request.contextPath}/customer/index.jsp">
                <i class="icon-home"></i>
                <span>主页</span>
            </a>
        </li>

        <li>
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
<%
    }
%>

<!-- 中间 -->
<div class="content" style="background:url(${pageContext.request.contextPath}/customer/images/bg.jpg)">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel"data-interval="2000">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active" >
                <img src="${pageContext.request.contextPath}/customer/images/home1.jpg" >
                <div class="carousel-caption" style="opacity: 0.5;">
                    发展供给侧 绿水青山就是金山银山
                </div>
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/customer/images/home2.png" >
                <div class="carousel-caption" style="opacity: 0.5;">
                    建设国家中心城市
                </div>
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/customer/images/home3.jpg" >
                <div class="carousel-caption" style="opacity: 0.5;">
                    建设天府新区 打造最具活力新兴增长极
                </div>
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/customer/images/home4.png" >
                <div class="carousel-caption" style="opacity: 0.5;">
                    实现“一带一路”战略
                </div>
            </div>
        </div>
        <!-- Controls -->

    </div>
</div>
<!--中间结束-->
<script>
    var errorInfo="${requestScope.errorInfo}";
    if(errorInfo!=""){
        alert(errorInfo);
    }
    var businessInfo="${requestScope.businessInfo}";
    if(businessInfo!=""){
        alert(businessInfo);
    }
    var applyCardInfo="${requestScope.applyCardInfo}";
    if(applyCardInfo!=""){
        alert(applyCardInfo);
    }
    var abolishCardInfo="${requestScope.abolishCardInfo}";
    if(abolishCardInfo!=""){
        alert(abolishCardInfo);
    }
    var applyCompanyInfo="${requestScope.applyCompanyInfo}";
    if(applyCompanyInfo!=""){
        alert(applyCompanyInfo);
    }
    var deleteCompanyInfo="${requestScope.deleteCompanyInfo}";
    if(deleteCompanyInfo!=""){
        alert(deleteCompanyInfo);
    }
    var applyVehicleInfo="${requestScope.applyVehicleInfo}";
    if(applyVehicleInfo!=""){
        alert(applyVehicleInfo);
    }

</script>
</body>
</html>