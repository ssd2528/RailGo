<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
							<div class="search-city">서울 &nbsp; ▼</div>
							<ul class="search-list">
								<li class="search-item search-accom"> 
									<a href="#">
										<img src="../img/main/bed.png" class="search-icons" alt="bed" /><br>숙 박 
									</a> 
								</li>
								<hr color="#595959" noshade>
								<li class="search-item search-place"> 
									<a href="#">
										<img src="../img/main/hotplace.png" class="search-icons" alt="hotplace" /><br>관광 명소 
									</a> 
								</li>
								<hr color="#595959" noshade>
								<li class="search-item search-food"> 
									<a href="#">
										<img src="../img/main/food.png" class="search-icons" alt="food" /><br>맛 집 
									</a> 
								</li>
							</ul>
						</div>
					</div>
					
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-75">
							<!-- 지역정보 (article-info) -->
							<div class="article-item article-info">
								<div class="article-title"><h2>서울</h2></div>
								<div class="info-detail">
									대한민국의 수도인 서울은 현대적인 고층 빌딩, 첨단 기술의 지하철, 대중문화와 사찰, 고궁, 노점상이 공존하는 대도시입니다. 
									주목할 만한 명소로는 곡선으로 이루어진 외관과 옥상 공원을 특징으로 하는 초현대적 디자인의 컨벤션 홀인 동대문디자인플라자, 
									한때 7,000여 칸의 방이 자리하던 경복궁, 회화나무와 소나무 고목이 있는 조계사가 있습니다.
								</div>
								<div class="info-imgs">
									<img class="info-img" src="../img/info/서울1.jpg">
									<img class="info-img" src="../img/info/서울2.jpg">
									<img class="info-img" src="../img/info/서울3.jpg">
								</div>
							</div>
							<!-- ./article-info -->
						
							<!-- 지역의 음식점 추천 (article-food) -->
							<div class="article-item article-food">
								<div class="article-title"><h2>서울, 이 음식점은 어때요?</h2></div>
								<ul class="food-wrap">
									<li class="food-item">
										<div class="food-img" style="background:#d9d9d9  url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="food-detail">
											<div class="fname">음식점이름</div>
											<div class="fcategory">카테고리</div>
										</div>
									</li>
									<li class="food-item">
										<div class="food-img" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="food-detail">
											<div class="fname">음식점이름</div>
											<div class="fcategory">카테고리</div>
										</div>									</li>
									<li class="food-item">
										<div class="food-img" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="food-detail">
											<div class="fname">음식점이름</div>
											<div class="fcategory">카테고리</div>
										</div>
									</li>
								</ul>
							</div>
							<!-- ./article-food -->
							
							<!-- 지역이 포함된 일정 추천(article-plan) -->
							<div class="article-item article-plan">
								<div class="article-title"><h2>서울이 포함된 다른 이용자들의 일정</h2></div>
								<ul class="plan-wrap">
									<li class="plan-item">
										<img class="plan-img" src="../img/default.png">
										<div class="plan-detail">
											<span class="plan-hashtag">#해시태그  #해시태그</span>
											<span class="plan-course">서울-전주-순천-여수-부산-강릉</span>
											<span class="plan-user">글쓴이</span>
										</div>
									</li>
									<li class="plan-item">
										<img class="plan-img" src="../img/default.png">
										<div class="plan-detail">
											<span class="plan-hashtag">#해시태그  #해시태그</span>
											<span class="plan-course">서울-전주-순천-여수-부산-강릉</span>
											<span class="plan-user">글쓴이</span>
										</div>
									</li>
									<li class="plan-item">
										<img class="plan-img" src="../img/default.png">
										<div class="plan-detail">
											<span class="plan-hashtag">#해시태그  #해시태그</span>
											<span class="plan-course">서울-전주-순천-여수-부산-강릉</span>
											<span class="plan-user">글쓴이</span>
										</div>
									</li>
								</ul>
							</div>
							<!-- ./article-plan -->
							
							<!-- 지역의 숙박 추천 (article-bed) -->
							<div class="article-item article-bed">
								<div class="article-title"><h2>서울, 이 숙박은 어때요?</h2></div>
								<ul class="bed-wrap">
									<li class="bed-item">
										<div class="bed-img" style="background:#d9d9d9  url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="bed-detail">
											<div class="bname">숙박이름</div>
											<div class="bcategory">카테고리</div>
										</div>
									</li>
									<li class="bed-item">
										<div class="bed-img" style="background:#d9d9d9  url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="bed-detail">
											<div class="bname">숙박이름</div>
											<div class="bcategory">카테고리</div>
										</div>
									</li>						
									<li class="bed-item">
										<div class="bed-img" style="background:#d9d9d9  url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
										<div class="bed-detail">
											<div class="bname">숙박이름</div>
											<div class="bcategory">카테고리</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
						
						
						<div class="article-wrapper article-25">
							<!-- article-sns-user -->
							<div class="article-item article-sns-user">
								<div class="article-title"><h2>SNS 이용자 추천</h2></div> 
								<div class="sns-user-list">
									<!-- 추천 이용자 1(sns-user-item) -->
									<div class="sns-user-item">
										<div class="sns-user-profile">
											<img class="user-img" src="../img/header/default_profile_m.png">
											<div class="user-nameid">
												<span class="user-name">사용자 닉네임</span><br> 
												<span class="user-id font-13">@UserId</span>
											</div>
										</div>
										<div class="user-loc font-15"><img src="../img/member/info_location.png">서울, 대한민국</div>
										<span class="user-posting font-15">123 포스팅</span> &nbsp;&nbsp;&nbsp; <span class="user-follower font-15">456 팔로워</span>
										<div class="user-detail font-15">사용자 소개 글</div>
										<div class="follow-btn"><img src="../img/sns/add_follow.png">팔로우</div>
									</div>
									<!-- ./sns-user-item -->
									<!-- 추천 이용자 2(sns-user-item) -->
									<div class="sns-user-item">
										<div class="sns-user-profile">
											<img class="user-img" src="../img/header/default_profile_m.png">
											<div class="user-nameid">
												<span class="user-name">사용자 닉네임</span><br> 
												<span class="user-id font-13">@UserId</span>
											</div>
										</div>
										<div class="user-loc font-15"><img src="../img/member/info_location.png">서울, 대한민국</div>
										<span class="user-posting font-15">123 포스팅</span> &nbsp;&nbsp;&nbsp; <span class="user-follower font-15">456 팔로워</span>
										<div class="user-detail font-15">사용자 소개 글</div>
										<div class="follow-btn"><img src="../img/sns/add_follow.png">팔로우</div>
									</div>
									<!-- ./sns-user-item -->
									<!-- 추천 이용자 3(sns-user-item) -->
									<div class="sns-user-item">
										<div class="sns-user-profile">
											<img class="user-img" src="../img/header/default_profile_m.png">
											<div class="user-nameid">
												<span class="user-name">사용자 닉네임</span><br> 
												<span class="user-id font-13">@UserId</span>
											</div>
										</div>
										<div class="user-loc font-15"><img src="../img/member/info_location.png">서울, 대한민국</div>
										<span class="user-posting font-15">123 포스팅</span> &nbsp;&nbsp;&nbsp; <span class="user-follower font-15">456 팔로워</span>
										<div class="user-detail font-15">사용자 소개 글</div>
										<div class="follow-btn"><img src="../img/sns/add_follow.png">팔로우</div>
									</div>
									<!-- ./sns-user-item -->
								</div>
							</div>
							<!-- ./article-sns-user -->
							
							<!-- article-adsense -->
							<div class="article-item article-adsense">
								<div class="article-title"><h2>에드센스</h2></div><br>
								에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>
							</div>
							<!-- ./article-adsense -->
							
						</div>
					</div> <!-- ./section-main -->
					
				</div>
			</div>
			
			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
			
		</div>
	</body>
</html>
