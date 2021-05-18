<%@page import="board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
    String pageNavi = (String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file= "/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<fieldset>
			<legend>자유게시판</legend>
			<%if (m != null) {%>
				<div>
					<a class="btn btn-info writeBtn" href="/boardWriteFrm">글쓰기</a>
				</div>
			<%} %>
			<table class="table" style="width:100%;">
				<tr class="table-primary">
					<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th>
				</tr>
				<%for(Board board: list) {%>
				<tr class="table-light">
					<td><%=board.getRnum() %></td>
					<td><a href="/boardView?boardNo=<%=board.getBoardNo() %>" style="text-decoration: none; color: black;"><%=board.getBoardTitle() %></td>
					<td><%=board.getBoardWriter() %></td>
					<td><%=board.getBoardDate() %></td>
				</tr>
				<%} %>
			</table>
			<div id="pageNavi"><%=pageNavi %></div>
		</fieldset>
	</div>
	<%@include file= "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>