<%--
  Created by IntelliJ IDEA.
  User: 18346
  Date: 17-3-14
  Time: ����8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <img src="admin/images/logo.png.png" />
  <h2 style="color:#FF6633;">最新通知：</h2>
  <p>请各位员工在3月20日之前完成体质测试。</p>

  <h4 style="margin-left:30px;">各部门负责人：</h4>
  <table border="3px;" style="margin-left:50px;">
      <thead>
      <tr><td width="100px;">部门</td><td width="100px;">负责人</td><td width="200px;">联系方式</td></tr></thead>
      <tbody>
      <tr>
          <td>办公室</td><td>张一</td><td>13900001111</td></tr>
      <tr>
          <td>人事部</td><td>李二</td><td>13911112222</td></tr>
      <tr>
          <td>办公室</td><td>张一</td><td>13644443333</td></tr>
      </tbody>
  </table>


  <script>
      //问题和回复列表为空
      var info = "${requestScope.suggestionInfo}";
      if(info != ""){
          alert(info);
      }

      //待处理的公司业务为空
      var info2 = "${requestScope.companyBusinessInfo}";
      if(info2 != ""){
          alert(info2);
      }

      //待处理的身份证业务为空
      var info3 = "${requestScope.personBusinessInfo}";
      if(info3 != ""){
          alert(info3);
      }
  </script>
  </body>
</html>