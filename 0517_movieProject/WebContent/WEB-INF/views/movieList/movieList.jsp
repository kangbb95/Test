<%@page import="movie.model.vo.Movie"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Movie mv = (Movie)request.getAttribute("mv");
    ArrayList<Movie> list = (ArrayList<Movie>)request.getAttribute("list");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>현재 상영작</title>
<style>
	.wrap{
	    width:1200px;
	    margin: 0 auto;
	}
	.header,.footer {
	background-color: rgb(37, 37, 37);
	width: 100%;
	height: 160px;
	overflow: hidden;
	}
	.tabMenu{
	    width: 1100px;
	    padding: 10px;
	    margin: 0 auto;
	}
	.tabs{
	    margin: 30px 0 20px 0;
	    padding: 0;
	    overflow: hidden;
	    list-style-type: none;
	}
	.tab{
	    width: 100px;
	    height: 30px;
	    line-height: 30px;
	    text-align: center;
	    float: left;
	    margin-left: 10px;
	}
	.tab:hover{
	    cursor:pointer;
	}
	.tabContent{
	    width:1100px;
	    height:auto;
	    display: none;
	}
	#more-btn {
	margin : 60px 0 30px 350px;
	width: 400px;
	height: 50px;
	border: none;
	border-top: 1px solid rgb(37, 37, 37);
	border-bottom: 1px solid rgb(37, 37, 37);
	background-color: transparent;
	}
	#more-btn:hover{
	    background-color: rgba(37, 37, 37, 0.030);
	}
	.movieListWrapper {
	clear: right;
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap;
	margin: 5px;
	}
	.movieList {
	margin-top: 20px;
	width: 230px;
	height: 410px;
	overflow: hidden;
	}
	.movieList > img {
	width: 100%;
	height: 320px;
	}
	.movieList > p {
	text-align: center;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<fieldset>
	        <div class="tabMenu">
	            <ul class="tabs">
	                <li class="tab" onclick="selectTab(0);">현재 상영작</li>
	                <li class="tab" onclick="selectTab(1);">상영 예정작</li>
	            </ul>
	            <%if(m != null) {%><%--로그인 되어있을 경우--%>
				<div>
					<a class="btn btn-info writeBtn" href="/wholeMovie">영화 추가</a>
				</div>
				<%} %>
		        <div class="tabContent">
		        	<%Calendar today = Calendar.getInstance(); %>
		        	<%Calendar movieDate = Calendar.getInstance(); %>
		        	
		        	<div class="movieListWrapper">
				<%if(!list.isEmpty()) {%>
					<%for(Movie movie : list) {%>
					<a href="/movieInfo?movieNo=<%=movie.getMovieNo()%>">
	                    <div class="movieList">
	                    	<img src="/poster/movieListPoster/<%=movie.getMovieImg() %>">
	                        <p><%=movie.getMovieTitle() %></p>
	                    </div>				
					</a>
					<%} %>
				<%}else {%>
					<p>영화없다</p>
				<%} %>
		           	</div>						
	                <button class="btn btn-outline-info btn-block" currentCount="0" value="" id="more-btn">더보기</button>
		        </div>
	            <div class="tabContent">
	                <div class="movieListWrapper">
	                    <div class="movieList">
	                        <img src="img/Crisis.png">
	                        <p>크라이시스</p>
	                        <p>개봉일 2021.05.14</p>
	                    </div>
	                </div>
	                <button class="btn btn-outline-info btn-block" currentCount="0" value="" id="more-btn">더보기</button>
	            </div>
	        </div>
	    </fieldset>
	</div>
	<script>
        function selectTab(idx){
            var tabContents = document.getElementsByClassName("tabContent");
            var tabs = document.getElementsByClassName("tab");
            for(var i=0;i<tabContents.length;i++){
                tabs[i].style.borderBottom = "1px solid transparent";
                tabContents[i].style.display = "none";
            }
            tabs[idx].style.borderBottom = "1px solid rgb(37, 37, 37)";
            tabContents[idx].style.display="block";
        }
        window.onload = function(){
            var tabContents = document.getElementsByClassName("tabContent");
            var tabs = document.getElementsByClassName("tab");
            tabs[0].style.borderBottom = "1px solid rgb(37, 37, 37)";
            tabContents[0].style.display="block";
        }
        
        $(function(){
			more(1);//start에 1 대입
			$("#more-btn").click(function(){
				more($(this).val());
			});
		});
		
    </script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>