<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="taglib.jsp" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主题回复</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<script src="JS/onclock.JS"></script><!--用于显示时间的JavaScript-->
</head>
<body  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="clockon(bgclock)">
<table id="__01" width="1002"  border="0" cellpadding="0" cellspacing="0">

	<tr>
		<td colspan="2" >
			<img src="images/010.gif" alt="" name="Image1" width="1002" height="121" id="Image1"></td>
	</tr>
	<tr>
		<td colspan="2" height="36" background="images/02.gif"><table width="100%" height="36"  border="0">
  <tr>
    <td width="7%" valign="bottom">&nbsp;</td>
    <td width="57%" valign="bottom">  [  <span class="zi"> <%
				if(session.getAttribute("name")!=null)
					out.println(session.getAttribute("name"));
				else 
					out.println("游客");

			
			%><html:errors/>
			</span> ] <span class="zi">  
            欢迎光临</span>      </td>
    <td width="7%" valign="bottom" class="zczi"><a href="adminlogin.do" class="zczi">管理员登录</a></td>
  <td width="2%" valign="bottom" class="zczi"> |</td>

    <td width="7%" valign="bottom" class="zczi"><a href="#" onClick="window.location.reload()" class="zczi">刷新主页</a></td>
    <td width="2%" valign="bottom" class="zczi">|</td>
    <td width="7%" valign="bottom" class="zczi"><a href='newtopic.do?name=<%=session.getAttribute("name") %>&forumid=<%=session.getAttribute("forumId") %>&forumname=<%=session.getAttribute("forumName") %>' class="zczi">发布主题</a></td>
    <td width="2%" valign="bottom" class="zczi">|</td>
  <td width="9%" valign="bottom" class="bzzi"><a href="index.do" class="zczi">返回首页</a></td>
  </tr>
</table>

		
		
			
	  </td>
	</tr>

	<tr>
		<td width="872" height="68" background="images/03.gif">

			<table width="100%"  border="0">
  <tr>
    <td width="22%" height="32">&nbsp;</td>
    <td width="37%"  valign="middle" class="zi" id="bgclock">&nbsp;</td>
    <td width="16%">&nbsp;</td>
    <td width="25%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td><span class="lz">广阔的空间，我们的天地，F论坛！</span></td>
  </tr>
</table>

			
			
	  </td>
		<td width="143">&nbsp;				  </td>
	</tr>	

</table>
<!-- 结束 -->

<!-- 主题内容 -->

<table id="__01" width="1002"  border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td background="images/r1.gif" width="1002" height="33"><table width="100%"  border="0" height="33">
          <tr>
            <td width="75%">&nbsp;</td>
            <td width="25%" class="whitezi"> <!--  a href="javascript:history.go(-1)" class="whitezi">返回</a--> <a href="topiclist.do" class="whitezi">返回主题页面</a></td>
          </tr>
        </table>
			
			</td>
	</tr>
	<!--tr>
		<td width="1002" height="189" valign="top" background="images/r2.gif">	  <table width="100%" height="171"  border="0">
          <tr>
            <td height="28" colspan="2">&nbsp;</td>
            <td width="7%">&nbsp;</td>
            <td width="21%" valign="baseline"></td>
            <td width="15%">&nbsp;</td>
            <td width="30%" class="fyzi"></td>
			<td><a href="#" class="hfzi">回复</a></td>
          </tr>
          <tr>
            <td height="71" colspan="2" align="center"><img src="images/touxiang/0.gif" width="60" height="60"></td>
            <td colspan="5" rowspan="2" valign="top" class="zczi">rwerew</td>
          </tr>
          <tr>
            <td height="21" colspan="2">rewrew&nbsp;</td>
          </tr>
          <tr>
            <td width="9%" align="center" valign="top">&nbsp;</td>
            <td width="8%" valign="top"><img src="images/xq/" width="20" height="20"></td>
            <td colspan="4">&nbsp;</td>
            <td width="10%">&nbsp;</td>
          </tr>
        </table></td>
	</tr-->
	<app:displayResponse/>

</table>

<!-- 页角 -->

<%@include file="footer.jsp" %>
</body>
</html>