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
		<th>注文番号</th>
		<th>日付</th>
		<th>利用者名</th>
		<th>現在のステータス</th>
		<th>総計（税込）</th>
	</tr>
	<c:forEach var="order" items="${page.cinemaList}">
		　<tr>
			<td><a href="/orderListDetail?id=<c:out value="${order.orderNumber}"/>"><c:out value="${order.orderNumber}"/></a></td>
			<td><c:out value="${order.date}"/></td>
			<td><c:out value="${order.userId}"/></td>
			<td><c:out value="${order.status}"/></td>
			<td><c:out value="${order.totalPrice}"/></td>
		</tr>
	</c:forEach>	
</table>
<a href="/administerMenu.jsp">メニューへ戻る</a>
</body>
</html>