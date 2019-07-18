<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>[RailGo] Info Page</title>
<link href="../../css/info/detail.css" rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://use.fontawesome.com/releases/v5.9.0/js/all.js"></script>
<script type="text/javascript" src="../../js/info/detail.rating.min.js"></script>
<script src="../../js/info/detail.js"></script>
</head>
<body>
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>

		<!-- menu-bar -->
		<div class="menu-bar">
			<!-- navi -->
			<div class="menu-navi">
				<ul class="menu-bar-ul">
					<li><a href="#"><span class="search-city">서울 &nbsp;
								▼</span></a></li>
					<a href="#"><li>숙 &nbsp;&nbsp;&nbsp; 박</li></a>
					<a href="#"><li>관광명소</li></a>
					<a href="/info/category"><li class="menu-clicked">맛 &nbsp;&nbsp;&nbsp; 집</li></a>
				</ul>
			</div>
		</div>

		<!-- place-info -->
		<div class="place-info-top">
			<div class="place-info-box">
				<br>
				<div class="place-category">
					<a href="#"><span>서울</span></a> 
						<i class="fas fa-angle-right"></i>
					<a href="#"><span>맛집</span></a> 
						<i class="fas fa-angle-right"></i>
					<a href="#"><span>한식</span></a> 
						<i class="fas fa-angle-right"></i>
					<a href="#"><span>을밀대</span></a>
				</div>
				<br> <br> <br>
				<div class="place-name">
					<h1>을밀대</h1>
				</div>
				<div class="place-info">
					<h3>서울 6대 냉면 맛집 중 진한 육수의 평양냉면</h3>
				</div>
				<br> <br>
				<ul class="info-text-ul">
					<li><i class="fas fa-map-marker-alt"></i> &nbsp; 서울 마포구 숭문길 24</li>
					<li><i class="fas fa-list"></i> &nbsp; 한식</li>
					<li><i class="fas fa-phone"></i> &nbsp; 02-717-1922</li>
					<span class="info-text-media"></span>
					<li><i class="fas fa-clock"></i> &nbsp; 매일 11:00 ~ 22:00</li>
					<li><i class="fas fa-wallet"></i> &nbsp; 11000원</li>
					<li><i class="fas fa-star"></i> &nbsp; 4.5</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- image -->
	<div class="info-img-container">
		<div class="info-imgs">
			<img class="info-img1" src="../../resources/img/info/을밀대.jpg">
			<img class="info-img2" src="../../resources/img/info/을밀대1.jpg">
			<img class="info-img3" src="../../resources/img/info/을밀대.jpg">
		</div>
	</div>
	<!-- content -->
	<div class="content">
		<div class="content-wrapper">

			<!-- section-main -->
			<div class="section-main clearfix">
				<div class="article-wrapper article-75">

					<!-- article-review-content -->
					<div class="article-item">
						<div class="review-title">
							<h2>리뷰</h2>
						</div>
						<br>

						<!-- review-board -->
						<!-- jstl <forEach> -->
						<div class="review-table">
							<img class="reviewer-img"
								src="../../resources/img/info/default_profile_f.png">
							<table>
								<tr>
									<th>박옥자</th>
									<td>슴슴한 국물이 일품입니다. 꼭 한번 가보세요.</td>
								</tr>
								<tr>
									<th class="review-rev">★★★★★</th>
									<td class="review-rdate">2019-07-11</td>
								</tr>
							</table>
						</div>
						<div class="review-imgs">
							<img class="review-img1" src="../../resources/img/info/을밀대.jpg">
							<img class="review-img2" src="../../resources/img/info/을밀대.jpg">
							<img class="review-img3" src="../../resources/img/info/을밀대.jpg">
							<img class="review-img4" src="../../resources/img/info/을밀대.jpg">
						</div>
						<!-- jstl </forEach> -->
						<div class="review-table">
							<img class="reviewer-img"
								src="../../resources/img/info/default_profile_m.png">
							<table>
								<tr>
									<th>정찬우</th>
									<td>정말 맛있습니다. 강추드립니다! 꼭 가보시길 ㅎㅎ</td>
								</tr>
								<tr>
									<th class="review-rev">★★★★</th>
									<td class="review-rdate">2019-07-15</td>
								</tr>
							</table>
						</div>
						<div class="review-imgs">
							<img class="review-img1" src="../../resources/img/info/을밀대.jpg">
							<img class="review-img2" src="../../resources/img/info/을밀대.jpg">
							<img class="review-img3" src="../../resources/img/info/을밀대.jpg">
							<img class="review-img4" src="../../resources/img/info/을밀대.jpg">
						</div>

						<!-- review-write -->
						<div class="review-register">
							<h2>리뷰 달기</h2>
						</div>
						<div class="review-table">
							<img class="reviewer-img"
								src="../../resources/img/info/default_profile_f.png">
							<div class="review-rating">
								<select id="example">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
								</select>
							</div>
						</div>
						<div class="review-register-box">
							<textarea class="review-textarea" rows="3" name='content'
								placeholder="&nbsp;&nbsp; 리뷰를 남겨주세요."></textarea>
							<button type="submit" class="review-register-btn">등록</button>
						</div>

					</div>
				</div>
				<div class="article-wrapper article-25">
					<!-- article-search -->
					<div class="search-container">
						<input type="text" class="search-keyword" name="keyword"
							placeholder="검색"> <a href="#"><i
							class="fa fa-search icon"></i></a>
					</div>
					<!-- recommend-area -->
					<div class="article-item">
						<h3>근처 다른 맛집</h3>
						<div class="recommend-area">
							<a href="#"><img class="recommend-img"
								src="../../resources/img/info/을밀대.jpg"></a>
							<div class="recommend-info">
								<a href="#"><span class="recommend-title">을밀대</span></a> <span
									class="recommend-category">한식</span> <span
									class="recommend-rating">★★★★</span>
							</div>
						</div>
						<div class="recommend-area">
							<a href="#"><img class="recommend-img"
								src="../../resources/img/info/을밀대.jpg"></a>
							<div class="recommend-info">
								<a href="#"><span class="recommend-title">을밀대</span></a> <span
									class="recommend-category">한식</span> <span
									class="recommend-rating">★★★★</span>
							</div>
						</div>
					</div>
					<!-- article-adsense -->
					<div class="article-item article-adsense">
						에드센스<br> 에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- footer -->
	<%@include file="../includes/footer.jsp"%>

</body>
</html>
