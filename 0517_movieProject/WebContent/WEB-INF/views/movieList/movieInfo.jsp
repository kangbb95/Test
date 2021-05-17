<%@page import="movie.model.vo.Genre"%>
<%@page import="movie.model.vo.Movie"%>
<%@page import="movie.model.vo.MovieData"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    MovieData md = (MovieData)request.getAttribute("md");
    Movie mv = md.getMv();
    Genre g = md.getG();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.box{
		width: 100%;
	}
    .backImg{
        background-color:cadetblue;
        width : 100%;
        height: 500px;
        display: inline-block;
    }
    .leftTable{
        border: none;
    }
    .postImg{
        width: 230px;
        height: 320px;
        border-radius: 5%;
    }
    .left{
        margin: 50px 0px 0px 150px;
        float: left;
    }
    .right{
        margin: 50px 150px 0px 0px;
        float: right;
    }
    .title{
        font-size: 45px;
        font-weight: bold;
        margin: 0px 0px;
    }
    .titleEng{
        font-size: 20px;
        font-weight: bold;
        margin-bottom: 200px;
    }
    .leftTable th{
        width: 150px;
        text-align: left;
        height: 50px;
    }
    .leftTable td{
        width: 150px;
        text-align: left;
        font-weight: bold;
        font-size: 25px;
    }
    .reserveBtn{
        background-color: rgb(71, 88, 95);
        width: 230px;
        height: 50px;
        text-align: center;
        margin-top: 10px;
    }
    .reserveBtn a{
        text-decoration: none;
        font-size: 20px;
        line-height: 50px;
        color:white
    }
    textarea{
        width:800px;
        height: 300px;
        border:none;
    }
    .content{
        margin-left: 150px;
    }
    .contentTable td{
        border-right: 1px solid gray;
    }
    .contentTable td:last-child{
        border-right: none;
    }
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="box">
		<div class="backImg">
            <div class="left">
                <p class="title"><%=mv.getMovieTitle()%></p>
                <p class="titleEng">영어제목</p>
                <table class="leftTable">
                    <tr>
                        <th class="th">실관람 평점</th>
                        <th class="th">예매율</th>
                        <th class="th">누적관객수</th>
                    </tr>
                    <tr>
                        <td class="td">0.0</td>
                        <td class="td">0위</td>
                        <td class="td">0명</td>
                    </tr>
                </table>
            </div>
            <div class="right">
                <img src="/poster/movieListPoster/<%=mv.getMovieImg() %>" class="postImg">
                <div class="reserveBtn">
                    <a href="/reserve">예매하기</a>
                </div>
            </div>
        </div>
        <div class="content">
            <p><%=mv.getMoviePlot() %></p>
            <table class="contentTable">
                <tr>
                    <td>감독 : <span><%=mv.getMovieDirector() %></span></td>
                    <td>장르 : <span><%=g.getGenreCate() %></span></td>
                    <td>상영시간 : <span><%=mv.getMovieRuntime() %></span></td>
                    <td>등급 : <span><%=mv.getMovieGrade() %></span></td>
                    <td>개봉일 : <span><%=mv.getMovieDate() %></span></td>
                </tr>
                <tr>
                    <td colspan="5">출연진 : <span><%=mv.getMovieCast() %></span></td>
                </tr>
            </table>
            <div>
                <p>차트들어갈곳</p>
            </div>
            
				<div>
					<a class="btn btn-info" href="/movieInfoUpdateFrm?movieNo=<%=mv.getMovieNo() %>">수정하기</a>
				</div>
				
            <div class="commentBox">
                
            </div>
        </div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>