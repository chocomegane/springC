<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrapのために追加 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript">


//jQueryの機能script

/* //スクロールで動く画像
$(document).ready(function(){

	//#MovieContents内スクロール初期化
	$('#MovieContents').scrollTop(0,0);
	//#MovieContentsをスクロールでfunction実行
	$('#MovieContents').scroll(function(){
		//#MovieContentsからのスクロール距離
		var MH = -400 * Math.floor(($(this).scrollTop() / 40));
		if( MH >= -292800){
		$('#MVimg').css({"margin-top": MH});
		}else if( MH <= -292800 && MH >= -300000){
		$('#MVimg').css({"margin-top": "-292800px"});
		}else if( MH <= -300000 ){
		//全て再生後に初期化。ループに入る
		$('#MovieContents').scrollTop(0,0);
		}
	});
}); */

//スクロールボックス
/* $(function () {
    $('.imgBox img').hide(); //ページ上の画像を隠す
});

var i = 0;
var int=0;
$(window).bind("load", function() {
  //ページコンテンツのロードが完了後、機能させる
    var int = setInterval("doThis(i)",500);
  //フェードするスピード
});


function doThis() {
    var images = $('img').length;//画像の数を数える
    if (i >= images) {// ループ
        clearInterval(int);//最後の画像までいくとループ終了
    }
    $('img:hidden').eq(0).fadeIn(500);//一つずつ表示する
    i++;
} 


$(function() {
	  $('#accordion dd').hide();
	  $('#accordion dt a').click(function(){
	    $('#accordion dd').slideUp();
	    $(this).parent().next().slideDown();
	    return false;
	  });
	});
	
$(function(){
    $(".open").click(function(){
      $("#slideBox").slideToggle("slow");
    });
}); */
//////////////////////////////////////////////////////////////////////////////////////////////////////


	function findByPrice() {
		document.searchCinemaPriceForm.submit();
	};

	//<!-- Bootstrapのジャンル検索 -->
	function findByGenre() {
		document.searchCinemaGenreForm.submit();
	};

	//<!-- Bootstrapのメディアタイプ検索	 -->
	function findByMediaType() {
		document.searchCinemaMediaTypeForm.submit();
	}

	//<!-- ダブルサブミット対策 -->	
	function DisableButton(b) {
		b.disabled = true;
		b.form.submit();
	}
	//<!-- 商品削除の警告 -->	
	function deleteConfirm(btn) {
		if (confirm("この商品を削除したいと思うことは確かですか？")) {
			btn.parentNode.submit();
		}
	}

	//<!-- 商品の再表示の警告 -->
	function displayConfirm(btn) {
		if (confirm("この商品を再表示したいと思うことは確かですか？")) {
			btn.parentNode.submit();
		}
	}

	//<!-- Bootstrapの削除された値段検索 -->
	function findByDeletePrice() {
		document.searchDeleteCinemaPriceForm.submit();
	}

	//<!-- Bootstrapの削除されたジャンル検索 -->
	function findByDeleteGenre() {
		document.searchDeleteCinemaGenreForm.submit();
	}

	//<!-- Bootstrapの削除されたメディアタイプ検索	 -->
	function findByDeleteMediaType() {
		document.searchDeleteCinemaMediaTypeForm.submit();
	}

	


</script>

<!-- Bootstarapで使うために追加                        +  jqueryUI   -->
<!-- <link rel="stylesheet" href="themes/base/jquery.ui.all.css"> -->
<!-- <script type="text/javascript" src="ui/jquery-ui-1.8.12.custom.min.js"></script> -->

<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>