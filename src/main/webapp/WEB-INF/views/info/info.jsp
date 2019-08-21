<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>[RailGo] Info Page</title>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->

<!-- CSS -->
<link href="../css/common.css" rel="stylesheet">
<link href="../css/info.css" rel="stylesheet">
<link href="../css/section_search.css" rel="stylesheet">
<link href="../css/article_sns_user.css" rel="stylesheet">

<!-- JavaScript -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
<script src="../js/info.js" type="text/javascript"></script>
<script src="../js/header.js" type="text/javascript"></script>
<script src="../js/section_search.js" type="text/javascript"></script>
</head>
<body>
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
							<li class="search-item search-accom"><a href="#"> <img
									src="../img/main/bed.png" class="search-icons" alt="bed" /><br>숙
									박
							</a></li>
							<hr color="#595959" noshade>
							<li class="search-item search-place"><a href="#"> <img
									src="../img/main/hotplace.png" class="search-icons"
									alt="hotplace" /><br>관광 명소
							</a></li>
							<hr color="#595959" noshade>
							<li class="search-item search-food"><a href="#"> <img
									src="../img/main/food.png" class="search-icons" alt="food" /><br>맛
									집
							</a></li>
						</ul>
					</div>
				</div>

				<!-- section-main -->
				<div class="section-main clearfix">
					<div class="article-wrapper article-75">
						<!-- 지역정보 (article-info) -->
						<div class="article-item article-info">
							<div class="article-title">
								<h2>${areaName}</h2>
							</div>
							<div class="info-detail">대한민국의 수도인 서울은 현대적인 고층 빌딩, 첨단 기술의
								지하철, 대중문화와 사찰, 고궁, 노점상이 공존하는 대도시입니다. 주목할 만한 명소로는 곡선으로 이루어진 외관과
								옥상 공원을 특징으로 하는 초현대적 디자인의 컨벤션 홀인 동대문디자인플라자, 한때 7,000여 칸의 방이 자리하던
								경복궁, 회화나무와 소나무 고목이 있는 조계사가 있습니다.</div>
							<div class="info-imgs">
								<img class="info-img" src="../img/info/서울1.jpg"> <img
									class="info-img" src="../img/info/서울2.jpg"> <img
									class="info-img" src="../img/info/서울3.jpg">
							</div>
						</div>
						<!-- ./article-info -->

						<!-- 지역의 음식점 추천 (article-food) -->
						<div class="article-item article-food">
							<div class="article-title">
								<h2>${areaName},이 음식점은 어때요?</h2>
							</div>

							<ul class="food-wrap">
								<c:forEach items="${foodList}" var="foodItem">
									<li class="food-item"> 
										<form class="detailForm" method="post" action="./detail/${foodItem.title}">
										<input type="hidden" value="${foodItem.contentid}" name="contentid">
										<input type="hidden" value="${foodItem.contenttypeid}" name="contenttypeid">
										<input type="hidden" value="${foodItem.mapx}" name="mapx">
										<input type="hidden" value="${foodItem.mapy}" name="mapy">
										<input type="hidden" value="${areaName}" name="areaName">
										<div class="food-img"
											style="background:#d9d9d9  url('${foodItem.firstimage}') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="food-detail">
											<div class="fname">${foodItem.title}</div>
											<div class="fcategory">${foodItem.cat3}</div>
										</div>
										</form>
									</li>
								</c:forEach>
							</ul>
						</div>
						<!-- ./article-food -->

						<!-- 지역이 포함된 일정 추천(article-plan) -->
						<div class="article-item article-plan">
							<div class="article-title">
								<h2>${areaName}이포함된 다른 이용자들의 일정</h2>
							</div>
							<ul class="plan-wrap">
								<li class="plan-item"><img class="plan-img"
									src="../img/default.png">
									<div class="plan-detail">
										<span class="plan-hashtag">#해시태그 #해시태그</span> <span
											class="plan-course">서울-전주-순천-여수-부산-강릉</span> <span
											class="plan-user">글쓴이</span>
									</div></li>
								<li class="plan-item"><img class="plan-img"
									src="../img/default.png">
									<div class="plan-detail">
										<span class="plan-hashtag">#해시태그 #해시태그</span> <span
											class="plan-course">서울-전주-순천-여수-부산-강릉</span> <span
											class="plan-user">글쓴이</span>
									</div></li>
								<li class="plan-item"><img class="plan-img"
									src="../img/default.png">
									<div class="plan-detail">
										<span class="plan-hashtag">#해시태그 #해시태그</span> <span
											class="plan-course">서울-전주-순천-여수-부산-강릉</span> <span
											class="plan-user">글쓴이</span>
									</div></li>
							</ul>
						</div>
						<!-- ./article-plan -->

						<!-- 지역의 숙박 추천 (article-bed) -->
						<div class="article-item article-bed">
							<div class="article-title">
								<h2>${areaName},이 숙박은 어때요?</h2>
							</div>
							<ul class="bed-wrap">
								<c:forEach items="${accomList}" var="accomItem">
									<li class="bed-item"> 
										<form class="detailForm" method="post" action="./detail/${accomItem.title}">
										<input type="hidden" value="${accomItem.contentid}" name="contentid">
										<input type="hidden" value="${accomItem.contenttypeid}" name="contenttypeid">
										<input type="hidden" value="${accomItem.mapx}" name="mapx">
										<input type="hidden" value="${accomItem.mapy}" name="mapy">
										<input type="hidden" value="${areaName}" name="areaName">
										<div class="bed-img"
											style="background:#d9d9d9  url('${accomItem.firstimage}') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="bed-detail">
											<div class="bname">${accomItem.title}</div>
											<div class="bcategory">${accomItem.cat3}</div>
										</div>
										</form>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>


					<div class="article-wrapper article-25">
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

			</div>
		</div>

		<!-- footer -->
		<%@include file="../includes/footer.jsp"%>

	</div>
</body>
</html>
