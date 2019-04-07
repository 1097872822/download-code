<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld" %>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script src="JS/check.jsp"></script>
<table width="622" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr>
    <td height="29" align="center" background="images/back_center.jpg">大分类信息列表</td>
  </tr>
  <tr><td height="15" align="center">
  <a href="superAdd.lzw">[ <img src="images/list.gif" width="11" height="13" />&nbsp;添加大分类信息]</a></td>
  </tr>
  <tr>
    <td align="center" valign="top"><form action="superType.lzw" method="post" name="frm">
          <table width="92%" height="192"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td valign="top">
                <table width="100%" height="14"  border="0" cellpadding="0" cellspacing="0">
                <tr><td align="center">
                <c:choose>
                <c:when test="${param.message eq 'false'}">
				<font color="red">该分类已经包括子分类信息，请先删除小分类信息后，再将其删除!</font>
				</c:when>
                <c:when test="${param.message eq 'true'}">大分类信息删除成功!</c:when>
                </c:choose></td></tr>
                </table>
                <table width="100%" height="48"  border="1" cellpadding="0" cellspacing="0" 
				bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
                  <tr bgcolor="#eeeeee">
                    <td height="24" align="center">大分类信息名称</td>
                    <td width="8%" align="center">删除</td>
                  </tr>
                  <c:forEach var="type" items="${list }">
                  <tr style="padding:5px;">
                    <td height="20" align="center">${type.typeName}</td>
                    <td align="center">
					<input name="delid" type="checkbox" class="noborder" value="${type.id}"></td>
                  </tr>
                  </c:forEach>
                </table>
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="73%" height="24">&nbsp;</td>
                    <td width="27%" align="right">
					<input name="checkbox" type="checkbox" class="noborder" onClick="CheckAll(frm.delid,frm.checkbox)">
                    [全选/反选] [<a style="color:red;cursor:hand;" onclick="checkdel(frm.delid,frm)">删除</a>]
                    <div id="ch">
                      <input name="delid2" type="checkbox" class="noborder" value="0">
                  </div></td>
                    <!--层ch用于放置隐藏的checkbox控件，因为当表单中只是一个checkbox控件时，应用javascript获得其length属性值为undefine-->
                    <script language="javascript">ch.style.display="none";</script>
                  </tr>
              </table></td>
            </tr>
          </table>
        </form>
    </td>
  </tr>
</table>
