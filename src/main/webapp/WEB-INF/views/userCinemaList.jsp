<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

<br>
<div class="main">
<!-- テキスト検索 -->
<form action="<%=request.getContextPath() %>/searchCinemaTitle" name="searchTitleForm">
<div class="input-group col-xs-6">
		<input type="text" name="title" class="form-control" placeholder="検索したい商品を入力してください">
	<span class="input-group-btn">
		<button type="submit" class="btn btn-default" aria-expanded="false" >
			<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
		</button>
	</span>
</div>
</form>
<div style="display:inline-flex">

<!-- 値段検索 -->
<form action="<%=request.getContextPath() %>/searchCinemaPrice" name="searchCinemaPriceForm">
	<select name="price" class="form-control" style="width: 180px" onchange="findByPrice();">
	  <option>価格</option>
	  <option value="0">～1000円</option>
	  <option value="1">1000円～2000円</option>
	  <option value="2">2000円～3000円</option>
	  <option value="3">3000円～</option>
	</select>
</form>

<!-- ジャンル検索 -->
<form action="<%=request.getContextPath() %>/searchCinemaGenre" name="searchCinemaGenreForm">	
	<select name ="genre" class="form-control" style="width: 180px" onchange="findByGenre();">
	  <option>ジャンル</option>
	  <option value="SF">SF</option>
	  <option value="ホラー">ホラー</option>
	  <option value="ファンタジー">ファンタジー</option>
	  <option value="アクション">アクション</option>
	</select>
</form>

<!-- メディアタイプ検索 -->
<form action="<%=request.getContextPath() %>/searchCinemaMediaType" name="searchCinemaMediaTypeForm">	
	<select name="mediaType" class="form-control" style="width: 180px" onchange="findByMediaType();">
	  <option>メディアタイプ</option>
	  <option value="DVD">DVD</option>
	  <option value="ブルーレイ">ブルーレイ</option>
	</select>
</form>

</div>
<br>
	<c:out value="${searchResult}"/>
	<font color="red">
	&nbsp;&nbsp;&nbsp;<c:out value="${message}"/><c:out value="${message2}"/>
	</font>
<br><br><br>
<table class="table table-striped">
	<tbody>
		<tr>
	<c:forEach var="child" items="${listPage.childPageList}" varStatus="status">
					<th>
						<a href="<%=request.getContextPath() %>/detail/${child.id}">
						<c:if test="${child.imagePath.length() < 60}">
						<img src="<%=request.getContextPath() %>/img/${child.imagePath}" 
						class="img-responsive img-rounded" width="100" height="300">
						</c:if>
						<c:if test="${child.imagePath.length() > 60}">
						<img src="${child.imagePath}" 
						class="img-responsive img-rounded" width="100" height="300">
						</c:if>
						</a>
						<br><a href="<%=request.getContextPath() %>/detail/${child.id}"><c:out value="${child.title}"/></a><br>
						<br><c:out value="${child.directedBy}"/><br>
						<br><fmt:formatNumber value="${child.price}" pattern="#,###円"/>
					</th>
					<c:if test="${status.count%4==0}"> 
					</tr>
					<tr>
					</c:if>
		</c:forEach>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>