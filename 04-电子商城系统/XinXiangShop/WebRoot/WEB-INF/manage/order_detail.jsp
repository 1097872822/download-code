<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld" %>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<table width="622" height="272"  border="0" cellpadding="0" cellspacing="0">
<tr><td height="15"></td></tr>
<tr><td align="center" height="29" background="images/back_center.jpg">������ϸ��Ϣ</td></tr>
<tr>
  <td width="97%" height="220" align="center" valign="top">
	<table width="90%" height="131"  border="0" cellpadding="0" cellspacing="0" class="tableBorder_LTR_dashed">
	  <tr>
		<td width="16%" style="padding:5px;">�� �� �ţ�&nbsp;${order.orderId }</td>
	  </tr>
	  <tr>
		<td style="padding:5px;">��&nbsp;&nbsp;&nbsp;&nbsp;ַ��&nbsp;${order.address }</td>
	  </tr>
	  <tr>
		<td style="padding:5px;">�������룺&nbsp;${order.postcode }</td>
	  </tr>
	  <tr>
		<td style="padding:5px;">��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;${order.tel }</td>
	  </tr>
	  <tr>
		<td style="padding:5px;">��&nbsp;&nbsp;&nbsp;&nbsp;ע��&nbsp;${order.bz }</td>
	  </tr>
	</table>
	<table width="90%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF"
	 bordercolorlight="#FFFFFF" bordercolordark="#CCCCCC" class="tableBorder_dashed">
	  <tr align="center" bgcolor="#eeeeee">
		<td width="21%" height="24">��Ʒ����</td>
		<td width="45%">��Ʒ����</td>
		<td width="22%">����</td>
		<td width="12%">����</td>
	  </tr>
	<c:forEach var="details" items="${order.tbOrderDetails }">
	  <tr align="center">
		<td height="27">${details.tbGoods.goodsName }</td>
		<td height="27">${details.tbGoods.goodsName }</td>
		<td>${details.tbGoods.nowPrice }(Ԫ)</td>
		<td>${details.number }</td>
	  </tr>
   </c:forEach>
	</table>
	<table width="100%" height="49"  border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<td align="center">
		<input name="Submit" type="submit" class="btn_grey" value="����" onClick="history.back(-1)"></td>
	  </tr>
  </table></td>
  <td width="3%" valign="top">&nbsp;</td>
</tr>
</table>