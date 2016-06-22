<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="userCommon.jsp"%>
<h2 align="center">ご注文内容</h2>
<hr>
<form action="/finishPayment" method="post">
	<table border="1" width="350" align="center">
		<tr>
			<th>商品名</th>
			<th>価格</th>
			<th>個数</th>
			<th>税抜き価格</th>
			<th>小計</th>
		</tr>
		<tr>
			<td>パソコン</td>
			<td>&yen;50,000</td>
			<td>１</td>
			<td>&yen;50,000</td>
			<td>&yen;54,000</td>
		</tr>
		<tr>
			<td>マウス</td>
			<td>&yen;500</td>
			<td>2</td>
			<td>&yen;1,000</td>
			<td>&yen;1,080</td>
		</tr>
		<tr>
			<td>消費税</td>
			<td align="right" colspan="4">&yen;4,080</td>

		</tr>
		<tr>
			<td>送料一律</td>
			<td align="right" colspan="4">&yen;500</td>

		</tr>
		<tr>
			<td>商品合計</td>
			<td align="right" colspan="4">&yen;55,580</td>
		</tr>
	</table>
	<br>
	<h2 align="center">お届け先</h2>
	<hr>
	<div align="center">
		お名前：ラクス太郎<br> メールアドレス：rakus@co.jp<br> 住所：東京都渋谷区<br>
		電話番号：0568-544-2585<br>
		<br> <input type="submit" value="確定">
	</div>
</form>
</body>
</html>