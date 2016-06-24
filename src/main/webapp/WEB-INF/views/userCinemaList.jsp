<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

<h3>値段</h3>
<a href="/cinemaShop/searchCinemaPrice?minPriceStr=0&maxPriceStr=1000">～1000円</a><br>
<a href="/cinemaShop/searchCinemaPrice?minPriceStr=1000&maxPriceStr=2000">1000円～2000円</a><br>
<a href="/cinemaShop/searchCinemaPrice?minPriceStr=2000&maxPriceStr=3000">2000円～3000円</a><br>
<a href="/cinemaShop/searchCinemaPrice?minPriceStr=3000&maxPriceStr">3000円～</a>

<h3>ジャンル</h3>
<a href="/cinemaShop/searchCinemaGenre?genreStr=SF">SF</a><br>
<a href="/cinemaShop/searchCinemaGenre?genreStr=ホラー">ホラー</a><br>
<a href="/cinemaShop/searchCinemaGenre?genreStr=ファンタジー">ファンタジー</a><br>
<a href="/cinemaShop/searchCinemaGenre?genreStr=アクション">アクション</a>

<h3>メディアタイプ</h3>
<a href="/cinemaShop/searchCinemaMediaType?mediaTypeStr=DVD">DVD</a>

<h2>商品一覧</h2>
<form action="/cinemaShop/searchCinemaTitle" method="get">
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
			<a href="/cinemaShop/detail/${child.id}"><c:out value="${child.title}"/></a>
			</th>
			<th><c:out value="${child.price}"/>円</th>
		</tr>
	</c:forEach>
 </tbody>
</table>

<!-- 試しでBootstrapを追加する -->

<table class="table table-striped">
	<tbody>
			<tr>
		<c:forEach var="child" items="${listPage.childPageList}">
				<th>
					<a href="/cinemaShop/detail/${child.id}">
					<img src="../img/1132686_1200-580x824.jpg" width="100" height="180">
					</a>
					<br><c:out value="${child.title}"/><br>
					<br>監督の名前<br>
					<br><c:out value="${child.price}"/>円<br>
				</th>
		</c:forEach>
			</tr>
	</tbody>
</table>


</body>
</html>