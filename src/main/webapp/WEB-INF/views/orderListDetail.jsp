<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文詳細画面</title>
</head>
<body>
<h2 align="center">注文詳細画面</h2>
<div align="center">
	<table border="1">
		<tr><th>注文番号</th><td><c:out value="${page.orderNumber}"/></td></tr>
		<tr><th>名前</th><td><c:out value="${page.userName}"/></td></tr>
		<tr><th>メールアドレス</th><td><c:out value="${page.email}"/></td></tr>
		<tr><th>住所</th><td><c:out value="${page.address}"/></td></tr>
		<tr><th>TEL</th><td><c:out value="${page.telephone}"/></td></tr>
	</table>
</div>
<br>
<div align="center">
	<table border="1">
		<tr><th>商品</th><th>価格</th><th>×</th><th>個数</th><th>金額</th></tr>
		<c:forEach var="item" items="${page.childPage}">
			<tr>
				<td><c:out value="${item.title}"/></td>
				<td><fmt:formatNumber value="${item.price}" pattern="###,###円"/></td>
				<td>×</td>
				<td><c:out value="${item.quantity}"/></td>
				<td><fmt:formatNumber value="${item.total}" pattern="###,###円"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
<br>
<div align="center">
	<table border="1">
		<tr><th>小計</th><td><fmt:formatNumber value="${page.subTotal}" pattern="###,###円"/></td></tr>
		<tr><th>税</th><td><fmt:formatNumber value="${page.tax}" pattern="###,###円"/></td></tr>
		<tr><th>支払い方法</th><td>銀行振り込み</td></tr>
		<tr><th>送料一律</th><td>500円</td></tr>
		<tr><th>総計</th><td><fmt:formatNumber value="${page.grandTotal}" pattern="###,###円"/></td></tr>
	</table>
</div>
<br>
<form:form modelAttribute="orderForm" action="/admin/statusUpdate">
	<div align="center">
		<table border="1">
			<tr><th>現在のステータス</th><th>ステータス変更</th></tr>
			<tr>
				<td><c:out value="${page.status}"/></td>
				<td>
					<form:hidden path="orderNumber" value="${page.orderNumber}"/>
					<form:select path="status" items="${page.statusMap}" delimiter="<br>"/>
					<input type="submit" value="更新">
				</td>
			</tr>
		</table>
	</div>
</form:form>
<br>
<br>
<div align="center">
	<c:out value="${message}"/>
</div>
<br>
<div align="center">
	<a href="/admin/orderList">注文一覧へ戻る</a>
	<br>
	<br>
	<a href="/admin/menu">メニューへ戻る</a>
</div>
</body>
</html>