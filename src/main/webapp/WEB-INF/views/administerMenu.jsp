<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者メニュー</title>
</head>
<body>
<h2 align="center">管理者メニュー</h2>
<div align="center">
	<table border="1">
		<tr align="center"><td><a href="<%=request.getContextPath() %>/admin/displayList">取り扱い商品一覧</a></td></tr>
		<tr align="center"><td><a href="<%=request.getContextPath() %>/admin/deleteList">削除済み商品一覧</a></td></tr>
		<tr align="center"><td><a href="<%=request.getContextPath() %>/admin/orderList">注文一覧</a></td></tr>
		<tr align="center"><td><a href="<%=request.getContextPath() %>/admin/insert">商品を登録</a></td></tr>
	</table>
	<br>
	<table border="1">
		<tr align="center"><td><a href="<%=request.getContextPath() %>/admin/register/">新規登録</a></td></tr>
		<tr align="center"><td><a href="<%=request.getContextPath() %>/admin/logout">ログアウト</a></td></tr>
	</table>
	<br>
	<br>
	<script>
		img = new Array();

		img = "<%=request.getContextPath() %>/img/映画泥棒.jpg";
		document.write("<img src=\""+img+"\" width=\"500\">");
	</script>
	
</div>
</body>
</html>