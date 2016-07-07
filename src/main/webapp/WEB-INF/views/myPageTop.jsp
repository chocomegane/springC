<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>
<form:form action="${pageContext.request.contextPath}/mypage/update" ></form:form>
購入履歴<br>
<h2 align="center">購入履歴</h2>
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
	</c:when>
	
	<c:otherwise>
		<p><font color="red">注文がありません。</font></p>	
	</c:otherwise>
			
</c:choose>	

</div>
<br>
<div align="center">
	<a href="<%=request.getContextPath() %>/admin/menu">メニューへ戻る</a>
</div>

	 			<sec:authentication var="id" property="principal.user.id" />
	 		<a href="<%=request.getContextPath() %>/myPage/userUpdate?id=${id}">ユーザー情報変更</a><br>
	 		<a href="<%=request.getContextPath() %>/myPage/passWordUpdate?id=${id}">パスワード変更</a><br>
			


</body>
</html>