<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp"%>
<script src="<%=request.getContextPath()%>/js/cinemaListJson.js"></script><body>
<br>
<div class="main">
<h3>取り扱い商品一覧</h3>
<!-- テキスト検索 -->
<form action="<%=request.getContextPath() %>/serchCinemaTitle">
<div class="input-group col-xs-6">
		<input type="text" name="title" class="form-control" placeholder="検索したい商品を入力してください">
	<span class="input-group-btn">
		<button type="submit" class="btn btn-default" aria-expanded="false">
			<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
		</button>
	</span>
</div>
</form>
<div style="display:inline-flex">

<!-- 値段検索 -->
<form action="<%=request.getContextPath() %>/serchCinemaPrice" name="searchCinemaPriceForm">
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
<form action="<%=request.getContextPath() %>/serchCinema/mediaType" name="searchCinemaMediaTypeForm">	
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
<div id="dvd">


</div>
<input type="hidden" id="contextPath"
		value="${pageContext.request.contextPath}">
			 <div id="page">
	 <table>
	 <tr>
	 <c:forEach varStatus="status" begin="1" step="1" end="${pageNumber}">
	 <td>
	 <p class="document">
	 <input  type="submit" value="${status.index}" id="paging_${status.index}" class="pagings"/>
	 </p>
	 </td>
	 </c:forEach>
	 </tr>
	 </table>
</body>
</html>