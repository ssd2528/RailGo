<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] TimeLine Page</title>
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
							<!-- <div class="article-item article-adsense">
								또드또스<br> 또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>
							</div> -->
							<!-- /article-adsense -->
						</div>
	
						<div class="article-wrapper article-75">
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								<!-- sns-content -->
								<img class="sns-user-img" src="/img/member/default_profile_f.png">
								<div class="sns-title">
									<div class="sns-menubar">
									   <ul>
									      <li><button id="current">···</button>
									         <ul id="hide_li" class="hide">
									           <li><button id="sns-modify">수정</button></li>
									           <li><hr style="border:none;border:0.5px solid gray;"></li>
									           <li><button id="sns-delete">삭제</button></li>
									         </ul>
									      </li>
									   </ul>
									</div>									
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
	
		</div>
	</body>
</html>
