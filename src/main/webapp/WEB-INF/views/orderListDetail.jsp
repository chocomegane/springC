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
		<tr><th><center>注文番号</center></th><td><c:out value="${page.orderNumber}"/></td></tr>
		<tr><th><center>名前</center></th><td><c:out value="${page.userName}"/></td></tr>
		<tr><th><center>メールアドレス</center></th><td><c:out value="${page.email}"/></td></tr>
		<tr><th><center>住所</center></th><td><c:out value="${page.address}"/></td></tr>
		<tr><th><center>TEL</center></th><td><c:out value="${page.telephone}"/></td></tr>
	</table>
</div>
<br>
<div align="center">
	<table border="1">
		<tr><th><center>商品</center></th><th><center>価格</center></th><th><center>×</center></th><th><center>個数</center></th><th><center>金額</center></th></tr>
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
		<tr><th><center>小計</center></th><td><fmt:formatNumber value="${page.subTotal}" pattern="###,###円"/></td></tr>
		<tr><th><center>税</center></th><td><fmt:formatNumber value="${page.tax}" pattern="###,###円"/></td></tr>
		<tr><th><center>支払い方法</center></th><td>銀行振り込み</td></tr>
		<tr><th><center>送料一律</center></th><td>500円</td></tr>
		<tr><th><center>総計</center></th><td><fmt:formatNumber value="${page.grandTotal}" pattern="###,###円"/></td></tr>
	</table>
</div>
<br>
<form:form modelAttribute="orderForm" action="<%=request.getContextPath() %>/admin/statusUpdate">
	<div align="center">
		<table border="1">
			<tr><th><center>現在のステータス</center></th><th><center>ステータス変更</center></th></tr>
			<tr>
				<td><c:out value="${page.status}"/></td>
				<td>
					<form:hidden path="orderNumber" value="${page.orderNumber}"/>
					<form:select path="status" items="${page.statusMap}" delimiter="<br>"/>
					<input type="submit" value="更新" onclick="DisableButton(this)"/>
				</td>
			</tr>
		</table>
	</div>
</form:form>

<div align="center">
	<c:out value="${message}"/>
</div>
<br>
<div align="center">
	<a href="<%=request.getContextPath() %>/admin/orderList">注文一覧へ戻る</a>
	<br>
	<a href="<%=request.getContextPath() %>/admin/menu">メニューへ戻る</a>
</div>
</body>
</html>