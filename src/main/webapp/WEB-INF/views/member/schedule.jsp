<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Schedule Page</title>
		<link rel="icon" href="/img/favicon.ico">
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- Css -->
		<link href="/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/article_sns_user.css" rel="stylesheet">
		<link href="/css/index.css" rel="stylesheet">
		<link href="/css/timeline.css" rel="stylesheet">
		<link href="/css/schedule.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="/js/fontawesome.js" type="text/javascript"></script>
		<script src="/js/header.js" type="text/javascript"></script>
		<script src="/js/timeline.js" type="text/javascript"></script>
		<script src="/js/schedule.js" type="text/javascript"></script>
	</head>
	<body>
		<!-- 비정상적 URL 직접 접근 차단 -->
		<% if(request.getHeader("referer") == null) {  %>
			<script> alert('정상적인 경로를 통해 다시 접근해 주세요.'); location.href='/'; </script>
		<% } %>
		
		<div class="wrap">
			<!-- header -->
			<%@include file="../includes/header.jsp"%>
	
			<!-- content -->
			<form class="paging-form">
				<input type="hidden" class="start" name="start" value="1">
				<input type="hidden" class="end" name="end" value="6">
			</form>
			<div class="content">
				<div class="content-wrapper">
				<!-- member-info -->
				<%@include file="../includes/member_info.jsp"%>
					<!-- section-main -->
					<form id="schedule-json-data" method="post" action="../../planner/plan">
						<input type="hidden" id="item" name="item">
						<input type="hidden" id="startday" name="startday">
						<input type="hidden" id="tickets" name="tickets">
						<input type="hidden" id="plancode" name="plancode">
					</form>
					<div class="section-main clearfix">
						<div class="article-wrapper">
							<div class="article-item">
								<div class="schedule-select">
									<span class="my-schedule">계획중인 일정</span>
									<span>|</span>
									<span class="complete-schedule">완성된 일정</span>
									<span>|</span>
									<span class="like-schedule">좋아한 일정</span>
								</div>
								<div class="schedule-lists">
								<!-- 
								<div class="schedule-list">
								  <img class="schedule-list-img" src="/img/member/winter.jpeg">
								  <div class="schedule-text-wrapper">
									  <div class="schedule-name">여행 이름</div>
									  <div class="schedule-date">(2019.07.03~2019.07.05)</div>
								    <div class="name-wrapper">
								      <img class="schedule-userImg" src="/img/member/default_profile_m.png">
								      <div class="schedule-userName">사용자 닉네임</div>
								      <div class="btn-wrapper">
								      <div class="m-btn">수정</div>
								      <div class="d-btn">| 삭제</div>
								      </div>  
								    </div>
								   </div>
								</div>
                                -->
								</div>
								<button class="more-btn"> 더보기 </button>
								<!-- 
								<div class="dg_warning-div">
									<img class="dg_warning" src="../img/planner/dg_warning.png"><br>
									<a class="dg_warning-txt">아직 작성하신 여행 일정이 없습니다.</a>
								</div>
								 -->
							</div>
						</div>
					</div>
					<!-- /section-main -->
				</div>
			</div>
			<!-- /content -->
			
			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
	
		</div>
	</body>
</html>