<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute name="list"/>
<script src="JS/check.jsp"></script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr><td height="29" align="center" background="images/back_center.jpg">С������Ϣ����</td>
  </tr>
  <tr><td height="15" align="center"><a href="subTypeAdd.lzw">[ <img src="images/list.gif" width="11" height="13" />&nbsp;���С������Ϣ]</a></td>
  </tr>
  <tr>
    <td align="center" valign="top"><form action="subType.lzw" method="post" name="frm">
          <table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
          <tr><td align="center">${message }</td></tr>
            <tr>
              <td valign="top">
                <table width="100%" height="14"  border="0" cellpadding="0" cellspacing="0">
                </table>
                <table width="100%" height="48"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
                  <tr bgcolor="#eeeeee">
                    <td width="48%" height="24" align="center">�������Ϣ����</td>
                    <td width="44%" align="center">С������Ϣ����</td>
                    <td width="8%" align="center">ɾ��</td>
                  </tr>
                  <c:forEach var="subType" items="${list }">
                  <tr style="padding:5px;">
                    <td height="20" align="center">${subType.id.superType }</td>
                    <td height="20" align="center">${subType.id.subType }</td>
                    <td align="center"><input name="delid" type="checkbox" class="noborder" value="${subType.id.subId }"></td>
                  </tr>
                  </c:forEach>
                </table>
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="73%" height="24">&nbsp;</td>
                    <td width="27%" align="right"><input name="checkbox" type="checkbox" class="noborder" onClick="CheckAll(frm.delid,frm.checkbox)">
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