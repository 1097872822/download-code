<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.news"  errorPage="../error.jsp"%>
    <jsp:useBean id="s" class="com.wsy.StringTrans"/>
    <jsp:useBean id="sql" class="com.wsy.selectsql"/>
    <jsp:useBean id="news" class="com.wsy.news"/>
    <jsp:setProperty property="*" name="news"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

<link href="../CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body background="../images/ht04.gif" bgcolor="#FFFFFF" >
<form name="form1" method="post" action="newsEdittest.jsp">
          
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="1" class="tableBorder">
  <%


  String test=(String)session.getAttribute("test");
 if(test!=null){ 
  %>
  <tr>
    <th colspan="2" class="lunzi">恭喜，修改成功！</th>
  </tr>
  <%} 
 session.removeAttribute("test");
  	String id=request.getParameter("id");
	Collection temp=sql.selectNews(id);
	Iterator it=temp.iterator();
	while(it.hasNext()){
		news newsl=(news)it.next();
%>
<input type="hidden" name="id" value="<%=newsl.getId() %>"/>
  <tr> 
    <th colspan="2" class="whitezi"><p class="lunzi">新闻修改</p>
      <p>&nbsp;</p></th>
  </tr>
  <tr> 
    <td width="17%" height="22" align="right"><span class="lunzi">名&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span></td>
    <td width="83%"><font color="navy" face="Arial">
      <input name="title" size="37" value="<%=newsl.getTitle() %>">
    </font>  
      </td>
  </tr>
  <tr> 
    <td height="22" align="right" class="whitezi"><span class="lunzi">发&nbsp;&nbsp;&nbsp;&nbsp;布&nbsp;&nbsp;&nbsp;&nbsp;者：</span></td>
    <td><input name="author" type="text" id="author" value="<%=newsl.getAuthor() %>" size="37"></td>
  </tr>
  <tr>
    <td height="22" align="right" class="lunzi">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;容：</td> 
    <td align="left"><textarea name="content" cols="70" rows="10" wrap="VIRTUAL" id="content" onkeydown="CountStrByte(this.form.content,this.form.total,this.form.used,this.form.remain);" onkeyup="CountStrByte(this.form.content,this.form.total,this.form.used,this.form.remain);" ><%=newsl.getContent() %></textarea> </td>
  </tr>
             <tr> 
      <td colspan="2" id="upid"> </td>
           </tr>
		   <tr><td colspan="2" align="center" class="lunzi">
		   最多允许 
<input name="total" type="text" disabled class="noborder" id="total"  value="1600" size="4" class="zczi"> 
个字节 已用字节：&nbsp;
<input name="used" type="text" disabled class="noborder" id="used"  value="0" size="4" class="zczi"> 
剩余字节：
<input name="remain" type="text" disabled class="noborder" id="remain" value="1600" size="4" class="zczi">

		   
		   </td>
		   </tr>
  <tr>
    <td height="22" align="right">&nbsp;</td>
    <td align="left" class="forumRow">
      <input type=Submit class=button value="提 交" name=Submit>
 　<input type=reset name=Submit2 class=button value="清 除">
      </td>
  </tr>
</table>
</form>
<%} %>
</body>
</html>