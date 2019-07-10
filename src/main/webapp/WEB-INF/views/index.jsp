<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Main Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		<link href="../css/common.css" rel="stylesheet">
		<link href="../css/index.css" rel="stylesheet">
	</head>
	<body>
		<div class="wrap">
			<!-- header -->
			<%@include file="includes/header.jsp"%>
			
			<!-- content -->
			<div class="content">
				<div class="content-wrapper">
				
					<!-- section-search -->
					<div class="section-search">
						<div class="search-wrap clearfix">
							<div class="search-city">어디로 가시나요?? &nbsp; ▼</div>
							<ul class="search-list">
								<li class="search-item search-accom"> 
									<a href="#">
										<img src="../img/main/bed_dgray.png" class="search-icons" alt="bed" /><br>숙 박 
									</a> 
								</li>
								<hr color="#595959" noshade>
								<li class="search-item search-place"> 
									<a href="#">
										<img src="../img/main/hotplace_dgray.png" class="search-icons" alt="hotplace" /><br>관광 명소 
									</a> 
								</li>
								<hr color="#595959" noshade>
								<li class="search-item search-food"> 
									<a href="#">
										<img src="../img/main/food_dgray.png" class="search-icons" alt="food" /><br>맛 집 
									</a> 
								</li>
							</ul>
						</div>
					</div>
					
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-75">
							<!-- article-course -->
							<div class="article-item article-course">
								<a href="#">혹시 내일로 여행이 처음이신가요? 이런 코스는 어떠세요?</a> <span class="close-btn">x</span>
							</div>
							<!-- article-concept -->
							<div class="article-item article-concept">
								컨셉 추천
								CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>
								CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>
							</div>
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								핫한 SNS 게시물
								CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>
								CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>
							</div>
						</div>
						
						
						<div class="article-wrapper article-25">
							<!-- article-course -->
							<div class="article-item article-sns-user">
								SNS 이용자 추천 <br>
								SNS 이용자 추천 <br>SNS 이용자 추천 <br>SNS 이용자 추천 <br>SNS 이용자 추천 <br>SNS 이용자 추천 <br>
							</div>
							<!-- article-adsense -->
							<div class="article-item article-adsense">
								에드센스<br>
								에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			
			<!-- footer -->
			<%@include file="includes/footer.jsp"%>
			
		</div>
	</body>
</html>
