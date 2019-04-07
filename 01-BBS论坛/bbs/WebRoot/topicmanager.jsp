<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.struts.bean.*,com.wsy.struts.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主题管理</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<script src="JS/onclock.JS"></script>
<script src="JS/menu.JS"></script>
<!--上述两个JavaScript代码包括下拉菜单显示和隐藏的函数-->
<div class=menuskin id=popmenu
      onmouseover="clearhidemenu();highlightmenu(event,'on')"
      onmouseout="highlightmenu(event,'off');dynamichide(event)" style="Z-index:100;position:absolute;"></div>
	  
	  
<table width="810" height="75" border="0" cellpadding="0" cellspacing="0" id="__01">
	<tr>
		<td>
			<img src="images/zt2_01.gif" width="810" height="38" alt=""></td>
	</tr>
	<%
	List listtotaltopic=(List)session.getAttribute("listtotalTopic");
	for(int i=0;i<listtotaltopic.size();i++){
	 TopicListBean topic=(TopicListBean)listtotaltopic.get(i);
	 StringTrans s=new StringTrans();
	 %>
	<tr>
		<td width="810" height="37" valign="top" background="images/zt2_02.gif"><table width="100%" height="37" border="0">
          <tr align="center" class="zczi">
            <td width="16%"><img src="images/xq/<%=topic.getXq() %>"></td>
            <td width="12%"><%=topic.getForumname() %></td>
            <td width="12%"><%=topic.getAuthor() %></td>
            <!-- td width="11%"><a  onmouseover=showmenu(event,sysmenu) onmouseout=delayhidemenu() class='navlink' style="CURSOR:hand" ><!--%=s.tranC(topic.getTitle()) %></a></td><!--设置其鼠标的onmouseover和onmouseout事件分别用于控制鼠标移入导航链接上时显示下拉菜单，和鼠标移出导航链接上时隐藏下拉菜单-->
            <td width="11%"><a class='navlink' style="CURSOR:hand" ><%=s.tranC(topic.getTitle()) %></a></td>
            <input type='hidden' name="content" value="<%=topic.getContent() %>"/>
            <td width="32%"><%=topic.getSubmittime() %></td>
            <td width="17%"><a href="#" onclick="window.open('topicdel.do?id=<%=topic.getId() %>&name=<%=topic.getTitle() %>','newwindow', 'height=174, width=274, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">删除</a></td>
          </tr>
        </table>
			
			
		</td>
	</tr>
	<%} %>
</table>
</body>
</html>