<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.username==null }">
<script language='javascript'>alert('���ȵ�¼!');window.location.href='index.jsp';</script>
</c:if>
<c:if test="${param.id==null }">
<jsp:forward page="index.lzw"/>
</c:if>
<table width="590"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50" background="images/orderDetails.jpg">&nbsp;</td>
  </tr>
  <tr><td height="15"></td></tr>
  <tr valign="top">
    <td height="134" align="center">
      <table width="100%" height="272"  border="0" cellpadding="0" cellspacing="0">
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
            <table width="90%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#CCCCCC" class="tableBorder_dashed">
              <tr align="center" bgcolor="#eeeeee">
                <td width="21%" height="24">��Ʒ���</td>
                <td width="45%">��Ʒ����</td>
                <td width="22%">����</td>
                <td width="12%">����</td>
              </tr>
				<c:forEach var="details" items="${order.tbOrderDetails }">
              <tr align="center">
                <td height="27">${details.tbGoods.id }</td>
                <td height="27">${details.tbGoods.goodsName }</td>
                <td>${details.tbGoods.nowPrice }(Ԫ)</td>
                <td>${details.number }</td>
              </tr>
              </c:forEach>
            </table>
            <table width="100%" height="49"  border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center"><input name="Submit" type="submit" class="btn_grey" value="����" onClick="history.back(-1)"></td>
              </tr>
          </table></td>
          <td width="3%" valign="top">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="38" align="right" background="images/center02.gif"><a href="sale.jsp"><br>
          <br>
    </a></td>
  </tr>
</table>