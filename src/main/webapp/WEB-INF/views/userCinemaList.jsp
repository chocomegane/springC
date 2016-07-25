<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp"%>
<script type="text/javascript">
$(function(){
	
	var contextPath = $("#contextPath").val();
	
	$.getJSON(
			contextPath + '/json/exe',
			function(json) {

					var htmlSource ="<br>";
				for (var i = 0; i < json.length; i++) {

					var numberFormat = /(\d)(?=(\d\d\d)+(?!\d))/g;
					var imagePath = json[i].imagePath;
					var title = json[i].title;
					var director = json[i].directedBy;
					var price = String(json[i].price).replace(
							numberFormat, '$1,');
					var id = json[i].id;
					if ((i+1) % 4 == 0 && !i == 0) {
						var cinemaDataTemplate =
						'<th>'
								+ '<a href="%{CONTEXTPATH}/detail/%{ID}"><img src="%{IMAGEPATH}" class="img-responsive img-rounded" width="100" height="300"></a>'
								+ '<br><a href="%{CONTEXTPATH}/detail/%{ID}">%{TITLE}</a><br><br>%{DIRECTOR}<br><br>%{PRICE}</th>'
								+ '</tr><tr>'
								provisionalHtmlSource =  cinemaDataTemplate
										.replace(/%{ID}/g, id)
										.replace(/%{CONTEXTPATH}/g,
												contextPath)
										.replace(/%{IMAGEPATH}/g,
												imagePath)
										.replace(/%{DIRECTOR}/g,
												director)
										.replace(/%{TITLE}/g, title)
										.replace(/%{PRICE}/g, price)
								
					}else {
						var cinemaDataTemplate = '<th>'
								+ '<a href="%{CONTEXTPATH}/detail/%{ID}"><img src="%{IMAGEPATH}" class="img-responsive img-rounded" width="100" height="300"></a>'
								+ '<br><a href="%{CONTEXTPATH}/detail/%{ID}">%{TITLE}</a><br><br>%{DIRECTOR}<br><br>%{PRICE}円</th>'
					
						 provisionalHtmlSource =  cinemaDataTemplate
										.replace(/%{ID}/g, id)
										.replace(/%{CONTEXTPATH}/g,
												contextPath)
										.replace(/%{IMAGEPATH}/g,
												imagePath)
										.replace(/%{DIRECTOR}/g,
												director)
										.replace(/%{TITLE}/g, title)
										.replace(/%{PRICE}/g, price)
								

					}
					htmlSource = htmlSource + provisionalHtmlSource;
				

				}
				
				htmlSource = '1ページ<br><table class="table table-striped"><tbody><tr>'
					+htmlSource+ '</tr></tbody></table>';
					
					$("#dvd").append(htmlSource);

			});	
});

	$(function() {
		var contextPath = $("#contextPath").val();

		$('.pagings').on('click',function(){
			
			var page = $(this).val();
			page=page-1;
			
			$.getJSON(
					contextPath + '/json/exe/paging?page='+page,
					function(json) {

							var htmlSource ="<br>";
						for (var i = 0; i < json.length; i++) {
							

							var numberFormat = /(\d)(?=(\d\d\d)+(?!\d))/g;
							var imagePath = json[i].imagePath;
							var title = json[i].title;
							var director = json[i].directedBy;
							var price = String(json[i].price).replace(
									numberFormat, '$1,');
							var id = json[i].id;
							if ((i+1) % 4 == 0 && !i == 0) {
								var cinemaDataTemplate =
								'<th>'
										+ '<a href="%{CONTEXTPATH}/detail/%{ID}"><img src="%{IMAGEPATH}" class="img-responsive img-rounded" width="100" height="300"></a>'
										+ '<br><a href="%{CONTEXTPATH}/detail/%{ID}">%{TITLE}</a><br><br>%{DIRECTOR}<br><br>%{PRICE}</th>'
										+ '</tr><tr>'
										provisionalHtmlSource =  cinemaDataTemplate
												.replace(/%{ID}/g, id)
												.replace(/%{CONTEXTPATH}/g,
														contextPath)
												.replace(/%{IMAGEPATH}/g,
														imagePath)
												.replace(/%{DIRECTOR}/g,
														director)
												.replace(/%{TITLE}/g, title)
												.replace(/%{PRICE}/g, price)
										
							}else {
								var cinemaDataTemplate = '<th>'
										+ '<a href="%{CONTEXTPATH}/detail/%{ID}"><img src="%{IMAGEPATH}" class="img-responsive img-rounded" width="100" height="300"></a>'
										+ '<br><a href="%{CONTEXTPATH}/detail/%{ID}">%{TITLE}</a><br><br>%{DIRECTOR}<br><br>%{PRICE}円</th>'
							
								 provisionalHtmlSource =  cinemaDataTemplate
												.replace(/%{ID}/g, id)
												.replace(/%{CONTEXTPATH}/g,
														contextPath)
												.replace(/%{IMAGEPATH}/g,
														imagePath)
												.replace(/%{DIRECTOR}/g,
														director)
												.replace(/%{TITLE}/g, title)
												.replace(/%{PRICE}/g, price)
										

							}
							htmlSource = htmlSource + provisionalHtmlSource;
								
						

						}
						
						htmlSource = page+1+'ページ<br><table class="table table-striped"><tbody><tr>'
							+htmlSource+ '</tr></tbody></table>';
							$("#dvd").html(htmlSource);
							

					});	
		});
		
	});
	<!--
//-->
</script>

<br>
<div class="main">
	<!-- テキスト検索 -->
	<form action="<%=request.getContextPath()%>/searchCinemaTitle"
		name="searchTitleForm">
		<div class="input-group col-xs-6">
			<input type="text" name="title" class="form-control"
				placeholder="検索したい商品を入力してください"> <span
				class="input-group-btn">
				<button type="submit" class="btn btn-default" aria-expanded="false">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				</button>
			</span>
		</div>
	</form>
	<div style="display: inline-flex">

		<!-- 値段検索 -->
		<form action="<%=request.getContextPath()%>/searchCinemaPrice"
			name="searchCinemaPriceForm">
			<select name="price" class="form-control" style="width: 180px"
				onchange="findByPrice();">
				<option>価格</option>
				<option value="0">～1000円</option>
				<option value="1">1000円～2000円</option>
				<option value="2">2000円～3000円</option>
				<option value="3">3000円～</option>
			</select>
		</form>

		<!-- ジャンル検索 -->
		<form action="<%=request.getContextPath()%>/searchCinemaGenre"
			name="searchCinemaGenreForm">
			<select name="genre" class="form-control" style="width: 180px"
				onchange="findByGenre();">
				<option>ジャンル</option>
				<option value="SF">SF</option>
				<option value="ホラー">ホラー</option>
				<option value="ファンタジー">ファンタジー</option>
				<option value="アクション">アクション</option>
			</select>
		</form>

		<!-- メディアタイプ検索 -->
		<form action="<%=request.getContextPath()%>/searchCinemaMediaType"
			name="searchCinemaMediaTypeForm">
			<select name="mediaType" class="form-control" style="width: 180px"
				onchange="findByMediaType();">
				<option>メディアタイプ</option>
				<option value="DVD">DVD</option>
				<option value="ブルーレイ">ブルーレイ</option>
			</select>
		</form>

	</div>
	<br>
	<c:out value="${searchResult}" />
	<font color="red"> &nbsp;&nbsp;&nbsp;<c:out value="${message}" />
		<c:out value="${message2}" />
	</font> <br> <br> <br>

	<div id="dvd">
	</div>
	 <div id="page">
	 <table>
	 <tr>
	 <c:forEach varStatus="status" begin="1" step="1" end="${pageNumber}">
	 <td>
	 <p class="document">
	 <input  type="submit" value="${status.index}" id="paging_${status.index}" class="pagings"/>
	 <%-- <a href="<%= request.getContextPath()%>/?pageNumber="+<c:out value="${status.index}"/>"><c:out value="${status.index}" /></a> --%>
	 </p>
	 </td>
	 </c:forEach>
	 </tr>
	 </table>
	 

	 
	</div> 
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<input type="hidden" id="contextPath"
		value="${pageContext.request.contextPath}">
		
		</body>
	</html>