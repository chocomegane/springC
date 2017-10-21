/**
 * 
 * 検索を取得し映画リストにHTMLを生成する
 * ※映画一覧が表示されるたび呼び出し
 * 
 * 
 */
function createHtml(json) {
	var htmlSource = "<br>";
	for (var i = 0; i < json.length; i++) {
		var provisionalHtmlSource = "";
		var numberFormat = /(\d)(?=(\d\d\d)+(?!\d))/g;
		var imagePath = json[i].imagePath;
		var title = json[i].title;
		var director = json[i].directedBy;
		var price = String(json[i].price).replace(numberFormat, '$1,');
		var id = json[i].id;
		if ((i + 1) % 4 == 0 && !i == 0) {
			var cinemaDataTemplate = '<th>'
					+ '<a href="/detail/%{ID}"><img src="%{IMAGEPATH}" class="img-responsive img-rounded" width="100" height="300"></a>'
					+ '<br><a href="detail/%{ID}">%{TITLE}</a><br><br>%{DIRECTOR}<br><br>%{PRICE}</th>'
					+ '</tr><tr>'
			provisionalHtmlSource = cinemaDataTemplate.replace(/%{ID}/g, id)
					.replace(
							/%{IMAGEPATH}/g, imagePath).replace(/%{DIRECTOR}/g,
							director).replace(/%{TITLE}/g, title).replace(
							/%{PRICE}/g, price)

		} else {
			var cinemaDataTemplate = '<th>'
					+ '<a href="detail/%{ID}"><img src="%{IMAGEPATH}" class="img-responsive img-rounded" width="100" height="300"></a>'
					+ '<br><a href="detail/%{ID}">%{TITLE}</a><br><br>%{DIRECTOR}<br><br>%{PRICE}円</th>'

			provisionalHtmlSource = cinemaDataTemplate.replace(/%{ID}/g, id)
					.replace(
							/%{IMAGEPATH}/g, imagePath).replace(/%{DIRECTOR}/g,
							director).replace(/%{TITLE}/g, title).replace(
							/%{PRICE}/g, price)

		}
	
	htmlSource = htmlSource + provisionalHtmlSource;
	}
	return htmlSource
}

$(function() {

	var contextPath = $("#contextPath").val();

	$.getJSON(contextPath + '/json/exe', function(json) {

		var htmlSource = "";

		htmlSource = createHtml(json);

		htmlSource = '1ページ<br><table class="table table-striped"><tbody><tr>'
				+ htmlSource + '</tr></tbody></table>';

		$("#dvd").append(htmlSource);

	});
});

$(function() {
	var contextPath = $("#contextPath").val();
	$('.pagings')
			.on(
					'click',
					function() {
						var page = $(this).val();
						page = page - 1;
						$
								.getJSON(
										contextPath + '/json/exe/paging?page='
												+ page,
										function(json) {
											var htmlSource = "";

											htmlSource = htmlSource
													+ createHtml(json);

											htmlSource = page
													+ 1
													+ 'ページ<br><table class="table table-striped"><tbody><tr>'
													+ htmlSource
													+ '</tr></tbody></table>';
											$("#dvd").html(htmlSource);
										});
					});
});