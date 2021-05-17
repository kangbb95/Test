<%@page import="schedule.model.vo.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Schedule> list = (ArrayList<Schedule>) request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
			<fieldset>
				<legend>스케쥴목록</legend>
				<table class="category" style="width: 100%;">
					<tr>
						<th>스케쥴번호</th>
						<th>상영관번호</th>
						<th>관람시작일</th>
						<th>관람종료일</th>
						<th>영화번호</th>
					</tr>
					<%
						for (Schedule s : list) {
					%>
					<tr class="table-light">
						<td><%=s.getScheduleNo()%></td>
						<td><%=s.getTheaterNo()%></td>
						<td><%=s.getStartDate()%></td>
						<td><%=s.getEndDate()%></td>
						<td><%=s.getMovieNo()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</fieldset>
			<form action="/deleteMovie" type="get">
			<input type="submit" value="관람종료일 지난 영화 삭제">
			</form>
	</div>
	<script>
		
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>