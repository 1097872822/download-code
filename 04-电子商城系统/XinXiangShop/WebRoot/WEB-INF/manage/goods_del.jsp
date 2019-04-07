<%@ page contentType="text/html; charset=gb2312" language="java" %>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<c:if test="${goods.id!=null }">
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="15"></td>
  </tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">删除商品信息</td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="top">
              <form action="goodsDel.lzw" method="post" name="form1">
                <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2" bordercolordark="#FFFFFF">
                  <tr>
                    <td width="14%" height="27">&nbsp;商品名称：</td>
                    <td height="27" colspan="3">&nbsp;
                        <input name="id" type="hidden" id="id" value="${goods.id }">
                        ${goods.goodsName } &nbsp;&nbsp; </td>
                  </tr>
                  <tr>
                    <td height="27">&nbsp;所属大类：</td>
                    <td width="31%" height="27">&nbsp;${goods.supertype }</td>
                    <td width="13%" height="27"> &nbsp;所属小类：</td>
                    <td width="42%" height="27">&nbsp;${goods.subtype }</td>
                  </tr>
                  <tr>
                    <td height="16">&nbsp;图片文件：</td>
                    <td height="27" colspan="3">&nbsp; ${goods.picture }</td>
                  </tr>
                  <tr>
                    <td height="27" align="center">定　　价：</td>
                    <td height="27">&nbsp;${goods.price }(元)</td>
                    <td height="27" align="center">现&nbsp;&nbsp;&nbsp;&nbsp;价： </td>
                    <td height="27">&nbsp;${goods.nowPrice }(元)</td>
                  </tr>
                  <tr>
                    <td height="45">&nbsp;是否新品：</td>
                    <td>&nbsp;${goods.newGoods==0?'不是新品':'是新品' }</td>
                    <td>&nbsp;是否特价：</td>
                    <td>${goods.sale==0?'不是特价商品':'是特价商品' }</td>
                  </tr>
                  <tr>
                    <td height="103">&nbsp;商品简介：</td>
                    <td colspan="3"><span class="style5">&nbsp; </span>${goods.introduce }</td>
                  </tr>
                  <tr>
                    <td height="38" colspan="4" align="center">
                      <input name="Submit" type="submit" class="btn_grey" value="确定删除">
&nbsp;
                    <input name="Submit3" type="button" class="btn_grey" value="返回" onClick="JScript:history.back()">                    </td>
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
    <td height="100" align="center">您的操作有误！</td>
  </tr>
</table>
</c:if>