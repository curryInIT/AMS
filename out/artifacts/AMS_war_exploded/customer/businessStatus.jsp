<%--
  Created by IntelliJ IDEA.
  User: hello~
  Date: 17-3-17
  Time: 上午10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="cn.scu.ams.domain.Customer"%>
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
    <title>查询业务状态</title>

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
        <li class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
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
    <center><h1>业务办理状态</h1></center>
    <h3 class="bg-success">我的公司业务</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <td>业务类型</td>
            <td>申请时间</td>
            <td>申请状态</td>
            <td>申请结果</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="companyBusinessList" var="c">
        <tr>
            <s:if test="#c.businessType ==2">
                <td>公司注册</td>
            </s:if>
            <s:else>
                <td>公司注销</td>
            </s:else>

            <td><s:property value="#c.applyTime"/> </td>

            <s:if test="#c.status ==0">
                <td>待处理</td>
                <td>暂无结果</td>
            </s:if>
            <s:else>
                <td>已处理</td>
                <s:if test="#c.passStatus == 0">
                    <td>未通过</td>
                </s:if>
                <s:else>
                    <td>已通过</td>
                </s:else>
            </s:else>

            <td><button type="button" class="btn btn-info" onclick="javascript:window.location.href='./company_findById?companyId=<s:property value="#c.companyId"/>'">详情</button></td>
        </tr>
        </s:iterator>
        </tbody>
    </table>
    <br><br><br><br>
    <h3 class="bg-success">我的个人业务</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <td>业务类型</td>
            <td>申请时间</td>
            <td>申请状态</td>
            <td>申请结果</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="personBusinessList" var="p">
            <tr>
                <s:if test="#p.businessType ==0">
                    <td>身份证申请</td>
                </s:if>
                <s:else>
                    <s:if test="#p.businessType ==1">
                        <td>身份证挂失</td>
                    </s:if>
                    <s:else>
                        <td>机动车申请</td>
                    </s:else>
                </s:else>

                <td><s:property value="#p.applyTime"/> </td>

                <s:if test="#p.status ==0">
                    <td>待处理</td>
                    <td>暂无结果</td>
                </s:if>
                <s:else>
                    <td>已处理</td>
                    <s:if test="#p.passStatus == 0">
                        <td>未通过</td>
                    </s:if>
                    <s:else>
                        <td>已通过</td>
                    </s:else>
                </s:else>

                <s:if test="#p.businessType ==5">
                    <td><button type="button" class="btn btn-info" onclick="javascript:window.location.href='./vehicle_findById?vehicleId=<s:property value="#p.vehicleId"/>'">详情</button></td>
                </s:if>
                <s:else>
                    <td><button type="button" class="btn btn-info" onclick="javascript:window.location.href='./idCard_findById?cardId=<s:property value="#p.idCardId"/>'">详情</button></td>
                </s:else>

            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
<!--中间结束-->

</body>
</html>
<%
    }
%>