<%@ page import="cn.scu.ams.domain.Business" %>
<%@ page import="cn.scu.ams.domain.IdCard" %>
<%--
  Created by IntelliJ IDEA.
  User: ypx
  Date: 17-3-17
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
    Business business = (Business)session.getAttribute("curCardBusiness");
    IdCard idCard = (IdCard)session.getAttribute("curCard");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>处理身份证申请和挂失业务详情</title>
</head>

<body>
<center>
    <h2>详细资料</h2>
    <table border="2px">
        <tr>
            <td style="width:200px;height:30px;">照片</td>
            <td style="width:300px;"><img src="<%=idCard.getPhotoPath()%>" width="110" height="154"></td>
        </tr>
        <tr>
            <td style="width:200px;height:30px;">姓名：</td>
            <td style="width:300px;"><%=idCard.getApplyName()%></td>
        </tr>
        <tr>
            <td style="height:30px;">性别：</td>
            <%
                if(idCard.getSex() == 0){
            %>
            <td>男</td>
            <%
                }else{
            %>
            <td>女</td>
            <%
                }
            %>
        </tr>
        <tr>
            <td style="height:30px;">身份证号：</td>
            <td><%=idCard.getIdNumber()%></td>
        </tr>
        <tr>
            <td style="height:30px;">年龄：</td>
            <td><%=idCard.getAge()%></td>
        </tr>
        <tr>
            <td style="height:30px;">民族：</td>
            <td><%=idCard.getRace()%></td>
        </tr>
        <tr>
            <td style="height:30px;">电话号码：</td>
            <td><%=idCard.getCustomerPhone()%></td>
        </tr>
        <tr>
            <td style="height:30px;">电子邮箱：</td>
            <td><%=idCard.getCustomerEmail()%></td>
        </tr>
        <tr>
            <td style="height:30px;">地址：</td>
            <td><%=idCard.getCustomerAddress()%></td>
        </tr>
    </table><br /><br />
    <%
        if(business.getBusinessType() == 0){
    %>
    <input type="button" value="申请通过" onClick="javascript:window.location.href='./business_passCard?businessId=<%=business.getBusinessId()%>'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="申请不通过" onClick="javascript:window.location.href='./business_unpassCard?businessId=<%=business.getBusinessId()%>'">
    <%
    }else{
    %>
    <input type="button" value="挂失通过" onClick="javascript:window.location.href='./business_passLoss?businessId=<%=business.getBusinessId()%>'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="挂失不通过" onClick="javascript:window.location.href='./business_unpassLoss?businessId=<%=business.getBusinessId()%>'">
    <%
        }
    %>
</center>
</body>
</html>
