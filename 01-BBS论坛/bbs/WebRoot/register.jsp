<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/style.css" rel="stylesheet" type="text/css">
	<title>注册页面</title>
	<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	<script type="text/javascript">
<!--
function submit2(){
	if(document.all.name.value.length==0){
		alert("请填写用户名!");
		return false;
	}
	if(document.all.password.value.length==0){
		alert("请填写密码!");
		return false;
	}
	document.all.loginForm.submit();
	return true;
}
function SelectSubmit(){
	document.all.registerForm.submit();
	return true;
}
function submit1(){
	if(document.all.username.value.length==0){
	alert("请填写用户名!");
		return false;
	}
	if(document.all.realname.value.length==0){
		alert("请填写真实姓名!");
		return false;
	}
	if(document.all.password1.value.length==0){
		alert("请填写密码!");
		return false;
	}
	if(document.all.password2.value.length==0){
		alert("请填写密码!");
		return false;
	}

	if(document.all.tel.value.length==0){
		alert("请填写联系电话!");
		return false;
	}
	if(document.all.mail.value.length==0){
		alert("请填写电子邮件!");
		return false;
	}
	if(document.all.lxdz.value.length==0){
		alert("请填写联系地址!");
		return false;
	}
	if(document.all.password1.value!=document.all.password2.value){
		alert("两次密码输入不相符！");
		return false;
	}
	if(!checkemail(registerForm.mail.value)){
		alert("您输入Email地址不正确!");registerForm.mail.focus();return false;
	}

	 //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression=/[^\u4E00-\u9FA5]/; 
	var objExp=new RegExp(Expression);
	if(objExp.test(registerForm.username.value)!=true){
		alert("用户名只可以是英文名称!");
		return false;
	}
	
	document.all.registerForm.submit();
	return true;
	
}

function checkemail(email){
	var str=email;
	var Expression=/\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/; 
	var objExp=new RegExp(Expression);
	if(objExp.test(str)==true){
		return true;
	}else{
		return false;
	}
}	
function reset(){
	document.all.registerForm.reset();
}
function checkusername(){
	 //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression=/[^\u4E00-\u9FA5]/; 
	var objExp=new RegExp(Expression);
	if(objExp.test(registerForm.username.value)!=true){
		alert("用户名只可以是英文名称!");
		return false;
	}
	else{
		window.location.href='checkuser.do?username='+registerForm.username.value
		return true;
	}
}


//-->
</script>
	<script src="JS/onclock.JS"></script>
	<!--用于显示时间的JavaScript-->
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="clockon(bgclock)">
	<!--  %@include file="top.jsp" %-->
	<%
				if (session.getAttribute("registersucess") != null
				&& session.getAttribute("registersucess").equals("1")) {
			System.out.println(session.getAttribute("registersucess")
			.equals("1"));
	%>
	<script>
javascript:window.alert("注册成功")
window.location.href="index.do";
</script>
	<%
		}
		session.removeAttribute("registersucess");
	%>

	<%
				if (session.getAttribute("checkuser") != null
				&& session.getAttribute("checkuser").equals("1")) {
			System.out.println(session.getAttribute("checkuser")
			.equals("1"));
	%>
	<script>
javascript:window.alert("用户名重复")
</script>
	<%
	}
	%>
	<!-- 登录部分 -->


	<table id="__01" width="1015" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="2">
				<img src="images/010.gif" alt="" name="Image1" width="1002"
					height="121" id="Image1">
			</td>
		</tr>
		<tr>
			<td colspan="2" height="36" background="images/02.gif">
				<table width="100%" height="33" border="0">
					<tr>
						<td width="6%">
							&nbsp;
						</td>
						<td width="63%">
							[
							<span class="zi"> <%
 		if (request.getAttribute("name") != null)
 		out.println(request.getAttribute("name"));
 	else
 		out.println("游客");
 %> <html:errors /> </span> ]
							<span class="zi"> 欢迎光临</span>
						</td>
						<td width="3%" valign="bottom" class="bzzi">
							<a href="index.do" class="zczi">首页</a>
						</td>
						<td width="1%" valign="bottom" class="zczi">
							|
						</td>
						<td width="6%" valign="bottom" class="zczi">
							<a href="adminlogin.do" class="zczi">版主登录</a>
						</td>
						<td width="1%" valign="bottom" class="zczi">
							|
						</td>
						<td width="6%" valign="bottom" class="zczi">
							<a href="#" class="zczi" onclick="window.location.reload()">刷新主页</a>
						</td>
						<td width="1%" valign="bottom" class="zczi">
							|
						</td>
						<%
							String iflogin = (String) session.getAttribute("i");
							if (iflogin == null) {
						%>
						<td width="7%" valign="bottom" class="zczi">
							<a href="#" onClick="alert('请您首先登录')" class="zczi">发布主题</a>
						</td>
						<%
						} else {
						%>
						<td width="6%" valign="bottom" class="zczi">
							<a
								href="newtopic.do?name=<%=session.getAttribute("name")%>&forumid=<%=session.getAttribute("forumId")%>&forumname=<%=request.getParameter("forumname")%>"
								class="zczi">发布主题</a>
						</td>
						<%
						}
						%>


					</tr>
				</table>




			</td>
		</tr>
		<html:form action="login.do" focus="name">
			<tr>
				<td width="872" height="63" background="images/03.gif">

					<table width="100%" border="0">
						<tr>
							<td width="22%" height="34">
								&nbsp;
							</td>
							<td width="37%" valign="middle" class="zi" id="bgclock">
								&nbsp;
							</td>
							<td width="19%">
								<span class="zi">用户名：</span>
								<input name="name" type="text" size="13">
							</td>
							<td width="22%">
								<span class="zi">密码：</span>
								<input name="password" type="password" size="13">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								<span class="lz">广阔的空间，我们的天地，F论坛！</span>
							</td>
						</tr>
					</table>



				</td>
				<td width="143">
					<img src="images/04.gif" alt="" name="Image2" width="130"
						height="63" border="0" usemap="#Map" id="Image2">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<img src="images/05.gif" alt="" name="Image3" width="1002"
						height="30" id="Image3">
				</td>
			</tr>

		</html:form>
		<%
		if (request.getAttribute("name") != null) {
		%>
		<app:display />
		<%
		}
		%>

	</table>

	<map name="Map">
		<area shape="rect" coords="4,12,52,36" href="#"
			onClick="return submit2();">
		<area shape="rect" coords="59,11,104,36" href="register.do">
	</map>
	<!-- 结束 -->
	<!-- 注册部分 -->
	<table id="__01" width="1002" border="0" cellpadding="0"
		cellspacing="0">
		<html:form action="registermanager.do">
			<tr>
				<td rowspan="2">
					<img src="images/zc_01.gif" width="448" height="484">
				</td>
				<td width="554" height="484" valign="top"
					background="images/zc_02.gif">
					<table width="100%" border="0" height="374">
						<tr>
							<td width="64" class="zczi">
								用 户 名：
							</td>
							<td width="152">
								<%
											if (session.getAttribute("checkuser") != null
											&& session.getAttribute("checkuser").equals("0")) {
								%>
								<input name="username" type="text" size="24" maxlength="10"
									value=<%=session.getAttribute("username")%>>
								<%
								} else {
								%>
								<input name="username" type="text" size="24" maxlength="10">
								<%
								}
								%>
							</td>
							<td colspan="3">
								<span class="style1">* <!--  input type="button"
										name="Submit3" value="检查用户名"
										onClick="window.location.href='checkuser.do?username='+registerForm.username.value"-->
										 <input type="button"
										name="Submit3" value="检查用户名"
										onClick="return checkusername();">
								</span><span class="zi"><html:errors /> </span>
							</td>
						</tr>
						<tr>
							<td class="zczi">
								真实姓名：
							</td>
							<td>
								<input name="realname" type="text" size="24" maxlength="10">
							</td>
							<td colspan="3">
								<span class="style1">*</span>
							</td>
						</tr>
						<tr>
							<td class="zczi">
								密&nbsp;&nbsp;码：
							</td>
							<td>
								<input name="password1" type="password" size="26" maxlength="10">
							</td>
							<td colspan="3">
								<span class="style1">*</span>
							</td>
						</tr>
						<tr>
							<td class="zczi">
								密码确认：
							</td>
							<td>
								<input name="password2" type="password" size="26" maxlength="10">
							</td>
							<td colspan="3">
								<span class="style1">*</span>
							</td>
						</tr>
						<tr>
							<td class="zczi">
								性 &nbsp;&nbsp;别：
							</td>
							<td>
								<input name="sex" type="radio" value="1" checked
									style="border-style:dotted ">
								<span class="zczi">男</span>
								<input type="radio" name="sex" value="2"
									style="border-style:dotted ">
								<span class="zczi">女</span>
							</td>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td class="zczi">
								联系电话：
							</td>
							<td>
								<input name="tel" type="text" size="24" maxlength="15">
							</td>
							<td colspan="3">
								<span class="style1">*</span>
							</td>
						</tr>
						<tr>
							<td>
								<span class="zczi">OICQ号：</span>
							</td>
							<td>
								<input name="qq" type="text" size="24" maxlength="10">
							</td>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td rowspan="2" class="zczi">
								选择头像：
							</td>
							<%
							String tx = (String) session.getAttribute("tx");
							%>
							<td height="34">
								<%
								if (session.getAttribute("tx") == null)
								%>
								<img src="images/touxiang/2.gif" id="img">
								<!--%}else{ %>
            <img src="images/touxiang/<!--%=tx %>" width="60" height="60">
            <!--%} %-->
							</td>
							<td width="57" align="left" class="zczi">
								签名档：
							</td>
							<td width="252" rowspan="2" valign="bottom">
								<textarea name="signature" rows="5"></textarea>
							</td>
							<td width="7">
								&nbsp;
							</td>
						</tr>
						<tr>
							<SCRIPT language=javascript>
						//通过下拉列表选择头像时应用该函数
						function showlogo(){
						document.registerForm.img.src="images/touxiang/"+document.registerForm.tx.options[document.registerForm.tx.selectedIndex].value;
						}
           </SCRIPT>
							<td>
								<select name="tx" class="zczi" style="width:65px "
									onChange="showlogo();">
									<%
									for (int i = 0; i <= 18; i++) {
									%>
									<option value="<%=i%>.gif">
										头像
										<%=i + 1%>
									</option>
									<!-- %
				
				if(i==Integer.parseInt(tx.substring(0,1))){
			%-->
									<%
									}
									%>
								</select>
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td class="zczi">
								E_mail：
							</td>
							<td colspan="4">
								<input name="mail" type="text" size="74" maxlength="50">
								<span class="style1">*</span>
							</td>
						</tr>
						<tr>
							<td class="zczi">
								个人主页：
							</td>
							<td colspan="4">
								<input name="grzy" type="text" size="74" maxlength="50">
							</td>
						</tr>
						<tr>
							<td class="zczi">
								联系地址：
							</td>
							<td colspan="4">
								<input name="lxdz" type="text" size="74" maxlength="50">
								<span class="style1">*</span>
							</td>
						</tr>
						<tr>
							<td height="21" class="zczi">
								&nbsp;
							</td>
							<td colspan="4" class="hczi">
								<html:errors />
							</td>
						</tr>
						<tr>
							<td height="21" class="zczi">
								&nbsp;
							</td>
							<td colspan="4" align="center" class="hczi">
								<input name="Submit" type="button" value="确认提交"
									onClick="return submit1();">
								<input name="Submit2" type="button" value="刷新重置"
									onClick="return reset()">
							</td>
						</tr>
					</table>
					<!--img src="images/zc_03.gif" width="525" height="414" alt=""-->
				</td>
			</tr>
			<!--tr>
		<td width="525" height="49" valign="top" background="images/zc_04.gif">		<table width="100%"  border="0">
          <tr>
            <td height="25" align="right"><input name="Submit" type="button" value="确认提交" onClick="return submit1();"></td>
            <td align="left"><input name="Submit2" type="button" value="刷新重置" onClick="return reset()"></td>
          </tr>
        </table>		  
        </td>
	</tr-->
		</html:form>
	</table>
	<%
	session.removeAttribute("checkuser");
	%>
	<!-- 页脚 -->
	<%@include file="footer.jsp"%>
</body>
</html:html>
