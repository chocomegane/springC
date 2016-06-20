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
	<c:forEach var="page1" items="${page.cinemaList}">
		　<tr>
			<td><c:out value="${page1.title}"/></td>
			<td><c:out value="${page1.price}"/></td>
			<td><c:out value="${page1.genre}"/></td>
			<td><c:out value="${page1.time}"/></td>
			<td><c:out value="${page1.releaseDate}"/></td>
			<td><c:out value="${page1.mediaType}"/></td>
			<td><c:out value="${page1.company}"/></td>
			<td><c:out value="${page1.directedBy}"/></td>
			<td><c:out value="${page1.rating}"/></td>
			<td><c:out value="${page1.description}"/></td>
			<td><c:out value="${page1.imagePath}"/></td>
		</tr>
	</c:forEach>	
</table>
<a href="/administerMenu.jsp">メニューへ戻る</a>
</body>
</html>