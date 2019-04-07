<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<table width="622" height="192"  border="0" cellpadding="0" cellspacing="0">
<tr><td height="15"></td></tr>
<tr>
  <td height="29" align="center" background="images/back_center.jpg">��Ա�б�</td>
</tr>
<tr>
  <td align="center" valign="top">
	<table width="98%" border="1" cellpadding="0"
	 cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
	  <tr>
		<td width="14%" height="27" align="center"> �û���</td>
		<td width="14%" align="center">��ʵ����</td>
		<td width="11%" align="center">����</td>
		<td width="14%" align="center">�绰</td>
		<td width="26%" align="center">Email</td>
		<td width="10%" align="center">���Ѷ�</td>
		<td width="11%" align="center">����/�ⶳ</td>
	  </tr>
	  <c:forEach var="mem" items="${memberList}">
		<tr style="padding:5px;">
		  <td height="24" align="center"><a href="memberDetail.lzw?id=${mem.id }">${mem.userName }</a></td>
		  <td align="center">${mem.trueName }</td>
		  <td align="center">${mem.city }&nbsp;</td>
		  <td align="center">${mem.tel }&nbsp;</td>
		  <td align="center">${mem.email }</td>
		  <td align="center">${mem.amount+0.0 }</td>
		  <td align="center"><c:if test="${mem.freeze==0||mem.freeze==null }">
		  <a href="memberFreeze.lzw?id=${mem.id }">
		  <img src="images/freeze.gif" alt="����" width="13" height="15"></a>
	</c:if>
	<c:if test="${mem.freeze==1 }">
	<a href="memberThaw.lzw?id=${mem.id }">
	<img src="images/thaw.gif" alt="�ⶳ" width="15" height="15"></a> </c:if></td>
		</tr>
	  </c:forEach>
	</table>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td height="27" align="right">��ǰҳ����[${page }/${maxPage}]&nbsp;
		  <c:if test="${page>1}">
		  <a href="memberManage.lzw?page=1">��һҳ</a>
		  <a href="memberManage.lzw?Page=${page-1 }">��һҳ</a> </c:if>
		  <c:if test="${page<maxPage}">
		  <a href="memberManage.lzw?page=${page+1 }">��һҳ</a>
		  <a href="memberManage.lzw?Page=${maxPage}">���һҳ&nbsp;</a> </c:if>
		</td>
	  </tr>
	</table></td>
</tr>
</table>
<c:if test="${message!=null}">
  <script type="text/javascript">
	<!--
	alert("${message}");
	//-->
	</script>
</c:if>
