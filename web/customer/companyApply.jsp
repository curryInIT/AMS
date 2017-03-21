<%--
  Created by IntelliJ IDEA.
  User: hello~
  Date: 17-3-16
  Time: 下午4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="cn.scu.ams.domain.Customer"%>
<%@ page import="cn.scu.ams.domain.Company" %>
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

    <title>公司注册申请</title>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/css/companyApply.css" />
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

        <li  class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
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
<div class="content">
    <div id="yourposition" >
        <p class="text-info" style="font-size:16px; font-family:宋体">你现在的位置：公司业务 > 公司注册申请</p>
    </div>
    <%
        Company company = (Company)request.getAttribute("company2");
        if(company == null){
     %>
    <div id="main">
        <div id="left">
            <center>
                <div style="width:400px;">
                    <s:form action="company_apply" method="post" namespace="/" enctype="multipart/form-data">
                        <form>
                            <br><br><br>
                            <input  name="companyName" class="form-control" placeholder="公司名称" required>
                            <br>
                            <input  name="applyCustomer" class="form-control" placeholder="法人姓名" required>
                            <br>
                            <input  name="customerIdNumber" class="form-control" placeholder="法人身份证号" required>
                            <br>
                            <input  name="customerPhone" class="form-control" placeholder="电话号码" required>
                            <br>
                            <input  name="customerEmail" class="form-control" placeholder="电子邮箱" required>
                            <br>
                            <input  name="customerAddress" class="form-control" placeholder="法人家庭住址" required>
                            <br>
                            <input  name="companyAddress" class="form-control" placeholder="公司地址" required>
                            <br>
                            <select name="managerArea" class="form-control">
                                <option value="制造业">制造业</option>
                                <option value="零售业">零售业</option>
                                <option value="矿业">矿业</option>
                            </select>
                            <br>
                            <input  name="managerProducts" class="form-control" placeholder="公司经营产品" required>
                            <br>
                            <textarea  name="companyDescribe" class="form-control" placeholder="公司简介" required></textarea>
                            <br>
                            <input  name="registerMoney" class="form-control" placeholder="公司注册资金" required>
                            <br>
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
<a href="http://baike.baidu.com/link?url=pBck8SNZmryKJtvcBnotXYNtWzboBrVcogSVb2M0Vi7lv2v3jl5zrdufIGv9jAVjPz8Bri56V0IUv8lbyNAn73Krfp-U2RgvUllO9zHSJWNbhEo3ABdDXTkWQSjwKxzf">点击查看申请要求</a>
		  </pre>
        </div>
    </div>
    <%
        }else{
     %>
    <br><br>
    <center><div>已存在公司，不可再注册！</div></center>
    <%
        }
    %>

</div>
<!--中间结束-->

</body>
</html>
<%
    }
%>