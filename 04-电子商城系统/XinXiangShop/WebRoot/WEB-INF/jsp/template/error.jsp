<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030" isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<%if(exception!=null){
out.println(exception.getMessage());
exception.printStackTrace();
}%>
</body>
</html>