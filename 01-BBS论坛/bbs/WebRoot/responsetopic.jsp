<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.struts.bean.*,com.wsy.struts.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复主题管理</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<table id="__01" width="807" height="79" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<img src="images/hf_01.gif" width="810" height="38" alt=""></td>
	</tr>
	<%
	StringTrans s=new StringTrans();
	
	List listallrecord=(List)session.getAttribute("listallrecord");
	for(int i=0;i<listallrecord.size();i++){
	ResponseBean r=(ResponseBean)listallrecord.get(i);
	 %>
	<tr>
		<td background="images/hf_02.gif" width="810" height="37"><table width="100%" height="37" border="0">
          <tr align="center" class="zczi">
            <td width="16%"><img src="images/xq/<%=r.getXq() %>" width="20" height="20"></td>
            <td width="12%"><%=s.tranC(r.getTopicname()) %></td>
            <td width="12%"><%=s.tranC(r.getAuthor()) %></td>
            <td width="11%"><%=s.tranC(r.getTitle()) %></td>
            <td width="32%"><%=r.getSubmittime() %></td>
            <td width="17%"> <a href="#" onclick="window.open('responsetopicdel.do?id=<%=r.getId() %>&name=<%=s.tranC(r.getTitle()) %>','newwindow', 'height=174, width=276, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">删除</a></td>
          </tr>
        </table>			
		
		</td>
	</tr>
	<%} %>
</table>

</body>

</html>