<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<body>

<h3>値段</h3>
<a href="/admin/serchCinema/price?minPriceStr=0&maxPriceStr=1000">～1000円</a><br>
<a href="/admin/serchCinema/price?minPriceStr=1000&maxPriceStr=2000">1000円～2000円</a><br>
<a href="/admin/serchCinema/price?minPriceStr=2000&maxPriceStr=3000">2000円～3000円</a><br>
<a href="/admin/serchCinema/price?minPriceStr=3000&maxPriceStr">3000円～</a>

<h3>ジャンル</h3>
<a href="/admin/serchCinema/genre?genreStr=SF">SF</a><br>
<a href="/admin/serchCinema/genre?genreStr=ホラー">ホラー</a><br>
<a href="/admin/serchCinema/genre?genreStr=ファンタジー">ファンタジー</a><br>
<a href="/admin/serchCinema/genre?genreStr=アクション">アクション</a>

<h3>メディアタイプ</h3>
<a href="/admin/serchCinema/mediaType?mediaTypeStr=DVD">DVD</a>

<h2>商品一覧</h2>
<form action="/admin/serchCinema/title" method="get">
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
			<a href="/admin/cinemaDetail/detail/${child.id}"><c:out value="${child.title}"/></a>
			</th>
			<th><fmt:formatNumber value="${child.price}" pattern="#,###"/>円</th>
			<th>
				<form:form action="">
					<input type="button" value="削除(次期開発)">
				</form:form>
			</th>
		</tr>
	</c:forEach>
 </tbody>
</table>

<!-- テキスト検索 -->
<c:out value="${message}"/>
<c:out value="${message2}"/><br>

<form action="/cinemaShop/searchCinemaTitle">
<div class="input-group col-xs-6">
		<input type="text" name="title" class="form-control" placeholder="検索したい商品を入力してください">
	<span class="input-group-btn">
		<button type="submit" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
			<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
		</button>
	</span>
</div>
</form>
<div style="display:inline-flex">

<!-- 値段検索 -->
<form action="/cinemaShop/searchCinemaPrice" name="searchCinemaPriceForm">
	<select name="price" class="form-control" style="width: 180px" onchange="findByPrice();">
	  <option>価格</option>
	  <option value="0">～1000円</option>
	  <option value="1">1000円～2000円</option>
	  <option value="2">2000円～3000円</option>
	  <option value="3">3000円～</option>
	</select>
</form>

<!-- ジャンル検索 -->
<form action="/cinemaShop/searchCinemaGenre" name="searchCinemaGenreForm">	
	<select name ="genre" class="form-control" style="width: 180px" onchange="findByGenre();">
	  <option>ジャンル</option>
	  <option value="SF">SF</option>
	  <option value="ホラー">ホラー</option>
	  <option value="ファンタジー">ファンタジー</option>
	  <option value="アクション">アクション</option>
	</select>
</form>

<!-- メディアタイプ検索 -->
<form action="/cinemaShop/searchCinemaMediaType" name="searchCinemaMediaTypeForm">	
	<select name="mediaType" class="form-control" style="width: 180px" onchange="findByMediaType();">
	  <option>メディアタイプ</option>
	  <option value="DVD">DVD</option>
	</select>
</form>

</div>

<br><br><br>

<table class="table table-striped">
	<tbody>
			<tr>
		<c:forEach var="child" items="${listPage.childPageList}" varStatus="status">
					<th>
						<a href="/cinemaShop/detail/${child.id}">
						<img src="../img/${child.imagePath}" 
						class="img-responsive img-rounded" width="100" height="300">
						</a>
						<br><a href="/cinemaShop/detail/${child.id}"><c:out value="${child.title}"/></a><br>
						<br><c:out value="${child.directedBy}"/><br>
						<br><fmt:formatNumber value="${child.price}" pattern="#,###"/>円
						<br>
							<form:form action="">
								<input type="button" value="削除(次期開発)">
							</form:form>
						<br>
				</th>
				<c:if test="${status.count%4==0}">
					</tr>
					<tr>
				</c:if>
		</c:forEach>
			</tr>
	</tbody>
</table>


</body>
</html>