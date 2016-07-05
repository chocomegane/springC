	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>
<body>
<br>
<h2  align="center">商品詳細</h2>

<div align="center">
	<table border="1">
		<tr>
			<td colspan="2" rowspan="10"><img src="<%=request.getContextPath() %>/img/${cinemaDetail.imagePath}" width="150"
				 alt="商品画像">
			</td>
			<th width="100">商品名</th>
			<td align="center" width="100"><c:out value="${cinemaDetail.title}"/></td>
		</tr>
		<tr>
			<th width="100">価格</th>
			<td align="center"><fmt:formatNumber value="${cinemaDetail.price}" pattern="###,###円"/></td>
		</tr>
		<tr>
			<th width="100">ジャンル</th>
			<td align="center"><c:out value="${cinemaDetail.genre}"/></td>
		</tr>
		<tr>
			<th width="100">上映時間</th>
			<td align="center"><fmt:formatNumber value="${cinemaDetail.time}" pattern="###,###分"/></td>
		</tr>
		<tr>
			<th width="100">公開日</th>
			<td align="center"><fmt:formatDate value="${cinemaDetail.releaseDate}" pattern="yyyy/MM/dd"/></td>
		</tr>
		<tr>
			<th width="100">メディアタイプ</th>
			<td align="center"><c:out value="${cinemaDetail.mediaType}"/></td>
		</tr>
		<tr>
			<th width="100">制作会社</th>
			<td align="center"><c:out value="${cinemaDetail.company}"/></td>
		</tr>
		<tr>
			<th width="100">監督</th>
			<td align="center"><c:out value="${cinemaDetail.directedBy}"/></td>
		</tr>
		<tr>
			<th width="100">レーティング</th>
			<td align="center"><c:out value="${cinemaDetail.rating}"/></td>
		</tr>
		<tr>
			<th width="100">概要</th>
			<td align="center"><textarea disabled="disabled" style="background-color:white;" rows="5" cols="30"><c:out value="${cinemaDetail.description}"/></textarea></td>
		</tr>
	</table>
</div>
	<br>
	<div id="selectQuantity" align="center">
	<form:form modelAttribute="cartForm" action="${pageContext.request.contextPath}/cart/insert"><br>
			<form:errors path="quantity" cssStyle="color:red" element="div"/>
			注文個数<input type="text" name="quantity" value="1"/><br>
		<form:hidden path="cinemaId" value="${cinemaDetail.id}"/>
		<div id="sendCart" align="center"><p><input type="submit" value="カートに入れる" onclick="DisableButton(this)"></p></div>
		<div id="backList" align="center"><p><a href="<%=request.getContextPath() %>/">商品一覧画面へ戻る</a></p></div>
	</form:form>
	</div>
</body>
</html>