	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="adminCommon.jsp" %>
<title>一括ダウンロード</title>
</head>
<body>
<div align="center" >
<h1>一括ダウンロード</h1>
<h2>csvfileは　"タイトル","金額","ジャンル","公開日","メディアタイプ","作成会社","監督","レーティング","詳細","imgCode or imgPath"の順で作成してください</h2>
<h2>zipfileには直下にイメージを入れてください</h2>
<h3>イメージコード</h3>
<form:form action="${pageContext.request.contextPath}/admin/bulkDownload/exe" modelAttribute="bulkCinemaForm" enctype="multipart/form-data">
<c:out value="${csvErrMassegePath}" />
 csv<input type="file" name = "csvFile"  accept="text/csv" />
 <input type="submit" value="一括ダウンロード" class="btn btn-default btn-sm">
 <input type="hidden" name="downloadType" value="imgCode">
  </form:form>
<br> 
<br>
<br> 
<h3>イメージパス</h3>
<form:form action="${pageContext.request.contextPath}/admin/bulkDownload/exe" modelAttribute="bulkCinemaForm" enctype="multipart/form-data">
<c:out value="${csvErrMassege}" /> 
 csv<input type="file" name = "csvFile"  accept="text/csv"/>
 <c:out value="${zipErrMassege}" /> 
 zip<input type="file" name = "zipFile"  accept="text/zip"/>
 <input type="hidden" name="downloadType" value="imgPath">  
 <input type="submit" value="一括ダウンロード" class="btn btn-default btn-sm">
</form:form>
<c:out value="${errMessage}" /> 

 </div>
 </body>
 
