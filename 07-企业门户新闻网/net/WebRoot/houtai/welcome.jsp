<%@page contentType="text/html;charset=UTF-8"pageEncoding="UTF-8" errorPage="../error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="a" class="com.wsy.selectsql" scope="page"/>
<%@ page language="java" import="java.util.*"%>
<%@page import="com.wsy.user;"%>
<html>
<head>

<link href="../CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="1002" height="118"  border="0" background="../images/ht05.gif">
  <%
String name=null;
Collection ret=a.getRet();
Iterator it=ret.iterator();
while(it.hasNext()){
	user user=(user)it.next();
	name=user.getName();
}

%>
  <tr>
    <td width="652">&nbsp;</td>
    <td width="340"><span class="lunzi">欢迎光临!</span><span class="hczi"><%=name%></span></td>
  </tr>
</table>


</body>
</html>