<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<c:if test="${userInfo!=null }">
<script language="javascript">
function check_member(form){
	if(form.truename.value==""){
		alert("������������ʵ����!");form.truename.focus();return;	
	}
	if(form.address.value==""){
		alert("������������ϵ��ַ!");form.address.focus();return;	
	}	
	if(form.postcode.value==""){
		alert("������������������!");form.postcode.focus();return;	
	}	
	if(form.tel.value==""){
		alert("������������ϵ�绰!");form.tel.focus();return;	
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
      <td height="26" colspan="2" class="word_deepgrey">ע�⣺������Ҫ�����Ƿ��ύ����������ɲ���Ҫ���鷳��</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">�� �� ����</td>
      <td width="74%"><input name="username" type="text" id="username" value="${userInfo.userName }" readonly="yes">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">��ʵ������</td>
      <td><input name="truename" type="text" id="truename" value="${userInfo.trueName }">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">��ϵ��ַ��</td>
      <td><input name="address" type="text" id="address" value="${userInfo.address }">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">�������룺</td>
      <td><input name="postcode" type="text" id="postcode" value="${userInfo.postcode }">
        *</td>
    </tr>
    <tr>
      <td height="26" colspan="2" align="center">��ϵ�绰��</td>
      <td><input name="tel" type="text" id="tel" value="${userInfo.tel }">
              *</td>
          </tr>
          <tr>
            <td height="26" colspan="2" align="center">���ʽ��</td>
            <td><select name="pay" class="textarea">
                <option>���и���</option>
                <option>��������</option>
                <option>�ֽ�֧��</option>
              </select> *</td>
          </tr>
          <tr>
            <td height="26" colspan="2" align="center">���ͷ�ʽ��</td>
            <td><select name="carry" class="textarea">
                <option>��ͨ�ʼ�</option>
                <option>�ؿ�ר��</option>
                <option>EMSר�ݷ�ʽ</option>
              </select>*</td>
          </tr>
          <tr>
            <td height="101" colspan="2" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
            <td><textarea name="bz" cols="50" rows="5" class="textarea" id="bz"></textarea></td>
          </tr>
          <tr align="center">
            <td colspan="3">
            <input name="Button" type="button" class="btn_grey" value="�ύ" onClick="check_member(form_checkout)">
	  &nbsp;<input name="Submit2" type="button" class="btn_grey" value="����" onClick="history.back(1);"></td>
          </tr>
        </table>
    </form></td>
  </tr>
</table>
</c:if>
<c:if test="${userInfo==null }">
	<script language='javascript'>alert('���ȵ�¼���ٽ��й���!');window.location.href='index.jsp';</script>
</c:if>