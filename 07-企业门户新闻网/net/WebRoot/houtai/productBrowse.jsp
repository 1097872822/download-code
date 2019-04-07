<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wsy.product" errorPage="../error.jsp"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/>
<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/>
<jsp:useBean id="down" class="com.wsy.DownTable" scope="page"/>
<%@ page language="java" import="java.util.*"%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.wsy.news;"%>
<html>
<head>
<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function Jumping(){
	document.PageForm.submit();
	return;
}
function gotoPage(pagenum){
	document.PageForm.current.value=pagenum;
	document.PageForm.submit();
	return;
}

</script>

</head>
<body background="../images/ht044.gif" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%
int curPage;
int current;
int MaxPage,MinPage;
down.setpageSize(10);
MaxPage=down.getTotalPage();
MinPage=1;
if(request.getParameter("current")==null){
	current=1;
}
else{
	current=Integer.parseInt(request.getParameter("current"));	
}

down.setCurrentPage(current);
if(current>MaxPage){
	current=MaxPage;
	//out.println("test1");
}
if(current<MinPage){
	current=MinPage;
	//out.println("test2");
}

//out.println("pagesize"+down.getPageSize());
//out.println("totalpage"+down.getTotalPage());
//out.println("rows"+down.getRows());
//out.println("maxpage"+MaxPage);
//out.println("minpage"+MinPage);
//out.println("current"+current);

%>

<div align="center">
<p>&nbsp;</p>
<p class="lunzi">&nbsp;</p>
<form action="productBrowse.jsp" name="PageForm" method="post">
<table width=85% border="1" cellspacing="0" bordercolor="#FFFFFF">
	<tr bgcolor="#D4E7F8">
		<td width=19%><div align="center" class="whitezi"><strong>商品名称</strong></div></td>
		<td colspan="3"><div align="center" class="whitezi"><strong>商品描述</strong></div></td>
	  <td width=19% colspan="2"><div align="center" class="whitezi"><strong>操作</strong></div></td>
	</tr>


<%

Collection temp=sql.selectBusinessFy(current-1);
Iterator it=temp.iterator();
int count=0;
while(it.hasNext())
{
	product product=(product)it.next();
	if(count%2==0)
	out.println("<tr bordercolor='#FFFFFF' bgcolor='#E4EFFA'>");
	else
	out.println("<tr bgcolor='FFFFFF' bordercolor='#E4EFFA'>");
	out.println("<td><div align='center' class='zczi'>"+product.getName()+"</td>");
	out.println("<td colspan='3'><div align='center'  class='zczi'>"+product.getMsg()+"</div></td>");
	out.println("<td><div align='center'><a href='#' onclick=window.open('productDelPage.jsp?catid="+product.getId().trim()+"&name="+product.getName().trim()+"','newwindow','width=276,height=174,top=400,left=500')>删除</a></td>");
	out.println("<td><div align='center'><a href='#' onclick=window.open('productview.jsp?catid="+product.getId().trim()+"','newwindow','width=600,height=350,top=300,left=300')>查看</a></td>");
	out.println("</tr>");
	count++;
}
%>
	<%
	if(down.getTotalPage()!=1){
	
	 %>
	<tr bgcolor="#6699FF">
		<td width=19%><div align="center" class="whitezi"><a href="javascript:gotoPage(1)">首页</a></div></td>
		<td width=22%><div align="center" class="whitezi"><a href=javascript:gotoPage(<%=down.getCurrentPage()-1 %>)>上一页</a></div></td>
	  <td width=22% align="center"><span class="whitezi"><a href=javascript:gotoPage(<%=down.getCurrentPage()+1 %>)>下一页</a></span></td>
      <td width=18% align="center"><span class="whitezi"><a href=javascript:gotoPage(<%=down.getTotalPage() %>)>尾页</a></span></td>
      <td width=19% colspan="2"><div align="center" class="whitezi">
        <select name="current" onChange="Jumping()">
        <%for(int i=1;i<=down.getTotalPage();i++){ 
        	if(i==down.getCurrentPage()){
        %>
        <option selected value=<%=i %>><%=i %></option>
        <%}else{ %>
        <option value=<%=i %>><%=i %></option>
        <%}} %>
        </select>
      </div></td>
	   
	</tr>
	<%} %>
  </table>
</form>
</body>
</html>
