<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/>
<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>     
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String submit=request.getParameter("submit");
String catid=(String)session.getAttribute("id");
int i=sql.DelCategory(catid);
if(i==1){
	
 %>
 <script language="JavaScript">
 window.alert("删除成功");
 window.close();
 window.opener.location.reload();
 </script>
 <%} %>


</body>
</html>