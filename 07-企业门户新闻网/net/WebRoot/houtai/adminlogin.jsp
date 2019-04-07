<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<form action="houtaitest.jsp" name="loginForm" method="post">
<table width="500" height="300" border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
	<tr>
		<td>
			<img src="../images/ht03.gif" width="500" height="175" alt=""></td>
	</tr>
	<tr>
		<td width="500" height="71" background="../images/ht01.gif">
		
		<table width="100%"  border="0" height="71">
  <tr>
    <td width="50%">&nbsp;</td>
    <td width="50%" valign="bottom"><input type="text" name="name" size="20" maxlength="20"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="bottom"><input type="password" name="password" size="22" maxlength="20"/></td>
  </tr>
</table>
	  </td>
	</tr>
	<tr>
		<td>
			<img src="../images/ht02.gif" alt="" width="500" height="54" border="0" usemap="#Map"></td>
	</tr>
</table>
<map name="Map">
  <area shape="rect" coords="280,8,335,34" href="#" onClick="return submit2();">
  <area shape="rect" coords="357,8,413,35" href="#" onClick="return reset();">
</map></form>
</body>
</html>