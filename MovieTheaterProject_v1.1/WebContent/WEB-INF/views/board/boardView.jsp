<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Board board = (Board)request.getAttribute("board");
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
				<table class="table" id="boardView" style="width:100%;">
					<tr class="table-info">
						<th colspan="4"><%=board.getBoardTitle() %></th>
					</tr>
					<tr class="table-light">
					<th>작성자</th>
					<th><%=board.getBoardWriter() %></th>
					<th>작성일</th>
					<th><%=board.getBoardDate() %></th>
				</tr>
				<tr class="table-light">
					<th>첨부파일</th>
					<th colspan="3">
						<%if(board.getFileName() != null) {%>	
						<img src="/img/file.png" width="16px">
						<a href = "/fileDown?boardNo=<%=board.getBoardNo()%>"style="text-decoration: none; color: black;"><%=board.getFileName() %></a>
						<%} %>
					</th>
				</tr>
				<tr class="table-light">
					<th>내용</th>
					<th colspan="3" style="height:450px;">
						<%=board.getBoardContentBr() %>
					</th>
				</tr>
				<tr class = "table-light">
					<th colspan="4" style = "text-align:center;">
						<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
						<%if(m !=null && m.getMemberId().equals(board.getBoardWriter())) {%>
						<a class = "btn btn-info" href="/boardUpdateFrm?boardNo=<%=board.getBoardNo() %>">수정하기</a>
						<a class = "btn btn-info" href="/boardDelete?boardNo=<%=board.getBoardNo() %>">삭제하기</a>
						<%} %>
					</th>
				</tr>
			</table>
		</fieldset>
	</div>
	<%@include file= "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>