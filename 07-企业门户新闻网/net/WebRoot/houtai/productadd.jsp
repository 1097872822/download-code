<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wsy.category" errorPage="../error.jsp"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page"/>   
<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page"/> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {font-size: 12px}
-->
</style>
<SCRIPT>
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
     function ok()
     {
     	if(document.form2.name.value.length==0){
			alert("请填写商品名称!");
			return false;
		}
		if(document.form2.ms.value.length==0){
			alert("请填写商品描述!");
			return false;
		}
		if(document.form1.file.value.length==0){
			alert("请选择上传文件!");
			return false;
		}
			 //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression=/[^\u4E00-\u9FA5]/; 
	var objExp=new RegExp(Expression);
		if(objExp.test(document.form1.file.value)==false){
			alert("请将上传图片名称修改为英文名称!");
			return false;
		}
       var name = form2.name.value;
		var category=form2.category.value;
      var ms= form2.ms.value;

       form1.action=encodeURI("save.jsp?name="+name+"&category="+category+"&ms="+ms);
       //window.location.href="save.jsp?name="+name+"&category="+category+"&ms="+ms;
       form1.submit(); 
     }
     </SCRIPT>
<SCRIPT language=javascript>
						//通过下拉列表选择头像时应用该函数
						function showlogo(){
						document.form1.img.src=document.form1.file.value;
						}
</SCRIPT>
</head>
<body background="../images/ht042.gif" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<div align="center">
  <table width="549" border="0">
    <tr>
      <td><strong>
      <div align="center" class="lunzi">
        <p>&nbsp;</p>
        <p>&nbsp;</p>
      </div>
      </strong></td>
    </tr>
  </table>
</div>
<%

String catid=request.getParameter("catid");
String name=request.getParameter("name");
String content=request.getParameter("content");
//out.println(catid);
//out.println(a.tranC(content));
session.setAttribute("catid",catid);
%>
<div align="center">

  <table width="549"  border="0">
  <form action="" name="form2"/>
    <tr>
      <td width="91" class="lunzi">商品名称：</td>
      <td colspan="2"><input name="name" type="text" size="34" maxlength="10"></td>
    </tr>

    <tr>
      <td class="lunzi">商品类别：</td>
      <td colspan="2"><select name="category" style="width:200px ">
          <%
    Collection temp=sql.selectCategoryAll();
    Iterator it=temp.iterator();
    while(it.hasNext()){
    	category category=(category)it.next();
     %>
     <option value=<%=category.getCategoryname() %>><%=category.getCategoryname() %></option>
     <%} %>
      </select></td>
    </tr>
    <tr>
      <td class="lunzi">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
      <td colspan="2" rowspan="3"><textarea name="ms" cols="40" rows="5" onkeydown="CountStrByte(this.form.ms,this.form.total,this.form.used,this.form.remain);" onkeyup="CountStrByte(this.form.ms,this.form.total,this.form.used,this.form.remain);"></textarea>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
       <tr>
      <td colspan="6" align="center" valign="top" class="lunzi"> 最多允许 <input name="total" type="text" disabled class="noborder" id="total"  value="100" size="4" class="zczi"> 
           个字节 已用字节：&nbsp;
<input name="used" type="text" disabled class="noborder" id="used"  value="0" size="4" class="zczi">
 剩余字节：<input name="remain" type="text" disabled class="noborder" id="remain" value="100" size="4" class="zczi"></td>
    </tr>
	</form>
    <form name="form1" method="post" action="save.jsp" ENCTYPE="multipart/form-data"> 
		 
	    <tr>
      <td rowspan="2" valign="top" class="lunzi">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</td>
    </tr>
      
	    <tr>
        	<td width="213" height="78" valign="top"><input type="file" name="file" onChange="showlogo()"></td>
			<td width="231" valign="top"><img src="../images/spimg/32.gif" width="70" height="70" name="img"></td>
    	</tr>
	
	    <tr>
      <td rowspan="2">&nbsp;</td>
    </tr>
	    <tr>
      <td colspan="2"><input type="button" name="Submit" value="提交" onclick="ok()"></td>
    </tr>
	</form>
  </table>

</div>
</body>
</html>