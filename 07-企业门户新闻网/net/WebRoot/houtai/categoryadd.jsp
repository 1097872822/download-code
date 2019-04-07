<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="../error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
</head>

<body background="../images/ht045.gif" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
  <form action=categoryaddtest.jsp method="post">
    <p align=center class="lunzi">&nbsp;</p>
    <p align=center class="lunzi">&nbsp;</p>
    <table width=75% border="0" cellspacing="0">
	<tr>
		<td width="42%" align="right"><font color="#663300" class="lunzi">商品类别名称:</font></td>
		<td width="58%">
			<input type="text" name="categoryname">
			<input type="submit" value="保存">
		</td>
	</tr>
</table>
</body>
</html>