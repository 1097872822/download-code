<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"import="com.wsy.selectsql,java.util.*,com.wsy.news,com.wsy.product,java.sql.*"%>
    <jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE>企业新闻</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<TABLE WIDTH=1024 BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD ROWSPAN=6>
			<IMG SRC="images/qyxw/11.gif" WIDTH=108 HEIGHT=812 ALT=""></TD>
		<TD COLSPAN=3>
			<IMG SRC="images/qyxw/1.gif" ALT="" WIDTH=778 HEIGHT=94 border="0" usemap="#Map2"></TD>
		<TD ROWSPAN=6>
			<IMG SRC="images/qyxw/2.gif" WIDTH=138 HEIGHT=812 ALT=""></TD>
	</TR>
	<TR>
		<TD ROWSPAN=2>
			<IMG SRC="images/qyxw/3.gif" ALT="" WIDTH=234 HEIGHT=242 border="0" usemap="#Map"></TD>
		<TD COLSPAN=2>			<img src="images/qyxw/4.gif" width=544 height=221 alt=""></TD>
	</TR>
	<TR>
		<TD ROWSPAN=3>
			<IMG SRC="images/qyxw/5.gif" WIDTH=142 HEIGHT=433 ALT=""></TD>
		<TD>
			<IMG SRC="images/qyxw/6.gif" WIDTH=402 HEIGHT=21 ALT=""></TD>
	</TR>
	<TR>
		<TD ROWSPAN=2>
			<IMG SRC="images/qyxw/7.gif" WIDTH=234 ALT=""></TD>
		<TD WIDTH=402 height="20">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="20">
              <%
              if(sql.selectCountBusiness()>=15){
              Collection temp2=sql.selectNewsforqyxw();
		  Iterator it2=temp2.iterator();
		 	while(it2.hasNext()){
		 		news news=(news)it2.next();
               %>
			  <tr>
                <td width="5%" height="20" valign="top"  class="">&nbsp;</td>
              <td width="95%" valign="middle" class="zczi"><%=news.getTitle() %></td>
			  </tr>
              <%} }
              else{
              for(int i=0;i<15;i++){
              %>
              <tr align="left">
                <td height="20" colspan="2" valign="top" background="images/qyxw/12.gif" class="zczi">&nbsp;&nbsp;&nbsp;&nbsp;目前没有添加新闻</td>
              </tr>
              <%} }%>
          </table></TD>
	</TR>
	<TR>
		<TD>
			<IMG SRC="images/qyxw/9.gif" ALT="" WIDTH=402 HEIGHT=53 border="0" usemap="#Map3"></TD>
	</TR>
	<TR>
		<TD COLSPAN=3>
			<IMG SRC="images/qyxw/10.gif" WIDTH=778 HEIGHT=64 ALT=""></TD>
	</TR>
</TABLE>
<map name="Map">
  <area shape="rect" coords="86,30,140,45" href="index.jsp">
  <area shape="rect" coords="84,62,140,77" href="qyxw.jsp">
  <area shape="rect" coords="85,93,140,107" href="cpjs.jsp">
  <area shape="rect" coords="85,125,137,140" href="gswh.html">
  <area shape="rect" coords="84,155,140,169" href="jszc.html">
  <area shape="rect" coords="83,186,142,202" href="houtai/adminlogin.jsp">
</map>
<map name="Map2">
  <area shape="rect" coords="548,71,599,84" href="jszc.html">
  <area shape="rect" coords="413,71,462,84" href="cpjs.jsp">
  <area shape="rect" coords="622,71,682,84" href="houtai/adminlogin.jsp">
  <area shape="rect" coords="485,71,532,85" href="gswh.html">
  <area shape="rect" coords="343,69,391,84" href="qyxw.jsp">
  <area shape="rect" coords="294,69,325,84" href="index.jsp">
  <area shape="rect" coords="545,35,592,50" href="#" onclick="this.style.behavior='url(#default#homepage)';this.sethomepage('http://localhost/net')">
  <area shape="rect" coords="623,36,672,50" href="javascript:window.external.AddFavorite('http://localhost/net','企业门户')">
  <area shape="rect" coords="697,37,747,49" href="mailto:tmoonbook@sina.com">
</map>
<map name="Map3">
  <area shape="rect" coords="301,31,351,44" href="#">
</map>
</BODY>
</HTML>