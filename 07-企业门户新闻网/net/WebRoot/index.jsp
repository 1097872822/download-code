<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wsy.selectsql,java.util.*,com.wsy.news,com.wsy.product,java.sql.*"%>
    	<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业门户网站首页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table id="__01" width="1025" height="813" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td rowspan="11">			<img src="images/1.gif" width="108" height="812" alt=""></td>
		<td colspan="4">
			<img src="images/2.gif" alt="" width="778" height="60" border="0" usemap="#Map5"></td>
		<td rowspan="11">
			<img src="images/3.gif" width="138" height="812" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="60" alt=""></td>
	</tr>
	<tr>
		<td rowspan="3">
			<img src="images/4.gif" alt="" width="234" height="276" border="0" usemap="#Map"></td>
		<td colspan="3">
			<img src="images/5.gif" alt="" width="544" height="256" border="0" usemap="#Map4"></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="256" alt=""></td>
	</tr>
	<tr>
		<td rowspan="4">
			<img src="images/6.gif" width="139" height="157" alt=""></td>
		<td colspan="2">
			<img src="images/7.gif" width="405" height="15" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="15" alt=""></td>
	</tr>
	
	<tr>
		<td colspan="2" rowspan="2" width="405" height="20"><table width="100%"  border="0"  background="images/17.gif" height="20">
          <%Collection temp2=sql.selectNews();
		  Iterator it2=temp2.iterator();
		 	while(it2.hasNext()){
		 		news news=(news)it2.next();%>
		  <tr>
            <td width="4%" class="zczi">&nbsp;</td>
          <td width="96%" class="zczi"><%=news.getTitle() %></td>
		  </tr>
		  <%}%>
        </table>
	  </td>
		<td>
			<img src="images/分隔符.gif" width="1" height="5" alt=""></td>
	</tr>


	<tr>
		<td rowspan="5" valign="bottom">
			<img src="images/9.gif" width="234" height="415" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="115" alt=""></td>
	</tr>
	<tr>
		<td colspan="2">
			<img src="images/10.gif" alt="" width="405" height="22" border="0" usemap="#Map2"></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="22" alt=""></td>
	</tr>
	<tr>
		<td colspan="3">
			<img src="images/11.gif" width="544" height="33" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="33" alt=""></td>
	</tr>
	<tr>
		<td width="544" height="201" colspan="3" background="images/12.gif" class="zczi">
		
		
		 <!-- 分栏显示 -->
            <%
					//int RowCount = sql.selectCountBusiness();//记录数
					int RowCount=4;
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
						&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/spimg/<%=Rs.getString("img")%>" width="70" height="70">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Rs.getString("name")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%}%>
			  
	  <%}%>	  </td>
		<td>
			<img src="images/分隔符.gif" width="1" height="201" alt=""></td>
	</tr>
	<tr>
		<td colspan="3">
			<img src="images/13.gif" alt="" width="544" height="44" border="0" usemap="#Map3"></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="44" alt=""></td>
	</tr>
	<tr>
		<td colspan="3">
			<img src="images/14.gif" width="777" height="51" alt=""></td>
		<td rowspan="2">
			<img src="images/15.gif" width="1" height="61" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="51" alt=""></td>
	</tr>
	<tr>
		<td colspan="3">
			<img src="images/16.gif" width="777" height="10" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="10" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/分隔符.gif" width="108" height="1" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="234" height="1" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="139" height="1" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="404" height="1" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="1" height="1" alt=""></td>
		<td>
			<img src="images/分隔符.gif" width="138" height="1" alt=""></td>
		<td></td>
	</tr>
</table>
<map name="Map">
  <area shape="rect" coords="84,219,142,235" href="houtai/adminlogin.jsp">
  <area shape="rect" coords="86,66,139,79" href="index.jsp">
  <area shape="rect" coords="85,158,137,174" href="gswh.html">
  <area shape="rect" coords="85,98,140,112" href="qyxw.jsp">
  <area shape="rect" coords="85,126,140,142" href="cpjs.jsp">
  <area shape="rect" coords="83,190,139,202" href="jszc.html">
</map>
<map name="Map2">
  <area shape="rect" coords="304,4,354,18" href="#">
</map>
<map name="Map3">
  <area shape="rect" coords="445,17,491,32" href="#">
</map>
<map name="Map4">
  <area shape="rect" coords="59,11,91,26" href="index.jsp">
  <area shape="rect" coords="388,12,449,25" href="houtai/adminlogin.jsp">
  <area shape="rect" coords="251,9,299,26" href="gswh.html">
  <area shape="rect" coords="108,10,158,26" href="qyxw.jsp">
  <area shape="rect" coords="180,11,229,26" href="cpjs.jsp">
  <area shape="rect" coords="315,11,365,26" href="jszc.html">
</map>
<map name="Map5">
  <area shape="rect" coords="475,35,523,49" href="#" onclick="this.style.behavior='url(#default#homepage)';this.sethomepage('http://localhost/net')">
  <area shape="rect" coords="628,38,676,50" href="mailto:tmoonbook@sina.com">
  <area shape="rect" coords="551,37,602,49" href="javascript:window.external.AddFavorite('http://localhost/net','企业门户')">
</map>
</body>
</html>