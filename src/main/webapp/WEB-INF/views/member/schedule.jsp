<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Schedule Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- Css -->
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/article_sns_user.css" rel="stylesheet">
		<link href="/css/index.css" rel="stylesheet">
		<link href="/css/timeline.css" rel="stylesheet">
		<link href="/css/schedule.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="/js/header.js" type="text/javascript"></script>
		<script src="/js/timeline.js" type="text/javascript"></script>
		<script src="/js/schedule.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- header -->
			<%@include file="../includes/header.jsp"%>
	
			<!-- content -->
			<div class="content">
				<div class="content-wrapper">
				<!-- member-info -->
				<%@include file="../includes/member_info.jsp"%>
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper">
							<div class="article-item">
								<div class="schedule-select">
									<a href="#" class="my-schedule">나의 일정</a>
									<a>|</a>
									<a href="#" class="like-schedule">좋아한 일정</a>
								</div>
								
									<div class="schedule-list">
										<ul>
											<li><img class="schedule-list-img" src="/img/member/winter.jpeg"></li>
											<li><div class="schedule-name">여행 이름</div></li>
											<li><div class="schedule-date">(2019.07.03~2019.07.05)</div></li>
											<li><a href="#" class="MnD">수정</a></li>
											<li><div class="MnD">|</div></li>
											<li><a href="#" class="MnD">삭제</a><br></li>
											<li><img class="schedule-userImg" src="/img/member/default_profile_m.png"></li>
											<li><div class="schedule-userName">사용자 닉네임</div></li>
										</ul>
									</div>
									<div class="schedule-list">
										<ul>
											<li><img class="schedule-list-img" src="/img/member/winter.jpeg"></li>
											<li><div class="schedule-name">여행 이름</div></li>
											<li><div class="schedule-date">(2019.07.03~2019.07.05)</div></li>
											<li><a href="#" class="MnD">수정</a></li>
											<li><div class="MnD">|</div></li>
											<li><a href="#" class="MnD">삭제</a><br></li>
											<li><img class="schedule-userImg" src="/img/member/default_profile_m.png"></li>
											<li><div class="schedule-userName">사용자 닉네임</div></li>
										</ul>
									</div>
									<div class="schedule-list">
										<ul>
											<li><img class="schedule-list-img" src="/img/member/winter.jpeg"></li>
											<li><div class="schedule-name">여행 이름</div></li>
											<li><div class="schedule-date">(2019.07.03~2019.07.05)</div></li>
											<li><a href="#" class="MnD">수정</a></li>
											<li><div class="MnD">|</div></li>
											<li><a href="#" class="MnD">삭제</a><br></li>
											<li><img class="schedule-userImg" src="/img/member/default_profile_m.png"></li>
											<li><div class="schedule-userName">사용자 닉네임</div></li>
										</ul>
									</div>
									<div class="schedule-list">
										<ul>
											<li><img class="schedule-list-img" src="/img/member/winter.jpeg"></li>
											<li><div class="schedule-name">여행 이름</div></li>
											<li><div class="schedule-date">(2019.07.03~2019.07.05)</div></li>
											<li><a href="#" class="MnD">수정</a></li>
											<li><div class="MnD">|</div></li>
											<li><a href="#" class="MnD">삭제</a><br></li>
											<li><img class="schedule-userImg" src="/img/member/default_profile_m.png"></li>
											<li><div class="schedule-userName">사용자 닉네임</div></li>
										</ul>
									</div>
								
								
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