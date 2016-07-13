<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="userCommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文詳細画面</title>
</head>
<body>
<br>
<br>
<br>

<h2 align="center">注文詳細画面</h2><br>
<div align="center">
<h3 align="center">発送先</h3>
	<table class="table table-striped">
		<tr><th><center>注文番号</center></th><td><c:out value="${page.orderNumber}"/></td></tr>
		<tr><th><center>名前</center></th><td><c:out value="${page.userName}"/></td></tr>
		<tr><th><center>メールアドレス</center></th><td><c:out value="${page.email}"/></td></tr>
		<tr><th><center>住所</center></th><td><c:out value="${page.address}"/></td></tr>
		<tr><th><center>TEL</center></th><td><c:out value="${page.telephone}"/></td></tr>
	</table>
</div>
<br>
<div align="center">
<h3 align="center">商品</h3>
	<table class="table table-striped">
		<tr><th><center>商品</center></th><th><center>価格</center></th><th><center>×</center></th><th><center>個数</center></th><th><center>金額</center></th></tr>
		<c:forEach var="item" items="${page.childPage}">
			<tr>
				<td align="center"><c:out value="${item.title}"/></td>
				<td align="center"><fmt:formatNumber value="${item.price}" pattern="###,###円"/></td>
				<td align="center">×</td>
				<td align="center"><c:out value="${item.quantity}"/></td>
				<td align="center"><fmt:formatNumber value="${item.total}" pattern="###,###円"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
<br>
<div align="center">
<h3 align="center">	金額</h3>
	<table class="table table-striped">
		<tr><th><center>小計</center></th><td><fmt:formatNumber value="${page.subTotal}" pattern="###,###円"/></td></tr>
		<tr><th><center>税</center></th><td><fmt:formatNumber value="${page.tax}" pattern="###,###円"/></td></tr>
		<tr><th><center>支払い方法</center></th><td>銀行振り込み</td></tr>
		<tr><th><center>送料一律</center></th><td>500円</td></tr>
		<tr><th><center>総計</center></th><td><fmt:formatNumber value="${page.grandTotal}" pattern="###,###円"/></td></tr>
	</table>
</div>
<br>
	

<div align="center">
	<c:out value="${message}"/>
</div>
<br>
<div align="center">
	<p><a href="<%=request.getContextPath() %>/myPage/?id=${id}">マイページ</a><p>
</div>
</body>
</html>