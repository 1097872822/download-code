<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wsy.struts.util.*"%>
<%@include file="taglib.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主题回复删除</title>
<script language="javascript">
function fanhui(){
	window.opener.location.reload();
	window.close();
}
function tijiao(){
	responseDelForm.submit();
}
</script>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body   bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="276" height="174" border="0" background="images/bj2.gif">
<html:form action="responseDel.do">
<tr>
    <td height="30" colspan="3" valign="top" >&nbsp;</td>
  </tr>
  <tr>
    <td width="20" rowspan="2" valign="top" class="bzzi">&nbsp;</td>
  <td width="143" rowspan="2" valign="top" class="bzzi">确定删除回复主题<span class="lunzi">
  <%
  StringTrans s=new StringTrans();
  if(request.getParameter("name")!=null)
  
  	{session.setAttribute("name",request.getParameter("name"));
  
  
  %>
  <%=s.tranC(request.getParameter("name")) %>
  <%}else{out.println(s.tranC((String)session.getAttribute("name")));}%></span>？</td>
	<%
	String id=request.getParameter("id");
	session.setAttribute("id",id);
	String sucess=null;
	sucess=(String)request.getAttribute("sucess");
	System.out.println(sucess!=null);
	if(sucess!=null){
	 %>
	 <script language="javascript">
	window.alert("删除成功");
	window.opener.location.reload();
	window.close();

</script>
<%} %>

    <td width="113" valign="top" class="bzzi"><html:errors/></td>
  </tr>
  <tr>
    <td valign="top" class="bzzi"><input type="button" name="Submit" value="提交" onclick="tijiao()">
    <input type="button" name="Submit2" value="返回" onclick="fanhui()"></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" >&nbsp;</td>
    </tr>
    </html:form>
</table>
</body>
</html>