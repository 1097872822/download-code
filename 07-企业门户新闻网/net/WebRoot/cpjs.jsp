<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wsy.selectsql,java.util.*,com.wsy.news,com.wsy.product,java.sql.*"%>
    <jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品介绍</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table id="__01" width="1025" height="819" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td rowspan="6">
			<img src="images/cpjs/9.gif" width="108" height="819" alt=""></td>
		<td colspan="2">
			<img src="images/cpjs/1.gif" alt="" width="778" height="62" border="0" usemap="#Map4"></td>
		<td rowspan="6">
			<img src="images/cpjs/2.gif" width="138" height="819" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="62" alt=""></td>
	</tr>
	<tr>
		<td rowspan="2">
			<img src="images/cpjs/3.gif" alt="" width="235" height="271" border="0" usemap="#Map2"></td>
		<td>
			<img src="images/cpjs/4.gif" alt="" width="543" height="255" border="0" usemap="#Map3"></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="255" alt=""></td>
	</tr>
	<tr>
		<td rowspan="2" width="543" height="339" background="images/cpjs/5.gif" class="zczi">
		
		 <!-- 分栏显示 -->
            <%
					//int RowCount = sql.selectCountBusiness();//记录数
					int RowCount=6;
					ResultSet Rs = sql.selectbusiness();
					int HRow = RowCount/2;//行数
					if (RowCount%2>0)
						HRow++;
					for (int i = 0 ;i<HRow;i++){%>		 
						<%
						for (int j=i*2+1;j<=(i+1)*2;j++){
						Rs.absolute(j);
						if (Rs.isAfterLast())
							break;
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/spimg/<%=Rs.getString("img")%>" width="70" height="70">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Rs.getString("name")%>
						<%}%>
			  
	  <%}%>	
	  </td>
		<td>
			<img src="images/分隔符.gif" width="1" height="16" alt=""></td>
	</tr>
	<tr>
		<td rowspan="2">			<img src="images/cpjs/6.gif" width="235" height="417" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="323" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/cpjs/7.gif" alt="" width="543" height="94" border="0" usemap="#Map"></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="94" alt=""></td>
	</tr>
	<tr>
		<td colspan="2">
			<img src="images/cpjs/8.gif" width="778" height="69" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="69" alt=""></td>
	</tr>
</table>

<map name="Map">
  <area shape="rect" coords="439,36,491,51" href="#">
</map>
<map name="Map2">
  <area shape="rect" coords="85,61,138,76" href="index.jsp">
  <area shape="rect" coords="85,94,139,110" href="qyxw.jsp">
  <area shape="rect" coords="84,125,140,138" href="cpjs.jsp">
  <area shape="rect" coords="83,156,138,172" href="gswh.html">
  <area shape="rect" coords="83,218,142,232" href="houtai/adminlogin.jsp">
  <area shape="rect" coords="83,187,140,200" href="jszc.html">
</map>
<map name="Map3">
  <area shape="rect" coords="57,8,90,24" href="index.jsp">
  <area shape="rect" coords="107,10,156,24" href="qyxw.jsp">
  <area shape="rect" coords="177,8,228,23" href="cpjs.jsp">
  <area shape="rect" coords="250,7,298,25" href="gswh.html">
  <area shape="rect" coords="386,9,448,25" href="houtai/adminlogin.jsp">
  <area shape="rect" coords="312,9,364,25" href="jszc.html">
</map>
<map name="Map4">
  <area shape="rect" coords="547,36,594,49" href="#" onclick="this.style.behavior='url(#default#homepage)';this.sethomepage('http://localhost/net')">
  <area shape="rect" coords="624,37,674,50" href="javascript:window.external.AddFavorite('http://localhost/net','企业门户')">
  <area shape="rect" coords="699,39,749,51" href="mailto:tmoonbook@sina.com">
</map>
</body>
</html>