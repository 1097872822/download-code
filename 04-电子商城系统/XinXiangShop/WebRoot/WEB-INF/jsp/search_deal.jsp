<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="189" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="50" background="images/searchResult.jpg">&nbsp;</td>
        </tr>
        <tr valign="top">
          <td height="134" align="center"><table width="90%" height="23"  border="0" cellpadding="0" cellspacing="0" class="tableBorder_B_dashed">
              <tr>
                <td height="23"> [${typeName }]&nbsp;²éÑ¯¹Ø¼ü×Ö£º[${key }]</td>
              </tr>
            </table>
			<c:forEach var="vgoods" items="${results }">
              <table width="96%" height="23"  border="0" cellpadding="0" cellspacing="0" class="noborder">
                <tr>
                  <td width="27%" height="23" style="padding-left:20px;"><a href="goodsDetails.lzw?id=${vgoods.id }">${vgoods.goodsName }</a> </td>
                  <td width="50%">${fn:substring(vgoods.introduce,0,20) }
                  <c:if test="${fn:length(introduce)>20 }">...</c:if></td>
                  <td width="14%" align="center">${vgoods.nowPrice }(Ôª)</td>
                  <c:if test="${sessionScope.username!=null }">
                  <td width="9%" align="center">&nbsp;
                      <input name="see" type="button" class="btn_grey" onClick="window.location.href='cartAdd.lzw?goodsID=${vgoods.id }'" value="¹ºÂò">
                  </td>
                  </c:if>
                </tr>
              </table>
           </c:forEach>
          </td>
        </tr>
        <tr>
          <td height="38" align="right" background="images/center02.gif"><a href="sale.jsp"><br>
                <br>
          </a></td>
        </tr>
    </table></td>
  </tr>
</table>