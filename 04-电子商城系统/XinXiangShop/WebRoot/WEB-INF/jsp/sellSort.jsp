<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50" background="images/sellSortHeader.jpg">&nbsp;</td>
  </tr>
  <tr valign="top">
    <td height="134" align="center">
	<c:forEach end="9" var="goods" items="${sellSort }" varStatus="index">
      <table width="96%" height="30"  border="0" cellpadding="0" cellspacing="0" class="tableBorder_B_dashed">
        <tr>
          <td width="18%" align="center">${index.index+1 }</td>
          <td width="61%"><a href="goodsDetails.lzw?id=${goods[0] }">${goods[1]}</a></td>
          <td width="21%" align="center">&nbsp;
			<c:if test="${sessionScope.username!=null }">
            <input name="see" type="button" class="btn_grey" onClick="window.location.href='cartAdd.lzw?goodsID=${goods[0] }'" value="¹ºÂò">
            </c:if>
            <c:if test="${sessionScope.username==null }">
            µÇÂ¼ºó¿É¹ºÂò
            </c:if></td>
        </tr>
      </table>
      </c:forEach>
    </td>
  </tr>
</table>
