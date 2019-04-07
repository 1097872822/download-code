<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出错了</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<span class="zczi">出错了！
发生了以下的错误：
<%=
exception.getMessage()
 %></span>
</body>
</html>