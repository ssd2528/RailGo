<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] UserInfo Page</title>
		<link rel="icon" href="/img/favicon.ico">
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- Css -->
		<link href="/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/article_sns_user.css" rel="stylesheet">
		<link href="/css/index.css" rel="stylesheet">
		<link href="/css/sns.css" rel="stylesheet">
		<link href="/css/sns_modal.css" rel="stylesheet">
		<link href="/css/content.css" rel="stylesheet">
		<link href="/css/timeline.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="/js/fontawesome.js" type="text/javascript"></script>
		<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
		<script src="/js/header.js" type="text/javascript"></script>
		<script src="/js/login_modal.js" type="text/javascript"></script>
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
							<c:if test="${member ne null}">
								<form method="POST" id="follow">
									<input type="hidden" value="${member.mem_code}" name="mem_code"> 
									<input type="hidden" value="${userInfo.mem_code}" name="following"> 
									<input type="hidden" value="${selFollowExist}" name="followExist"> 
									<input type="button" name="submit" class="follow" value="팔로우">
								</form>
							</c:if>
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
													<input type="hidden" class="reply-memCode" value="${member.mem_code}">
							<c:forEach items="${sns}" var="sns">
								<form id="sns-form" class="sns-form" method="POST" action="">
									<div class="article-item article-sns-content">
										<div class="sns-content-user">
											<c:choose>
												<c:when test="${sns.profile != null}">
													<img class="user-img" src='/member/display?fileName=${sns.profile}' alt="프로필">
												</c:when>
												<c:when test="${sns.profile == null}">
													<c:choose>
														<c:when test="${sns.gender eq 'M'}">
															<img class="user-img" src="/img/member/default_profile_m.png" alt="프로필 남" >
														</c:when>
														<c:when test="${sns.gender eq 'F'}">
															<img class="user-img" src="/img/member/default_profile_f.png" alt="프로필 여" >
														</c:when>
													</c:choose>	
												</c:when>
											</c:choose>
											<div class="user-name">${sns.name}</div>
											<c:if test="${member.mem_code == sns.mem_code && member ne null}">
												<div class="sns-edit-btn"> ...
													<div class="sns-edit-btn-group">
														<div class="sns-content-edit">수정</div>
														<div class="sns-content-delete">삭제</div>
													</div>
												</div>	
											</c:if>
										</div>
										
										<div class="sns-imgs">
											<ul class="bxslider">
												<c:forEach items="${sns.imgList}" var="imgList">
													<li><img src='/sns/display?fileName=${imgList.imagePath}/s_${imgList.uuid}_${imgList.fileName}' alt="SNS이미지"></li>
												</c:forEach>
											</ul>
										</div>
										
										<div class="sns-content-body">
											${sns.content}
										</div>
										<c:if test="${sns.commCount != 0}">
											<div class="sns-content-reply sns-content-modal">${sns.commCount}개의 댓글 모두보기</div>
										</c:if>
										<ul class="icon-list"> 
											<c:if test="${sns.snsLikeCheck == false}">
												<li> <img id="sns-heart" class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
											</c:if>
											<c:if test="${sns.snsLikeCheck == true}">
												<li> <img id="sns-heart" class="sns-icon sns-heart-clicked" src="../img/sns/heart_clicked.png" alt="좋아요"> </li>
											</c:if>
											<li> <img class="sns-icon sns-chat sns-content-modal" src="../img/sns/chat.png" alt="댓글달기"> </li>
										</ul>
										<input type="hidden" name="sns_code" class="sns_code" value="${sns.sns_code}">
										<c:if test="${sns.snsLikeCount != 0}">
											<div class="sns-heart-count">좋아요 <span class="like-count">${sns.snsLikeCount}</span>개</div>
										</c:if>
										<c:if test="${sns.snsLikeCount == 0}">
											<div class="sns-heart-count" style="display:none;">좋아요 <span class="like-count">${sns.snsLikeCount}</span>개</div>
										</c:if>
										<div class="sns-content-regDate">
											<fmt:formatDate value="${sns.regDate}" pattern="yyyy년 MM월 dd일 HH:mm"/>
										</div>
									</div>
								</form>
							</c:forEach>
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
