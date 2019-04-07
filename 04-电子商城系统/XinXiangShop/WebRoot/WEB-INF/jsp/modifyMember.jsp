<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<c:if test="${username!=null }">
<tiles:importAttribute/>
<form action="modifyMember.lzw" method="post" name="myform">
  <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2">
  <tr><td height="55" colspan="2" background="images/memberModify.jpg"></td></tr>
    <tr>
      <td width="100" height="30" align="center">�� �� ����</td>
      <td class="word_grey">
      <spring:bind path="command.username">
      <input name="${status.expression }" type="text" value="${user.userName }" maxlength="20" readonly>
      </spring:bind>
          <span class="word_orange">(�û��������޸�)</span>
          <spring:bind path="command.id">
          <input name="${status.expression }" type="hidden" value="${user.id }">
          </spring:bind></td>
    </tr>
    <tr>
      <td height="2" align="center">��ʵ������</td>
      <td height="28">
      <spring:bind path="command.truename">
      <input name="${status.expression }" type="text" value="${user.trueName }" maxlength="10">
      </spring:bind>
          <span class="word_orange">*</span></td>
    </tr>
    <tr>
      <td height="28" align="center"> ԭ �� �룺</td>
      <td height="28">
      <spring:bind path="command.oldpwd">
      <input name="${status.expression }" type="password" size="20" maxlength="20">
      <span class="word_orange">*&nbsp;${status.errorMessage }</span>      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">�� ��&nbsp;�룺</td>
      <td height="28">
      <spring:bind path="command.pwd">
      <input name="${status.expression }" type="password" size="20" maxlength="20">
        <span class="word_orange">*&nbsp;${status.errorMessage }</span>      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">ȷ�������룺</td>
      <td height="28">
      <spring:bind path="command.pwd1">
      <input name="pwd1" type="password" size="20" maxlength="20">
        <span class="word_orange">*&nbsp;${status.errorMessage }</span>	  </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">���ڳ��У�</td>
      <td>
      <spring:bind path="command.city">
      <input name="city" type="text" value="${user.city }">
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">��ϵ��ַ��</td>
      <td class="word_grey">
      <spring:bind path="command.address">
      <input name="${status.expression }" type="text" value="${user.address }" size="50">
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">�������룺</td>
      <td class="word_grey">
      <spring:bind path="command.postcode">
      <input name="${status.expression }" type="text" value="${user.postcode }" size="20">
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">֤�����룺</td>
      <td>
      <spring:bind path="command.cardno">
      <input name="${status.expression }" type="text" value="${user.cardNo }" ></spring:bind>
          <span class="word_orange">*</span></td>
    </tr>
    <tr>
      <td height="28" align="center">֤�����</td>
      <td>
      <spring:bind path="command.cardtype">
      <input name="${status.expression }" type="radio" class="noborder" value="���֤" 
      	<c:if test="${user.cardType=='���֤' }"> checked</c:if>>
        ���֤&nbsp;
        <input name="${status.expression }" type="radio" class="noborder" value="����֤" 
        <c:if test="${user.cardType=='����֤' }"> checked</c:if>>
        ����֤
        <input name="${status.expression }" type="radio" class="noborder" value="ѧ��֤" 
        <c:if test="${user.cardType=='ѧ��֤' }"> checked</c:if>>
        ѧ��֤        </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center">��ϵ�绰��</td>
      <td>
      <spring:bind path="command.tel">
      <input name="${status.expression }" type="text" value="${user.tel }">
      </spring:bind></td>
    </tr>
    <tr>
      <td height="28" align="center" style="padding-left:10px">Email��</td>
      <td class="word_grey">
      <spring:bind path="command.email">
      <input name="${status.expression }" type="text" value="${user.email }" size="50">
      </spring:bind>
          <span class="word_orange">*</span></td>
    </tr>
    <tr>
      <td height="34">&nbsp;</td>
      <td class="word_grey"><input name="Button" type="submit" class="btn_grey" value="ȷ������">
          <input name="Submit2" type="reset" class="btn_grey" value="������д">      </td>
    </tr>
  </table>
</form>
</c:if>
<c:if test="${username==null }">
<center><br><br><br><br>���ȵ�¼�����޸ĸ�����Ϣ!</center>
</c:if>
