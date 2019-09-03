<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] SNS Page</title>
	<link rel="icon" href="/img/favicon.ico">
	<!-- CSS -->
	<link href="/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/index.css" rel="stylesheet">
	<link href="/css/article_sns_user.css" rel="stylesheet">
	<link href="/css/login_modal.css" rel="stylesheet">
	<link href="/css/content.css" rel="stylesheet">
	
	<!-- JavaScript -->
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	<script src="/js/fontawesome.js" type="text/javascript"></script>
	<script src="/js/content.js" type="text/javascript"></script>
	<script src="/js/login_modal.js" type="text/javascript"></script>
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
</head>
<body>
	<c:if test="${not empty authMsg}">
		<script type="text/javascript">
			alert('${authMsg}');
		</script>
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
					<!-- sns-content-wrapper -->
					<div class="sns-content-wrapper">
						<div class="sns-modal-imgs">
							<ul class="bxslider">
								<c:forEach items="${imgList}" var="imgList">
									<li><img src='/sns/display?fileName=${imgList.imagePath}/s_${imgList.uuid}_${imgList.fileName}' alt="SNS이미지"></li>
								</c:forEach>
							</ul>
						</div>
						<div class="sns-article-right">
							<span class="close">&times;</span>
							<div class="sns-content">
								<!-- 게시글 작성자의 Mem_Code 및 닉네임 조회 -->
								<input type="hidden" value="${content.mem_code}" id="sns-writer-code">
								<input type="hidden" value="${content.name}" id="sns-writer-name">
								
								<div class="sns-content-write">
									<c:choose>
										<c:when test="${content.profile eq null || content.profile eq ''}">
											<c:choose>
												<c:when test="${content.gender eq 'M'}">
													<img class="sns-writer-profile" src="/img/member/default_profile_m.png" alt="프로필 남" >
												</c:when>
												<c:when test="${content.gender eq 'F'}">
													<img class="sns-writer-profile" src="/img/member/default_profile_f.png" alt="프로필 여" >
												</c:when>
											</c:choose>	
										</c:when>
										<c:when test="${content.profile ne null}">
											<img class="sns-writer-profile" src='/member/display?fileName=${content.profile}' alt="프로필">
										</c:when>
									</c:choose>
									<div class="sns-write-box">
										<div class="sns-write-explain">
											<span class="sns-writer-name"></span>${content.name}님이 리뷰를 남기셨습니다.
										</div>
										<div class="sns-write-date">
											<fmt:formatDate value="${content.regDate}" pattern="yyyy년 MM월 dd일 HH:mm"/>
										</div>
									</div>
								</div>
								<hr class="sns-line">
							</div>
	
							<div class="sns-content-substance">
								<div class="sns-substance">
									${content.content}
								</div>
							</div>
							<div class="sns-reply-container">
								<c:if test="${commList ne null}">
									<c:forEach items="${commList}" var="commList">
										<div class="sns-rereply-append">
											<div class="sns-reply-box">
												<c:choose>
													<c:when test="${commList.profile != null}">
														<img class="sns-replyer-profile" src='/member/display?fileName=${commList.profile}' alt="프로필">
													</c:when>
													<c:when test="${commList.profile == null}">
														<c:choose>
															<c:when test="${commList.gender eq 'M'}">
																<img class="sns-replyer-profile" src="/img/member/default_profile_m.png" alt="프로필 남" >
															</c:when>
															<c:when test="${commList.gender eq 'F'}">
																<img class="sns-replyer-profile" src="/img/member/default_profile_f.png" alt="프로필 여" >
															</c:when>
														</c:choose>	
													</c:when>
												</c:choose>
												<table class="sns-reply-content">
													<tr>
														<th class="sns-reply-writer">${commList.name}</th>
														<td class="sns-reply-substance">${commList.content}</td>
														<c:if test="${commList.mem_code == member.mem_code}">
															<td class="delete-btn reply-delete-btn">삭제</td>
														</c:if>
													</tr>
													<tr>
														<th class="sns-reply-reply">
															<span class="sns-reply-write">답글달기</span></th>
														<td class="sns-reply-date">
															<fmt:formatDate value="${commList.regDate}" pattern="yyyy년 MM월 dd일 HH:mm"/>
														</td>
													</tr>
												</table>
												<input type="hidden" class="reply-commCode" name="comm_code" value="${commList.comm_code}">
												
											</div>
										</div>
										<c:if test="${commList.rereList ne null}">
											<c:forEach items="${commList.rereList}" var="rereList">
												<div class="sns-rereply">
													<div class="sns-rereply-left">
														<img class="sns-rereply-icon" src="/img/sns/rereply.png">
														<c:choose>
															<c:when test="${rereList.profile != null}">
																<img class="sns-replyer-profile" src='/member/display?fileName=${commList.profile}' alt="프로필">
															</c:when>
															<c:when test="${rereList.profile == null}">
																<c:choose>
																	<c:when test="${rereList.gender eq 'M'}">
																		<img class="sns-replyer-profile" src="/img/member/default_profile_m.png" alt="프로필 남" >
																	</c:when>
																	<c:when test="${rereList.gender eq 'F'}">
																		<img class="sns-replyer-profile" src="/img/member/default_profile_f.png" alt="프로필 여" >
																	</c:when>
																</c:choose>	
															</c:when>
														</c:choose>
													</div>
													<div class="sns-rereply-right">
														<table class="sns-rereply-content">
															<tr>
																<th rowspan="2" class="sns-rereply-writer">${rereList.name}</th>
																<td class="sns-rereply-substance">${rereList.content}</td>
																<c:if test="${rereList.mem_code == member.mem_code}">
																	<td class="delete-btn rereply-delete-btn">삭제</td>
																	<input type="hidden" class="rereply-commCode" name="comm_code" value="${rereList.comm_code}">
																	<input type="hidden" class="rereply-memCode" name="mem_code" value="${rereList.mem_code}">
																</c:if>
															</tr>
															<tr>
																<td class="sns-rereply-date">
																	<fmt:formatDate value="${rereList.regDate}" pattern="yyyy년 MM월 dd일 HH:mm"/>
																</td>
															</tr>
														</table>
													</div>
												</div>
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
							<div class="sns-bottom">
								<hr class="sns-line">
								<ul class="sns-icon-list">
									<c:if test="${likeCheck == false || likeCheck == null}">
										<li> <img id="sns-heart" class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
									</c:if>
									<c:if test="${likeCheck == true}">
										<li> <img id="sns-heart" class="sns-icon sns-heart-clicked" src="/img/sns/heart_clicked.png" alt="좋아요"> </li>
									</c:if>
									<li><img class="sns-icon sns-chat"
										src="/img/sns/chat.png" alt="댓글달기"></li>
									<c:if test="${likeCount != 0}">
										<div class="sns-heart-count">좋아요 <span class="like-count">${likeCount}</span>개</div>
									</c:if>
									<c:if test="${likeCount == 0}">
										<div class="sns-heart-count" style="display:none;">좋아요 <span class="like-count">${likeCount}</span>개</div>
									</c:if>
								</ul>
								<div class="sns-reply-register">
									<textarea class="sns-reply-textarea" rows="1" name='reply'
										placeholder="&nbsp;&nbsp; 댓글 달기..."></textarea>
									<input type="button" class="sns-reply-btn" value="게시" />
									<input type="hidden" class="reply-snsCode" value="${content.sns_code}">
									<input type="hidden" class="member-memCode" value="${member.mem_code}">
								</div>
								<input type="hidden" name="member-gender" value="${member.gender}">
								<input type="hidden" name="member-profile" value="${memadd.profile}">
							</div>
						</div>
						<!-- ./section-main -->
					</div>
					<!-- ./sns-content-wrapper -->
				</div>
				<!-- ./content-wrapper -->
				
			</div>
			<!-- ./content -->

			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
		</div>
</body>
</html>