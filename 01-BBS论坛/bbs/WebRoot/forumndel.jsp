<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wsy.struts.util.*"%>
<%@include file="taglib.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>del</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function fanhui(){
	window.opener.location.reload();
	window.close();
}
function tijiao(id){
	window.location.href="forumDel.do?id='"+id+"'";
	//window.close();
}

</script>
</head>
<body  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="276" height="174" border="0" align="center" cellpadding="0" cellspacing="0" background="images/bj2.gif">
  <!--  html:form action="forumDel.do"-->
  <!--  form action="forumDel.do?id='d'" name="forumDelForm" target="_self"-->
  <tr>
    <td height="30" colspan="3" valign="top" >&nbsp;</td>
  </tr>
  <tr>
    <td width="20" rowspan="2" valign="top" class="bzzi">&nbsp;</td>
  <td width="143" rowspan="2" valign="top" class="bzzi">确定删除<span class="lunzi"><%
  StringTrans s=new StringTrans();
  if(request.getParameter("forumname")!=null)
  
  	{session.setAttribute("forumname",request.getParameter("forumname"));
  
  
  %>
  <%=s.tranC(request.getParameter("forumname")) %>
  <%}else{out.println(s.tranC((String)session.getAttribute("forumname")));}%></span>主题？</td>
  	<%
	String id=request.getParameter("id");
	session.setAttribute("id",id);
	String sucess=null;
	sucess=(String)request.getAttribute("sucess");
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
    <td valign="top" class="bzzi">
    <input type="button" name="Submit" value="删除" onClick="tijiao(id)">
    <input type="button" name="Submit2" value="返回" onClick="fanhui()"></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" >&nbsp;</td>
  </tr>
  <!--  /form-->
  <!--  /html:form-->
</table>
</body>
</html>