<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute name="superTypes"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
function mycheck(){
	if (form1.typename.value==""){
		alert("请输入小分类名称！");form1.typename.focus();return;
	}
	form1.submit();
}
</script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr><td height="29" align="center" background="images/back_center.jpg">小分类添加</td>
  </tr>
  <tr>
    <td align="center" valign="top">
	<table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
        <tr><td align="center">${message }</td></tr>
          <tr>
            <td valign="top">
              <form action="subTypeAdd.lzw" method="post" name="form1">
                <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2" bordercolordark="#FFFFFF">
                  <tr>
                    <td height="50">选择所属分类：</td>
                    <td height="50"><select name="superid">
						<c:forEach var="superType" items="${superTypes }">
                        <option value="${superType.id }">${superType.typeName }</option>
                        </c:forEach>
                    </select></td>
                  </tr>
                  <tr>
                    <td width="17%" height="50">小分类名称：</td>
                    <td width="83%" height="50"><input name="typename" type="text" id="typename" size="60"></td>
                  </tr>
                  <tr align="center">
                    <td height="38" colspan="3">
                      <input name="Button" type="button" class="btn_grey" value="保存" onClick="mycheck()">
&nbsp;
                    <input name="Submit2" type="reset" class="btn_grey" value="重置">
&nbsp;
                    <input name="Submit3" type="button" class="btn_grey" value="返回" onClick="JScript:window.location.href='superType.lzw';">
                    </td>
                  </tr>
                </table>
            </form></td>
          </tr>
      </table>
    </td>
  </tr>
</table>