<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화신규등록</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<form action="/insertMovie" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<fieldset>
					<label class="form-label" for="movieTitle">영화제목</label> <input
class="form-control" id="movieTitle" name="movieTitle" type="text"
						placeholder="영화제목입력"> <br> <label for="movieGrade"
						class="form-label mt-4">영화등급 선택</label> <select
						class="form-select" id="movieGrade" name="movieGrade">
						<option>12세</option>
						<option>15세</option>
						<option>18세</option>
						<option>ALL</option>
					</select> <br> <label class="form-label mt-4">상영시간 입력</label>
					<div class="input-group mb-3">
						<span class="input-group-text">총</span> <input type="text"
							class="form-control" name="movieRuntime"> <span
							class="input-group-text">분</span>
					</div>
					<br> <label class="form-label" for="movieDate">영화개봉일</label> <input
						class="form-control" id="movieDate" name="movieDate" type="text"
						placeholder="개봉일입력 ex)2019-01-05"> <br> <label for="movieGenNo"
						class="form-label mt-4">장르 번호선택</label> <select
						class="form-select" id="movieGenNo" name="movieGenNo">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
					</select> <br> <label for="moviePlot" class="form-label mt-4">영화줄거리</label>
					<textarea class="form-control" id="moviePlot" name="moviePlot"
						placeholder="줄거리입력" rows="7"></textarea>
					<br> <label class="form-label" for="movieDirector">감독</label>
					<input class="form-control" id="movieDirector" name="movieDirector"
						type="text" placeholder="감독이름입력"> <br> <label
						class="form-label" for="movieCast">배우</label> <input
						class="form-control" id="movieCast" name="movieCast" type="text"
						placeholder="배우이름입력"> <br>
					<table class="table" style="width: 100%;">
						<th>포스터이미지</th>
						<td style="text-align: left"><input type="file"
							name="movieImg" onchange="loadImg(this);"> <!-- 파일을 선택하거나 이런 체인지 동작감지 -->
						</td>
						</tr>
						<tr class="table-active">
							<th>포스터</th>
							<td colspan="3">
								<div id="img-viewer">
									<img id="img-view" width="500px" src="">
								</div>
							</td>
						</tr>
					</table>
					<button class="btn btn-danger btn-block" type="submit">영화등록</button>
				</fieldset>
			</div>
		</form>
	</div>
	<script>
		function loadImg(f){	//input file에서 선택한 파일을 배열로 가지고옴
			if(f.files.length != 0){		//배열로 가져오는건데 배열의 길이ㄱㅏ 0이 아니라는건 첨부파일이 있다는거잖아
				var reader = new FileReader();	//첨부파일을 읽어올 객체
				reader.readAsDataURL(f.files[0]);	
	
				reader.onload=function(e){		
					$("#img-view").attr("src",e.target.result);		
				}
			}else{			//첨부파일이 없을 때
				$("#img-view").attr("src","");
			}
		}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>



</body>
</html>