<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="../error.jsp" import="java.util.*,com.wsy.category"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/> 
<jsp:useBean id="s" class="com.wsy.selectsql" scope="page"/>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
String name=null;
String catid=request.getParameter("catid");
Collection ret=s.selectCategoryAll(catid);
Iterator it=ret.iterator();
while(it.hasNext()){
	category category=(category)it.next();
	name=category.getCategoryname();
}
session.setAttribute("catid",catid);
%>
<form action=categoryEditPagetest.jsp method="post">
<div align="center">
<p>&nbsp;</p>
<p align=center class="lunzi"><Strong>商品类别-修改</strong></p>
<div align=center>
<table width=75% border="0" cellspacing="0">
	<tr>
		<td align="right"><font color="#663300" class="lunzi">商品类别名称:</font></td>
		<td>
		<%
		if(name!=null){
		%>
			<input type="text" name="categoryname" value=<%=name %>>
		<%}
		else {%>
			<input type="text" name="categoryname" >
		<%} %>
			<input type="submit" value="修改">
		</td>
	</tr>
</table>
</div>
</div>
</body>
</html>