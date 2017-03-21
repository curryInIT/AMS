<%@ page import="cn.scu.ams.domain.Customer" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%--
  Created by IntelliJ IDEA.
  User: hello~
  Date: 17-3-18
  Time: 上午10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>公告</title>
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

        <li>

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
        <li class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
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
        <li>
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
        <li class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
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
<div class="content">
    <div id="yourposition" >
        <br>
        <p class="text-info" style="font-size:16px; font-family:宋体">你现在的位置: 公告栏</p>
    </div>
    <br><br>
    <center>
    <div id="main"  style="width:800px">

        <h3 class="bg-success">公告栏</h3>
        <table class="table table-striped" style="width:800px;font-size:16px">
            <thead>
            <tr>
                <th style="width:650px">标题</th>
                <th style="width:150px">时间</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="list" var="a">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/announce_detail?currentPage=<s:property value="currPage"/>&announceId=<s:property value="#a.announceId"/>">
                        <s:property value="#a.contentTitle"/>
                        </a>
                    </td>
                    <td><s:property value="#a.pubTime"/></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <nav>
            <ul class="pager">
                <li><span>第<s:property value="currPage"/>/<s:property value="totalPage"/>页</span></li>
                <li><span>总记录数：<s:property value="totalCount"/></span></li>
                <s:if test="currPage != 1">
                    <li><a href="${pageContext.request.contextPath}/announce_findAll?currentPage=1">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/announce_findAll?currentPage=<s:property value="currPage-1"/>">上一页</a></li>
                </s:if>

                <s:if test="currPage != totalPage">
                    <li><a href="${pageContext.request.contextPath}/announce_findAll?currentPage=<s:property value="currPage+1"/>">下一页</a></li>
                    <li><a href="${pageContext.request.contextPath}/announce_findAll?currentPage=<s:property value="totalPage" />">尾页</a></li>
                </s:if>

            </ul>
        </nav>
    </div>
    </center>
</div>
<!--中间结束-->
<script>
    var commitInfo="${requestScope.commitInfo}";
    if(commitInfo!=""){
        alert(commitInfo);
        window.location.href='${pageContext.request.contextPath}/suggestion_findAll';
    }
</script>
</body>
</html>