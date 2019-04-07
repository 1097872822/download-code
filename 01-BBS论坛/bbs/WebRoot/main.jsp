<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.struts.bean.*,com.wsy.struts.util.*"%>
<%@include file="taglib.jsp" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

		  <%
		  StringTrans s=new StringTrans();
List listforum=(List)session.getAttribute("listforum");
List listusertotal=(List)session.getAttribute("listusertotal");

 %>
<TABLE WIDTH=808 BORDER=0 CELLPADDING=0 CELLSPACING=0>
	
	<TR>
		<TD WIDTH=808 HEIGHT=92 valign="top" background="images/gl_3_1.gif"><table width="100%" HEIGHT=87  border="0" cellspacing="0" cellpadding="0">
          <html:form action="forumedit.do">
          <tr>
            <td width="29%" height="32">&nbsp;</td>
            <td width="33%">&nbsp;</td>
            <td width="38%" colspan="2">&nbsp;</td>
          </tr>

          <tr>
            <td align="center"><span class="zczi">论坛名：</span>              
            <select name="forumname" style="width:120px ">
            <%for(int i=0;i<listforum.size();i++){
            	ForumBean forum=(ForumBean)listforum.get(i);
            	int id=forum.getId();
            	session.setAttribute("idforum",id+"");
             %>

            <option value=<%=s.tranC(forum.getForumnname()) %>><%=s.tranC(forum.getForumnname()) %></option>
              <%} %>
            </select>            </td>
            <td align="center"><span class="zczi">更换斑竹：</span>             
            <select name="manager" style="width:120px ">
            <%

            for(int i=1;i<listusertotal.size();i++){
            	LoginBean login=(LoginBean)listusertotal.get(i);
            	int id=login.getId();
            	session.setAttribute("idlogin",id+"");
            	
             %>
            <option value=<%=s.tranC(login.getUsername()) %>><%=s.tranC(login.getUsername()) %></option>
            <%} %>
            </select></td>
            <td align="left"><input type="submit" name="Submit" value="提交"></td>
            <td align="left"><a href="#" onClick="window.open('forumadd.do','newwindow', 'height=174, width=274, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">新增专区</a></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
          </html:form>
        </table>
			
			
			
	  </TD>
	</TR>
	<TR>
		<TD>
			<IMG SRC="images/gl_3_2.gif" WIDTH=814 HEIGHT=39 ALT=""></TD>
	</TR>
	<%
	for(int i=0;i<listforum.size();i++){
	ForumBean forum=(ForumBean)listforum.get(i);
	 %>
	<TR>
		<TD WIDTH=808 HEIGHT=34 background="images/gl_3_3.gif"><table width="100%"  height="34" border="0" cellspacing="0" cellpadding="0">
          <tr align="center" class="zczi">
            <td width="16%"><%=forum.getId()%></td>
            <td width="12%"><%=s.tranC(forum.getForumnname()) %></td>
            <%
            if(forum.getManager()!=null){
             %>
            <td width="24%"><%=s.tranC(forum.getManager())%></td>
            <%}else {%>
            <td width="24%">此主题斑竹没有设定</td>
            <%} %>
            <td width="31%"><%=forum.getCreatetime() %></td>
            <td width="17%">
            <a href="#" onClick="window.open('forumndel.do?id=<%=forum.getId() %>&forumname=<%=s.tranC(forum.getForumnname()) %>&manager=<%=forum.getManager() %>','newwindow', 'height=174, width=276, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">删除</a>
            </td>
          </tr>
        </table>
			
			
			
	  </TD>
	</TR>
	<%} %>
</TABLE>
</body>
</html>