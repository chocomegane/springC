<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

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
						<img src="../img/1132686_1200-580x824.jpg" 
						class="img-responsive img-rounded" width="100" height="300">
						</a>
						<br><c:out value="${child.title}"/><br>
						<br>監督の名前<br>
						<br><c:out value="${child.price}"/>円<br>
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