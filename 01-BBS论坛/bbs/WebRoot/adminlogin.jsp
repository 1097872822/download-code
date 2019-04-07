<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="taglib.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
function submit2(){
	if(document.all.name.value.length==0){
		alert("请填写用户名!");
		return false;
	}
	if(document.all.password.value.length==0){
		alert("请填写密码!");
		return false;
	}
	document.all.loginForm.submit();
	return true;
}
	
//-->
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="login.do" focus="name">
<table width="527" height="356" border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
	<tr>
		<td>
			<img src="images/ht01.gif" width="527" height="92" alt=""></td>
	</tr>
	<tr>
		<td background="images/ht02.gif" width="527" height="58">
			<table width="100%"  border="0" height="58">
  <tr>
    <td width="42%">&nbsp;</td>
    <td width="58%" valign="baseline"><html:text property="name" size="20" maxlength="20"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="baseline"><html:password property="password" size="22" maxlength="20"/></td>
  </tr>
</table>

			
	  </td>
	</tr>
	<tr>
		<td>
			<img src="images/ht03.gif" alt="" width="527" height="206" border="0" usemap="#Map"></td>
	</tr>
</table>
</html:form>

<map name="Map">
  <area shape="rect" coords="219,9,276,35" href="#" onClick="return submit2();">
  <area shape="rect" coords="297,10,352,36" href="#" onClick="document.all.loginForm.reset();">
</map>
</body>
</html>
