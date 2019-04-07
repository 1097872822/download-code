<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table width="590"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50" background="images/productDetails.jpg">&nbsp;</td>
  </tr>
  <tr><td height="15"></td></tr>
  <tr valign="top">
    <td height="134" align="center">
      <table width="98%" height="79"  border="0" cellpadding="0" cellspacing="0" class="noborder">
        <tr>
          <td width="37%" height="95" rowspan="4" align="center"><img src="images/goods/${goods.picture }" width="90" height="75"></td>
          <td width="63%">${goods.goodsName }</td>
        </tr>
        <tr>
          <td>µ¥¼Û£º${goods.nowPrice }</td>
        </tr>
        <tr>
          <td>${goods.introduce }</td>
        </tr>
        <tr>
          <td><input name="Submit" type="submit" class="btn_grey" onClick="history.back(-1);" value="·µ»Ø">
&nbsp;
            <c:if test="${sessionScope.username!=null }">&nbsp;
            <input name="see" type="button" class="btn_grey" onClick="window.location.href='cartAdd.lzw?goodsID=${goods.id }'" value="¹ºÂò">
            </c:if></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="38" align="right" background="images/center02.gif"><a href="sale.jsp"><br>
          <br>
    </a></td>
  </tr>
</table>