<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${sessionScope.username==null }">
<script language='javascript'>alert('���ȵ�¼!');window.location.href='index.jsp';</script>
</c:if>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="189" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="50" background="images/order.jpg">&nbsp;</td>
        </tr>
        <tr valign="top">
          <td height="134" align="center">
            <table width="94%"  border="0" cellpadding="0" cellspacing="0">
              <tr align="center" >
                <td width="8%" height="30" class="tableBorder_B_dashed">������</td>
                <td width="8%" class="tableBorder_B_dashed">Ʒ����</td>
                <td width="13%" class="tableBorder_B_dashed">��ʵ����</td>
                <td width="18%" class="tableBorder_B_dashed">���ʽ</td>
                <td width="18%" class="tableBorder_B_dashed">���ͷ�ʽ</td>
                <td width="9%" class="tableBorder_B_dashed">�ۿ�</td>
                <td width="26%" class="tableBorder_B_dashed">��������</td>
              </tr>
			<c:forEach items="${orders }" var="order">
              <tr align="center">
                <td height="24"><a href="orderDetails.lzw?id=${order.orderId }">${order.orderId }</a></td>
                <td>${order.bnumber }</td>
                <td>${order.truename }</td>
                <td>${order.pay }</td>
                <td>${order.carry }</td>
                <td><fmt:formatNumber value="${order.rebate }" type="percent"/></td>
                <td><fmt:formatDate value="${order.orderDate }" dateStyle="full" timeStyle="medium" type="both"/></td>
              </tr>
              </c:forEach>
          </table></td>
        </tr>
    </table></td>
  </tr>
</table>