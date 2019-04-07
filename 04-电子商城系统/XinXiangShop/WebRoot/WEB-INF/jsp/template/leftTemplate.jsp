<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<table width="198" align="right" valign="top" cellpadding="0"
 cellspacing="0" bgcolor="#F0F0F0">
	<tr>
		<td  valign="top"><tiles:insert attribute="login" /></td>
	</tr>
	<tr>
		<td><tiles:insert attribute="placard" /></td>
	</tr>
	<tr>
		<td><tiles:insert attribute="sellSortLeft" /></td>
	</tr>
</table>
