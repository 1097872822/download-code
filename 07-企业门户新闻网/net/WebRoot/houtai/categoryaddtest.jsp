<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/>
<jsp:useBean id="sql" scope="page" class="com.wsy.selectsql"/> 
    <jsp:useBean id="category" class="com.wsy.category"/>
    <jsp:setProperty property="*" name="category"/>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String additem=category.getCategoryname().trim();
System.out.println(additem);
int i=sql.InsertCategory(additem);
System.out.println(i);
session.setAttribute("addok","ok");
response.sendRedirect("categoryBrowse.jsp");

%>
</body>
</html>