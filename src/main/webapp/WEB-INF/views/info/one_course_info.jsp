<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] Course Info Page</title>
	<link rel="icon" href="/img/favicon.ico">
	
	<!-- CSS -->
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/info/one_course_info.css" rel="stylesheet">
	<link href="/css/login_modal.css" rel="stylesheet">
	
	<!-- JavaScript -->
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
	<script src="/js/header.js" type="text/javascript"></script>
	<script src="/js/info/one_course_info.js" type="text/javascript"></script>
	<script src="/js/login_modal.js" type="text/javascript"></script>
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
</head>
<body>
	<!-- login_modal -->
	<%@include file="../includes/login_modal.jsp"%>
	<input type="hidden" id="firstimage-url" value="${firstimage}">
	
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>

		<!-- content -->
		<div class="content">
			<div class="content-wrapper">
				<!-- article-course-info -->
				<div class="article-course-info">
					<div class="firstimage"></div>
					<div class="course-info-box">
						<div class="course-title">" ${title} "</div>
						<div class="course-overview">${overview}</div>
						<div class="course-intro">- 코스 총 거리 : ${distance} <br>- 코스 총 소요시간 : ${taketime}</div>
					</div>
				</div>
				<!-- ./article-course-info -->

				<!-- section-main -->
				<div class="section-main clearfix">
					
					<!-- 왼쪽 부분 -->
					<div class="article-wrapper article-75">
						<!-- 일일 코스 부분 : article-course-detail -->
						<div class="article-item article-course-detail">
							<ul class="course-list">
								<c:forEach items="${list}" var="item" varStatus="status">
									<li class="course-item">
										<div class="course-item-left">
											<c:if test="${item.subdetailimg eq null}">
												<img class="course-item-subdetailimg" src="/img/default.png" alt="이미지 없음" />
											</c:if>
											<c:if test="${item.subdetailimg ne null}">
												<img class="course-item-subdetailimg" src="${item.subdetailimg}" alt="${item.subdetailalt}" />
											</c:if>
										</div>
										<div class="course-item-right">
											<div class="course-item-subname">${item.subnum+1}코스. ${item.subname}</div>
											<div class="course-item-subdetailoverview">${item.subdetailoverview}</div>
											<div class="course-item-detail-btn"> 자세히 보기 ☞ </div>
											<form class="detailForm" action="detailInfo" method="post">
												<input type="hidden" name="areaName" value="${areaName}">
												<input type="hidden" name="contentid" value="${item.subcontentid}">
											</form>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<!-- ./article-course-detail -->
					</div>
					<!-- ./왼쪽 부분 -->
					
					<!-- 오른쪽 부분 -->
					<div class="article-wrapper article-25">
						<div class="article-item article-course-recommend">
							<h2 style="color: #eb7d31;"> 다른 일일 추천 코스 </h2>
							<ul class="recommend-lists">
								<c:forEach items="${recommedList}" var="recommedItem" varStatus="status">
									<c:if test="${status.count <= 3}">
										<li class="recommend-item">
											<div class="recommend-area">
												<c:if test="${recommedItem.firstimage eq null}">
													<img class="recommend-img" src="/img/default.png">
												</c:if>
												<c:if test="${recommedItem.firstimage ne null}">
													<img class="recommend-img" src="${recommedItem.firstimage}">
												</c:if>
												<div class="recommend-info">
													<div class="recommend-title">${recommedItem.title}</div> 
													<span class="recommend-category">${recommedItem.cat3}</span> 
													<span class="recommend-readcount">[조회수: ${recommedItem.readcount}]</span>
													<!-- <span class="recommend-rating">★★★★</span> -->
												</div>
											</div>
											
											<form class="courseForm" method="post" action="/info/courseDetail">
												<input type="hidden" name="areaName" value="${areaName}">
												<input type="hidden" name="firstimage" value="${recommedItem.firstimage}">
												<input type="hidden" name="title"  value="${recommedItem.title}">
												<input type="hidden" name="overview"  value="${recommedItem.overview}">
												<input type="hidden" name="contentId"  value="${recommedItem.contentid}">
											</form>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
					<!-- ./오른쪽 부분 -->
					
				</div>
				
			</div>
			<!-- ./content-wrapper -->
		</div>
		<!-- ./content -->

		<!-- footer -->
		<%@include file="../includes/footer.jsp"%>

	</div>
	<!-- ./wrap -->
	
	
	<!-- JS (section-search) -->
	<script>
		$('.search-bed').on('click', function() {
			location.href = '../info/accom/' + '<c:out value="${areaName}"/>';
		});
		$('.search-hotplace').on('click', function() {
			location.href = '../info/hotplace/' + '<c:out value="${areaName}"/>';
		});
		$('.search-food').on('click', function() {
			location.href = '../info/food/' + '<c:out value="${areaName}"/>';
		});
	</script>
</body>
</html>
