<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.struts.util.*"%>
<%@include file="taglib.jsp" %> 
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %> 
<jsp:useBean id="down" scope="page" class="com.wsy.struts.util.pageBean"/>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<title>论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<script src="JS/onclock.JS"></script><!--用于显示时间的JavaScript-->
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="clockon(bgclock)">
<!-- 登录部分 -->



<table id="__01" width="1002"  border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<img src="images/010.gif" alt="" name="Image1" width="1002" height="121" id="Image1"></td>
	</tr>
	<tr>
		<td colspan="2" height="40" background="images/02.gif"><table width="100%" height="33"  border="0">
  <tr>
    <td width="7%" valign="bottom">&nbsp;</td>
    <td width="57%" valign="bottom">  [  <span class="zi"> <%StringTrans s=new StringTrans();

				if(session.getAttribute("name")!=null){
					
					out.println((String)session.getAttribute("name"));
					}
				else 
					out.println("游客");

			
			%><html:errors/>
			</span> ] <span class="zi">  
            欢迎光临</span>      </td>
    <td width="7%" valign="bottom" class="zczi"><a href="adminlogin.do" class="zczi">管理员登录</a></td>
  <td width="1%" valign="bottom" class="zczi">|</td>
    <td width="6%" valign="bottom" class="zczi"><a href="#" class="zczi" onclick="window.location.reload()">刷新主页</a></td>
    <td width="1%" valign="bottom" class="zczi">|</td>
    <td width="6%" valign="bottom" class="zczi"><a href="newtopic.do?name=<%=session.getAttribute("name") %>&forumid=<%=session.getAttribute("forumId") %>" class="zczi">发布主题</a></td>
    <td width="1%" valign="bottom" class="zczi">|</td>
  <td width="14%" valign="bottom" class="bzzi"><a href="index.do" class="zczi">返回首页</a></td>
  </tr>
</table>	
	  </td>
	</tr>

	<tr>
		<td width="872" height="68" background="images/03.gif">

			<table width="100%"  border="0">
  <tr>
    <td width="22%" height="29">&nbsp;</td>
    <td width="45%" class="zi" id="bgclock">&nbsp;</td>
    <td width="12%">&nbsp;</td>
    <td width="21%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
    <td colspan="2"><span class="lz">广阔的空间，我们的天地，F论坛！</span></td>
    </tr>
</table>	
	  </td>
		<td width="143">&nbsp;				  </td>
	</tr>	
</table>
<!-- 结束 -->


<!-- 主题部分 -->
<%String forumName=(String)session.getAttribute("forumName");
//论坛名称
String forumId=(String)session.getAttribute("forumId");
String name=(String)session.getAttribute("name");
//String pageid=(String)session.getAttribute("pageid");
//String forumid=(String)session.getAttribute("forumid");
//System.out.println(pageid);

 
 		int curPage;
		int pageid;
		int MaxPage=0,MinPage=0;		
		if(session.getAttribute("pageid")!=null){
			
			pageid=Integer.parseInt((String)session.getAttribute("pageid"));
			MaxPage=Integer.parseInt((String)session.getAttribute("maxpage"));
		}
		else{
			pageid=0;
		}
		if(pageid<MinPage){
			pageid=MinPage;

		}
		if(pageid>=MaxPage-1){
			pageid=MaxPage-2;

		}

		//out.println("MaxPage"+MaxPage);
		//out.println("pageid"+pageid);
%>
 
 

<table id="__01" width="1002"  border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="1002" height="33" background="images/lb.gif"><table width="100%"  border="0">
          <tr>
            <td width="6%">&nbsp;</td>
            <td width="8%" align="left" class="whitezi"></td>
            <td width="86%">&nbsp;</td>
          </tr>
        </table>
	  </td>
	</tr>
		<tr>
		<td width="1002" height="27" background="images/lb2.gif"><table width="100%"  border="0">
          <tr>
				<td>&nbsp;</td>
          </tr>
        </table>
	  </td>
	</tr>
	
	<!--  tr>
		<td width="1002" height="37" background="images/zt_02.gif"><table width="100%"  border="0">
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="8%" align="center" valign="middle"><img src="images/xq/daku.gif" width="20" height="20"></td>
            <td width="45%">&nbsp;</td>
            <td width="10%">&nbsp;</td>
            <td width="9%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
          </tr>
        </table>
			</td>
	</tr-->
	
<app:displayTopic/>	<!-- 显示这个论坛中的文章的自定义标签 -->
	
</table>
<% List list=(List)session.getAttribute("List");//如果主题内没有文章，不显示分页
if (!list.isEmpty()) {%>
<table width="1002" height="48" border="0" background="images/lb4.gif">
  <tr>
    <td>
	<table width="1002" border="0">

  <tr>
    <td width="725">&nbsp;</td>
    <td width="41" align="center" class="fyzi"><a href="topicList.do?forumid=<%=forumId%>&name=<%=name%>&forumname=<%=forumName%>&pageid=0" class="fyzi">首页</a></td>
    <td width="53" align="center" class="fyzi"><a href="topicList.do?forumid=<%=forumId%>&name=<%=name%>&forumname=<%=forumName%>&pageid=<%=pageid-1 %>" class="fyzi">上一页</a></td>
    <td width="59" align="center" class="fyzi"><a href="topicList.do?forumid=<%=forumId%>&name=<%=name%>&forumname=<%=forumName%>&pageid=<%=pageid+1 %>" class="fyzi">下一页</a></td>
    <td width="102" align="left" class="fyzi"><a href="topicList.do?forumid=<%=forumId%>&name=<%=name%>&forumname=<%=forumName%>&pageid=<%=MaxPage-1%>" class="fyzi">尾页</a></td>
  </tr>
</table>
</td>
  </tr>
</table>
<%} %>

<!-- 页脚 -->
<%@include file="footer.jsp" %>
</body>
</html>