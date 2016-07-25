<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

function posting(){
	('.article').on('click',function(){
		var getArticle =  $(this).val();
	})
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<center><h1>memo</h1></center>
<br>
<br>

<input type="text" name="Posting" id="article"><input type="submit" value="投稿" onclick= "posting()">
----------------------------------------------------------------------------------------------------------
<div class="articleList" >
</div>

</body>
</html>