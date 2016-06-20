<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>
<body>

<h2>商品一覧/h2>
<table border="1" align="center">
 <tbody>
  <tr>
  	<th>商品名</th>
  	<th>価格</th>
  </tr>
	<c:forEach var="cinema" items="${cinemaList}">
		<tr>
			<th><c:out value="${cinema.title}"/></th>
			<th><c:out value="${cinema.price}"/></th>
		</tr>
	</c:forEach>
 </tbody>
</table>

</body>
</html>