<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.struts.bean.*,com.wsy.struts.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function fanhui(){
	window.opener.location.reload();
	window.close();
}
function tijiao(){
	ueditForm.submit();
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%
		StringTrans s=new StringTrans();
		System.out.println(request.getParameter("name"));
		String name=s.tranC(s.tranC(request.getParameter("name")));
		int w=0;
		int q=0;
 %>
<table width="276" height="174" background="images/bj2.gif" border="0">
  <html:form action="uedit.do">
  <tr>
    <td height="170" valign="top"><table width="100%" height="168" border="0">
      <tr>
        <td colspan="2" class="whitezi">修改用户权限</td>
				<%
		if(session.getAttribute("i")!=null){
		w=Integer.parseInt((String)session.getAttribute("i"));
		q=Integer.parseInt((String)session.getAttribute("j"));//判断表是否更新成功
		
		
		if(w==1||q==1){
		
		 %>
		<td class="whitezi">修改成功</td>
        <td class="whitezi">&nbsp;</td>
         <script language="javascript">
	window.alert("修改成功");
	window.opener.location.reload();
	window.close();

</script>
        
        <%} }%>
      </tr>
      
      <tr>
        <td width="9%" class="whitezi">&nbsp;</td>
        <td width="29%" class="whitezi">用户名称：</td>
        <td colspan="2"><% if(w==0||q==0){%><input type="text" name="name" value=<%=name%>><%}else {%><input type="text" name="name" value=<%=s.tranC(request.getParameter("name"))%>><%} %></td>
        <input type="hidden" name="id" value=<%=request.getParameter("id") %> >
        </tr>
      <tr>
        <td class="whitezi">&nbsp;</td>
      <td class="whitezi">级别：</td>

	    <td colspan="2">
      
        <select name="grade" onChange="show();">
		<%
		if(request.getParameter("grade").equals("admin")){
		%>
		<option value="admin" selected>管理员</option>
		<option value="bz">斑竹</option>
		<option value="user">普通用户</option>
		<% 	
		}
		 %>
		 		<%
		if(request.getParameter("grade").equals("bz")){
		%>
		<option value="admin">管理员</option>
		<option value="bz"  selected>斑竹</option>
		<option value="user">普通用户</option>
		<% 	
		}
		 %>
		 		 		<%
		if(request.getParameter("grade").equals("user")){
		%>
		<option value="admin">管理员</option>
		<option value="bz">斑竹</option>
		<option value="user"  selected>普通用户</option>
		<% 	
		}
		 %>
      </select>
      
       </td>
      </tr>
       	<SCRIPT language=javascript>
						
						function show(){
							if(document.ueditForm.grade.options[document.ueditForm.grade.selectedIndex].value=="bz"){
									document.ueditForm.bz.style.display='';
									document.ueditForm.ifbz.value='y';
							}
							else{
									document.ueditForm.bz.style.display='none';	
							}
						}
           </SCRIPT>

      <tr>
        <td class="whitezi">&nbsp;</td>
        <td class="whitezi">论坛：<input type="hidden" name="ifbz" value="n"><!-- 判断是否选择斑竹 --></td>

      	<td colspan="2">
      	        <%
        if(!request.getParameter("grade").equals("bz")){
         %>

      	<select style="display:none" name="bz">
      	<% 
		List listforum=(List)session.getAttribute("listforum");//取出论坛的名称
		for(int i=0;i<listforum.size();i++){
			ForumBean f=(ForumBean)listforum.get(i);
			
		%>
		<option value=<%=s.tranC(f.getForumnname())%>><%=s.tranC(f.getForumnname())%></option>
		<%} %>
		</select>
      	<%} 
      	else{
      	%>
      	<select name="bz">
      	<% 
		List listforum=(List)session.getAttribute("listforum");//取出论坛的名称
		for(int i=0;i<listforum.size();i++){
			ForumBean f=(ForumBean)listforum.get(i);
			
		%>
		<option value=<%=s.tranC(f.getForumnname())%>><%=s.tranC(f.getForumnname())%></option>
		<%} %>
		</select>
      	<%} %>
      	</td>
      </tr>
      

      <tr>
        <td height="40" colspan="2">&nbsp;</td>
        <td width="41%"><input type="submit" name="Submit" value="提交" onClick="tijiao()">
          <input type="submit" name="Submit2" value="返回" onClick="fanhui()"></td>
        <td width="21%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  </html:form>
</table>

</body>
</html>