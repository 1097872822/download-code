<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<%
session.invalidate();
response.sendRedirect("../index.jsp");

%>
</body>
</html>