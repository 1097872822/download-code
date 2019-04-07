<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<tiles:importAttribute/>
<table width="198" height="194" border="0" cellpadding="0"
	cellspacing="0" background="images/placard.jpg">
	<tr>
		<td height="40">&nbsp;</td>
	</tr>
	<tr>
		<td align="center" valign="top" class="tableBorder_l">
		<table width="88%"  border="0" cellspacing="0" cellpadding="0">
		<c:forEach var="bbs" items="${placard }" end="6">
		<tr valign="top">
             <td height="24" nowrap="nowrap" class="tableBorder_T_dashed">
			 <img src="images/placardStar.gif" width="11" height="11" />
			 <a href="placardDetails.lzw?id=${bbs.id }">${bbs.title }</a>
			 </td>
          </tr>
          </c:forEach>
         </table>
	  </td>
	</tr>
</table>
