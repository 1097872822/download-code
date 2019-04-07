<%@ page contentType="text/html; charset=gb2312" language="java"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<table width="622" height="192"  border="0" cellpadding="0" cellspacing="0">
<tr>
    <td height="15">&nbsp;</td>
  </tr>
  <tr>
    <td align="center" height="29" background="images/back_center.jpg">会员详细资料</td>
  </tr>
  <tr>
	<td valign="top">
	  <table width="96%"  border="0" align="center" cellpadding="0" cellspacing="0" class="tableBorder_dashed">
		<tr>
		  <td width="19%" height="27" align="center" class="tableBorder_B_dashed">&nbsp;用 户 名：</td>
		  <td height="13" class="tableBorder_B_dashed"> &nbsp;${member.userName }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;真实姓名：</td>
		  <td height="14" class="tableBorder_B_dashed">&nbsp;${member.trueName }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;城&nbsp;&nbsp;&nbsp;&nbsp;市：</td>
		  <td class="tableBorder_B_dashed">&nbsp;${member.city }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
		  <td height="14" class="tableBorder_B_dashed">&nbsp;${member.address }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;邮政编码：</td>
		  <td width="81%" height="27" class="tableBorder_B_dashed">&nbsp;${member.postcode }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;证件类型：</td>
		  <td height="14" class="tableBorder_B_dashed">&nbsp;${member.cardType }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;证件号码：</td>
		  <td class="tableBorder_B_dashed">&nbsp;${member.cardNo }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
		  <td class="tableBorder_B_dashed">&nbsp;${member.tel }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;Email：</td>
		  <td class="tableBorder_B_dashed">&nbsp;${member.email }</td>
		</tr>
		<tr>
		  <td height="27" align="center" class="tableBorder_B_dashed">&nbsp;消 费 额：</td>
		  <td class="tableBorder_B_dashed">&nbsp;${member.amount }(元)</td>
		</tr>
		<tr>
		  <td height="38" colspan="2" align="center">
			<input name="Submit3" type="button" class="btn_grey" value="返回" onClick="JScript:history.back()">
		  </td>
		</tr>
	</table></td>
  </tr>
</table>