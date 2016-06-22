<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr><th>注文番号</th><td><c:out value="${id}"/></td></tr>
	<tr><th>名前</th><td><c:out value="${user.name}"/></td></tr>
	<tr><th>メールアドレス</th><td><c:out value="${user.email}"/></td></tr>
	<tr><th>住所</th><td><c:out value="${user.address}"/></td></tr>
	<tr><th>TEL</th><td><c:out value="${user.telephone}"/></td></tr>
</table>
<br>
<table border="1">
	<tr><th>商品</th><th>価格</th><th>×</th><th>個数</th><th>金額</th></tr>
	<tr>
		<td><c:out value="${cinema.title}"/></td>
		<td><c:out value="${cinema.price}"/></td>
		<td>×</td>
		<td><c:out value="${item.quantity}"/></td>
		<td><c:out value="${totalPrice}"/></td>
	</tr>
</table>
<br>
<table border="1">
	<tr><th>小計</th><td><c:out value="${cinema.price}"/>円</td></tr> <!-- <fmt>タグ！ -->
	<tr><th>税</th><td><c:out value="${tax}"/>円</td></tr> <!-- <fmt>タグ！ -->
	<tr><th>支払い方法</th><td>銀行振り込み</td></tr>
	<tr><th>送料一律</th><td>500円</td></tr>
	<tr><th>総計</th><td><c:out value="${sum}"/>円</td></tr>
</table>
<br>
<form:form modelAttribute="orderForm" action="/admin/statusUpdate">
	<table border="1">
		<tr><th>現在のステータス</th><th>ステータス変更</th></tr>
		<tr>
			<td><c:out value="${order.status}"/></td>
			<td>
				<form:hidden path="orderNumber" value="${id}"/>
				<form:select path="status" items="${statusMap}" delimiter="<br>"/>
				<input type="submit" value="更新">
			</td>
		</tr>
	</table>
</form:form>
<br>
<br>
<c:out value="${message}"/>
<br>
<a href="/admin/orderList">注文一覧へ戻る</a>
</body>
</html>