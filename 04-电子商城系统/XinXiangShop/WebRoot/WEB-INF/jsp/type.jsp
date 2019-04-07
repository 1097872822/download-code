<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="69%">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="98%" height="50" background="images/productSort.jpg">&nbsp;</td>
  </tr>
  <tr><td height="15"></td></tr>
  <tr align="center" valign="top">
    <td height="134" align="center"><table width="90%" height="23"  border="0" cellpadding="0" cellspacing="0" class="tableBorder_B_dashed">
        <tr>
          <td height="23"> 当前选择的商品类别：[ ${typeInfo[0].supertype==null?'该商品类别空':typeInfo[0].supertype } ]->[ ${typeInfo[0].subtype==null?'子类别商品空':typeInfo[0].subtype } ]</td>
        </tr>
      </table>
	<c:forEach var="goods" items="${typeInfo }">
        <table width="96%" height="23"  border="0" cellpadding="0" cellspacing="0" class="noborder">
          <tr>
            <td width="28%" height="23" style="padding-left:20px;"><a href="goodsDetails.lzw?id=${goods.id }">${goods.goodsName }</a> </td>
            <td width="41%">&nbsp;${fn:substring(goods.introduce,0,10)}
            <c:if test="${fn:length(goods.introduce)>10 }">...</c:if>
            </td>
            <td width="21%" align="center">${goods.nowPrice }(元)</td>
            <c:if test="${sessionScope.username!=null }">
            <td width="10%" align="center">&nbsp;
                <input name="see" type="button" class="btn_grey" onClick="window.location.href='cartAdd.lzw?goodsID=${goods.id }'" value="购买">
            </td>
            </c:if>
          </tr>
        </table>
    </c:forEach>
    </td>
  </tr>
  <tr>
    <td height="42" align="right" background="images/shop_24.gif"><a href="goodsSale.lzw"><br>
          <br>
    </a></td>
    <td height="42" background="images/shop_25.gif">&nbsp;</td>
  </tr>
</table>
</td>
</tr>
</table>
