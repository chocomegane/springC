<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>
<body>

<h3>値段</h3>
<a href="/userSerch/price?minPriceStr=0&maxPriceStr=1000">～1000円</a><br>
<a href="/userSerch/price?minPriceStr=1000&maxPriceStr=2000">1000円～2000円</a><br>
<a href="/userSerch/price?minPriceStr=2000&maxPriceStr=3000">2000円～3000円</a><br>
<a href="/userSerch/price?minPriceStr=3000&maxPriceStr">3000円～</a>

<h3>ジャンル</h3>
<a href="/userSerch/genre?genreStr=SF">SF</a><br>
<a href="/userSerch/genre?genreStr=ホラー">ホラー</a><br>
<a href="/userSerch/genre?genreStr=ファンタジー">ファンタジー</a><br>
<a href="/userSerch/genre?genreStr=アクション">アクション</a>

<h3>メディアタイプ</h3>
<a href="/userSerch/mediaType?mediaTypeStr=DVD">DVD</a>

<h2>商品一覧</h2>
<form action="/userSerch/title" method="get">
<c:out value="${message}"/>
<c:out value="${message2}"/><br>
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
			<a href="/user/cinemaDetail/detail/${child.id}"><c:out value="${child.title}"/></a>
			</th>
			<th><c:out value="${child.price}"/>円</th>
		</tr>
	</c:forEach>
 </tbody>
</table>

</body>
</html>