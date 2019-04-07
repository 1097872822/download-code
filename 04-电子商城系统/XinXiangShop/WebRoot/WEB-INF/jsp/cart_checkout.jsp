<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<c:if test="${userInfo!=null }">
<script language="javascript">
function check_member(form){
	if(form.truename.value==""){
		alert("请输入您的真实姓名!");form.truename.focus();return;	
	}
	if(form.address.value==""){
		alert("请输入您的联系地址!");form.address.focus();return;	
	}	
	if(form.postcode.value==""){
		alert("请输入您的邮政编码!");form.postcode.focus();return;	
	}	
	if(form.tel.value==""){
		alert("请输入您的联系电话!");form.tel.focus();return;	
	}
	form.submit();
}
</script>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50" background="images/checkOut.jpg">&nbsp;</td>
  </tr>
  <tr valign="top">
    <td height="134" align="center">
<form method="post" action="cartCheckout.lzw" name="form_checkout">
  <table width="100%" height="339"  border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="7%" height="26">&nbsp; </td>
      <td height="26" colspan="2" class="word_deepgrey">注意：请您不要恶意或非法提交订单以免造成不必要的麻烦！</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">用 户 名：</td>
      <td width="74%"><input name="username" type="text" id="username" value="${userInfo.userName }" readonly="yes">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">真实姓名：</td>
      <td><input name="truename" type="text" id="truename" value="${userInfo.trueName }">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">联系地址：</td>
      <td><input name="address" type="text" id="address" value="${userInfo.address }">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">邮政编码：</td>
      <td><input name="postcode" type="text" id="postcode" value="${userInfo.postcode }">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">联系电话：</td>
      <td><input name="tel" type="text" id="tel" value="${userInfo.tel }">
              *</td>
          </tr>
          <tr>
            <td height="26" colspan="2" align="center">付款方式：</td>
            <td><select name="pay" class="textarea">
                <option>银行付款</option>
                <option>邮政付款</option>
                <option>现金支付</option>
              </select> *</td>
          </tr>
          <tr>
            <td height="26" colspan="2" align="center">运送方式：</td>
            <td><select name="carry" class="textarea">
                <option>普通邮寄</option>
                <option>特快专递</option>
                <option>EMS专递方式</option>
              </select>*</td>
          </tr>
          <tr>
            <td height="101" colspan="2" align="center">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
            <td><textarea name="bz" cols="50" rows="5" class="textarea" id="bz"></textarea></td>
          </tr>
          <tr align="center">
            <td colspan="3">
            <input name="Button" type="button" class="btn_grey" value="提交" onClick="check_member(form_checkout)">
	  &nbsp;<input name="Submit2" type="button" class="btn_grey" value="返回" onClick="history.back(1);"></td>
          </tr>
        </table>
    </form></td>
  </tr>
</table>
</c:if>
<c:if test="${userInfo==null }">
	<script language='javascript'>alert('请先登录后，再进行购物!');window.location.href='index.jsp';</script>
</c:if>