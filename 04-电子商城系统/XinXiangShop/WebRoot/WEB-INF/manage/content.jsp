<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<table align="center" width="622" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr>
    <td valign="top">
	<table width="100%" height="14"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="29" align="center" background="images/back_center.jpg">��Ʒ�б�</td>
        </tr>
        <tr>
          <td height="29" align="center">
		  <a href="superType.lzw">
		  [ <img src="images/list.gif" width="11" height="13" />&nbsp;�������Ϣ����]</a>
		  &nbsp;<a href="subType.lzw">
		  [ <img src="images/list.gif" width="11" height="13" />&nbsp;С������Ϣ����]</a>
		  &nbsp;<a href="goodsAdd.lzw">
		  [ <img src="images/list.gif" width="11" height="13" />&nbsp;�����Ʒ��Ϣ]</a>
		  </td>
        </tr>
      </table>
      <table width="100%" height="48"  border="1" cellpadding="0" cellspacing="0" 
		  bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
        <tr bgcolor="#eeeeee">
          <td width="22%" height="24" align="center">��Ʒ����</td>
          <td width="40%" align="center">���</td>
          <td width="11%" align="center">�Ƿ���Ʒ</td>
          <td width="11%" align="center">�Ƿ��ؼ�</td>
          <td width="8%" align="center">�޸�</td>
          <td width="8%" align="center">ɾ��</td>
        </tr>
        <c:forEach var="goods" items="${goodsList }">
          <tr style="padding:5px;">
            <td height="20" align="center">
			<a href="goodsDetailM.lzw?id=${goods.id }">${goods.goodsName }</a></td>
            <td align="center">${goods.introduce }</td>
            <td align="center">${goods.newGoods==0? '��':'��' }</td>
            <td align="center">${goods.sale==0? '��':'��' }</td>
            <td align="center"><a href="goodsModify.lzw?id=${goods.id }">
			<img src="images/modify.gif" width="15" height="15"></a></td>
            <td align="center"><a href="goodsDel.lzw?id=${goods.id }">
			<img src="images/del.gif" width="16" height="16"></a></td>
          </tr>
        </c:forEach>
      </table>
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right">��ǰҳ����[${page }/${maxPage }]&nbsp;
            <c:if test="${page > 1 }">
			<a href="indexM.lzw?page=1">��һҳ</a>
			<a href="indexM.lzw?page=${page-1 }">��һҳ</a> </c:if>
            <c:if test="${page < maxPage }">
			<a href="indexM.lzw?page=${page+1 }">��һҳ</a>
			<a href="indexM.lzw?page=${maxPage }">���һҳ&nbsp;</a> </c:if>
          </td>
        </tr>
      </table></td>
  </tr>
</table>
