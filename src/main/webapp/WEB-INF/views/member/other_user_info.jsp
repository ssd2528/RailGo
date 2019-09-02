<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] UserInfo Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- Css -->
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/article_sns_user.css" rel="stylesheet">
		<link href="/css/index.css" rel="stylesheet">
		<link href="/css/timeline.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="/js/header.js" type="text/javascript"></script>
		<script src="/js/timeline.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- header -->
			<%@include file="../includes/header.jsp"%>
	
			<!-- content -->
			<div class="content">
				<div class="content-wrapper">
				<!-- member-info -->
				<!-- profile-background -->
				<div class="section-back-img clearfix">
					<c:choose>
						<c:when test="${userInfoAdd.backImage != null}">
							<img id="section-back-img" alt="프로필 바탕화면" src='/member/display?fileName=${userInfoAdd.backImage}'>
						</c:when>
						<c:when test="${userInfoAdd.backImage == null}">
							<img id="section-back-img" alt="프로필 바탕화면" src="/img/info/서울1.jpg"></c:when>
					</c:choose>
				</div>
				<!-- /profile-background -->
				
				<!-- profile-info -->
				<div class="profile-info">
					<!-- profile-img -->
					<div class="profile-img clearfix">
						<c:choose>
							<c:when test="${userInfoAdd.profile != null}">
									<img id="profile-img" src='/member/display?fileName=${userInfoAdd.profile}' alt="프로필">
							</c:when>
							<c:when test="${userInfoAdd.profile == null}">
								<c:choose>
									<c:when test="${userInfo.gender eq null}">
											<img id="profile-img" src="/img/member/default_profile_m.png" alt="프로필">
									</c:when>	
									<c:when test="${userInfo.gender eq 'M'}">
											<img id="profile-img" src="/img/member/default_profile_m.png" alt="프로필">
									</c:when>	
									<c:when test="${userInfo.gender eq 'F'}">
											<img id="profile-img" src="/img/member/default_profile_f.png" alt="프로필">
									</c:when>	
								</c:choose>
							</c:when>
						</c:choose>	
					</div>
					<!-- /profile-img -->
				
					<!-- profile-detail -->
					<div class="profile-detail">
						<div class="row1">
							<span class="user-name">사용자 닉네임</span>
							<span>포스팅</span>
							<span>팔로워</span>
							<span>팔로잉</span>
							<form method="POST" id="follow">
							<input type="hidden" value="${member.mem_code}" name="mem_code"> 
							<input type="hidden" value="${userInfo.mem_code}" name="following"> 
							<input type="hidden" value="${selFollowExist}" name="followExist"> 
							<input type="button" name="submit" class="follow" value="팔로우">
							</form>
							
						</div>
						<div class="row2">
							<span class="user-id">${userInfo.name}</span>
							<span class="posting">0</span>
							<span class="follower">${selFollower}</span>
							<span class="following">${selFollowing}</span>
						</div>
					</div>
					<!-- /profile-detail -->
				
					<!-- tab-list -->
					<div class="tab-list">
						<a class="timeline">타임라인</a> 
					</div>
					<!-- /tab-list -->
				</div>
				<!-- /profile-info -->
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-25">
							<!-- article-introduce -->
							<div class="article-item article-sns-user1">
								<h2>소개</h2>
								<br>
								<img class="location-job-birth-intertest" src="/img/member/info_location.png" alt="지역">
								<c:choose>
								<c:when test="${userInfoAdd.address != null}"><span class="profile-content">${userInfoAdd.address}</span><br></c:when>
								<c:when test="${userInfoAdd.address == null}"><span class="profile-content">정보가 없습니다</span><br></c:when>
								</c:choose>
								<img class="location-job-birth-intertest"src="/img/member/info_job.png" alt="직업">
								<c:choose>
								<c:when test="${userInfoAdd.job != null}"><span class="profile-content">${userInfoAdd.job}</span><br></c:when>
								<c:when test="${userInfoAdd.job == null}"><span class="profile-content">정보가 없습니다</span><br></c:when>
								</c:choose>
								<img class="location-job-birth-intertest" src="/img/member/info_birth.png" alt="생일">
								<c:choose>
								<c:when test="${userInfoAdd.birth != null}"><span class="profile-content">${userInfoAdd.birth}</span><br></c:when>
								<c:when test="${userInfoAdd.birth == null}"><span class="profile-content">정보가 없습니다</span><br></c:when>
								</c:choose>
								<img class="location-job-birth-intertest" src="/img/member/info_interests.png" alt="관심사">
								<c:choose>
								<c:when test="${userInfoAdd.interest != null}"><span class="profile-content">${userInfoAdd.interest}</span><br></c:when>
								<c:when test="${userInfoAdd.interest == null}"><span class="profile-content">정보가 없습니다</span><br></c:when>
								</c:choose>						
							</div>
							<!-- /article-introduce -->
	
							<!-- article-sns-user -->
							<%@include file="../includes/article_sns_user.jsp"%>
							<!-- article-adsense -->
							<div class="article-item article-adsense">
								또드또스<br> 또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>
							</div>
							<!-- /article-adsense -->
						</div>
	
						<div class="article-wrapper article-75">
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								<!-- sns-content -->
								<img class="sns-user-img" src="/img/member/default_profile_f.png">
								<div class="sns-title">								
									<h2>XXX님이 리뷰를 남겼습니다.</h2>
									리뷰 날짜<br />
								</div>
								<div class="sns-imgP">
									<img class="sns-imgC" src="/img/member/나홀로_기차.jpg">
								</div>
								<div class="sns-content">
									Holy Shit <br /> What the Fuck <br /> Oh my GOD<br /> #앙기모디
								</div>
								<div id="like-reply-share">
									<img class="likeImg" src="/img/planner/heart_normal.png">
									<a href="#"><img class="replyImg" src="/img/sns/chat.png"></a>
									<a href="#"><img class="shareImg" src="/img/sns/share.png"></a>
								</div>
								<form class="reply">
									<input type="text" id="reply" name="reply" placeholder="   댓글 달기...">
									<hr class="underline">
								</form>
								<!-- /sns-content -->
	
								<!-- sns-reply(1) -->
								<div class="sns-user-reply">
									<img class="reply-user-img" src="/img/member/default_profile_f.png">
									<a class="reply-username">사용자 닉네임</a> 
									<a class="reply-content">와!!!</a> <br>
									<ul class="like-write_reply-write_date">
										<li><a class="reply-like" href=#>좋아요</a></li>
										<li><a class="reply-reply" href=#>답글달기</a></li>
										<li class="reply-write_date">작성날짜</li>
									</ul>
								</div>
								<!-- /sns-reply(1) -->
	
								<!-- sns-reply(2) -->
								<div class="sns-user-reply">
									<img class="reply-user-img" src="/img/member/default_profile_m.png">
									<a class="reply-username">정찬우</a> 
									<a class="reply-content">Fuck You Man</a> <br>
									<ul class="like-write_reply-write_date">
										<li><a class="reply-like" href=#>좋아요</a></li>
										<li><a class="reply-reply" href=#>답글달기</a></li>
										<li class="reply-write_date">작성날짜</li>
									</ul>
								</div>
								<!-- /sns-reply(2) -->
							</div>
							<!-- /article-sns-content -->
	
							<!-- see-more-button -->
							<div class="see-more-buttondiv">
								<button class="see-more-button">더 보 기 ▼</button>
							</div>
							<!-- /see-more-button -->
						</div>
					</div>
					<!-- /section-main -->
				</div>
			</div>
			<!-- /content -->
			<script src="/js/timeline_modal.js" type="text/javascript"></script>
			
			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
	
		</div>
	</body>
</html>
