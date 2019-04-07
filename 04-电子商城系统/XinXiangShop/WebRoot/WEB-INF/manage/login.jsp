<%@ page contentType="text/html; charset=gb2312" language="java"%>
<html>
<head>
<title>后台管理!</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" rel="stylesheet">
<style type="text/css">
<!--
body {
	background-color: #f0f0f0;
}
-->
</style>
</head>
<body onLoad="form1.manager.focus();">
<br><br><br><br><br><br>
<%session.invalidate(); %>
<form name="form1" method="post" action="loginM.lzw">
  <table width="547" height="294"  border="0" align="center" cellpadding="0" cellspacing="0"
   background="images/loginM.jpg" class="tableBorder">
    <tr>
      <td height="220" align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" valign="top"><table width="93%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="53%" height="36" align="right" valign="bottom">
			<input name="manager" type="text" id="manager" size="13">
            </td>
            <td width="28%" align="right" valign="bottom">
			<input name="PWD" type="password" id="PWD" size="13"></td>
            <td width="19%" height="25" align="center" valign="bottom">
			<a href="#" onClick="form1.submit()">
			<img id="lb" src="images/loginButton.jpg" width="72" height="22"
			 onMouseDown="lb.src='images/loginButtonClick.jpg'"
			 onMouseOver="lb.src='images/loginButtonHov.jpg'"
			 onMouseOut="lb.src='images/loginButton.jpg'"></a></td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td height="30" align="right"><a href="index.lzw">返回</a>&nbsp;&nbsp;</td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
</body>
</html>
