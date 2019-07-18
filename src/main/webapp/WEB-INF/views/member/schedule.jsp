<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Schedule Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- Css -->
		<link href="../css/common.css" rel="stylesheet">
		<link href="../css/article_sns_user.css" rel="stylesheet">
		<link href="../css/index.css" rel="stylesheet">
		<link href="../css/timeline.css" rel="stylesheet">
		<link href="../css/schedule.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="../js/header.js" type="text/javascript"></script>
		<script src="../js/timeline.js" type="text/javascript"></script>
		<script src="../js/schedule.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- header -->
			<%@include file="../includes/header.jsp"%>
	
			<!-- content -->
			<div class="content">
				<div class="content-wrapper">
	
					<!-- profile-background -->
					<div class="section-profilebg">
						<a href="#"><img id="profilePicUpdate" src="../img/member/edit.png" alt="프로필 사진 수정"
						onmouseover="this.src='../img/member/edit_hover.png'" onmouseout="this.src='../img/member/edit.png'"></a>
					</div>
					<!-- /profile-background -->
	
					<!-- profile-info -->
					<div class="profile-info">
						<!-- profile-img -->
						<div class="profile-img clearfix">
							<img src="../img/member/default_profile_f.png" alt="프로필">
						</div>
						<!-- /profile-img -->
	
						<!-- profile-detail -->
						<div class="profile-detail">
							<div class="row1">
								<span class="user-name">사용자 닉네임</span> <span>포스팅</span> <span>팔로워</span>
								<span>팔로잉</span>
							</div>
							<div class="row2">
								<span class="user-id">@UserId</span> <span class="posting">0</span>
								<span class="follower">0</span> <span class="following">0</span>
							</div>
						</div>
						<!-- /profile-detail -->
	
						<!-- tab-list -->
						<div class="tab-list">
							<a href="../member/timeline" class="timeline1">타임라인</a> 
							<a href="../member/schedule" class="schedule">일정관리</a>
						</div>
						<!-- /tab-list -->
					</div>
					<!-- /profile-info -->
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper">
							<div class="article-item">
								<div class="schedule-select">
									<a href="#" class="my-schedule">나의 일정</a>
									<a>|</a>
									<a href="#" class="like-schedule">좋아한 일정</a>
								</div>
								<div class="schedule-lists">
									<div class="schedule-list-img">
										<div class="schedule-list">
											<a class="schedule-name">여행 이름</a>
											<a class="schedule-date">(2019.07.03~2019.07.05)</a>
											<a href=# class="MnD">수정</a>
											<a class="MnD">|</a>
											<a href=# class="MnD">삭제</a><br>
											<img class="schedule-userImg" src="../img/member/default_profile_m.png">
											<a class="schedule-userName">사용자 닉네임</a>
										</div>
									</div>
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