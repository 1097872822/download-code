<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
<!--
.pName {color: #00b1c0}
.pPrice {color: #ff6600}
.pIntro {color: #3300FF}
-->
</style>
<table width="100%" height="264"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="50" background="images/sale.jpg">&nbsp;</td>
  </tr>
  <tr valign="top">
    <td height="134" align="center">
	<c:forEach var="goods" items="${saleGoods }">
      <table width="98%" height="79"  border="0" cellpadding="0" cellspacing="0" class="noborder">
        <tr>
          <td width="37%" height="95" rowspan="5" align="center">
		  <img src="images/goods/${goods.picture}" width="90" height="75"></td>
          <td width="63%" height="27">产品名称：<span class="pName">${goods.goodsName}</span></td>
        </tr>
        <tr>
          <td height="27">产品价格：<span class="pPrice">${goods.nowPrice} (元) </span></td>
        </tr>
        <tr>
          <td height="27">产品说明：<span class="pIntro">${goods.introduce}</span></td>
        </tr>
        <tr>
          <td><input name="Submit" type="submit" class="btn_grey" onClick="history.back(-1);" value="返回">
            <c:if test="${sessionScope.username!=null}">
            <input name="see" type="button" class="btn_grey"
			 onClick="window.location.href='cartAdd.lzw?goodsID=${goods.id }'" value="购买">
            </c:if></td>
        </tr>
      </table>
      <hr align="center" width="92%" size="1">
      </c:forEach>
    </td>
  </tr>
</table>