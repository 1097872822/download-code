<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<table width="198" height="195" border="0" cellpadding="0"
	cellspacing="0" background="images/sellSort.jpg" bgcolor="#F0F0F0">
	<tr>
		<td height="40">&nbsp;</td>
	</tr>
	<tr>
		<td align="center" valign="top" class="tableBorder_lb">
			<table width="88%" border="0" cellspacing="0" cellpadding="0">
				<c:forEach end="6" var="goods" items="${sellSort }">
				<tr>
					<td height="20" class="tableBorder_B_dashed">
					<img src="images/sellSortLeftStar.gif" width="11" height="11" />
						<a href="goodsDetails.lzw?id=${goods[0] }">${goods[1] }
						</a></td>
				</tr>
				</c:forEach>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;
						
				  </td>
				</tr>
			</table>
	  </td>
	</tr>
</table>
