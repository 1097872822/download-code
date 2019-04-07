<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.news" errorPage="../error.jsp"%>
    <jsp:useBean id="sql" class="com.wsy.selectsql"/>
    <jsp:useBean id="news" class="com.wsy.news"/>
    <jsp:useBean id="s" class="com.wsy.StringTrans"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body background="../images/ht041.gif" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div align="center">
  <p align="right">&nbsp;</p>
  <p align="right"><font size="2"><a href="news.jsp" class="hczi">添加新闻</a></font></p>
  <table width=60% border="01" align="center" cellspacing="0" bordercolor="#FFFFFF">
	<tr bgcolor="#D4E7F8">
	  <td width=70%><div align="center" class="whitezi"><strong>名称</strong></div></td>
		<td width=30% colspan="2"><div align="center" class="whitezi"><strong>操作</strong></div></td>
	</tr>
<%
Collection temp=sql.selectNewsAll();
Iterator it=temp.iterator();
int count=0;
while(it.hasNext())
{
	news newsl=(news)it.next();
	if(count%2==0)
	out.println("<tr bordercolor='#FFFFFF' bgcolor='#E4EFFA'>");
	else
	out.println("<tr bgcolor='FFFFFF' bordercolor='#E4EFFA'>");
	out.println("<td><div align='center' class='zczi'>"+newsl.getTitle()+"</div></td>");
	out.println("<td><div align='center'><strong><a href='newsEdit.jsp?id="+newsl.getId()+"' class='zczi'>修改</a></div></td>");
	out.println("<td><div align='center'><strong><a href='#' onclick=window.open('newsDel.jsp?id="+newsl.getId()+"','newwindow','width=276,height=174,top=400,left=500') class='zczi'>删除</a></div></td>");
	out.println("</tr>");
	count++;
}
%>
<p align="center">&nbsp;</p>
</table>
</div>
</body>
</html>