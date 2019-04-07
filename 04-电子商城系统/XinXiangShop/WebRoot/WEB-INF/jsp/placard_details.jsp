<%@ page contentType="text/html; charset=gb2312" language="java"%>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50" background="images/placardDetails.jpg">&nbsp;</td>
  </tr>
  <tr><td height="15"></td></tr>
  <tr valign="top">
    <td height="134" align="center">
      <table width="98%" height="79"  border="0" cellpadding="0" cellspacing="0" class="noborder">
        <tr>
          <td width="2%" height="12" align="center">&nbsp;</td>
          <td width="95%" height="27">&nbsp;${placard.title }</td>
          <td width="3%">&nbsp;</td>
        </tr>
        <tr>
          <td width="2%" height="12" align="center">&nbsp;</td>
          <td height="27">&nbsp;${placard.content }</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td width="2%" height="24" align="center">&nbsp;</td>
          <td height="27">&nbsp;发布日期：${placard.intime }</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td width="2%" height="47" align="center">&nbsp;</td>
          <td align="center"><input name="Submit" type="submit" class="btn_grey" onClick="history.back(-1);" value="返回"></td>
          <td></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="38" align="right" background="images/center02.gif"><a href="sale.jsp"><br><br></a></td>
  </tr>
</table>