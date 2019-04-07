<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${info==null }">
<form action="register.lzw" method="post" name="myform">
  <table width="100%"  border="0" cellspacing="-2" cellpadding="-2">
  	<tr><td height="50" colspan="2" background="images/register.jpg"></td>
  	</tr>
    <tr>
      <script language="javascript">
	   function openwin(UID){
	   if (UID==""){
			alert("�������û���!");
			myform.username.focus();
			return;
	   }
	   var str="checkUserName.jsp?username="+UID;
	   window.showModalDialog(str,"","dialogWidth=300px;dialogHeight=150px;status=no;help=no;scrollbars=no");
	   }
	  </script>
      <td width="18%" height="30" align="center">�� �� ����</td>
      <td width="82%" class="word_grey">
      <spring:bind path="command.username">
      <input name="${status.expression }" type="text" maxlength="20" value="${status.value }">
          <span class="word_orange">* ${status.errorMessage }</span></spring:bind>
    </tr>
    <tr>
      <td height="28" align="center">��ʵ������</td>
      <td height="28">
      <spring:bind path="command.truename">
      <input name="${status.expression }" value="${status.value }" type="text" maxlength="10">
          <span class="word_orange">* ${status.errorMessage }</span></spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;�룺</td>
      <td height="28">
      <spring:bind path="command.pwd">
      <input name="${status.expression }" type="password" size="20" maxlength="20">
          <span class="word_orange">* ${status.errorMessage }</span></spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">ȷ�����룺</td>
      <td height="28">
      <spring:bind path="command.pwd1">
      <input name="${status.expression }" type="password" size="20" maxlength="20">
          <span class="word_orange">* ${status.errorMessage }</span></spring:bind> </td>
    </tr>
    <tr>
      <td height="28" align="center">���ڳ��У�</td>
      <td><spring:bind path="command.city">
      <input name="${status.expression }" value="${status.value }" type="text">
      <span class="word_orange"> ${status.errorMessage }</span>
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">��ϵ��ַ��</td>
      <td class="word_grey">
      <spring:bind path="command.address">
      <input name="${status.expression }" value="${status.value }" type="text"size="50">
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">�������룺</td>
      <td class="word_grey">
      <spring:bind path="command.postcode">
      <input name="${status.expression }" value="${status.value }" type="text" size="20">
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">֤�����룺</td>
      <td><spring:bind path="command.cardno">
      <input name="${status.expression }" value="${status.value }" type="text">
          <span class="word_orange">* ${status.errorMessage }</span></spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">֤�����</td>
      <td>
      	<input name="cardtype" type="radio" class="noborder" value="���֤" checked>
        ���֤&nbsp;
        <input name="cardtype" type="radio" class="noborder" value="����֤">����֤
        <input name="cardtype" type="radio" class="noborder" value="ѧ��֤">ѧ��֤
        </td>
    </tr>
    <tr>
      <td height="28" align="center">��ϵ�绰��</td>
      <td><spring:bind path="command.tel">
      <input name="${status.expression }" value="${status.value }" type="text">
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center" style="padding-left:10px">Email��</td>
      <td class="word_grey">
      <spring:bind path="command.email">
      <input name="${status.expression }" value="${status.value }" type="text" size="50">
          <span class="word_orange">* ${status.errorMessage }</span></spring:bind></td>
    </tr>
    <tr>
      <td height="34">&nbsp;</td>
      <td class="word_grey"><input name="Button" type="submit" class="btn_grey" value="ע��">
          <input name="Submit2" type="reset" class="btn_grey" value="����">
          <input name="Submit22" type="button" class="btn_grey" value="����" onClick="window.location.href='index.lzw'">
      </td>
    </tr>
  </table>
</form></c:if>
<c:if test="${info!=null }">
<table width="100%" height="300" border="0" cellspacing="-2" cellpadding="-2">
	<tr><td valign="middle" headers="300" align="center">
	<span class="word_orange">${info }</span></td></tr>
</table>
</c:if>