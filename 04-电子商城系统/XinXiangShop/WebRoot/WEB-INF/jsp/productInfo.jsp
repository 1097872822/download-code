<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<table width="396" height="661" border="0" cellpadding="0"
 cellspacing="0"  background="images/productInfo.jpg">
	<tr>
		<td width="69%" height="189" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="98%" height="65" align="right">
						<a href="goodsSale.lzw">更多&gt;&gt;</a>&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" valign="top">
					<td height="200" colspan="2">
						<table width="100%" height="200" border="0" cellpadding="0"
							cellspacing="0">
							<c:forEach begin="1" end="${saleGoodsLine*2 }" var="line" step="2">
								<tr>
									<c:forEach begin="${line-1}" end="${line}" var="goods" items="${saleGoods }">
										<td width="49%" height="200" valign="top">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td height="95" align="center">
														<img src="images/goods/${goods.picture }" width="90"
															height="75"></td>
												</tr>
												<tr>
													<td height="22" align="center">
														<a href="goodsDetails.lzw?id=${goods.id }">
															${goods.goodsName }</a></td>
												</tr>
												<tr>
													<td height="20" align="center"
														style="text-decoration:line-through;color:#FF0000">
														原价： ${goods.price }</td>
												</tr>
												<tr>
													<td height="20" align="center">
														现价：${goods.nowPrice }</td>
												</tr>
												<tr>
													<td height="22" align="center">
														<input name="see" type="button" class="btn_grey"
															onClick="window.location.href='goodsDetails.lzw?id=${goods.id }'"
															value="详细信息">
															<c:if test="${sessionScope.username!=null }">
														<input name="see2" type="button" class="btn_grey"
															onClick="window.location.href='cartAdd.lzw?goodsID=${goods.id }'"
															value="购买">
															</c:if></td>
												</tr>
											</table>
									  </td>
									</c:forEach>
								</tr>
						  </c:forEach>
					  </table>
					</td>
				</tr>
				<tr>
					<td height="10" align="right">&nbsp;</td>
				</tr>
		  </table>
			<table width="100%" height="76" border="0" cellpadding="0"  cellspacing="0">
				<tr>
				<td width="407" height="25" align="right">
					<a href="goodsSale.lzw">更多&gt;&gt;</a>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>
						<c:forEach begin="0" end="${newGoodsLine-1 }" var="goods" items="${newGoods }">
							<table width="100%" height="79" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="35%" height="95" rowspan="4" align="center">
										<img src="images/goods/${goods.picture }" width="90"
											height="75"></td>
									<td width="9%" height="40">&nbsp;</td>
								    <td width="56%" height="40">${goods.goodsName }</td>
								</tr>
								<tr>
									<td height="24">&nbsp;</td>
								    <td height="25">单价：${goods.nowPrice }(元)</td>
								</tr>
								<tr>
									<td height="25">&nbsp;</td>
								    <td height="25">${goods.introduce }</td>
								</tr>
								<tr>
									<td height="25" align="center"><c:if test="${sessionScope.username!=null }"></c:if></td>
								    <td height="25"><input name="see22" type="button" class="btn_grey"
											onclick="window.location.href='goodsDetails.lzw?id=${goods.id }'"
											value="详细信息" />
								      <c:if test="${sessionScope.username!=null }">
                                        <input name="see22" type="button" class="btn_grey"
											onclick="window.location.href='cartAdd.lzw?goodsID=${goods.id } '"
											value="购买" />
                                  </c:if></td>
								</tr>
							</table>
						</c:forEach></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
