<%@ page contentType="text/html; charset=gb2312" language="java" %>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<c:if test="${goods.id!=null }">
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="15"></td>
  </tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">ɾ����Ʒ��Ϣ</td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="top">
              <form action="goodsDel.lzw" method="post" name="form1">
                <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2" bordercolordark="#FFFFFF">
                  <tr>
                    <td width="14%" height="27">&nbsp;��Ʒ���ƣ�</td>
                    <td height="27" colspan="3">&nbsp;
                        <input name="id" type="hidden" id="id" value="${goods.id }">
                        ${goods.goodsName } &nbsp;&nbsp; </td>
                  </tr>
                  <tr>
                    <td height="27">&nbsp;�������ࣺ</td>
                    <td width="31%" height="27">&nbsp;${goods.supertype }</td>
                    <td width="13%" height="27"> &nbsp;����С�ࣺ</td>
                    <td width="42%" height="27">&nbsp;${goods.subtype }</td>
                  </tr>
                  <tr>
                    <td height="16">&nbsp;ͼƬ�ļ���</td>
                    <td height="27" colspan="3">&nbsp; ${goods.picture }</td>
                  </tr>
                  <tr>
                    <td height="27" align="center">�������ۣ�</td>
                    <td height="27">&nbsp;${goods.price }(Ԫ)</td>
                    <td height="27" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;�ۣ� </td>
                    <td height="27">&nbsp;${goods.nowPrice }(Ԫ)</td>
                  </tr>
                  <tr>
                    <td height="45">&nbsp;�Ƿ���Ʒ��</td>
                    <td>&nbsp;${goods.newGoods==0?'������Ʒ':'����Ʒ' }</td>
                    <td>&nbsp;�Ƿ��ؼۣ�</td>
                    <td>${goods.sale==0?'�����ؼ���Ʒ':'���ؼ���Ʒ' }</td>
                  </tr>
                  <tr>
                    <td height="103">&nbsp;��Ʒ��飺</td>
                    <td colspan="3"><span class="style5">&nbsp; </span>${goods.introduce }</td>
                  </tr>
                  <tr>
                    <td height="38" colspan="4" align="center">
                      <input name="Submit" type="submit" class="btn_grey" value="ȷ��ɾ��">
&nbsp;
                    <input name="Submit3" type="button" class="btn_grey" value="����" onClick="JScript:history.back()">                    </td>
                  </tr>
                </table>
            </form></td>
          </tr>
        </table>        </td>
  </tr>
</table>
</c:if>
<c:if test="${goods.id==null }">
<table>
  <tr>
    <td height="100" align="center">���Ĳ�������</td>
  </tr>
</table>
</c:if>