<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Plan Page</title>
		<link rel="icon" href="/img/favicon.ico">
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- CSS -->
		<link href="../css/plan.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<script src="https://use.fontawesome.com/releases/v5.9.0/js/all.js"></script>
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="../js/plan.js" type="text/javascript"></script>
	</head>
	<body>
		<form class="member-code">
			<input type="hidden" value="${member.mem_code}">
		</form>
		<div class="wrap">
			<!-- header -->
			<header class="clearfix">
				<!-- logo -->
				<div class="logo"> <a href="/"><img src="../img/logo_default.png" alt="내일고"></a></div>
				<div class="plan-name">일정 제목</div>
				<input type="text" style="display:none;" maxlength="15" class="plan-name-text">
				<div class="plan-name-text-btn" style="display:none;">확인</div>
				<div class="btn-group">
					<div class="closeBtn">저장&닫기</div>
					<div class="completeBtn">완료</div>
				</div>
			</header>
			
			<div class="content">
				<!-- 일정 부분 -->
				<div class="plan-wrap">
					<!-- 1. 일정 정보 부분(schedule-info-box) -->
					<div class="schedule-info-box">
						<!-- 전체 일정(plan-date)  -->
						<input type="hidden" id="scheduleitem" name="scheduleitem" value='${scheduleitem}'>
						<input type="hidden" id="ticket" name="ticket" value='${ticket}'>
						<input type="hidden" id="startday" name="startday" value='${startday}'>
						<input type="hidden" id="plancode" name="plancode" value='${plancode}'>
						<div class="plan-date"> <span class="start"></span> ~ <span class="end"></span></div> 
						<!-- 상세 일정(plan-date-box) -->
						<ul class="plan-date-box">
							<li class="plan-date-list1">
								<div id="day1" class="detail-box">
									<div class="day">DAY1</div>
									<div class="date"></div>
									<div class="region">지역을 선택하세요.</div>
								</div>
							</li>
							<li class="plan-date-list2">
								<div id="day2" class="detail-box">
									<div class="day">DAY2</div>
									<div class="date"></div>
									<div class="region">지역을 선택하세요.</div>
								</div>
							</li>
							<li class="plan-date-list3">
								<div id="day3" class="detail-box">
									<div class="day">DAY3</div>
									<div class="date"></div>
									<div class="region">지역을 선택하세요.</div>
								</div>
							</li>
							<li class="plan-date-list4">
								<div id="day4" class="detail-box">
									<div class="day">DAY4</div>
									<div class="date"></div>
									<div class="region">지역을 선택하세요.</div>
								</div>
							</li>
							<li class="plan-date-list5">
								<div id="day5" class="detail-box">
									<div class="day">DAY5</div>
									<div class="date"></div>
									<div class="region">지역을 선택하세요.</div>
								</div>
							</li>
							<li class="plan-date-list6">
								<div id="day6" class="detail-box">
									<div class="day">DAY6</div>
									<div class="date"></div>
									<div class="region">지역을 선택하세요.</div>
								</div>
							</li>
							<li class="plan-date-list7">
								<div id="day7" class="detail-box">
									<div class="day">DAY7</div>
									<div class="date"></div>
									<div class="region">지역을 선택하세요.</div>
								</div>
							</li>																					
						</ul>
						<!-- ./상세 일정(plan-date-box) -->
					</div>
					<!-- ./ 1. 일정 정보 부분(schedule-info-box) -->
					
					<!-- 2. 상세 일정 정보(schedule-detail-box) -->
					<div class="schedule-detail-box">
						<div class="empty-detail-box">상세일정정보</div>	
						<div class="item-detail-box"></div>	
						<!-- ./ 2. 상세 일정 정보(schedule-detail-box)
						<div class="schedule-item-wrapper">
							<div class="schedule-item-img"></div>
							<div class="schedule-item-name">
								<div class="schedule-item-addr"></div>
							</div>
						</div>
						 -->	
						 <div class="get-directions">여행지 길 찾기</div>		
					</div>		
				</div>
				<!-- ./plan-wrap -->
				
				<!-- 지도 부분 -->
				<div class="map-search-view-cover">
					<div class="list-cover"> &gt; </div>
					
					<!-- 길찾기 부분 -->
					<div class="transit-find" style="display:none;">
						<div class="transit-info-box">출발지와 도착지를 선택해주세요.</div>
						<div class="transit-set-box">
							<div class="transit-origin">
								<span class="transit-find-text">출발지</span>
								<input type="text" class="origin transit-find-input" readonly /> 
								<span class="reset-btn">&times;</span>
							</div>
							<div class="transit-destination">
								<span class="transit-find-text">도착지</span>
								<input type="text" class="destination transit-find-input" readonly />
								<span class="reset-btn">&times;</span>
							</div>
							
							<div class="transit-find-btn">길 찾기</div>
						</div>
						<div id="transit-result-box" class="transit-result-box"></div>
					</div>
					<div class="return-btn">&lt;</div>
					<!-- ./길찾기 부분 -->
					
					<div class="city-list" style="display:none;">
						<div class="list-close"> &lt;</div>
						<div class="list-title">
							<div class="title-name"></div>
							<div class="select-city-title" style="display:none;">
								<div class="city-item">서울</div>
								<div class="city-item">인천</div>
								<div class="city-item">대전</div>
								<div class="city-item">대구</div>
								<div class="city-item">광주</div>
								<div class="city-item">부산</div>
								<div class="city-item">울산</div>
								<div class="city-item-do">경기도</div>
								<div class="city-item">가평</div>
								<div class="city-item">고양</div>
								<div class="city-item">광명</div>
								<div class="city-item">동두천</div>
								<div class="city-item">수원</div>
								<div class="city-item">안양</div>
								<div class="city-item">양평</div>
								<div class="city-item">평택</div>
								<div class="city-item">화성</div>
								<div class="city-item-do">강원도</div>
								<div class="city-item">강릉</div>
								<div class="city-item">동해</div>
								<div class="city-item">영월</div>
								<div class="city-item">정선</div>
								<div class="city-item">춘천</div>
								<div class="city-item">태백</div>
								<div class="city-item">평창</div>
								<div class="city-item-do">충청북도</div>
								<div class="city-item">단양</div>
								<div class="city-item">영동</div>
								<div class="city-item">제천</div>
								<div class="city-item">충주</div>
								<div class="city-item-do">충청남도</div>
								<div class="city-item">대천</div>
								<div class="city-item">조치원</div>
								<div class="city-item">천안</div>
								<div class="city-item">천안아산</div>
								<div class="city-item">홍성</div>
								<div class="city-item-do">경상북도</div>
								<div class="city-item">경주</div>
								<div class="city-item">구미</div>
								<div class="city-item">김천구미</div>
								<div class="city-item">신경주</div>
								<div class="city-item">안동</div>
								<div class="city-item">영주</div>
								<div class="city-item">영천</div>
								<div class="city-item">점촌</div>
								<div class="city-item">춘양</div>
								<div class="city-item">포항</div>
								<div class="city-item-do">경상남도</div>
								<div class="city-item">마산</div>
								<div class="city-item">진주</div>
								<div class="city-item-do">전라북도</div>
								<div class="city-item">남원</div>
								<div class="city-item">익산</div>
								<div class="city-item">전주</div>
								<div class="city-item">정읍</div>
								<div class="city-item-do">전라남도</div>
								<div class="city-item">곡성</div>
								<div class="city-item">구례구</div>
								<div class="city-item">나주</div>
								<div class="city-item">목포</div>
								<div class="city-item">순천</div>
								<div class="city-item">여수엑스포</div>
							</div>
							<!-- <input type="text" class="place-search-bar"> -->
						</div>
						<div class="city-list-body">
							<input type="search" class="list-search-text" placeholder="장소검색(2글자 이상)">
							<div class="list-theme-wrapper">
								<div class="list-theme-tour"></div>
								<div class="list-theme-accom"></div>
								<div class="list-theme-food"></div>	
							</div>
							<div class="selected-theme-list">
							<!-- 
								<div class="day-spot-item">
									<div class="item-img-box"></div>
									<div class="item-info-box">
									<div class="item-sub"
									</div>
									<div class="item-insert-box"></div>
								</div>
							-->
							</div>
						</div>
					</div>
					<div id="map" class="map-wrap"></div>
				</div>
			</div>
		</div>
		<!-- Google Map api call script	-->	
		<script>
			function initMap(){
				//console.log('init map');
			    map = new google.maps.Map(document.getElementById('map'), {
			        center: { lat: 36.569519, lng: 127.839628 },
			        scrollwheel: true,
			        zoom: 7,
			        minZoom:7
			    });	
			    // Create a renderer for directions and bind it to the map.
			    directionsDisplay = new google.maps.DirectionsRenderer();
				// Instantiate a directions service.
		        directionsService = new google.maps.DirectionsService;
			    google.maps.event.addListener(map,'dragend', function() {  
			   		dragMapEvent();
			      });  
				google.maps.event.addListener(map, 'zoom_changed', function() {
				    var zoomLevel = map.getZoom();
				    /*
					if(zoomLevel <= 7){
						setStationMarker(7);
					}else
				    {
						setStationMarker(stationLocations.length);	
						if(zoomLevel == 13){
							setLocMarker();
						}else if(zoomLevel < 12){
							delTourMarkers();
						}
				    }*/		
				});
				//addStationsMarker(stationLocations.length);
				//setStationMarker(7);
				toggleCityList(80,350,'show');
			}
		</script>
		<% String RailGoKey = "AIzaSyAdFDMU_KU_Ro2hPEPNNJw0ub3Zv21X-CY"; %>
		<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=<%=RailGoKey%>&sensor=false&callback=initMap"async defer></script>				
		<!-- complete plan schedule save modal -->
		<%@include file="plan_save_modal.jsp" %>		
	</body>
</html>
