<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld" %>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script src="JS/check.jsp"></script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">�������Ϣ�б�</td>
  </tr>
  <tr><td height="15" align="center">
  <a href="superAdd.lzw">[ <img src="images/list.gif" width="11" height="13" />&nbsp;��Ӵ������Ϣ]</a></td>
  </tr>
  <tr>
    <td align="center" valign="top"><form action="superType.lzw" method="post" name="frm">
          <table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td valign="top">
                <table width="100%" height="14"  border="0" cellpadding="0" cellspacing="0">
                <tr><td align="center">
                <c:choose>
                <c:when test="${param.message eq 'false'}">
				<font color="red">�÷����Ѿ������ӷ�����Ϣ������ɾ��С������Ϣ���ٽ���ɾ��!</font>
				</c:when>
                <c:when test="${param.message eq 'true'}">�������Ϣɾ���ɹ�!</c:when>
                </c:choose></td></tr>
                </table>
                <table width="100%" height="48"  border="1" cellpadding="0" cellspacing="0" 
				bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
                  <tr bgcolor="#eeeeee">
                    <td height="24" align="center">�������Ϣ����</td>
                    <td width="8%" align="center">ɾ��</td>
                  </tr>
                  <c:forEach var="type" items="${list }">
                  <tr style="padding:5px;">
                    <td height="20" align="center">${type.typeName}</td>
                    <td align="center">
					<input name="delid" type="checkbox" class="noborder" value="${type.id}"></td>
                  </tr>
                  </c:forEach>
                </table>
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="73%" height="24">&nbsp;</td>
                    <td width="27%" align="right">
					<input name="checkbox" type="checkbox" class="noborder" onClick="CheckAll(frm.delid,frm.checkbox)">
                    [ȫѡ/��ѡ] [<a style="color:red;cursor:hand;" onclick="checkdel(frm.delid,frm)">ɾ��</a>]
                    <div id="ch">
                      <input name="delid2" type="checkbox" class="noborder" value="0">
                  </div></td>
                    <!--��ch���ڷ������ص�checkbox�ؼ�����Ϊ������ֻ��һ��checkbox�ؼ�ʱ��Ӧ��javascript�����length����ֵΪundefine-->
                    <script language="javascript">ch.style.display="none";</script>
                  </tr>
              </table></td>
            </tr>
          </table>
        </form>
    </td>
  </tr>
</table>
