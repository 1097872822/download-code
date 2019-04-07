<%@ page contentType="text/html; charset=gb2312" language="java"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
function mycheck(){
	if (form1.title.value==""){
		alert("请输入公告标题！");form1.title.focus();return;
	}
	if (form1.content.value==""){
		alert("请输入公告内容！");form1.content.focus();return;
	}
	form1.submit();
}
</script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">添加公告信息</td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
        <tr><td align="center"><font color=red>${message }</font></td></tr>
          <tr>
            <td valign="top">
              <form action="placardAdd.lzw" method="post" name="form1">
                <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2" bordercolordark="#FFFFFF">
                  <tr>
                    <td height="45">公告标题：</td>
                    <td><input name="title" type="text" id="title" size="60"></td>
                  </tr>
                  <tr>
                    <td width="14%" height="232">&nbsp;公告内容：</td>
                    <td width="86%">
                      <textarea name="content" cols="60" rows="15" class="textarea"></textarea></td>
                  </tr>
                  <tr>
                    <td height="38" colspan="2" align="center">
                      <input name="Button" type="button" class="btn_grey" value="保存" onClick="mycheck()">
&nbsp;
                    <input name="Submit2" type="reset" class="btn_grey" value="重置">
&nbsp;
                    <input name="Submit3" type="button" class="btn_grey" value="返回" onClick="JScript:history.back()">
                    </td>
                  </tr>
                </table>
            </form></td>
          </tr>
        </table>
    </td>
  </tr>
</table>