<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=etb9fnwa61&submodules=geocoder"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<fieldset>
			<legend>지점정보</legend>
				<table class= "table" id="info" style="width:100%; border: 0px;">
					<tr>
						<th rowspan = "4"><img src="/img/movie.jpg" width="600px" height="500px"></th>
						<th style="height:10%; width:100%;">강남 상영관</th>
					</tr>
					<tr>
						<th style="height:10%;">전화번호 : 000-xxxx-xxxx</th>
					</tr>
					<tr>
						<th style="height:10%;">위치 : 서울 특별시 강남구</th>
					</tr>
					<tr>
						<th style="height:500px;"><div id="map" style="width:100%; height:500px;"></div></th>
					</tr>
				</table>
		</fieldset>
	</div>
	<script>
		//네이버 지도 사용
		window.onload=function(){
			//아무 설정 없이 지도 객체를 만드는 경우 서울 시청이 지도의 중심
			var map = new naver.maps.Map('map',{
				center : new naver.maps.LatLng(37.5017118880998, 127.0264048276569),
				zoom : 17,
				zoomControl : true,
				zoomControlOptions : {
					position : naver.maps.Position.TOP_RIGHT,
					style : naver.maps.ZoomControlStyle.SMALL
				}
			});
			var marker = new naver.maps.Marker({
				position : new naver.maps.LatLng(37.5017118880998, 127.0264048276569),
				map : map
			});
			var contentString = [
				'<div class = "iw_inner">',
				'	<h3>강남 상영관</h3>',
				'	<p>서울특별시 강남구</p>',
				'</div>'
			].join("");
			var infoWindow = new naver.maps.InfoWindow();
			naver.maps.Event.addListener(marker,"click",function(e){
				if(infoWindow.getMap()){
					infoWindow.close();
				}else{
					infoWindow = new naver.maps.InfoWindow({
						content : contentString
					});
					infoWindow.open(map,marker);
				}
			});
			naver.maps.Event.addListener(map,"click",function(e){
				marker.setPosition(e.coord);	//마커 위치를 클릭한 위치로 이동
				if(infoWindow.getMap()){
					infoWindow.close();
				}
				//reverseGeocoder를 이용해서 위/경도 값을 주소로 변환
				naver.maps.Service.reverseGeocode({
					location : new naver.maps.LatLng(e.coord.lat(),e.coord.lng()),
				},function(status,response){
					if(status != naver.maps.Service.Status.OK){
						return alert("주소를 찾을 수 없습니다.")
					}
					var result = response.result;
					items = result.items;
					var address = items[1].address;
					contentString = [
						'<div class = "iw_inner">',
						'<p>'+address+'</p>',
						'</div>'
					].join("");		
				});
			});
		}
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>