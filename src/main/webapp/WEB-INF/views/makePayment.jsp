<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="userCommon.jsp"%>
<h2 align="center">ご注文内容</h2>
<hr>
<form action="/finishPayment" method="post">
        <table border ="1"  align="center">
            <tr>
                <th colspan="2">商品名</th>
                <th>価格</th>
                <th>個数</th>
                <th></th>
            </tr>
            <c:forEach var="cartItem" items="${cartPage.cartListChildPage}">
            <tr>
				<td><a href="itemDetail.html"><img src="../img/pc.jpg" width="150"height="125" alt="商品画像"></a></td>
                <td><a href="itemDetail.html"><c:out value="${cartItem.title}"/></a></td>
                <td><fmt:formatNumber value="${cartItem.price}" pattern="###,###,###"/></td>
                <td><c:out value="${cartItem.quantity}"/>枚</td>
                <td>
                    <form action="viewShoppingCart.html" method="post">
                        <input type="hidden" name="item.id" value="1">
                        <input type="submit" value="削除">
                    </form>
                </td>
            </tr>
            </c:forEach>
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