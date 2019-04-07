<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="../error.jsp" import="java.util.*,com.wsy.news"%>
    <jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/>
<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<form action=newsDeltest.jsp method="post">
<div align="center">
  <p align=center class="lunzi"><Strong>新闻-删除</strong></p>
<div align=center>
<table width=75% border="0" cellspacing="0">
	<tr>
		<td><font color="#663300" class="lunzi">名称:</font></td>
		<td class="lunzi">
			<%
				String name=null;
				String id=request.getParameter("id");
				Collection temp=sql.selectNews(id);
				Iterator it=temp.iterator();
				while(it.hasNext()){
					news news=(news)it.next();
					name=news.getTitle();
					}
				session.setAttribute("id",id);
				out.println(name);
			%>
		</td>
	</tr>
</table>
</div>
<p align="center" class="hczi"><font color="Blue">确定要删除此记录?</font></p>
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