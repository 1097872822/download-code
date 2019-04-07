<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table width="590" height="494" border="0" cellpadding="0"
	cellspacing="0" background="images/cardback.jpg">
	<tr>
		<td height="189" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="50" background="images/cardheader.jpg">
						&nbsp;
					</td>
				</tr>
				<tr valign="top">
					<td height="134" align="center">
						<table width="100%" height="126" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td valign="top">
									<c:choose>
										<c:when test="${sessionScope.username==null }">
											<table width="90%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td height="24" colspan="2" align="center">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td width="48%" height="38" align="right"
														class="word_orange">
														<img src="images/cart.gif" width="45" height="38"
															align="absmiddle" />
														请先登录！
													</td>
													<td width="52%" rowspan="3" align="right" valign="bottom">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td height="57" align="right"></td>
												</tr>
												<tr>
													<td height="72" align="right">
														&nbsp;
													</td>
												</tr>
											</table>
										</c:when>
										<c:when test="${param.orderId!=null }">
											<table width="90%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td height="24" colspan="2" align="center">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td width="48%" height="38" align="right"
														class="word_orange">
														订单生成，请记住您的订单号[${param.orderId }]
													</td>
													<td width="52%" rowspan="3" align="right" valign="bottom">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td height="57" align="right">
														<input name="Button" type="button" class="btn_grey"
															value="继续购物！" onClick="window.location.href='index.jsp'">
													</td>
												</tr>
												<tr>
													<td height="72" align="right">
														&nbsp;
													</td>
												</tr>
											</table>
										</c:when>
										<c:when
											test="${sessionScope.cart==null||fn:length(sessionScope.cart)<=0 }">
											<table width="90%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td height="24" colspan="2" align="center">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td width="48%" height="38" align="right"
														class="word_orange">
														<img src="images/cart.gif" width="45" height="38"
															align="absmiddle" />
														您的购物车为空！
													</td>
													<td width="52%" rowspan="3" align="right" valign="bottom">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td height="57" align="right">
														<input name="Button" type="button" class="btn_grey"
															value="我要购买商品！"
															onClick="window.location.href='index.jsp'">
													</td>
												</tr>
												<tr>
													<td height="72" align="right">
														&nbsp;
													</td>
												</tr>
											</table>
										</c:when>
										<c:when test="${fn:length(sessionScope.cart)>0 }">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
<form method="post" action="cartModify.lzw" name="form1">
	<table width="92%" height="48" border="0" align="center"
		cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td height="27" class="tableBorder_B1">
				编号
			</td>
			<td height="27" class="tableBorder_B1">
				商品编号
			</td>
			<td class="tableBorder_B1">
				商品名称
			</td>
			<td height="27" class="tableBorder_B1">
				单价
			</td>
			<td height="27" class="tableBorder_B1">
				数量
			</td>
			<td height="27" class="tableBorder_B1">
				金额
			</td>
			<td class="tableBorder_B1">
				退回
			</td>
		</tr>
		<c:forEach varStatus="idx" var="goods"
			items="${sessionScope.cart }">
			<c:set var="sum"
				value="${sum+goods.number*goods.nowPrice }" />
			<tr align="center" valign="middle">
				<td width="32" height="27">
					${idx.index+1 }
				</td>
				<td width="109" height="27">
					${goods.ID }
				</td>
				<td width="199" height="27">
					${goods.goodsName }
				</td>
				<td width="59" height="27">
					￥${goods.nowPrice }
				</td>
				<td width="51" height="27">
					<input name="num${idx.index }" size="7" type="text"
						class="txt_grey" value="${goods.number }"
						onBlur="check(this.form)">
				</td>
				<td width="65" height="27">
					￥${goods.nowPrice*goods.number }
				</td>
				<td width="34">
					<a href="cartMove.lzw?ID=${idx.index }"><img
							src="images/del.gif" width="18" height="18">
					</a>
				</td>
				<script language="javascript">
<!--
function check(myform){
	if(isNaN(myform.num${idx.index }.value) || myform.num${idx.index }.value.indexOf('.',0)!=-1){
		alert("请不要输入非法字符");myform.num${idx.index }.focus();return;}
	if(myform.num${idx.index }.value==""){
		alert("请输入修改的数量");myform.num${idx.index }.focus();return;}	
	myform.submit();
}
-->
</script>
			</tr>
		</c:forEach>
	</table>
</form>
<table width="100%" height="52" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td height="10">
			&nbsp;
		</td>
		<td width="24%" height="10" colspan="-3" align="left">
			&nbsp;
		</td>
	</tr>
	<tr align="center" valign="middle">
		<td height="21" class="tableBorder_B1">
			&nbsp;
		</td>
		<td height="21" colspan="-3" align="left"
			class="tableBorder_B1">
			合计总金额：￥${sum }
		</td>
	</tr>
	<tr align="center" valign="middle">
		<td height="21" colspan="2">
			<a href="index.jsp">继续购物</a> |
			<a href="cartCheckout.lzw">收银结账</a> |
			<a href="cartClear.lzw">清空购物车</a> |
			<a href="#">修改数量</a>
		</td>
	</tr>
</table>
													</td>
												</tr>
											</table>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
