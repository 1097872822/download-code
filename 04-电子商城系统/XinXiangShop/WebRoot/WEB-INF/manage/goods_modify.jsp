<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
function mycheck(){
	if (form1.goodsName.value==""){
		alert("��������Ʒ���ƣ�");form1.goodsName.focus();return;
	}
	if (form1.picture.value==""){
		alert("������ͼƬ�ļ���·����");form1.picture.focus();return;
	}
	if (form1.price.value==""){
		alert("��������Ʒ�Ķ��ۣ�");form1.price.focus();return;
	}
	if (isNaN(form1.price.value)){
		alert("������Ķ��۴������������룡");form1.price.value="";form1.price.focus();return;
	}	
	if (form1.introduce.value==""){
		alert("��������Ʒ��飡");form1.introduce.focus();return;
	}		
	form1.submit();
}
</script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">�޸���Ʒ��Ϣ</td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="top">
              <form action="goodsModify.lzw" method="post" name="form1">
                <table width="100%"  border="0" align="center" cellpadding="-2" cellspacing="-2" bordercolordark="#FFFFFF">
                  <tr>
                    <td height="27">&nbsp;�������ࣺ</td>
                    <td width="31%" height="27">&nbsp;
                        <script language="javascript">
					function F_super(val,val2){
						window.location.href="goodsModify.lzw?superSelect="+val+"&id="+val2;   //ʵ�ּ����˵�
					}
					</script>
					<input id="id" type="hidden" value="${goods.id }" />
					<input id="intime" type="hidden" value="${goods.intime }" />
                    <select name="supertype" class="textarea" id="supertype" onChange="F_super(this.value,${goods.id })">
					  <c:forEach varStatus="n" var="name" items="${typeNameList }">
					  <option value="${n.index }" ${n.index == superSelect?'selected':name == superTypeName?'selected':'' }>${name }</option>
					  </c:forEach>
                    </select></td>
                    <td width="13%" height="27"> &nbsp;����С�ࣺ</td>
                    <td width="42%" height="27">&nbsp;
                      <select name="typeId" class="textarea" id="typeId">
					  <c:forEach var="type" items="${superTypeName!=null?typeList[superTypeName]:superSelect != null?typeList[typeNameList[superSelect]]:null }">
					   <option value="${type.id.subId }" ${type.id.subId==goods.typeId?'selected':'' } >${type.id.subType }</option>
					   </c:forEach>
                      </select>                    </td>
                  </tr>
                  <tr>
                    <td width="14%" height="27">&nbsp;��Ʒ���ƣ�
                        <input name="id" type="hidden" id="ID" value="${goods.id }"></td>
                    <td height="27" colspan="3">&nbsp;
                        <input name="goodsName" type="text" class="Sytle_text" value="${goods.goodsName }" size="50">
&nbsp;&nbsp; </td>
                  </tr>
                  <tr>
                    <td height="16">&nbsp;ͼƬ�ļ���</td>
                    <td height="27" colspan="3">&nbsp;
                        <input name="picture" type="text" class="Style_upload" id="picture" value="${goods.picture }" size="30">
                    (�˴�ֻ��дͼƬ�ļ�·������) </td>
                  </tr>
                  <tr>
                    <td height="27" align="center">�������ۣ�</td>
                    <td height="27">&nbsp;
                        <input name="price" type="text" class="Sytle_text" id="price3" value="${goods.price }" size="14">
                    (Ԫ)</td>
                    <td height="27" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;�ۣ� </td>
                    <td height="27">&nbsp;
                        <input name="nowPrice" type="text" class="Sytle_text" id="price4" value="${goods.nowPrice }" size="14">
                    (Ԫ)</td>
                  </tr>
                  <tr>
                    <td height="45">&nbsp;�Ƿ���Ʒ��</td>
                    <td>&nbsp;
                        <input name="newGoods" type="radio" class="noborder" value="1" ${goods.newGoods==1?'checked':'' }>
                    ��
                    <input name="newGoods" type="radio" class="noborder" value="0" ${goods.newGoods==0?'checked':'' }>
                    ��</td>
                    <td>&nbsp;�Ƿ��ؼۣ�</td>
                    <td><input name="sale" type="radio" class="noborder" value="1" ${goods.sale==1?'checked':'' }>
                    ��
                      <input name="sale" type="radio" class="noborder" value="0" ${goods.sale==0?'checked':'' }>
                    ��</td>
                  </tr>
                  <tr>
                    <td height="103">&nbsp;��Ʒ��飺</td>
                    <td colspan="3"><span class="style5">&nbsp; </span>
                        <textarea name="introduce" cols="60" rows="5" class="textarea" id="introduce">${goods.introduce }</textarea></td>
                  </tr>
                  <tr>
                    <td height="38" colspan="4" align="center">
                      <input name="Button" type="button" class="btn_grey" value="����" onClick="mycheck()">
&nbsp;
                    <input name="Submit3" type="button" class="btn_grey" value="����" onClick="window.location.href='indexM.lzw';">                    </td>
                  </tr>
                </table>
            </form></td>
          </tr>
        </table>    </td>
  </tr>
</table>
