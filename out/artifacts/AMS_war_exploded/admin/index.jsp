<%@ page import="cn.scu.ams.utils.Token" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Object obj = ActionContext.getContext().getSession().get("token");
    if(obj == null){
        out.print("<script>alert(\"请先登录\");window.location.href = '/admin/login.jsp';</script>");
    }
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/styles/backstage.css">
</head>

<body>
<div class="head">
    <h3 class="head_text fr">行政管理系统后台</h3>
</div>
<div class="operation_user clearfix">
    <!--   <div class="link fl"><a href="#">慕课</a><span>&gt;&gt;</span><a href="#">商品管理</a><span>&gt;&gt;</span>商品修改</div>-->
    <div class="link fr">
        <b>欢迎您
            <s:property value="#session.admin.account" />
        </b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/index.jsp" class="icon icon_i">首页</a><span></span><a href="javascript:location.reload()" class="icon icon_n">刷新</a><span></span><a href="admin_logout" class="icon icon_e">退出</a>
    </div>
</div>
<div class="content clearfix">
    <div class="main">
        <!--右侧内容-->
        <div class="cont">
            <div class="title">后台管理</div>
            <!-- 嵌套网页开始 -->
            <iframe src="main.jsp"  frameborder="0" name="mainFrame" width="100%" height="522"></iframe>
            <!-- 嵌套网页结束 -->
        </div>
    </div>
    <!--左侧列表-->
    <div class="menu">
        <div class="cont">
            <div class="title">管理员</div>
            <ul class="mList">
                <li>
                    <h3><span onclick="show('menu1','change1')" id="change1">+</span>员工管理</h3>
                    <dl id="menu1" style="display:none;">
                        <dd><a href="addEmployee.jsp" target="mainFrame">添加员工</a></dd>
                        <dd><a href="/employee_findAll.action" target="mainFrame">员工列表</a></dd>
                    </dl>
                </li>
                <li>
                    <h3><span onclick="show('menu2','change2')" id="change2">+</span>业务管理</h3>
                    <dl id="menu2" style="display:none;">
                        <dd><a href="queryBusiness.jsp" target="mainFrame">查询业务</a></dd>
                        <dd><a href="/business_findAll" target="mainFrame">业务列表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

</div>
<script type="text/javascript">
    function show(num,change){
        var menu=document.getElementById(num);
        var change=document.getElementById(change);
        if(change.innerHTML=="+"){
            change.innerHTML="-";
        }else{
            change.innerHTML="+";
        }
        if(menu.style.display=='none'){
            menu.style.display='';
        }else{
            menu.style.display='none';
        }
    }
</script>
</body>
</html>
