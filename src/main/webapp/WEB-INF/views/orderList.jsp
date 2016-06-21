<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="adminCommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文商品一覧</title>
</head>
<body>
<h2>注文一覧画面</h2>
<table border="1">
	<tr>
		<th>オーダー番号</th>
		<th>購入者id</th>
		<th>ステータス</th>
		<th>合計金額</th>
		<th>購入日</th>
	</tr>
	<c:forEach var="order" items="${page.xxxxList}">
		　<tr>
			<td><c:out value="${order.orderNumber}"/></td>
			<td><c:out value="${order.userId}"/></td>
			<td><c:out value="${order.status}"/></td>
			<td><c:out value="${order.totalPrice}"/></td>
			<td><c:out value="${order.date}"/></td>
		</tr>
	</c:forEach>	
</table>
<a href="/administerMenu.jsp">メニューへ戻る</a>
</body>
</html>