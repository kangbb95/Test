<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<form action="/updateMember" method="post">
			<legend style= "margin-left : 30%;"><%=m.getMemberName() %>의 정보</legend>
			<table class="table table-hover" style= "width : 500px; margin-left : 35%;">
			  <tbody>
			    <tr class="table-info">
			      <th scope="row">아이디</th>
			      <td></td>
			    </tr>
			    <tr class="table-light">
			      <th scope="row"></th>
			      <td><input type="text" id="memberId" name="memberId" class="from-control" value="<%=m.getMemberId() %>" readonly></td>
			    </tr>
			    <tr class="table-info">
			      <th scope="row">비밀번호</th>
			      <td></td>
			    </tr>
			    <tr class="table-light">
			      <th scope="row"></th>
			      <td><input type="password" id="memberPw" name="memberPw" class="from-control" value="<%=m.getMemberPw() %>"></td>
			    </tr>
			    <tr class="table-info">
			      <th scope="row">이름</th>
			      <td></td>
			    </tr>
			    <tr class="table-light">
			      <th scope="row"></th>
			      <td><input type="text" id="memberName" name="memberName" class="from-control" value="<%=m.getMemberName() %>"></td>
			    </tr>
			    <tr class="table-info">
			      <th scope="row">이메일</th>
			      <td></td>
			    </tr>
			    <tr class="table-light">
			      <th scope="row"></th>
			      <td><input type="text" id="memberEmail" name="memberEmail" class="from-control" value="<%=m.getMemberEmail() %>"></td>
			    </tr>
			    <tr class="table-info">
			      <th scope="row">선호 장르</th>
			      <td></td>
			    </tr>
			    <tr class="table-light">
			      <th scope="row"></th>
			      <td><%=m.getMemberGenre() %></td>
			    </tr>
			    <tr class="table-info">
			      <th scope="row">전화번호</th>
			      <td></td>
			    </tr>
			    <tr class="table-light">
			      <th scope="row"></th>
			      <td><input type="text" id="phone" name="phone" class="from-control" value="<%=m.getPhone() %>"></td>
			    </tr>
			  </tbody>
			</table>
			<button type="submit" class="btn btn-info" style="margin-left : 50%;">정보 수정</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>