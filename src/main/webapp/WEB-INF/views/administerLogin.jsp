<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp"%>
<body>
<!-- <header>
		<div id="userHeader" align="right">
			<p>こんにちは管理者さん</p>
		</div>
				<div id="linkHeader" align="left">
			<h1 align ="left"><a href="../user/itemList.html"><img src="../img/rakus.jpg" width="50"
				height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1>
		<div id="title" align="center">
		</div>
</header>
 -->
<div align="center">
  <h2>管理者ログイン</h2>
  <form:errors path="adminLoginForm.*" />
  <form:form action="/admin/login" method="post" modelAttribute="adminLoginForm">
    <form:label path="email">メールアドレス：<form:input path="email"/></form:label><br>
    <form:label path="password">パスワード：<form:password path="password"/></form:label><br>
    <input type="submit" value="ログイン">
  </form:form>
</div>
</body>
</html>