<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] Info Page</title>
	<link rel="icon" href="/img/favicon.ico">
	
	<!-- CSS -->
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/info.css" rel="stylesheet">
	<link href="/css/section_search.css" rel="stylesheet">
	<link href="/css/article_sns_user.css" rel="stylesheet">
	<link href="/css/login_modal.css" rel="stylesheet">
	
	<!-- JavaScript -->
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
	<script src="https://use.fontawesome.com/releases/v5.9.0/js/all.js"></script>
	<script src="/js/info.js" type="text/javascript"></script>
	<script src="/js/header.js" type="text/javascript"></script>
	<script src="/js/section_search.js" type="text/javascript"></script>
	<script src="/js/login_modal.js" type="text/javascript"></script>
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
</head>
<body>
	<c:if test="${not empty msg}">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</c:if>
	<!-- login_modal -->
	<%@include file="../includes/login_modal.jsp"%>

	<c:set var="areaName" value="${areaName}"></c:set> <input type="hidden" id="areaName" value="${areaName}">
	
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>

		<!-- content -->
		<div class="content">
			<div class="content-wrapper">

				<!-- section-search -->
				<div class="section-search">
					<div class="search-wrap clearfix">
						<div class="search-city">${areaName}&nbsp;▼</div>
						<ul class="search-list">
							<li class="search-item search-bed"><img src="/img/main/bed.png" class="search-icons" alt="bed" /><br>숙박</li>
							<hr color="#595959" noshade />
							<li class="search-item search-hotplace"><img src="/img/main/hotplace.png" class="search-icons" alt="hotplace" /><br>관광 명소</li>
							<hr color="#595959" noshade />
							<li class="search-item search-food"><img src="/img/main/food.png" class="search-icons" alt="food" /><br>맛집</li>
						</ul>
					</div>
				</div>
				<!-- ./section-search -->

				<!-- section-main -->
				<div class="section-main clearfix">
					<div class="article-wrapper article-75">
					
						<!-- 지역정보 (article-info) -->
						<div class="article-item article-info">
							<div class="article-title"><h2>${areaName}</h2></div>
							<div class="info-detail">${overview}</div>
							<div class="info-imgs">
								<img class="info-img" src="/img/info/${areaName}1.jpg"> 
								<img class="info-img" src="/img/info/${areaName}2.jpg"> 
								<img class="info-img" src="/img/info/${areaName}3.jpg">
							</div>
						</div>
						<!-- ./article-info -->

						<!-- 지역의 음식점 추천 (article-food) -->
						<div class="article-item article-food">
							<div class="article-title"><h2>${areaName}, 이 음식점은 어때요?</h2></div>
							<ul class="food-wrap">
								<c:forEach items="${foodList}" var="foodItem" varStatus="status">
									<c:if test="${status.count <= 3}">
										<li class="food-item">
											<form class="detailForm" method="post" action="./detail/${foodItem.title}">
												<input type="hidden" value="${foodItem.contentid}" name="contentid"> 
												<input type="hidden" value="${foodItem.contenttypeid}" name="contenttypeid">
												<input type="hidden" value="${foodItem.mapx}" name="mapx">
												<input type="hidden" value="${foodItem.mapy}" name="mapy">
												<input type="hidden" value="${areaName}" name="areaName">
												<input type="hidden" value="음식점" name="category">
												<div class="food-img" style="background:#d9d9d9  url('${foodItem.firstimage}') no-repeat center center/cover; width:100%; height:170px;"></div>
												<div class="food-detail">
													<div class="fname">${foodItem.title}</div>
													<div class="fcategory">${foodItem.cat3}</div>
												</div>
											</form>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
						<!-- ./article-food -->
						
						<!-- 특정 지역이 포함된 일일 코스 추천(article-course) -->
						<div class="article-item article-course">
							<div class="article-title"> <h2>${areaName}이 포함된 일일 코스 추천</h2> </div>
							<ul class="one-course-wrap">
								<c:forEach items="${courseList}" var="courseItem" varStatus="status">
									<c:if test="${status.count <= 2}">
										<li class="one-course-item">
											<c:if test="${courseItem.firstimage eq null}">	
												<img class="one-course-img" src="/img/default.png">
											</c:if>
											<c:if test="${courseItem.firstimage ne null}">	
												<img class="one-course-img" src="${courseItem.firstimage}">
											</c:if>
												
											<div class="one-course-info">
												<div class="one-course-title"> [ ${courseItem.title} ] <span class="one-course-cat3">${courseItem.cat3}</span></div>
												<div class="one-course-detail">${courseItem.courseSubNames}</div>
												<div class="one-course-overview">${courseItem.overview}</div>
												<div class="one-course-readcount">조회수 : ${courseItem.readcount}</div> 
												
											</div>
											<form class="courseForm" method="post" action="/info/courseDetail">
												<input type="hidden" name="areaName" value="${areaName}">
												<input type="hidden" name="firstimage" value="${courseItem.firstimage}">
												<input type="hidden" name="title"  value="${courseItem.title}">
												<input type="hidden" name="overview"  value="${courseItem.overview}">
												<input type="hidden" name="contentId"  value="${courseItem.contentid}">
											</form>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
						<!-- ./article-course -->

						<!-- 지역의 숙박 추천 (article-bed) -->
						<div class="article-item article-bed">
							<div class="article-title"> <h2>${areaName},이숙박은 어때요?</h2> </div>
							<ul class="bed-wrap">
								<c:forEach items="${accomList}" var="accomItem" varStatus="status">
									<c:if test="${status.count <= 3}">
										<li class="bed-item">
											<form class="detailForm" method="post" action="./detail/${accomItem.title}">
												<input type="hidden" value="${accomItem.contentid}" name="contentid"> 
												<input type="hidden" value="${accomItem.contenttypeid}" name="contenttypeid">
												<input type="hidden" value="${accomItem.mapx}" name="mapx">
												<input type="hidden" value="${accomItem.mapy}" name="mapy">
												<input type="hidden" value="${areaName}" name="areaName">
												<input type="hidden" value="숙박" name="category">
												<div class="bed-img" style="background:#d9d9d9  url('${accomItem.firstimage}') no-repeat center center/cover; width:100%; height:170px;"></div>
												<div class="bed-detail">
													<div class="bname">${accomItem.title}</div>
													<div class="bcategory">${accomItem.cat3}</div>
												</div>
											</form>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
						<!-- ./article-bed -->
					</div>


					<div class="article-wrapper article-25">
						<!-- article-search -->
						<%-- <div class="search-container">
							<input type="text" name="keyword" class="search-keyword" placeholder="검색"> <i class="fa fa-search icon"></i>
							<input type="hidden" name="areaName" value="${areaName}">
						</div> --%>
					
						<!-- article-sns-user -->
						<%@include file="../includes/article_sns_user.jsp"%>
						<!-- ./article-sns-user -->

						<!-- article-adsense -->
						<div class="article-item article-adsense">
							<div class="article-title">
								<h2>에드센스</h2>
							</div>
							<br> 에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>
						</div>
						<!-- ./article-adsense -->

					</div>
				</div>
				<!-- ./section-main -->
				
				<!-- search-modal부분 -->
				<%@include file="../includes/search_modal.jsp"%>
				
			</div>
		</div>

		<!-- footer -->
		<%@include file="../includes/footer.jsp"%>

	</div>
	
	
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
