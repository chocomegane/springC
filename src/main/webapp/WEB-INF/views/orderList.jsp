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
<h2 align="center">注文一覧画面</h2>
<div align="center">

<c:choose >

	<c:when test="${flag}">
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
					<td>
						<a href="<%=request.getContextPath() %>/admin/orderListDetail?orderNumber=<c:out value="${order.orderNumber}"/>">
						<c:out value="${order.orderNumber}"/></a>
					</td>
					<td><fmt:formatDate value="${order.date}" pattern="yyyy/MM/dd"/></td>
					<td><c:out value="${order.userName}"/></td>
					<td><c:out value="${order.status}"/></td>
					<td><fmt:formatNumber value="${order.totalPrice}" pattern="###,###円"/></td>
				</tr>
			</c:forEach>	
		</table>
		<a href="<%=request.getContextPath() %>/admin/csvDownload">csvfileダウンロード</a>
	</c:when>
	
	<c:otherwise>
		<p><font color="red">注文がありません。</font></p>	
	</c:otherwise>
			
</c:choose>	


</div>
<br>
	<c:out value="${errMessage}" />
<div align="center">
	<a href="<%=request.getContextPath() %>/admin/menu">メニューへ戻る</a>
</div>
</body>
</html>