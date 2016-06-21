<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>
<body>

<h3>値段</h3>
<a href="/serch/price?minPriceStr=0&maxPriceStr=1000">～1000円</a><br>
<a href="/serch/price?minPriceStr=1000&maxPriceStr=2000">1000円～2000円</a><br>
<a href="/serch/price?minPriceStr=2000&maxPriceStr=3000">2000円～3000円</a><br>
<a href="/serch/price?minPriceStr=3000&maxPriceStr">3000円～</a>

<h3>ジャンル</h3>
<a href="/serch/genre?genreStr=SF">SF</a><br>
<a href="/serch/genre?genreStr=ホラー">ホラー</a><br>
<a href="/serch/genre?genreStr=ファンタジー">ファンタジー</a><br>
<a href="/serch/genre?genreStr=アクション">アクション</a>

<h3>メディアタイプ</h3>
<a href="/serch/mediaType?mediaTypeStr=DVD">DVD</a>

<h2>商品一覧</h2>
<form action="/serch/title" method="get">
<input type="text" name="title"/>
<input type="submit" value="検索"/>
</form>

<table>

 <tbody>
  <tr>
  	<th>商品名</th>
  	<th>価格</th>
  </tr>
	<c:forEach var="child" items="${listPage.childPageList}">
		<tr>
			<th>
			<a href="詳細表示のRequestMapping/${child.id}"><c:out value="${child.title}"/></a>
			</th>
			<th><c:out value="${child.price}"/>円</th>
			<th>
				<form:form action="">
					<input type="submit" value="削除">
				</form:form>
			</th>
		</tr>
	</c:forEach>
 </tbody>
</table>

</body>
</html>