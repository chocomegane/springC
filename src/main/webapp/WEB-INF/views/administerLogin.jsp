<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<!--  <font color="#ff0000">・入力情報は不正です</FONT><BR> -->
  <form action="#" method="post">
    <label>メールアドレス：<input type="text" name="email"></label><br>
    <label>パスワード： <input type="password" name="password"></label><br>
    <input type="submit" value="ログイン">
  </form>
</div>
</body>
</html>