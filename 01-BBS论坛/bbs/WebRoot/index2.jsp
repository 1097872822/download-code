<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>  
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>论坛首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<script src="JS/onclock.JS"></script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="clockon(bgclock)">
<!-- 注册成功弹出对话框 -->
<%
if(session.getAttribute("name")==null){//判断是否登录
%>
<%@include file="top.jsp" %>
<%}else {%>
<%@include file="top2.jsp" %>
<%} %>
<%@include file="footer.jsp" %>
</body>
</html:html>