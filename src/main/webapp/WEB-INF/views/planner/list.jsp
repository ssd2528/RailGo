<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
		<!-- JavaScript -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="../js/planner.js" type="text/javascript"></script>
	</head>
</head>
<body>
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>
		<!--  body  -->
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
						<button class="create-plan-btn">일정 만들기</button>
						<button class="my-plan-btn">나의 일정</button>
					</div>
				</div>
				<!-- 플랜 일정 선택 체크박스 -->
				<div class="section-main clearfix">	<!-- common.css -->
					<div class="article-item article-concept">
						<div class="filter-text">다른 내일러들의 일정은 어떨까요?</div>
						<table class="filter-table">
							<tr>
								<td class="td-subject">필 터</td>
								<td>&nbsp;서울x&nbsp;&nbsp;3일권x&nbsp;&nbsp;여름x</td>
							</tr>
							<tr>
								<td class="td-subject">여행 도시</td>
								<td class="td-option">
									<a id="seoul-tag" href="#">서울</a>
									<a id="gyonggi-tag" href="#">경기</a>
									<a id="gangwon-tag" href="#">강원</a>
									<a id="choongchung-tag" href="#">충청</a>
									<a id="jeonla-tag" href="#">전라</a>
									<a id="gyeongsang-tag" href="#">경상</a></td>
							</tr>
							<tr>
								<td class="td-subject">여행일</td>
								<td class="td-option">
									<a id="third-day-tag" href="#">3일권</a>
									<a id="fifth-day-tag" href="#">5일권</a>
									<a id="seventh-day-tag" href="#">7일권</a>
								</td>
							</tr>
							<tr>
								<td class="td-subject">여행 시기</td>
								<td class="td-option">
									<a id="summer-tag" href="#">여름</a>
									<a id="winter-tag" href="#">겨울</a>
								</td>
							</tr>
							<tr>
								<td class="td-subject">여행 테마</td>
								<td class="td-option">
									<a id="solo-tag" href="#">나홀로</a>
									<a id="duo-tag" href="#">단둘이</a>
									<a id="squad-tag" href="#">셋이상</a>
									<a id="eatting-tag" href="#">먹방</a>
									<a id="healing-tag" href="#">힐링</a>
								</td>
							</tr>																												
						</table>
					</div>
					<div class="article-item article-concept">
						<div class="filter-text">다른 내일러들의 일정</div>
							<!-- 일정들의 목록 -->
						<div class="calendar-lists-wrapper">
							<div class="calendar-lists">
								<div class="calendar-map" onclick="location.href='#';"></div>
								<div class="calendar-text-wrapper">
									<div class="calendar-text-tag">#셋이서</div>
									<div class="calendar-text-tag">#전국일주</div>
									<div class="calendar-text-course">서울-대전-대구-부산</div>
									<div class="calendar-text-writer">씹찬우</div>
									<div class="calendar-like-number-wrapper">
										<a class="calendar-text-like">♡</a>	
										<div class="calendar-text-like-number">111</div>
									</div>
								</div>
							</div>
							<div class="calendar-lists">
								<div class="calendar-map" onclick="location.href='#';"></div>
								<div class="calendar-text-wrapper">
									<div class="calendar-text-tag">#셋이서</div>
									<div class="calendar-text-tag">#전국일주</div>
									<div class="calendar-text-course">서울-대전-대구-부산</div>
									<div class="calendar-text-writer">혐찬우</div>
									<div class="calendar-like-number-wrapper">
										<a class="calendar-text-like">♡</a>	
										<div class="calendar-text-like-number">1</div>
									</div>
								</div>
							</div>
							<div class="calendar-lists">
								<div class="calendar-map" onclick="location.href='#';"></div>
								<div class="calendar-text-wrapper">
									<div class="calendar-text-tag">#셋이서</div>
									<div class="calendar-text-tag">#전국일주</div>
									<div class="calendar-text-course">서울-대전-대구-부산</div>
									<div class="calendar-text-writer">개찬우</div>
									<div class="calendar-like-number-wrapper">
										<a class="calendar-text-like">♡</a>	
										<div class="calendar-text-like-number">11</div>
									</div>
								</div>
							</div>
						</div>
						<button class="more-btn"> 더보기 </button>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<%@include file="../includes/footer.jsp" %>
	</div>
</body>
</html>