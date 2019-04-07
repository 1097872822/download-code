<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tags/fmt.tld"%>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<tiles:importAttribute/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<table width="622" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="15"></td></tr>
  <tr><td height="29" align="center" background="images/back_center.jpg">订单列表</td>
  </tr>
  <tr>
    <td align="center" valign="top">
	<table width="95%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td valign="top">
		  <table width="100%" height="48"  border="1" cellpadding="0" cellspacing="0"
		   bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
              <tr align="center">
                <td width="8%" height="30">订单号</td>
                <td width="8%">品种数</td>
                <td width="10%">真实姓名</td>
                <td width="15%">付款方式</td>
                <td width="17%">运送方式</td>
                <td width="6%">折扣</td>
                <td width="26%">订货日期</td>
                <td width="10%">执行</td>
              </tr>
              <c:forEach var="mem" items="${memberList}">
                <tr align="center">
                  <td height="24"><a href="orderMDetail.lzw?id=${mem.orderId }">${mem.orderId }</a></td>
                  <td>${mem.bnumber }</td>
                  <td>${mem.truename }</td>
                  <td>${mem.pay }</td>
                  <td>${mem.carry }</td>
                  <td><fmt:formatNumber type="percent" value="${mem.rebate }"/></td>
                  <td>${mem.orderDate }</td>
                  <td><c:choose>
                      <c:when test="${mem.enforce==0 }">
					   <a href="orderMEnforce.lzw?id=${mem.orderId }">
					   <img src="images/enforce.gif" width="16" height="16"></a> </c:when>
                      <c:otherwise> 已执行 </c:otherwise>
                    </c:choose></td>
                </tr>
              </c:forEach>
            </table>
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="27" align="right">当前页数：[${page }/${maxPage}]&nbsp;
                  <c:if test="${page>1}"> 
				  <a href="orderManage.lzw?page=1">第一页</a>
				  <a href="orderManage.lzw?page=${page-1 }">上一页</a> </c:if>
                  <c:if test="${page<maxPage}">
				  <a href="orderManage.lzw?page=${page+1 }">下一页</a>
				  <a href="orderManage.lzw?page=${maxPage}">最后一页&nbsp;</a> </c:if>
                </td>
              </tr>
            </table></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
