<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] SNS Page</title>
		<link rel="icon" href="/img/favicon.ico">
		<!-- CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/article_sns_user.css" rel="stylesheet">
		<link href="/css/login_modal.css" rel="stylesheet">
		<link href="/css/sns.css" rel="stylesheet">
		<link href="/css/sns_modal.css" rel="stylesheet">
		
		
		<!-- JavaScript -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
		<script src="/js/sns.js" type="text/javascript"></script>
		<script src="/js/login_modal.js" type="text/javascript"></script>
		<script src="/js/sns_modal.js" type="text/javascript"></script>
		<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
		
	</head>
	<body>
		<c:if test="${not empty authMsg}">
			<script type="text/javascript">alert('${authMsg}');</script>
		</c:if>
		<!-- login_modal -->
		<%@include file="../includes/login_modal.jsp"%>
	
		
		<div class="wrap">
			<!-- header -->
			<%@include file="../includes/header.jsp"%>
			
			<!-- content -->
			<div class="content">
				<!-- content-wrapper -->
				<div class="content-wrapper">
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-75">
							<!-- article-sns-content -->
							<input type="hidden" class="reply-memCode" value="${member.mem_code}">
							<c:forEach items="${sns}" var="sns">
								<form id="sns-form" class="sns-form" method="POST" action="">
									<div class="article-item article-sns-content">
										<div class="sns-content-user">
											<c:choose>
												<c:when test="${sns.profile eq '' || sns.profile eq null}">
													<c:choose>
														<c:when test="${sns.gender eq 'M'}">
															<img class="user-img" src="/img/member/default_profile_m.png" alt="프로필 남" >
														</c:when>
														<c:when test="${sns.gender eq 'F'}">
															<img class="user-img" src="/img/member/default_profile_f.png" alt="프로필 여" >
														</c:when>
													</c:choose>	
												</c:when>
												<c:when test="${sns.profile ne '' || sns.profile ne null}">
													<img class="user-img" src='/member/display?fileName=${sns.profile}' alt="프로필">
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
										<input type="hidden" name="mem_code" class="mem_code" value="${member.mem_code}">
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
						</div>
						
						<div class="article-wrapper article-25">
							<!-- article-sns-user -->
							<%@include file="../includes/article_sns_user.jsp"%>
							<!-- ./article-sns-user -->
							
							<!-- article-adsense -->
							<div class="article-item article-adsense">
								<div class="article-title"><h2>에드센스</h2></div><br>
								에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>
							</div>
							<!-- ./article-adsense -->
						</div>
					<!-- 글 작성 (+버튼) -->
					<c:if test="${member ne null}">
						<img class="sns-write-img" src="../img/sns/plus.png" alt="작성">
					</c:if>	
					<!-- ./글 작성 (+버튼) -->
					</div>
					<!-- ./section-main -->
				</div>
				<!-- ./content-wrapper -->
			</div>
			<!-- ./content -->
			
			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
		</div>
		
		<!-- sns_modal -->
		<%@include file="../includes/sns_modal.jsp"%>
	</body>
</html>