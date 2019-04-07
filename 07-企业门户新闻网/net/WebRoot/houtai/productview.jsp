<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.product,java.io.*" errorPage="../error.jsp"%>
    <jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品查看</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div align="center">
  <p class="lunzi">商品详细信息</p>
</div>
<%
String id=request.getParameter("catid");
Collection temp=sql.selectBusinessForId(id);
Iterator it=temp.iterator();
while(it.hasNext()){
product product=(product)it.next();
 %>
<table width="500"  border="0" align="center" bordercolor="#FFFFFF" bgcolor="#CCCCCC">
  <tr bgcolor="#FFFFFF">
    <td width="190" align="center" class="lunzi">名称</td>
    <td width="300" align="center" class="lunzi">内容</td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td class="lunzi">产品ID</td>
    <td align="center" class="zczi"><%=product.getId() %></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td class="lunzi">名称</td>
    <td align="center" class="zczi"><%=product.getName() %></td>
  </tr>
    <tr bgcolor="#FFFFFF">
    <td class="lunzi">提交时间</td>
    <td align="center" class="zczi"><%=product.getSubmittime() %></td>
  </tr>
      <tr bgcolor="#FFFFFF">
    <td class="lunzi">商品类别</td>
    <td align="center" class="zczi"><%=product.getCategory() %></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td class="lunzi">描述</td>
    <td rowspan="2" align="center" class="zczi"><%=product.getMsg() %></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td>&nbsp;</td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td class="lunzi">封面</td>
    <td rowspan="3">
    <%
    File path=new File("D:\\Upload");
    if(!path.isDirectory()){
    	path.mkdir();
    }
    System.out.println(path.toString()+'\\'+product.getImg());
     String pathFile=path.toString()+'\\'+product.getImg();
     %>
    
    <img src="<%=pathFile %>" width="70" height="70">    
     
    
    </td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td>&nbsp;</td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td>&nbsp;</td>
  </tr>
</table>
<%} %>
</body>
</html>