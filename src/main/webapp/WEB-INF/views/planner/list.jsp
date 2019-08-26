<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Planner list Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		<!-- CSS -->
		<link href="../css/common.css" rel="stylesheet">
		<link href="../css/index.css" rel="stylesheet">
		<link href="../css/planner.css" rel="stylesheet">
		<link href="../css/nailer_schedule_modal.css" rel="stylesheet">
		<link href="../css/login_modal.css" rel="stylesheet"> <!-- 로그인 모달창 -->
		<!-- JavaScript -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="../js/planner.js" type="text/javascript"></script>
		<script src="../js/nailer_schedule_modal.js" type="text/javascript"></script>
		<!-- 로그인 모달창 -->
		<script src="../js/login_modal.js" type="text/javascript"></script>
		<script src="../../js/jquery.validate.min.js" type="text/javascript"></script>
	</head>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
	<!-- login_modal -->
	<%@include file="../includes/login_modal.jsp"%>
	
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>
		<!--  body  -->
		<form class="mem-code"><input type="hidden" value="${member.mem_code}"></form>
		<div class="content">
			<div class="content-wrapper">
				<!-- 여행 플래너 설명  -->
				<div class="planner-info-img">
					<div class="planner-info-wrapper">
						<div class="planner-info-left">
							<div class="planner-info-text">내 입맛에 맞춰 손쉽게 만들 수 있는 여행!</div>
						</div>
						<hr style="border:solid 1px rgba(248,248,248,0.2);">
						<div class="planner-info-right">
							<div class="planner-info-text">친구들과 함께 일정을 만들어보세요!</div>
						</div>
					</div>
					<div class="planner-info-btn-wrapper">
						<button class="create-plan-btn"><img src="../img/planner/wh_plus.png"></img>일정 만들기</button>
						<button class="my-plan-btn"><img src="../img/planner/wh_search.png">나의 일정</button>
					</div>
				</div>
				<!-- 플랜 일정 선택 체크박스 -->
				<div class="section-main clearfix">	<!-- common.css -->
					<div class="article-item article-concept">
						<div class="filter-text">다른 내일러들의 일정은 어떨까요?</div>
						<!-- 필터 테이블 -->
						<table class="filter-table">
							<tr>
								<td class="td-subject">필 터</td>
								<td>
									<!-- 필터 선택하면 올라오는 여행 도시 폼 -->
									<div id="f-city" class="filter-value">
										<span class="f-text"></span>
										<div class="f-line"></div>
										<div class="f-x-icon">&times;</div>
									</div>
									<!-- 필터 선택하면 올라오는 여행 기간 폼 -->
									<div id="f-days" class="filter-value">
										<span class="f-text"></span>
										<div class="f-line"></div>
										<div class="f-x-icon">&times;</div>
									</div>
									<!-- 필터 선택하면 올라오는 여행 시기 폼 -->
									<div id="f-period" class="filter-value">
										<span class="f-text"></span>
										<div class="f-line"></div>
										<div class="f-x-icon">&times;</div>
									</div>
									<!-- 필터 선택하면 올라오는 여행 테마 폼 -->
									<div id="f-theme-wrapper">
										<div id="f-theme0" class="filter-value">
											<span class="f-text"></span>
											<div class="f-line"></div>
											<div class="f-x-icon">&times;</div>
										</div>
										<div id="f-theme1" class="filter-value">
											<span class="f-text"></span>
											<div class="f-line"></div>
											<div class="f-x-icon">&times;</div>
										</div>
										<div id="f-theme2" class="filter-value">
											<span class="f-text"></span>
											<div class="f-line"></div>
											<div class="f-x-icon">&times;</div>
										</div>
										<div id="f-theme3" class="filter-value">
											<span class="f-text"></span>
											<div class="f-line"></div>
											<div class="f-x-icon">&times;</div>
										</div>
										<div id="f-theme4" class="filter-value">
											<span class="f-text"></span>
											<div class="f-line"></div>
											<div class="f-x-icon">&times;</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td id="city" class="td-subject">여행 도시</td>
								<td id="city-option" class="td-option">
									<div id="capital-tag">수도권</div>
									<div id="gangwon-tag">강원권</div>
									<div id="choongchung-tag">충청권</div>
									<div id="jeonla-tag">전라권</div>
									<div id="gyeongsang-tag">경상권</div>
								</td>
							</tr>
							<tr>
								<td id="days"class="td-subject">여행일</td>
								<td id="days-option"class="td-option">
									<div id="third-day-tag">3일권</div>
									<div id="fifth-day-tag">5일권</div>
									<div id="seventh-day-tag">7일권</div>
								</td>
							</tr>
							<tr>
								<td id="period" class="td-subject">여행 시기</td>
								<td id="period-option" class="td-option">
									<div id="summer-tag">여름</div>
									<div id="winter-tag">겨울</div>
								</td>
							</tr>
							<tr>
								<td id="theme"class="td-subject">여행 테마</td>
								<td id="theme-option" class="td-option">
									<div id="solo-tag">나홀로</div>
									<div id="duo-tag">단둘이</div>
									<div id="squad-tag">셋이상</div>
									<div id="eatting-tag">먹방</div>
									<div id="healing-tag">힐링</div>
								</td>
							</tr>																												
						</table>
					</div>
					<div class="article-item article-concept">
						<div class="filter-text">다른 내일러들의 일정</div>
							<!-- 일정들의 목록 -->
						<div class="calendar-lists-wrapper">
						<% for(int i = 0; i < 6; i++){ %>
							<div class="calendar-lists">
								<div class="calendar-map"></div>
								<div class="calendar-text-wrapper">
									<div class="calendar-text-tag">#셋이서</div>
									<div class="calendar-text-tag">#전국일주</div>
									<div class="calendar-text-course">서울-대전-대구-부산</div>
									<div class="calendar-text-writer">혐잉</div>
									<div class="calendar-like-number-wrapper">
										<div class="calendar-text-like" ><img class="like-img" src="../img/sns/heart.png"></img></div>	
										<div class="calendar-text-like-number">111</div>
									</div>
								</div>
							</div>
						<% } %>
						</div>
						<button class="more-btn"> 더보기 </button>
					</div>
				</div>
			</div>
		</div>
		<!-- nailer schedule modal -->
		<%@include file="nailer_schedule_modal.jsp" %>
		<!-- plan-create-modal -->
		<%@include file="./plan_modal.jsp" %>
		<!-- footer -->
		<%@include file="../includes/footer.jsp" %>
	</div>
</body>
		<!-- Calendar DatePicker import (download) Not CDN -->
		<link rel="stylesheet" type="text/css" href="../calendarpicker/jquery.datetimepicker.css"/>
		<script src="../calendarpicker/jquery.js"></script>
		<script src="../calendarpicker/build/jquery.datetimepicker.full.min.js"></script>
</html>