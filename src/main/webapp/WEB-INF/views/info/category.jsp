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
<link href="../../css/info/category.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://use.fontawesome.com/releases/v5.9.0/js/all.js"></script>
<script src="../../js/info/category.js"></script>
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
					<a href="#"><li class="menu-clicked">맛 &nbsp;&nbsp;&nbsp;
							집</li></a>
				</ul>
			</div>
		</div>

		<!-- category-info -->
		<div class="category-info-top">
			<div class="category-info-box">
				<br>
				<div class="category-category">
					<a href="#"><span>서울</span></a> <i class="fas fa-angle-right"></i>
					<a href="#"><span>맛집</span></a>
				</div>
				<br> <br> <br>
				<div class="category-name">
					<h1>서울 맛집</h1>
				</div>
			</div>
		</div>
	</div>

	<!-- content -->
	<div class="content">
		<div class="content-wrapper">
			<!-- section-main -->
			<div class="section-main clearfix">

				<div class="article-wrapper article-25">
					<!-- article-search -->
					<div class="search-container">
						<input type="text" class="search-keyword" name="keyword"
							placeholder="검색"> <a href="#"><i
							class="fa fa-search icon"></i></a>
					</div>
					
					<!-- checkbox-area -->
					<div class="article-item">
						<div class="checkbox-block">
							<div class="checkbox-title">카테고리</div>
							<div class="checkbox-chk">
								<input type="checkbox" id="c1" name="chk_category" />
							    <label for="c1"><span></span>한식</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="c2" name="chk_category" />
							    <label for="c2"><span></span>중식</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="c3" name="chk_category" />
							    <label for="c3"><span></span>일식</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="c4" name="chk_category" />
							    <label for="c4"><span></span>디저트</label>
						    </div>
						    <span id="checkbox-more" class="checkbox-more-category">더 보기  <i class="fas fa-caret-down"></i></span>
						    <div id="checkbox-chk-hide" class="checkbox-category-hide">
							    <div class="checkbox-chk">
								    <input type="checkbox" id="c5" name="chk_category" />
								    <label for="c5"><span></span>양식</label>
							    </div>
							    <div class="checkbox-chk">
								    <input type="checkbox" id="c6" name="chk_category" />
								    <label for="c6"><span></span>조성식</label>
							    </div>
							<span id="checkbox-fold" class="checkbox-fold-category">접기 <i class="fas fa-caret-up"></i></span>
							</div>
						    <hr class="checkbox-hr">
						</div>
						
						<div class="checkbox-block">
							<div class="checkbox-title">여행테마</div>
							<div class="checkbox-chk">
								<input type="checkbox" id="t1" name="chk_theme" />
							    <label for="t1"><span></span>나홀로</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="t2" name="chk_theme" />
							    <label for="t2"><span></span>둘이서</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="t3" name="chk_theme" />
							    <label for="t3"><span></span>셋이상</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="t4" name="chk_theme" />
							    <label for="t4"><span></span>힐링</label>
						    </div>
						    <span id="checkbox-more" class="checkbox-more-theme">더 보기  <i class="fas fa-caret-down"></i></span>
						    <div id="checkbox-chk-hide" class="checkbox-theme-hide">
							    <div class="checkbox-chk">
								    <input type="checkbox" id="t5" name="chk_theme" />
								    <label for="t5"><span></span>먹방</label>
							    </div>
							    <div class="checkbox-chk">
								    <input type="checkbox" id="t6" name="chk_theme" />
								    <label for="t6"><span></span>조성식</label>
							    </div>
							<span id="checkbox-fold" class="checkbox-fold-theme">접기 <i class="fas fa-caret-up"></i></span>
							</div>
						    <hr class="checkbox-hr">
						</div>
						
						<div class="checkbox-block">
							<div class="checkbox-title">가격대</div>
							<div class="checkbox-chk">
								<input type="checkbox" id="p1" name="chk_price" />
							    <label for="p1"><span></span>~ 10000</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="p2" name="chk_price" />
							    <label for="p2"><span></span>10000 ~ 30000</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="p3" name="chk_price" />
							    <label for="p3"><span></span>30000 ~ 50000</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="p4" name="chk_price" />
							    <label for="p4"><span></span>50000 ~</label>
						    </div>
						    <hr class="checkbox-hr">
						</div>
						
						<div class="checkbox-block">
							<div class="checkbox-title">여행시기</div>
							<div class="checkbox-chk">
								<input type="checkbox" id="s1" name="chk_season" />
							    <label for="s1"><span></span>여름에 주로</label>
						    </div>
						    <div class="checkbox-chk">
							    <input type="checkbox" id="s2" name="chk_season" />
							    <label for="s2"><span></span>겨울에 주로</label>
						    </div>
						    <div class="checkbox-br"></div>
						</div>
					</div>
				</div>


				<div class="article-wrapper article-75">

					<!-- info-board -->
					<div class="article-item">
						<div class="info-top">
							검색결과 총 
							<span class="result-amount">310</span>개
							
							<span class="info-group">조회순 / 평점순</span>							
						</div>

						<!-- info-list -->
						<!-- jstl forEach-->
						<div class="info-list">
							<a href="#"><img class="info-list-img"
								src="../../resources/img/info/을밀대.jpg"></a>
							<div class="info-list-data">
								<a href="/info/detail"><h2 class="info-list-title">을밀대</h2></a>
								<div class="info-explain">서울 6대 냉면 맛집 중 진한 육수의 평양냉면</div>
								<div class="info-category">
									<i class="fas fa-map-marker-alt"></i> 서울 마포구 숭문길 24
									&nbsp;&nbsp;
									<i class="fas fa-list"></i> 한식
								</div>
								<div class="info-rating">★★★★</div>
							</div>
						</div>
						<hr class="checkbox-hr">
						<!-- jstl /forEach -->
						
						<div class="info-list">
							<a href="#"><img class="info-list-img"
								src="../../resources/img/info/을밀대.jpg"></a>
							<div class="info-list-data">
								<a href="#"><h2 class="info-list-title">을밀대</h2></a>
								<div class="info-explain">서울 6대 냉면 맛집 중 진한 육수의 평양냉면</div>
								<div class="info-category">
									<i class="fas fa-map-marker-alt"></i> 서울 마포구 숭문길 24
									&nbsp;&nbsp;
									<i class="fas fa-list"></i> 한식
								</div>
								<div class="info-rating">★★★★</div>
							</div>
						</div>
						<hr class="checkbox-hr">
						<div class="info-list">
							<a href="#"><img class="info-list-img"
								src="../../resources/img/info/을밀대.jpg"></a>
							<div class="info-list-data">
								<a href="#"><h2 class="info-list-title">을밀대</h2></a>
								<div class="info-explain">서울 6대 냉면 맛집 중 진한 육수의 평양냉면</div>
								<div class="info-category">
									<i class="fas fa-map-marker-alt"></i> 서울 마포구 숭문길 24
									&nbsp;&nbsp;
									<i class="fas fa-list"></i> 한식
								</div>
								<div class="info-rating">★★★★</div>
							</div>
						</div>
						<hr class="checkbox-hr">
						<div class="info-list">
							<a href="#"><img class="info-list-img"
								src="../../resources/img/info/을밀대.jpg"></a>
							<div class="info-list-data">
								<a href="#"><h2 class="info-list-title">을밀대</h2></a>
								<div class="info-explain">서울 6대 냉면 맛집 중 진한 육수의 평양냉면</div>
								<div class="info-category">
									<i class="fas fa-map-marker-alt"></i> 서울 마포구 숭문길 24
									&nbsp;&nbsp;
									<i class="fas fa-list"></i> 한식
								</div>
								<div class="info-rating">★★★★</div>
							</div>
						</div>
						<hr class="checkbox-hr">
						<div class="info-list">
							<a href="#"><img class="info-list-img"
								src="../../resources/img/info/을밀대.jpg"></a>
							<div class="info-list-data">
								<a href="#"><h2 class="info-list-title">을밀대</h2></a>
								<div class="info-explain">서울 6대 냉면 맛집 중 진한 육수의 평양냉면</div>
								<div class="info-category">
									<i class="fas fa-map-marker-alt"></i> 서울 마포구 숭문길 24
									&nbsp;&nbsp;
									<i class="fas fa-list"></i> 한식
								</div>
								<div class="info-rating">★★★★</div>
							</div>
						</div>
						<hr class="checkbox-hr">
						
						<!-- list-paging -->
						<div class="paging">
							<ul class="pagination">
								<a href="#"><li class="pagination-prev">이전</li></a>
								<a href="#"><li>1</li></a>
								<a href="#"><li>2</li></a>
								<a href="#"><li>3</li></a>
								<a href="#"><li class="pagination-next">다음</li></a>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- footer -->
	<%@include file="../includes/footer.jsp"%>
</body>
</html>
