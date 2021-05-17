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
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
        <div class="wholeBox">
            <form action="/movieInfoUpdate" method="post" enctype="multipart/form-data">
                <input type="hidden" name="movieNo" value="<%=mv.getMovieNo() %>">
                <fieldset>
                    <legend>영화 상세정보 수정</legend>
                    <table class="table" style="width:100%;">
                        <tr class="table-active">
                            <th>영화 제목</th>
                            <td colspan="3"><input type="text" class="form-control" name="movieTitle" value="<%=mv.getMovieTitle()%>"></td>
                        </tr>
                        <tr class="table-active">
                            <th>포스터 이미지</th>
                            <td style="text-align:left">
                                <input type="hidden" name="status" value="stay">
                                <img src="/img/file.png" width="16px" class="delFile">
                                <span class="delFile"><%=mv.getMovieImg() %></span>
                                <button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">삭제</button>
                                <input type="file" name="movieImg" id="file" style="display:none;" onchange="loadImg(this);">
                                <input type="hidden" name="oldFilename" value="<%=mv.getMovieImg() %>">
                            </td>
                        </tr>
                        <tr class="tacbl-active">
                        <th>포스터 미리보기</th>
                        <td colspan="3">
								<div id="img-viewer">
									<img id="img-view" width="500px" src="/poster/movieListPoster/<%=mv.getMovieImg()%>">
								</div>
							</td>
                        </tr>
                        <tr class="table-active">
                            <th>영화 줄거리</th>
                            <td colspan="3"><textarea name="moviePlot" class="form-control"><%=mv.getMoviePlot() %></textarea></td>
                        </tr>
                        <tr class="table-active">
                            <th>영화 감독</th>
                            <td colspan="3"><input type="text" class="form-control" name="movieDirector" value="<%=mv.getMovieDirector()%>"></td>
                        </tr>
                        <tr class="table-active">    
                            <th>영화 상영시간</th>
                            <td colspan="3"><input type="text" class="form-control" name="movieRuntime" value="<%=mv.getMovieRuntime()%>"></td>
                        </tr>
                        
                        <tr class="table-active">    
                            <th>영화 등급</th>
                            <td colspan="3"><input type="text" class="form-control" name="movieGrade" value="<%=mv.getMovieGrade()%>"></td>
                        </tr>
                        <tr class="table-active">    
                            <th>영화 개봉일</th>
                            <td colspan="3"><input type="text" class="form-control" name="movieDate" value="<%=mv.getMovieDate()%>"></td>
                        </tr>
                        <tr class="table-active">    
                            <th>영화 출연진</th>
                            <td colspan="3"><input type="text" class="form-control" name="movieCast" value="<%=mv.getMovieCast()%>"></td>
                        </tr>
                        <tr class="table-active">
                            <th colspan="4">
                                <button type="submit" class="btn btn-danger btn-block">수정하기</button>
                            </th>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
    </div>
    <script>
		$("#delBtn").click(function(){
			if(confirm("첨부파일을 삭제하시겠습니까?")){
				$(".delFile").hide();
				$("#file").show();
				$("[name=status]").val("delete");
				$("#img-view").attr("src","");	
			}
		});
		function loadImg(f){	//input file에서 선택한 파일을 배열로 가지고옴
			if(f.files.length != 0){		//배열로 가져오는건데 배열의 길이ㄱㅏ 0이 아니라는건 첨부파일이 있다는거잖아
				var reader = new FileReader();	//첨부파일을 읽어올 객체
				reader.readAsDataURL(f.files[0]);	
	
				reader.onload=function(e){		
					$("#img-view").attr("src",e.target.result);		
				}
			}
		}
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>