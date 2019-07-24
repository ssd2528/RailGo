<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Plan Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- CSS -->
		<link href="../css/plan.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- header -->
			<header class="clearfix">
				<!-- logo -->
				<div class="logo"> <a href="/"><img src="../img/logo_default.png" alt="내일고"></a></div>
				<div class="plan-name">일정 제목</div>
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
						<div class="plan-date"> 07.05 ~ 07.07 </div> 
						<!-- 상세 일정(plan-date-box) -->
						<ul class="plan-date-box">
							<li>
								<div class="detail-box">
									<div class="day">DAY1</div>
									<div class="date">07.05</div>
									<div class="region">서울</div>
								</div>
							</li>
							<li>
								<div class="detail-box">
									<div class="day">DAY2</div>
									<div class="date">07.06</div>
									<div class="region">부산</div>
								</div>
							</li>
							<li>
								<div class="detail-box">
									<div class="day">DAY3</div>
									<div class="date">07.07</div>
									<div class="region">광주</div>
								</div>
							</li>
						</ul>
						<!-- ./상세 일정(plan-date-box) -->
					</div>
					<!-- ./ 1. 일정 정보 부분(schedule-info-box) -->
					
					<!-- 2. 상세 일정 정보(schedule-detail-box) -->
					<div class="schedule-detail-box">
						상세 일정 정보
					</div>
					<!-- ./ 2. 상세 일정 정보(schedule-detail-box) -->
					<!-- 3.  -->
					<div class="">
					</div>
				</div>
				<!-- ./plan-wrap -->
				
				<!-- 지도 부분 -->
				<div class="map-wrap">
					지도 부분
				</div>
			</div>
		</div>
	</body>
</html>
