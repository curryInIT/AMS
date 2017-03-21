<%@ page import="cn.scu.ams.domain.Business" %>
<%@ page import="cn.scu.ams.domain.Company" %>
<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-17
  Time: 上午9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
    Business business = (Business)session.getAttribute("curBusiness");
    Company company = (Company)session.getAttribute("curCompany");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>处理公司申请和注销业务详情</title>
</head>

<body>
<center>
    <h2>详细资料</h2>
    <table border="2px">
        <tr>
            <td style="width:200px;height:30px;">公司名称：</td>
            <td style="width:300px;"><%=company.getCompanyName()%></td>
        </tr>
        <tr>
            <td style="height:30px;">法人姓名：</td>
            <td><%=company.getApplyCustomer()%></td>
        </tr>
        <tr>
            <td style="height:30px;">法人身份证号：</td>
            <td><%=company.getCustomerIdNumber()%></td>
        </tr>
        <tr>
            <td style="height:30px;">电话号码：</td>
            <td><%=company.getCustomerPhone()%></td>
        </tr>
        <tr>
            <td style="height:30px;">电子邮箱：</td>
            <td><%=company.getCustomerEmail()%></td>
        </tr>
        <tr>
            <td style="height:30px;">法人家庭地址：</td>
            <td><%=company.getCustomerAddress()%></td>
        </tr>
        <tr>
            <td style="height:30px;">公司地址：</td>
            <td><%=company.getCompanyAddress()%></td>
        </tr>
        <tr>
            <td style="height:30px;">公司经营范围：</td>
            <td><%=company.getManagerArea()%></td>
        </tr>
        <tr>
            <td style="height:30px;">公司主营产品：</td>
            <td><%=company.getManagerProducts()%></td>
        </tr>
        <tr>
            <td style="height:70px;">公司简介：</td>
            <td><%=company.getCompanyDescribe()%></td>
        </tr>
        <tr>
            <td style="height:30px;">公司注册资金：</td>
            <td><%=company.getRegisterMoney()%></td>
        </tr>
        <tr>
            <td style="height:30px;">上传资料：</td><td><a href="">点击下载</a></td>
        </tr>
    </table><br /><br />
    <%
        if(business.getBusinessType() == 2){
    %>
    <input type="button" value="申请通过" onClick="javascript:window.location.href='./business_passCom?businessId=<%=business.getBusinessId()%>'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="申请不通过" onClick="javascript:window.location.href='./business_unpassCom?businessId=<%=business.getBusinessId()%>'">
    <%
        }else{
    %>
    <input type="button" value="注销通过" onClick="javascript:window.location.href='./business_passOff?businessId=<%=business.getBusinessId()%>'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="注销不通过" onClick="javascript:window.location.href='./business_unpassOff?businessId=<%=business.getBusinessId()%>'">
    <%
        }
    %>
</center>
</body>
</html>