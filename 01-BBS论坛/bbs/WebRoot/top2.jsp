<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.struts.bean.*"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>  
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
		<td colspan="2">
			<img src="images/010.gif" width="1002" height="121" alt=""></td>
	</tr>
	<tr>
		<td colspan="2" height="40" background="images/02.gif"><table width="100%" height="33"  border="0">
  <tr>
    <td width="7%">&nbsp;</td>
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
		String forumid=(String)request.getAttribute("forumId");
		if(iflogin==null||forumid==null){
	%>
    <td width="6%" valign="bottom" class="zczi"><a href="#" onClick="alert('请您选择论坛后再发表文章')" class="zczi">发布主题</a></td>
    <%
	}else{%>
	<td width="15%" valign="bottom" class="zczi"><a href="newtopic.do?name=<%=session.getAttribute("name") %>&forumid=<%=request.getAttribute("forumId") %>&forumname=<%=request.getParameter("forumname") %>" class="zczi">发布主题</a></td>
	<%}%> 
	
  
  </tr>
</table>

		
		
			
	  </td>
	</tr>

	<tr>
		<td width="872" height="68" background="images/03.gif">

			<table width="100%"  border="0" height="68">
  <tr>
    <td width="22%" height="32">&nbsp;</td>
    <td width="37%" id="bgclock" class="zi">&nbsp;</td>
    <td width="11%">&nbsp;</td>
    <td width="30%">&nbsp;</td>
  </tr>

  <tr>

    <td colspan="2" class="zczi">&nbsp;</td>
    <td>&nbsp;</td>
    <td><span class="lz">广阔的空间，我们的天地，F论坛！</span></td>

  </tr>

</table>

			
			
	  </td>
		<td width="143" background="images/011.gif" height="68">
			</td>
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
              <td width="25%">帖子：<%=ztAndResponseCount %>个</td>
                <td>今日帖子：<%= todaycount%>	个</td>
              </tr>
            </table>
            <%} %>
            
            </td>
          </tr>
        </table>
	  </td>
	</tr>
	  <app:display/><!-- 自定义标签，用于显示所有论坛信息 -->
	<!--  %} %-->
</table>
