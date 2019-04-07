<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.struts.bean.*,com.wsy.struts.util.*"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<script type="text/javascript">
<!--
function submit2(){
	if(document.all.name.value.length==0){
		alert("请填写用户名!");
		return false;
	}
	if(document.all.password.value.length==0){
		alert("请填写密码!");
		return false;
	}
	document.all.loginForm.submit();
	return true;
}

	
//-->
</script>
<link href="CSS/style.css" rel="stylesheet" type="text/css">


<table id="__01" width="1002"  border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td  colspan="2">
			<img src="images/010.gif" width="1002" height="121"/>
			</td>
	</tr>
	<tr>
		<td colspan="2" height="40" background="images/02.gif"><table width="100%" height="33"  border="0">
  <tr>
    <td width="8%">&nbsp;</td>
    <td width="54%">  [  <span class="zi"> <%
				if(session.getAttribute("name")!=null)
					out.println(session.getAttribute("name"));
				else 
					out.println("游客");

			
			%>
    <html:errors/>
			</span> ] <span class="zi">  
            欢迎光临</span>&nbsp;&nbsp;
			<%
			if(session.getAttribute("name")!=null){
			%>
			<span class="zczi"><a href="logoff.do" class="zczi">注销</a> </span>
			<%
			}else{
			%>
			<span class="zczi">请登录</span>
			<%}%>
		  </td>
		  <td width="3%" valign="bottom" class="bzzi"><a href="index.do" class="zczi">首页</a></td>
		  <td width="1%" valign="bottom" class="zczi">|</td>
    <td width="6%" valign="bottom" class="zczi"><a href="adminlogin.do" class="zczi">管理员登录</a></td>
  	<td width="1%" valign="bottom" class="zczi">|</td>
    <td width="6%" valign="bottom" class="zczi"><a href="#" class="zczi" onclick="window.location.reload()">刷新主页</a></td>
    <td width="1%" valign="bottom" class="zczi">|</td>
	<%
		String iflogin=(String)session.getAttribute("i");
		if(iflogin==null){
	%>
    <td width="6%" valign="bottom" class="zczi"><a href="#" onClick="alert('请您首先登录')" class="zczi">发布主题</a></td>
    <%
	}else{%>
	<td width="14%" valign="bottom" class="zczi"><a href="newtopic.do?name=<%=session.getAttribute("name") %>&forumid=<%=session.getAttribute("forumId") %>&forumname=<%=request.getParameter("forumname") %>" class="zczi">发布主题</a></td>
	<%}%> 
	
  
  </tr>
</table>

		
		
			
	  </td>
	</tr>
<html:form action="login.do" focus="name">
	<tr>
		<td width="872" height="63" background="images/03.gif">

			<table width="100%"  border="0">
  <tr>
    <td width="22%" height="24">&nbsp;</td>
    <td width="30%" id="bgclock" class="zi">&nbsp;</td>
    <td width="24%"><span class="zi">用户名：</span><html:text property="name" size="13" maxlength="20"/></td>
    <td width="24%"><span class="zi">密码：</span><html:password property="password" size="13" maxlength="20"/></td>
  </tr>

  <tr>

    <td colspan="2" class="zczi">&nbsp;</td>
    <td class="lz">&nbsp;</td>
    <td class="lz">广阔的空间，我们的天地，F论坛！</td>
  </tr>

</table>

			
			
	  </td>
		<td width="143">
			<img src="images/04.gif" alt="" width="130" height="63" border="0" usemap="#Map"></td>
	</tr>
	<tr>
		<td colspan="2" width="1002" height="30" background="images/05.gif">
		<%
		String ztcount=(String)session.getAttribute("ztcount");
		String ztAndResponseCount=(String)session.getAttribute("ztAndResponseCount");
		String todaycount=(String)session.getAttribute("todaycount");
		if(ztcount!=null){

		 %>
		
		<table width="100%"  border="0">
          <tr>
            <td width="60%">&nbsp;</td>
            <td width="40%"><table width="100%"  border="0" >
              <tr class="whitezi">
                <td width="22%" height="20">主题：<%=ztcount %>篇</td>
              <td width="25%">文章：<%=ztAndResponseCount %>篇</td>
                <td>今日文章：<%= todaycount%>	个</td>
              </tr>
            </table>
            <%} %>
            
            </td>
          </tr>
        </table>
			</td>
	</tr>
	
</html:form>
	  <app:display/><!-- 自定义标签，用于显示所有论坛信息 -->
</table>

<map name="Map">
  <area shape="rect" coords="4,12,52,36" href="#" onClick="return submit2();">
  <area shape="rect" coords="59,11,104,36" href="register.do">
</map>
