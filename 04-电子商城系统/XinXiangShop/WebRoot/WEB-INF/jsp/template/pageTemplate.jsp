<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="error.jsp"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<html>
<head>
<title>
<tiles:getAsString name="title" />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" rel="stylesheet">
</head>
<body>
<table width="790" border="0" cellspacing="0" cellpadding="0"
	bgcolor="white" align="center">
  <tr>
    <td class="tableBorder"><tiles:insert attribute="navigation" /></td>
	</tr>
	<tr>
        <td><tiles:insert attribute="search" />
      <table width="100%" height="236" border="0" align="center"
	cellpadding="0" cellspacing="0">
        <tr>
          <td valign="top" width="182"><tiles:insert attribute="left" /></td>
          <td valign="top"><table width="100%" cellpadding="0" cellspacing="0">
              
              <tr>
                <td valign="top"><tiles:insert attribute="content" /></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <table width="790" border="0" align="center" cellpadding="0"
	cellspacing="0">
        <tr>
          <td><tiles:insert attribute="footer" /></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
