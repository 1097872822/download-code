<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<table width="198" height="147" align="right" cellpadding="0" cellspacing="0" background="images/login.jpg" valign="top">
	<tr>
		<td height="40" align="right">&nbsp;</td>
	</tr>
    <tr>
      <td align="right">&nbsp;</td>
    </tr> 
	<tr>
		<td height="98">
<form name="form1" method="post" action="login.lzw">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td width="10%">&nbsp;</td>
	<td width="90%" valign="top">
	<c:if test="${param.loger=='error'}">
		<script type="text/javascript">
		<!--
		alert("�û�����������󣬻��߸��û��ѱ����ᡣ");
		//-->
		</script>
	</c:if>
	<c:if test="${sessionScope.username==null }">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="94%" height="24">��Ա��
				<input name="username" type="text" class="txt_grey"
					id="username" size="16"></td>
		</tr>
		<tr>
			<td height="24">��&nbsp;&nbsp;��
				<input name="password" type="password" class="txt_grey" id="PWD"
					size="16"></td>
		</tr>
		<tr>
			<td height="31">&nbsp;<a href="register.lzw">ע��</a>
				<input name="Submit2" type="submit" class="btn_grey"
					value="��¼">
				<input name="Submit3" type="reset" class="btn_grey" value="����">
				</td>
		</tr>
	</table>
	</c:if>
	<c:if test="${sessionScope.username!=null }">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="94%" height="31" align="center">
				${sessionScope.userTrueName }����!<br>�����ڿ��Թ�����!</td>
		</tr>
		<tr>
			<td height="24" align="center">
				<input name="Submit22" type="button" class="btn_grey"
					value="�޸�����"
					onClick="window.location.href='modifyMember.lzw';"></td>
		</tr>
		<tr>
			<td height="24" align="center">
				<input name="Submit32" type="button" class="btn_grey"
		value="�˳���¼" onClick="window.location.href='login.lzw?loginOut=true';">
		</td>
		</tr>
	</table>
	</c:if>
	</td>
</tr>
</table>
</form>
		</td>
	</tr>
</table>
