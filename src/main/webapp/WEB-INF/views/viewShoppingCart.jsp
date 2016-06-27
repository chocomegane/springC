<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>
<body>
    
     
    <h2 align="center">ショッピングカート一覧</h2>
<c:if test="${!flag}">
   <p align="center">カートには何も入っていません</p>
   </c:if>
   
   <c:if test="${flag}">
        <table border ="1"  align="center">
        
            <tr>
                <th colspan="2">商品名</th>
                <th>価格</th>
                <th>個数</th>
                <th></th>
            </tr>
            <c:forEach var="cartItem" items="${cartPage.cartListChildPage}">
            <tr>
				<td><a href="/cinemaShop/detail/${cartItem.cinemaId}"><img src="/img/${cartItem.imagePath}" width="150" alt="商品画像"></a></td>
                <td><a href="/cinemaShop/detail/${cartItem.cinemaId}"><c:out value="${cartItem.title}"/></a></td>
                <td>¥<fmt:formatNumber value="${cartItem.price}" pattern="###,###,###,###,###"/></td>
                <td><c:out value="${cartItem.quantity}"/>枚</td>
                <td>
                <form:form action="/cinemaShop/insert" modelAttribute="cartForm">
                		<input type="hidden" name="cinemaId" value="<c:out value="${cartItem.cinemaId}"/>">
                		<input type="hidden" name="quantity" value="1">
                        <input type="submit" value="1個追加"/>
                </form:form>
                
                <form:form action="/cinemaShop/delete" modelAttribute="">
                		<input type="hidden" name="orderCinemaId" value="<c:out value="${cartItem.orderCinemaId}"/>">
                        <input type="submit" value="まとめて削除"/>
                </form:form>
                </td>
            </tr>
            </c:forEach>
            
        </table><br>

		<table border ="3"  align="center">
			<tr>
                <th>小計</th>
                <th>¥<fmt:formatNumber value="${cartPage.totalPrice}" pattern="###,###,###,###,###"/></th>
            </tr>
         </table><br>
    <div  align="center"><a href="/cinemaShop/mekePayment">決済へ</a></div>
    </c:if>
</body>