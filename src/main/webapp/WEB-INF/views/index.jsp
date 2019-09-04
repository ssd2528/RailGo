<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Main Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		<link rel="icon" href="/img/favicon.ico">
		<!-- CSS -->
		<link href="/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/index.css" rel="stylesheet">
		<link href="/css/section_search.css" rel="stylesheet">
		<link href="/css/article_sns_user.css" rel="stylesheet">
		<link href="/css/login_modal.css" rel="stylesheet">
		<link href="/css/sns_modal.css" rel="stylesheet">
		<link href="/css/nailer_schedule_modal.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
		<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
		<script src="/js/fontawesome.js" type="text/javascript"></script>
		<script src="/js/header.js" type="text/javascript"></script>
		<script src="/js/index.js" type="text/javascript"></script>
		<script src="/js/nailer_schedule_modal.js" type="text/javascript"></script>
		<script src="/js/section_search.js" type="text/javascript"></script>
		<script src="/js/login_modal.js" type="text/javascript"></script>
		<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
		<script src="/js/sns_modal.js" type="text/javascript"></script>
		
	</head>
	<body>
		<input type="hidden" value="${msg}">
		<c:if test="${not empty msg}">
			<script type="text/javascript">alert('${msg}');</script>
		</c:if>
		<!-- login_modal -->
		<%@include file="includes/login_modal.jsp"%>
		
		<!-- 컨셉 중 자세히 보기 클릭시  planner/plan페이지로 이동 -->
		<form id="list-schedule-json-data" method="post" action="./planner/plan">
			<input type="hidden" class="item" name="item">
			<input type="hidden" class="startday" name="startday">
			<input type="hidden" class="tickets" name="tickets">
			<input type="hidden" class="plancode" name="plancode">
		</form>
		
		<div class="wrap">
			<!-- header -->
			<%@include file="includes/header.jsp"%>
			
			<!-- content -->
			<div class="content">
				<div class="content-wrapper">
				
					<!-- section-search -->
					<div class="section-search">
						<div class="search-wrap clearfix">
							<div class="search-city">어디로 가시나요? &nbsp; ▼</div>
							<ul class="search-list">
								<li class="search-item search-bed"> 
									<img src="/img/main/bed.png" class="search-icons" alt="bed" /><br>숙 박 
								</li>
								<hr color="#595959" noshade>
								<li class="search-item search-hotplace"> 
									<img src="/img/main/hotplace.png" class="search-icons" alt="hotplace" /><br>관광 명소 
								</li>
								<hr color="#595959" noshade>
								<li class="search-item search-food"> 
									<img src="/img/main/food.png" class="search-icons" alt="food" /><br>맛 집 
								</li>
							</ul>
						</div>
					</div>
					<!-- ./section-search -->
					
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-75">
							<!-- article-course -->
							<div class="article-item article-course">
								<span class="show-course"><a href="#">혹시 내일로 여행이 처음이신가요? 이런 코스는 어떠세요?</a></span> <span class="close-btn">x</span>
								
								<div class="course-wrap">
									<div class="course-title"><h2>초보자를 위한 내일로 코스 추천</h2></div>
									<ul class="course-content">
										<li class="course-item">
											<div class="course-img" style="background:#d9d9d9  url('/img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="course-detail">#초보자_코스추천</div>
										</li>
										<li class="course-item">
											<div class="course-img" style="background:#d9d9d9 url('/img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="course-detail">#초보자_코스추천</div>
										</li>
										<li class="course-item">
											<div class="course-img" style="background:#d9d9d9 url('/img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="course-detail">#초보자_코스추천</div>
										</li>
									</ul>
								</div>
							</div>
							<!-- ./article-course -->
							
							<!-- article-concept -->
							<div class="article-item article-concept">
								<div class="article-title"><h2>컨셉 추천</h2></div>
								<!-- 컨셉1 (concept-wrap) -->
								<div class="concept-wrap">
									<div class="concept-title">"나홀로 떠나는 여행"에 대한 추천 코스</div>
									<c:if test='${plannerListBySolo eq null}'>
										<div class="no-list" style="background:#efefef; width:100%; padding:50px 0; text-align:center;">
											"나홀로 떠나는 여행"에 대한 코스 목록이 존재하지 않습니다.
										</div>
									</c:if>
									<c:if test='${plannerListBySolo ne null}'>
										<input type="hidden" id="plannerListBySolo" value='${plannerListBySolo}'>
										<ul class="concept-content concept-solo"></ul>
									</c:if>
								</div>
								<!-- 컨셉2 (concept-wrap) -->
								<div class="concept-wrap" style="margin-top:20px;">
									<div class="concept-title">"맛있는 음식들과 함께 떠나는 여행"에 대한 추천 코스</div>
									<c:if test='${plannerListByEating eq null}'>
										<div class="no-list" style="background:#efefef; width:100%; padding:50px 0; text-align:center;">
											"맛있는 음식들과 함께 떠나는 여행"에 대한 코스 목록이 존재하지 않습니다.
										</div>
									</c:if>
									<c:if test='${plannerListByEating ne null}'>
										<input type="hidden" id="plannerListByEating" value='${plannerListByEating}'>
										<ul class="concept-content concept-eating"></ul>
									</c:if>
								</div>
							</div>
							<!-- ./article-concept -->
							
							<!-- article-sns-content -->
							<c:forEach items="${sns}" var="sns" varStatus="status">
								<c:if test="${status.count <= 3}">
								<div class="article-item article-sns-content">
									<!-- SNS 게시물 목록 (sns-content-list)  -->
									<div class="sns-content-list">
										<!-- 각 SNS 게시글 (sns-content-item) -->
										<input type="hidden" class="reply-memCode" value="${member.mem_code}">
											<form id="sns-form" class="sns-form" method="POST" action="">
												<div class="sns-content-item">
													<!-- 게시물 이미지 목록 조회 -->
													<div class="sns-content-img col-50">
														<ul class="bxslider">
															<c:forEach items="${sns.imgList}" var="imgList">
																<li><img src='/sns/display?fileName=${imgList.imagePath}/s_${imgList.uuid}_${imgList.fileName}' alt="SNS이미지"></li>
															</c:forEach>
														</ul>
													</div>
													<!-- <div class="sns-content-img col-50" style="background:#d9d9d9 url('/img/default.png') no-repeat center center/cover;">게시글 이미지</div> -->
													<div class="col-45" style="padding:5px 0px;">
														<div class="sns-content-user">
															<!-- 작성자 프로필 사진 조회 -->
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
															<!-- 작성자 닉네임 조회 -->
															<div class="user-name">${sns.name} <span style="color:#595959; font-weight:normal; font-size:15px;">님이 글을 남겼습니다.</span></div>
															<!-- 작성자인 경우에만 수정/삭제 버튼 생성 -->
															<c:if test="${member.mem_code == sns.mem_code && member ne null}">
																<div class="sns-edit-btn"> ...
																	<div class="sns-edit-btn-group">
																		<div class="sns-content-edit">수정</div>
																		<div class="sns-content-delete">삭제</div>
																	</div>
																</div>	
															</c:if>
														</div>
														<!-- 게시물의 작성 내용 -->
														<div class="sns-content-body font-13">
															${sns.content}
														</div>
														<c:if test="${sns.commCount != 0}">
															<div class="sns-content-reply font-13 sns-content-modal">${sns.commCount}개의 댓글 모두보기</div>
														</c:if>
														<!-- 좋아요 버튼 -->
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
														<!-- 좋아요 갯수 조회 -->
														<c:if test="${sns.snsLikeCount != 0}">
															<div class="sns-heart-count font-13">좋아요 <span class="like-count">${sns.snsLikeCount}</span>개</div>
														</c:if>
														<c:if test="${sns.snsLikeCount == 0}">
															<div class="sns-heart-count" style="display:none;">좋아요 <span class="like-count">${sns.snsLikeCount}</span>개</div>
														</c:if>
														<!-- 게시물 작성일자 조회 -->
														<div class="sns-content-regDate font-13">
															<fmt:formatDate value="${sns.regDate}" pattern="yyyy년 MM월 dd일 HH:mm"/>
														</div>
													</div>
												</div> 
												<!-- ./각 SNS 게시글 (sns-content-item) -->
											</form>
									</div> 
									<!-- ./SNS 게시물 목록 (sns-content-list)  -->
								</div>
								<!-- article-sns-content -->
								</c:if>
							</c:forEach>
							
						</div>
						
						
						<div class="article-wrapper article-25">
							<!-- article-sns-user -->
							<%@include file="includes/article_sns_user.jsp"%>
							<!-- ./article-sns-user -->
						</div>
					</div> <!-- ./section-main -->
					
					<!-- search-modal부분 -->
					<%@include file="includes/search_modal.jsp"%>
				</div>
				<!-- ./content-wrapper -->
			</div>
			<!-- ./content -->
			
			<!-- nailer schedule modal -->
			<%@include file="planner/nailer_schedule_modal.jsp" %>
			<!-- login_modal -->
			<%@include file="includes/login_modal.jsp"%>	
			<!-- sns_modal -->
			<%@include file="includes/sns_modal.jsp"%>
			
			
			<!-- footer -->
			<%@include file="includes/footer.jsp"%>
			
		</div>
	</body>
</html>
