<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="sql" scope="page" class="com.wsy.selectsql"/>
<jsp:useBean id="user" scope="page" class="com.wsy.user"/>
<jsp:setProperty property="*" name="user"/>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<%
String name=user.getName().trim();
String password=user.getPassword().trim();
int i=0;
String x=request.getParameter("66");
i=sql.check(name,password);

if(i==1){
	session.setAttribute("ok","ok");
	response.sendRedirect("index.jsp");
}
if(i==0){
%>
<script>
	javaScript:window.alert("登录失败");
</script>
<%  
	response.sendRedirect("adminlogin.jsp");
}
%>
</body>
</html>