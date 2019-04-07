<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<%@page import="com.wsy.StringTrans"%>
<jsp:useBean id="sql" scope="page" class="com.wsy.selectsql"/>
<jsp:useBean id="s" scope="page" class="com.wsy.StringTrans"/>
<jsp:useBean id="news" scope="page" class="com.wsy.news"/>
<jsp:setProperty property="*" name="news"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
String title=news.getTitle().trim();
String author=news.getAuthor().trim();
String content=news.getContent().trim();
if(title!=null&&author!=null&&content!=null){
int i=sql.Insert(title,author,content);
System.out.println(i);

String x=null;
if(i==1){
	x="恭喜，添加完毕！";

}
else{
	x="添加失败";
}
session.setAttribute("test",x);
response.sendRedirect("news.jsp");
}
%>
</body>
</html>