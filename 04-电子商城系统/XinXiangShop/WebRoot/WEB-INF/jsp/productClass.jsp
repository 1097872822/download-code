<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:importAttribute/>
<table width="194" height="486" border="0" cellpadding="0" cellspacing="0" background="images/sort.jpg">
  <tr>
    <td height="60"></td>
  </tr>
  <tr valign="top">
    <td>
    <table width="100%" height="40"  border="0" cellpadding="0" cellspacing="0">
	<c:forEach varStatus="suff" var="names" items="${typeNameList }">
        <tr>
          <td>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="Javascript:ShowTR(img${suff.index },OpenRep${suff.index })">
		<img src="images/jia.gif" border="0" alt="展开" id="img${suff.index }"></a>
		<a href="Javascript:ShowTR(img${suff.index },OpenRep${suff.index })">${names }</a></td>
		<tr id="OpenRep${suff.index }" style="display:none;">
		  <td colspan="6">
		<c:forEach var="subList" items="${typeList[names] }">
		    <table width="100%"  border="0" cellspacing="-2" cellpadding="-2">
		      <tr>
		        <td height="25" align="right"><table width="90%"  border="0" cellspacing="0" cellpadding="0">
		            <tr onMouseOver="this.style.background='#EEEEEE'" onMouseOut="this.style.background=''">
		              <td width="15%">&nbsp;</td>
		              <td width="10%" align="center"><img src="images/folder.gif" width="16" height="16" border="0"> </td>
		              <td width="75%"><a href="type.lzw?id=${subList.id.subId }">${subList.id.subType }</a></td>
		            </tr>
		        </table></td>
		      </tr>
		    </table>
		 </c:forEach>
		  </td>
		</tr>
	</c:forEach>
		<script language="javascript">
<%--		ShowTR(img0,OpenRep0)  //设置第1个结点为展开状态--%>
		function ShowTR(objImg,objTr){
			if(objTr.style.display == ""){
				objTr.style.display = "none";
				objImg.src = "images/jia.gif";
				objImg.alt = "展开";
			}else{
				objTr.style.display = "";
				objImg.src = "images/jian.gif";
				objImg.alt = "折叠";
			}
		}
		</script>
    </table></td>
  </tr>
</table>
