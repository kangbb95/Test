
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
</style>
</head>
<body>
	<h1>관리자페이지</h1>

	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<button type="button" class="btn btn-success"><a href="/insertMovieFrm">영화 신규등록</a></button><br>
		<button type="button" class="btn btn-info"><a href="showMovieList">영화 정보리스트</a></button><br>
		<button type="button" class="btn btn-warning"><a href="#">영화 정보수정</a></button><br>
		<button type="button" class="btn btn-danger"><a href="/deleteMovieFrm">영화 삭제</a></button>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>


</body>
</html>