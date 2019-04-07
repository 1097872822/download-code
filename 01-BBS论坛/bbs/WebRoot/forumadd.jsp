<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function fanhui(){
	window.opener.location.reload();
	window.close();
}

</script>
</head>
<body  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="276" height="174" border="0" background="images/bj2.gif">
  <html:form action="forumAdd.do">
  <tr>
    <td valign="top">
	<br>
	<br>
	<table width="100%"  border="0">
      <tr align="center">
        <td colspan="3" class="whitezi">新增论坛</td>
        </tr>
      <tr>
        <td width="26%" class="whitezi">论坛名：</td>
        <td width="33%"><input name="forumname" type="text" size="15"></td>
        <td width="41%">&nbsp;</td>
      </tr>
		<tr>
        <td width="26%" class="whitezi">&nbsp;</td>
        <td  class="whitezi"><html:errors/></td>
        <td width="41%"><input type="submit" name="Submit" value="提交">
          <input type="button" name="Submit2" value="返回" onClick="fanhui()"></td>
        </tr>
        <% 
        String sucess=null;
	sucess=(String)request.getAttribute("sucess");
	if(sucess!=null){
	 %>
	 <script language="javascript">
	window.alert("添加成功");
	window.opener.location.reload();
	window.close();

</script>
<%} %>
    </table></td>
  </tr>
  </html:form>
</table>
</body>
</html>