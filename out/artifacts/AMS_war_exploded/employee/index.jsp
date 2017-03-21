<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-14
  Time: 下午10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Object obj = ActionContext.getContext().getSession().get("employee_token");
    if(obj == null){
        out.print("<script>alert(\"请先登录\");window.location.href = '/employee/login.jsp';</script>");
    }
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/styles/backstage.css">
</head>

<body>
<div class="head">
    <h3 class="head_text fr">行政管理系统</h3>
</div>
<div class="operation_user clearfix">
    <!--   <div class="link fl"><a href="#">慕课</a><span>&gt;&gt;</span><a href="#">商品管理</a><span>&gt;&gt;</span>商品修改</div>-->
    <div class="link fr">
        <b>欢迎您
             <s:property value="#session.employee.account"/>
        </b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/employee/index.jsp" class="icon icon_i">首页</a><span></span><a href="javascript:location.reload()" class="icon icon_n">刷新</a><span></span><a href="employee_logout" class="icon icon_e">退出</a>
    </div>
</div>
<div class="content clearfix">
    <div class="main">
        <!--右侧内容-->
        <div class="cont">
            <div class="title">业务管理</div>
            <!-- 嵌套网页开始 -->
            <iframe src=""  frameborder="0" name="mainFrame" width="100%" height="522"></iframe>
            <!-- 嵌套网页结束 -->
        </div>
    </div>
    <!--左侧列表-->
    <div class="menu">
        <div class="cont">
            <div class="title">办公人员</div>
            <ul class="mList">
                <li>
                    <h3><span onClick="show('menu1','change1')" id="change1">+</span>公司业务管理</h3>
                    <dl id="menu1" style="display:none;">
                        <dd><a href="business_findCompanyBusiness" target="mainFrame">业务列表</a></dd>
                    </dl>
                </li>
                <li>
                    <h3><span onClick="show('menu2','change2')" id="change2">+</span>个人业务管理</h3>
                    <dl id="menu2" style="display:none;">
                        <dd><a href="business_findVehicleBusiness" target="mainFrame">机动车业务列表</a></dd>
                        <dd><a href="business_findPersonBusiness" target="mainFrame">身份证业务列表</a></dd>
                    </dl>
                </li>
                <li>
                    <h3><span onClick="show('menu3','change3')" id="change3">+</span>意见与公告</h3>
                    <dl id="menu3" style="display:none;">
                        <dd><a href="suggestion_findAll2" target="mainFrame">意见列表</a></dd>
                        <dd><a href="announce.jsp" target="mainFrame">发布公告</a></dd>
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