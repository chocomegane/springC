<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>>
<body>
<h2  align="center">商品詳細</h2>
	<table border="1" align="center">
		<tr>
			<td colspan="2" rowspan="3"><img src="../img/pc.jpg" width="150"
				height="150" alt="商品画像">
			</td>
			<th>商品名：</th>
			<td align="center"><c:out value="${cinemaDetail.title}"/></td>
		</tr>
		<tr>
			<th>価格：</th>
			<td align="center">&yen;<c:out value="${cinemaDetail.price}]"/></td>
		</tr>
		<tr>
			<th>ジャンル:</th>
			<td align="center"><c:out value="${cinemaDetail.genre}"/></td>
		</tr>
		<tr>
			<th>上映時間：</th>
			<td align="center"><c:out value="${cinemaDetail.time}"/></td>
		</tr>
		<tr>
			<th>公開日：</th>
			<td align="center"><c:out value="${cinemaDetail.releaseDate}"/></td>
		</tr>
		<tr>
			<th>メディアタイプ：</th>
			<td align="center"><c:out value="${cinemaDetail.mediaType}"/></td>
		</tr>
		<tr>
			<th>制作会社：</th>
			<td align="center"><c:out value="${cinemaDetail.company}"/></td>
		</tr>
		<tr>
			<th>監督：</th>
			<td align="center"><c:out value="${cinemaDetail.directedBy}"/></td>
		</tr>
		<tr>
			<th>レーティング：</th>
			<td align="center"><c:out value="${cinemaDetail.rating}"/></td>
		</tr>
		<tr>
			<th>概要：</th>
			<td>最新型のパソコンです</td>
		</tr>
	</table>
	<br>
	<div id="selectQuantity" align="center">
	<form:form modelattribute="cartForm" action="viewShoppingCart.html">
					個数：<select name="quantity">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
			</select></div>
		
		<div ="sendCart" align="center"><p><input type="submit" value="カートに入れる"></p>
		<div ="sendCart" align="center"><p><a href="itemList.html">商品一覧画面へ戻る</a></p></div>
		<a href="itemList.html">
	</form:form>
</body>
</html>