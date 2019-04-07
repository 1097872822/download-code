<%@ page contentType="text/html; charset=gb2312" language="java"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
function mycheck(){
	if (form1.typename.value==""){
		alert("请输入大分类名称！");form1.typename.focus();return;
	}
	form1.submit();
}
</script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">添加大分类信息</td>
  </tr>
  <tr>
    <td align="center" valign="top">
	<table width="92%"  border="0" cellpadding="0" cellspacing="0">
        <tr><td align="center">${message }</td></tr>
          <tr>
            <td valign="top">
              <form action="superAdd.lzw" method="post" name="form1">
                <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2" bordercolordark="#FFFFFF">
                  <tr>
                    <td width="16%" height="45">大分类名称：</td>
                    <td width="84%"><input name="typename" type="text" id="typename" size="60"></td>
                  </tr>
                  <tr align="center">
                    <td height="38" colspan="3">
                      <input name="Button" type="button" class="btn_grey" value="保存" onClick="mycheck()">
&nbsp;
                    <input name="Submit2" type="reset" class="btn_grey" value="重置">
&nbsp;
                    <input name="Submit3" type="button" class="btn_grey" value="返回" onClick="JScript:window.location.href='superType.lzw';">                    </td>
                  </tr>
                </table>
            </form></td>
          </tr>
      </table>
    </td>
  </tr>
</table>
