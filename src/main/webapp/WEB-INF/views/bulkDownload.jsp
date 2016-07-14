	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>
<title>Insert title here</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/admin/bulkDownload/exe" modelAttribute="bulkCinemaForm" enctype="multipart/form-data">
 csv<input type="file" name = "csvFile"  accept="text/csv"/>
 <input type="submit">
</form:form>

