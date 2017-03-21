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
    <title>留言板</title>
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
    <style type="text/css">
        #show{margin-left:20px;}
    </style>
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
        <li class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
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
        <li class="active">
            <div class="pointer">
                <div class="arrow"></div>
                <div class="arrow_border"></div>
            </div>
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
<div class="content">
    <center>
        <s:form action="suggestion_commit" method="post" namespace="/">
        <h1>留言板</h1><br><br>
        <select class="form-control" style="width:500px" name="contentType">
            <option value="民生问题">民生问题</option>
            <option value="治安问题">治安问题</option>
            <option value="其他">其他</option>
        </select>
        <br>
        <textarea  class="form-control" style="width:500px" placeholder="在此输入你的留言" name="content" required></textarea>
        <br><br>
            <button type="submit" class="btn btn-lg btn-warning">我要留言</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="reset" class="btn btn-lg btn-warning">取消</button>
        <br><br><br>
        </s:form>
    </center>
    <div id="show">
        <h3 class="bg-success">来信公开</h3>
        <table class="table table-striped" style="width:1000px;font-size:16px">
            <thead>
            <tr>
                <th></th>
                <th>类型</th>
                <th>内容</th>
                <th>时间</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="list" var="s">
            <tr>
                <td>群众来信：</td>
                <td><s:property value="#s.contentType"/></td>
                <td><s:property value="#s.content"/></td>
                <td><s:property value="#s.pubTime"/></td>
            </tr>
            <tr>
                <td>回复：</td>
                <td></td>
                <td><s:property value="#s.feedback"/></td>
                <td><s:property value="#s.reTime"/></td>
            </tr>
            </s:iterator>
            </tbody>
        </table>
        <nav>
            <ul class="pager">
                <li><span>第<s:property value="currPage"/>/<s:property value="totalPage"/>页</span></li>
                <li><span>总记录数：<s:property value="totalCount"/></span></li>
                <s:if test="currPage != 1">
                    <li><a href="${pageContext.request.contextPath}/suggestion_findAll?currentPage=1">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/suggestion_findAll?currentPage=<s:property value="currPage-1"/>">上一页</a></li>
                </s:if>

                <s:if test="currPage != totalPage">
                    <li><a href="${pageContext.request.contextPath}/suggestion_findAll?currentPage=<s:property value="currPage+1"/>">下一页</a></li>
                    <li><a href="${pageContext.request.contextPath}/suggestion_findAll?currentPage=<s:property value="totalPage" />">尾页</a></li>
                </s:if>

            </ul>
        </nav>
    </div>
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