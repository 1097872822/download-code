<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="../error.jsp" import="java.util.*,com.wsy.category"%>
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
	window.opener.location.reload();
	window.close();
}
</script>
</head>
<body>
<form action=categoryDelPagetest.jsp method="post">
<div align="center">
  <p align=center class="lunzi"><Strong>商品类别-删除</strong></p>
<div align=center>
<table width=75% border="0" cellspacing="0">
	<tr>
		<td><font color="#663300" class="lunzi">类别名称:</font></td>
		<td class="zczi">
			<%
				String id=request.getParameter("catid");
				String name=null;
				Collection temp=sql.selectCategoryAll(id);
				Iterator it=temp.iterator();
				while(it.hasNext()){
					category category=(category)it.next();
					name=category.getCategoryname();
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
			<input type=button name=button value=否 onClick="fanhui()">
		</td>
	</tr>		
</table>
</div>

</body>
</html>