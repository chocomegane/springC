<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品登録画面</title>
</head>
<body>
<h2 align="center">商品更新画面</h2>
<div align="center">
	<form:form modelAttribute="cinemaForm" action="/admin/updateCinema/execute" enctype="multipart/form-data">
		<table border="1">
			<form:hidden path="id" value="${cinema.id}"/>
			<tr>
				<th>タイトル</th>
				<td align="center">
					<form:hidden path="originallyTitle" value="${cinema.title}"/>
					<form:input path="title" style="width:20em; height:1em" value="${cinema.title}"/><form:errors path="title" cssStyle="color:red" element="div"/>
					<div><font color="red"><c:out value="${err2}"/></font></div>
				</td>
			</tr>
			<tr><th>価格</th><td align="center"><form:input path="price" style="width:20em; height:1em" value="${cinema.price}"/><form:errors path="price" cssStyle="color:red" element="div"/></td></tr>
			<tr><th>ジャンル</th><td align="center"><form:input path="genre" style="width:20em; height:1em" value="${cinema.genre}"/><form:errors path="genre" cssStyle="color:red" element="div"/></td></tr>
			<tr><th>上映時間</th><td align="center"><form:input path="time" style="width:20em; height:1em" value="${cinema.time}"/><form:errors path="time" cssStyle="color:red" element="div"/></td></tr>
			<tr><th>公開日</th><td align="center"><form:input type="date" path="releaseDate" style="width:20em; height:1em" value="${cinema.strReleaseDate}"/><form:errors path="releaseDate" cssStyle="color:red" element="div"/></td></tr>
			<tr><th>メディアタイプ</th><td align="center"><form:input path="mediaType" style="width:20em; height:1em" value="${cinema.mediaType}"/><form:errors path="mediaType" cssStyle="color:red" element="div"/></td></tr>
			<tr><th>制作会社</th><td align="center"><form:input path="company" style="width:20em; height:1em" value="${cinema.company}"/><form:errors path="company" cssStyle="color:red" element="div"/></td></tr>
			<tr><th>監督</th><td align="center"><form:input path="directedBy" style="width:20em; height:1em" value="${cinema.directedBy}"/><form:errors path="directedBy" cssStyle="color:red" element="div"/></td></tr>
			<tr><th>レーディング</th><td align="center"><form:input path="rating" style="width:20em; height:1em" value="${cinema.rating}"/><form:errors path="rating" cssStyle="color:red" element="div"/></td></tr>	
			<!-- <tr><th>概要（ストーリー）</th><td><form:input path="description" style="width:20em; height:1em" value="${cinema.description}"/></td></tr> -->
			<tr><th>概要（ストーリー）</th><td align="center"><textarea name="description" style="width:20em; height:10em"><c:out value="${cinema.description}"/></textarea><form:errors path="description" cssStyle="color:red" element="div"/></td></tr>
			<tr>
				<th>イメージ画像</th>
				<td align="center">
					<form:hidden path="originallyImagePath" value="${cinema.imagePath}"/>
					<form:input type="file" path="imagePath"/><div><c:out value="${cinema.imagePath}"/></div>
					<div><font color="red"><c:out value="${err3}"/></font></div>
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" value="更新" onclick="DisableButton(this)"/>
	</form:form>
</div>
<div align="center">
	<c:out value="${message}"/>
</div>
<br>
<div align="center">
	<a href="/admin/displayList">商品一覧画面へ戻る</a>
	<a href="/admin/menu">メニューへ戻る</a>
</div>
</body>
</html>