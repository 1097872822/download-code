<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="../error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript>
var LastCount =0;
function CountStrByte(Message,Total,Used,Remain){
 var ByteCount = 0;
 var StrValue  = Message.value;
 var StrLength = Message.value.length;
 var MaxValue  = Total.value;
if(LastCount != StrLength){
	for (i=0;i<StrLength;i++){
	ByteCount  = (StrValue.charCodeAt(i)<=256) ? ByteCount + 1 : ByteCount + 2;
      if (ByteCount>MaxValue) {
            Message.value = StrValue.substring(0,i);
	alert("留言内容最多不能超过 " +MaxValue+ " 个字节！\n注意：一个汉字为两字节。");
         ByteCount = MaxValue;
         break;
      }
	}
   Used.value = ByteCount;
   Remain.value = MaxValue - ByteCount;
   LastCount = StrLength;
 }
}
</SCRIPT>
<script type="text/javascript">
<!--
function submit2(){
	if(document.all.title.value.length==0){
		alert("请填写新闻标题!");
		return false;
	}
	if(document.all.author.value.length==0){
		alert("请填写新闻作者!");
		return false;
	}
	if(document.all.content.value.length==0){
		alert("请填写新闻内容!");
		return false;
	}
	document.all.form1.submit();
	return true;
}
//-->
</script>
</head>

<body background="../images/ht04.gif" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="form1" method="post" action="insert.jsp">
          
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="1" class="tableBorder">
  <%
  String test=(String)session.getAttribute("test");
 if(test!=null){ 
  %>
  <tr>
    <th height="50" colspan="2" class="lunzi">恭喜，添加成功！</th>
  </tr>
  <%} 
 session.removeAttribute("test");
%>
  <tr> 
    <th colspan="2" class="whitezi"><p class="lunzi">&nbsp;</p>
      <p class="lunzi">&nbsp;</p>
      <p>&nbsp;</p></th>
  </tr>
  <tr> 
    <td width="17%" height="22" align="right"><span class="lunzi">名&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span></td>
    <td width="83%"><font color="navy" face="Arial">
      <input name="title" size="37">
    </font>  
      </td>
  </tr>
  <tr> 
    <td height="22" align="right" class="lunzi"><span class="lunzi">发&nbsp;&nbsp;&nbsp;&nbsp;布&nbsp;&nbsp;&nbsp;&nbsp;者</span>：</td>
    <td><input name="author" type="text" id="author" value="未知" size="37"></td>
  </tr>
  <tr>
    <td height="22" align="right" class="lunzi">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;容：</td> 
    <td align="left"><textarea name="content" cols="70" rows="10" wrap="VIRTUAL" id="content" onkeydown="CountStrByte(this.form.content,this.form.total,this.form.used,this.form.remain);" onkeyup="CountStrByte(this.form.content,this.form.total,this.form.used,this.form.remain);"></textarea> </td>
  </tr>
             <tr> 
      <td colspan="2" id="upid"> </td>
           </tr>
		   <tr><td colspan="2" align="center" class="lunzi">
		  最多允许 <input name="total" type="text" disabled class="noborder" id="total"  value="1600" size="4" class="zczi"> 
           个字节 已用字节：&nbsp;
<input name="used" type="text" disabled class="noborder" id="used"  value="0" size="4" class="zczi">
 剩余字节：<input name="remain" type="text" disabled class="noborder" id="remain" value="1600" size="4" class="zczi">

		   
		   </td>
		   </tr>
  <tr>
    <td height="22" align="right">&nbsp;</td>
    <td align="left" class="forumRow">
      <input type=button class=button value="提 交" name=Submit onClick="return submit2();">
 　<input type=reset name=Submit2 class=button onClick="return reset();">
      </td>
  </tr>
</table>
</form>
</body>
</html>