<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@include file="taglib.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="804"  border="0" align="center"  cellpadding="0" cellspacing="0" id="__01">
	<tr>
		<td width="804" height="80" valign="top" background="images/um_01.gif">
			<table width="100%" height="80" border="0">
              <tr>
                <td width="33%">&nbsp;</td>
                <td width="27%"><html:errors/></td>
                <td width="40%">&nbsp;</td>
              </tr>
              <html:form action="usersearch.do">
              <tr>
                <td height="30" valign="bottom"><div align="right"><span class="zczi">请输入您要搜索的用户名</span>：</div></td>
                <td valign="bottom"><input name="name" type="text" size="30"></td>
                <td><input type="submit" name="Submit" value="提交"></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              </html:form>
          </table></td>
	</tr>
	<tr>
		<td width="804" height="40" valign="top" background="images/um_02.gif"><table width="100%" height="38" border="0">
          <tr align="center" >
            <td width="16%" class="hczi">头像</td>
            <td width="11%" class="hczi">用户名</td>
            <td width="24%" class="hczi">级别</td>
            <td class="hczi">编辑</td>
            <td width="17%" class="hczi">删除</td>
          </tr>
      </table>	  </td>
	</tr>
	
	<!--  tr>
		<td width="804" height="67" valign="top" background="images/um_03.gif">
			
			
			<table width="100%"  border="0" height="67">
              <tr>
                <td width="16%" align="center" valign="top"><img src="images/touxiang/0.gif" width="60" height="58"></td>
                <td width="11%">&nbsp;</td>
                <td width="24%">&nbsp;</td>
                <td width="32%">&nbsp;</td>
                <td width="17%">&nbsp;</td>
              </tr>
            </table></td>
	</tr-->
	<% 

	if(session.getAttribute("listuser")!=null)
	//所有用户结果集
	%>
	<app:displayUsermanager/>
	<%
	if(session.getAttribute("listsearchname")!=null){
	//查询用户结果集
	%>
	<app:displaySearchUser/>
	<%
	//放入5个空白的行
	for(int i=0;i<5;i++){
	 %>
	<tr>
		<td width="804" height="67" valign="top" background="images/um_03.gif">&nbsp;
			
		</td>
	</tr>
	<%} %>
	<%} 
	session.invalidate();
	
	%>
</table>
</body>
</html>