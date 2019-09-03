<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] TimeLine Page</title>
		<link rel="icon" href="/img/favicon.ico">
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		
		<!-- Css -->
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
		<!-- <script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script> -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
		<script src="/js/header.js" type="text/javascript"></script>
		<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
		<script src="/js/timeline.js" type="text/javascript"></script>
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
			<div class="content">
				<div class="content-wrapper">
				<!-- member-info -->
				<%@include file="../includes/member_info.jsp"%>
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-25">
							<!-- article-introduce -->
							<div class="article-item article-sns-user1">
								<h2>소개</h2>
								<img class="profileUpdate" src="/img/member/edit.png" alt="정보수정"
								onmouseover="this.src='/img/member/edit_hover.png'" onmouseout="this.src='/img/member/edit.png'"><br>
								<img class="location-job-birth-intertest" src="/img/member/info_location.png" alt="지역">
								<c:choose>
								<c:when test="${memadd.address != null}"><span class="profile-content">${memadd.address}</span><br></c:when>
								<c:when test="${memadd.address == null}"><span class="profile-content">정보를 입력해주세요</span><br></c:when>
								</c:choose>
								<img class="location-job-birth-intertest"src="/img/member/info_job.png" alt="직업">
								<c:choose>
								<c:when test="${memadd.job != null}"><span class="profile-content">${memadd.job}</span><br></c:when>
								<c:when test="${memadd.job == null}"><span class="profile-content">정보를 입력해주세요</span><br></c:when>
								</c:choose>
								<img class="location-job-birth-intertest" src="/img/member/info_birth.png" alt="생일">
								<c:choose>
								<c:when test="${memadd.birth != null}"><span class="profile-content">${memadd.birth}</span><br></c:when>
								<c:when test="${memadd.birth == null}"><span class="profile-content">정보를 입력해주세요</span><br></c:when>
								</c:choose>
								<img class="location-job-birth-intertest" src="/img/member/info_interests.png" alt="관심사">
								<c:choose>
								<c:when test="${memadd.interest != null}"><span class="profile-content">${memadd.interest}</span><br></c:when>
								<c:when test="${memadd.interest == null}"><span class="profile-content">정보를 입력해주세요</span><br></c:when>
								</c:choose>						
							</div>
							<div class="article-item article-sns-user2">
								<h2>소개</h2>
								<form action="/member/updateMemadd" method="GET" name="profileDetail">
									<input type="image" class="profileUpdate" src="/img/member/edit.png" name="profileUpdate" value="Submit"
									onmouseover="this.src='/img/member/edit_hover.png'" onmouseout="this.src='/img/member/edit.png'"><br>
									<input type="hidden" name="mem_code" value="${member.mem_code}">
									<input type="hidden" name="profile" value="${memadd.profile}">
									<input type="hidden" name="backImage" value="${memadd.backImage}">
									<img class="location-job-birth-intertest" src="/img/member/info_location.png" alt="지역">
									<c:choose>
									<c:when test="${memadd.address != null}">
									<input type="text" name="address" class="profile-text1" value="${memadd.address}"><br></c:when>
									<c:when test="${memadd.address == null}">
									<input type="text" name="address" class="profile-text1" value="정보를 입력해주세요"><br></c:when>
									</c:choose>
									<img class="location-job-birth-intertest"src="/img/member/info_job.png" alt="직업">
									<c:choose>
									<c:when test="${memadd.job != null}">
									<input type="text" name="job" class="profile-text2" value="${memadd.job}"><br></c:when>
									<c:when test="${memadd.job == null}">
									<input type="text" name="job" class="profile-text2" value="정보를 입력해주세요"><br></c:when>
									</c:choose>
									<img class="location-job-birth-intertest" src="/img/member/info_birth.png" alt="생일">
									<c:choose>
									<c:when test="${memadd.birth != null}">
									<input type="text" name="birth" class="profile-text3" value="${memadd.birth}"><br></c:when>
									<c:when test="${memadd.birth == null}">
									<input type="text" name="birth" class="profile-text3" value="정보를 입력해주세요"><br></c:when>
									</c:choose>
									<img class="location-job-birth-intertest" src="/img/member/info_interests.png" alt="관심사">
									<c:choose>
									<c:when test="${memadd.interest != null}">
									<input type="text" name="interest" class="profile-text4" value="${memadd.interest}"><br></c:when>
									<c:when test="${memadd.interest == null}">
									<input type="text" name="interest" class="profile-text4" value="정보를 입력해주세요"><br></c:when>
									</c:choose>						
								</form>
							<div class="profile-detailInfo">
						</div>
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
						</div>
					</div>
					<!-- /section-main -->
				</div>
				<!-- modal -->
				<div class ="modal fade" id='myModal' tabindex="-1" role="dialog" 
				aria-labelledby="myModalLabel" aria-hidden="true">
					<!-- modal-content -->	
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title clearfix" id="myModalLabel">게시글 수정</h4>
							<hr class="underline">
						</div>
						<div class="modal-body">
							<div class="form-group">	
								<textarea class="form-control" rows="1" cols="1" placeholder="수정할 내용"></textarea>							
							</div>
						</div>
						<div class="modal-footer">
							<hr class="underline">
							<button id='modalMoBtn' type="button" class="btn btn-warning">수정</button>
							<button id='modalCloseBtn' type="button" class="btn btn-default">닫기</button>
						</div>
					</div>
					<!-- /modal-content -->	
				</div>
				<!-- /modal-->	
			</div>
			<!-- /content -->
			<script src="/js/timeline_modal.js" type="text/javascript"></script>
			
			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
			
			<!-- sns_modal -->
			<%@include file="../includes/sns_modal.jsp"%>
	
		</div>
	</body>
</html>
