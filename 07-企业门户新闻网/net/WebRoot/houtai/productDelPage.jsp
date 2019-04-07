<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.product" errorPage="../error.jsp"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/>
<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>     
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function fanhui(){
	//window.opener.location.reload();
	window.close();
}
</script>
</head>
<body>
<form action=productDelPagetest.jsp method="post">
<div align="center">
  <p align=center class="lunzi"><Strong>商品-删除</strong></p>
<div align=center>
<table width=75% border="0" cellspacing="0">
	<tr>
		<td><font color="#663300" class="lunzi">商品名称:</font></td>
		<td class="zczi">
			<%
			
				String name=null;
				String id=request.getParameter("catid");
				Collection temp=sql.selectBusinessForId(id);
				Iterator it=temp.iterator();
				while(it.hasNext()){
					product product=(product)it.next();
					name=product.getName();
				}
				session.setAttribute("id",id);
				out.println(name);
			%>
		</td>
	</tr>
</table>
</div>
<p align="center" class="whitezi"><font color="Blue">确定要删除此记录?</font></p>
<table width=10% border="0">
	<tr>
		<td>
			<input type=submit name=submit value=是>
		</td>
		<td>
			<input type=button name=submit value=否 onclick="fanhui();">
		</td>
	</tr>		
</table>
</div>
</body>
</html>