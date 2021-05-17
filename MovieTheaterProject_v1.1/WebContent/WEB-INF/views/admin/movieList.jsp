<%@page import="movie.model.vo.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Movie> list = (ArrayList<Movie>) request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.style-hover{
	background-color: white;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<fieldset>
			<legend>영화목록</legend>
			<table class="style-hover" style="width: 100%;">
				<tr class="table-primary">
					<th>영화번호</th>
					<th>제목</th>
					<th>감독</th>
					<th>개봉일</th>
				</tr>
				<%
					for (Movie mv : list) {
				%>
				<tr class="table-light">
					<td><%=mv.getMovieNo()%></td>
					<td><%=mv.getMovieTitle()%></td>
					<td><%=mv.getMovieDirector() %></td>
					<td><%=mv.getMovieDate()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</fieldset>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>