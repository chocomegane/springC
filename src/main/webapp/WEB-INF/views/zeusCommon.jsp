<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrapのために追加 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

<script type="text/javascript">
	//<!-- Bootstrapの値段検索 -->
		function findByPrice(){
			document.searchCinemaPriceForm.submit();
	};
	
	//<!-- Bootstrapのジャンル検索 -->
		function findByGenre(){
			document.searchCinemaGenreForm.submit();
		};

	//<!-- Bootstrapのメディアタイプ検索	 -->
		function findByMediaType(){
			document.searchCinemaMediaTypeForm.submit();
		}
		
	//<!-- ダブルサブミット対策 -->	
		function DisableButton(b){
		b.disabled = true;
		b.form.submit();
				}
	//<!-- 商品削除の警告 -->	
		function deleteConfirm(btn){
			if(confirm("この商品を削除したいと思うことは確かですか？")){
				btn.parentNode.submit();
			}
	}
		
	//<!-- 商品の再表示の警告 -->
		function displayConfirm(btn){
			if(confirm("この商品を再表示したいと思うことは確かですか？")){
				btn.parentNode.submit();
			}
		}
		
	//<!-- Bootstrapの削除された値段検索 -->
		function findByDeletePrice(){
			document.searchDeleteCinemaPriceForm.submit();
	}
	
	//<!-- Bootstrapの削除されたジャンル検索 -->
		function findByDeleteGenre(){
			document.searchDeleteCinemaGenreForm.submit();
		}

	//<!-- Bootstrapの削除されたメディアタイプ検索	 -->
		function findByDeleteMediaType(){
			document.searchDeleteCinemaMediaTypeForm.submit();
		}

	</script>
	
	<!-- Bootstarapで使うために追加 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

