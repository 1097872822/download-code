<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="../error.jsp"%>
    <%//out.println(session.getAttribute("ok"));
    if(session.getAttribute("ok")!="ok")
    	response.sendRedirect("adminlogin.jsp");
   		%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>网站后台系统</title>
</head>
<DIV style="PADDING-RIGHT:10px;OVERFLOW-Y:auto;PADDING-LEFT:10px;SCROLLBAR-FACE-COLOR:#ffffff;FONT-SIZE:11pt;PADDING-BOTTOM:0px;SCROLLBAR-HIGHLIGHT-COLOR:#ffffff;OVERFLOW:auto;WIDTH:510px;SCROLLBAR-SHADOW-COLOR:#919192;COLOR:blue;SCROLLBAR-3DLIGHT-COLOR:#ffffff;LINE-HEIGHT:100%;SCROLLBAR-ARROW-COLOR:#919192;PADDING-TOP:0px;SCROLLBAR-TRACK-COLOR:#ffffff;FONT-FAMILY:瀹嬩綋;SCROLLBAR-DARKSHADOW-COLOR:#ffffff;LETTER-SPACING:1pt;HEIGHT:200px;TEXT-ALIGN:left">
<frameset rows="118,*" frameborder="NO" framespacing="0">
	<frame src="welcome.jsp" noresize>
	<frameset cols="200,*" frameborder="NO" framespacing="0">
		<frame src="menu.jsp" name=hello1 scrolling="NO" noresize>
		<frame src="news.jsp" name=hello scrolling="yes">
	</frameset>
</frameset><noframes></noframes>
</div>
</html>