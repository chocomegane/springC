<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="addminCommon.jsp" %>
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
		<th>映画名</th>
		<th>価格</th>
		<th>ジャンル</th>
		<th>上映時間</th>
		<th>公開日</th>
		<th>メディアタイプ</th>
		<th>制作会社</th>
		<th>監督</th>
		<th>レーディング</th>
		<th>概要（ストーリー）</th>
		<th>イメージ画像</th>
	</tr>
	<c:forEach var="cinema" items="${cinemaList}">
		　<tr>
			<td><c:out value="${cinema.title}"/></td>
			<td><c:out value="${cinema.price}"/></td>
			<td><c:out value="${cinema.genre}"/></td>
			<td><c:out value="${cinema.time}"/></td>
			<td><c:out value="${cinema.releaseDate}"/></td>
			<td><c:out value="${cinema.mediaType}"/></td>
			<td><c:out value="${cinema.company}"/></td>
			<td><c:out value="${cinema.directedBy}"/></td>
			<td><c:out value="${cinema.rating}"/></td>
			<td><c:out value="${cinema.description}"/></td>
			<td><c:out value="${cinema.imagePath}"/></td>
		</tr>
	</c:forEach>	
</table>
<a href="/administerMenu.jsp">メニューへ戻る</a>
</body>
</html>