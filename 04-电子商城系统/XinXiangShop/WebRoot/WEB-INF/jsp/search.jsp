<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<tiles:importAttribute/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<form name="form2" method="post" action="searchController.lzw" style="margin:0">
  <table width="790" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="9%" height="23" align="right"><img src="images/clock.gif" width="13" height="13" /></td>
            <td width="21%" style="font-size:9pt;color:#cc0000"><span id="liveclock"></span>&nbsp;</td>
            <td width="57%"><img src="images/searchStar.gif" width="13" height="13" />请输入查询条件：
              <select name="type" class="textarea">
                <option value="0" selected>全部分类</option>
                <c:forEach var="type" items="${searchClassify }">
                  <option value="${type[0] }">${type[1] }</option>
                </c:forEach>
              </select>
              <input name="key" type="text" class="txt_grey" size="40">
            </td>
            <td><input name="search" type="submit" class="btn_grey" value="搜索"></td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td valign="top"><img src="images/banner.jpg" alt="广告标语" width="788" height="109" /></td>
    </tr>
    </td>
    
    </tr>
    
  </table>
</form>
<script language=javascript>
function www_helpor_net()
{
var Digital=new Date()
var hours=Digital.getHours()
var minutes=Digital.getMinutes()
var seconds=Digital.getSeconds()

if(minutes<=9)
minutes="0"+minutes
if(seconds<=9)
seconds="0"+seconds
myclock="现在时刻："+hours+":"+minutes+":"+seconds+"</font>"
if(document.layers){document.layers.liveclock.document.write(myclock)
document.layers.liveclock.document.close()
}else if(document.all)
liveclock.innerHTML=myclock
setTimeout("www_helpor_net()",1000)
}
www_helpor_net();
          </script>
