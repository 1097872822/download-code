<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.category"  errorPage="../error.jsp"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/>
<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>

<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body background="../images/ht043.gif" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div align="center">
<p>&nbsp;</p>
<p class="lunzi">&nbsp;</p>
<p align="right"><font size="2"><a href="categoryadd.jsp" class="hczi">添加商品类别</a></font></p>
<table width=65% border="1" cellspacing="0" bordercolor="#FFFFFF">
	<tr bgcolor="#D4E7F8">
		<td width=70%><div align="center" class="whitezi"><strong>名称</strong></div></td>
		<td width=30% colspan="2"><div align="center" class="whitezi"><strong>操作</strong></div></td>
	</tr>
<%
Collection temp=sql.selectCategoryAll();
Iterator it=temp.iterator();
int count=0;
while(it.hasNext())
{
	category category=(category)it.next();
	if(count%2==0)
	out.println("<tr bordercolor='#FFFFFF' bgcolor='#E4EFFA'>");
	else
	out.println("<tr bgcolor='FFFFFF' bordercolor='#E4EFFA'>");
	out.println("<td><div align='center' class='zczi'>"+category.getCategoryname()+"</div></td>");
	out.println("<td><div align='center'><strong><a href='categoryEditPage.jsp?catid="+category.getId()+"'>修改</a></div></td>");
	out.println("<td><div align='center'><strong><a href='#' onClick=window.open('categoryDelPage.jsp?catid="+category.getId()+"','newwindow','width=276,height=174,top=400,left=500')>删除</a></div></td>");
	out.println("</tr>");
	count++;
}
%>
<p align="center">&nbsp;</p>
</table>

</div>
</body>
</html>