<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>
<body>
    
     
    <h2 align="center">ショッピングカート一覧</h2>

   <p align="center"><c:out value="${message}"></c:out></p>
   
   
        <table border ="1"  align="center">
        
            <!-- <tr>
                <th colspan="2">商品名</th>
                <th>価格</th>
                <th>個数</th>
                <th></th>
            </tr> -->
            <c:forEach var="cartItem" items="${cartPage.cartListChildPage}">
            <tr>
				<td><a href="/cinemaShop/detail/${cartItem.orderCinemaId}"><img src="/img/pc.jpg" width="150"height="125" alt="商品画像"></a></td>
                <td><a href="/cinemaShop/detail/${cartItem.orderCinemaId}"><c:out value="${cartItem.title}"/></a></td>
                <td><fmt:formatNumber value="${cartItem.price}" pattern="###,###,###"/></td>
                <td><c:out value="${cartItem.quantity}"/>枚</td>
                <td>
                <form:form action="/cinemaShop/delete" modelAttribute="">
                		<input type="hidden" name="orderCinemaId" value="<c:out value="${cartItem.orderCinemaId}"/>">
                        <input type="submit" value="削除"/>
                </form:form>
                </td>
            </tr>
            </c:forEach>
        </table><br>

    <div  align="center"><a href="/cinemaShop/mekePayment"><c:out value="${payment}"/></a></div>
</body>