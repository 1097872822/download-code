<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
function mycheck(){
	if (form1.goodsName.value==""){
		alert("请输入商品名称！");form1.goodsName.focus();return;
	}
	if (form1.picture.value==""){
		alert("请输入图片文件的路径！");form1.picture.focus();return;
	}
	if (form1.price.value==""){
		alert("请输入商品的定价！");form1.price.focus();return;
	}
	if (isNaN(form1.price.value)){
		alert("您输入的定价错误，请重新输入！");form1.price.value="";form1.price.focus();return;
	}	
	if (form1.introduce.value==""){
		alert("请输入商品简介！");form1.introduce.focus();return;
	}		
	form1.submit();
}
</script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="15"></td>
  </tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">添加商品信息</td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="top">
              <form action="goodsAdd.lzw" method="post" name="form1">
                <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2" bordercolordark="#FFFFFF">
                  <tr>
                    <td height="27">&nbsp;所属大类：</td>
                    <td width="31%" height="27">&nbsp;
                        <script language="javascript">
					function F_super(val){
						window.location.href="goodsAdd.lzw?superSelect="+val;   //实现级联菜单
					}
					</script>
                    <select name="supertype" class="textarea" 
                    	id="supertype" onChange="F_super(this.value)">
					  <c:forEach varStatus="n" var="name" items="${typeNameList }">
					  <option value="${n.index }" 
					  <c:choose>
					  <c:when test="${n.index==superSelect }">selected</c:when>
					  <c:when test="${superSelect==null }">
					  	<c:set var="superSelect" value="${n.index }"/>
					  </c:when>
					  </c:choose>>${name }</option>
					  </c:forEach>
                    </select></td>
                    <td width="13%" height="27"> &nbsp;所属小类：</td>
                    <td width="42%" height="27">&nbsp;
                      <select name="typeId" class="textarea" id="typeId">
					  <c:forEach var="type" items="${typeList[typeNameList[superSelect]] }">
					   <option value="${type.id.subId }">${type.id.subType }</option>
					   </c:forEach>
                      </select></td>
                  </tr>
                  <tr>
                    <td width="14%" height="27">&nbsp;商品名称：</td>
                    <td height="27" colspan="3">&nbsp;
                        <input name="goodsName" type="text"
                         class="Sytle_text" id="bookID2" size="50">&nbsp;&nbsp; </td>
                  </tr>
                  <tr>
                    <td height="41">&nbsp;图片文件：</td>
                    <td height="41">&nbsp;
                        <input name="picture" type="text" class="Style_upload" id="picture">                    </td>
                    <td height="41">&nbsp;定　　价：</td>
                    <td height="41">&nbsp;
                        <input name="price" type="text" class="Sytle_text" id="price">
                    (元)</td>
                  </tr>
                  <tr>
                    <td height="45">&nbsp;是否新品：</td>
                    <td>&nbsp;
                        <input name="newGoods" type="radio" class="noborder" value="1" checked>是
                    <input name="newGoods" type="radio" class="noborder" value="0">否</td>
                    <td>&nbsp;是否特价：</td>
                    <td><input name="sale" type="radio" class="noborder" value="1" checked>是
                      <input name="sale" type="radio" class="noborder" value="0">否</td>
                  </tr>
                  <tr>
                    <td height="103">&nbsp;商品简介：</td>
                    <td colspan="3"><span class="style5">&nbsp; </span>
                        <textarea name="introduce" cols="60" rows="5"
                         class="textarea" id="introduce"></textarea></td>
                  </tr>
                  <tr>
                    <td height="38" colspan="4" align="center">
                      <input name="Button" type="button" class="btn_grey"
                       value="保存" onClick="mycheck()">&nbsp;
                    <input name="Submit2" type="reset" class="btn_grey" value="重置">&nbsp;
                    <input name="Submit3" type="button" class="btn_grey" value="返回"
                     onClick="JScript:history.back(-1)"></td>
                  </tr>
                </table>
            </form></td>
          </tr>
        </table>
        <table width="100%" height="46"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td background="images/manage_06.gif">&nbsp;</td>
          </tr>
      </table></td>
  </tr>
</table>
