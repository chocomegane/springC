<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="userCommon.jsp"%>
<fmt:setLocale value="ja_JP"/>
<h2 align="center">ご注文内容</h2>
<hr>
<table border="1" align="center">
	<tr>
		<th>商品名</th><th>単価</th><th>個数</th><th>税抜<%-- 価格</th><th> --%>小計</th>
	</tr>
	<c:forEach var="paymentChildPage" items="${paymentPage.paymentChildPageList}">
		<tr>
			<td align="center"><c:out value="${paymentChildPage.orderCinemaTitle}" /></td>
			<td align="center">
				&yen;<fmt:formatNumber value="${paymentChildPage.price}" maxFractionDigits="0"/>
			</td>
			<td align="center"><c:out value="${paymentChildPage.quantity}" /></td>
			<td align="center">
				&yen;<fmt:formatNumber value="${paymentChildPage.subTotalPrice}" maxFractionDigits="0" />
			</td>
<%-- 			<td align="center">
				&yen;<fmt:formatNumber value="${paymentChildPage.pretaxTotalPrice}" maxFractionDigits="0" />
			</td> --%>
		</tr>
	</c:forEach>
	<tr>
		<td>消費税</td>
		<td align="right" colspan="3">
			&yen;<fmt:formatNumber value="${paymentPage.totalTax}" maxFractionDigits="0" />
		</td>
	</tr>
	<tr>
		<td>送料一律</td>
		<td align="right" colspan="3">
			&yen;<fmt:formatNumber value="${paymentPage.postage}" maxFractionDigits="0" />
		</td>
	</tr>
	<tr>
		<td>商品合計</td>
		<td align="right" colspan="4">
			&yen;<fmt:formatNumber value="${paymentPage.totalPrice}" maxFractionDigits="0" />
		</td>
	</tr>
</table>
<br>



<h2 align="center">お届け先</h2>
<hr>
<div align="center">
	お名前：
	<c:out value="${paymentPage.userName}" />
	<br> メールアドレス：
	<c:out value="${paymentPage.userEMail}" />
	<br> 住所：
	<c:out value="${paymentPage.userAddress}" />
	<br> 電話番号：
	<c:out value="${paymentPage.userTelephone}" />
	<br> <br>

	<form:form action="/finishPayment" method="post">
		<input type="hidden" name="orderId" value="${paymentPage.orderId}">
		<input type="submit" value="確定" onclick="DisableButton(this)"/>
	</form:form>
</div>
</body>
</html>