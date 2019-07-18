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
							<div class="search-city">어디로 가시나요? &nbsp; ▼</div>
							<ul class="search-list">
								<li class="search-item search-accom"> 
									<a href="info/category">
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
							<!-- ./article-course -->
							
							<!-- article-concept -->
							<div class="article-item article-concept">
								<div class="article-title"><h2>컨셉 추천</h2></div>
								<!-- 컨셉1 (concept-wrap) -->
								<div class="concept-wrap">
									<div class="concept-title">"나홀로 떠나는 여행"에 대한 추천 코스</div>
									<ul class="concept-content">
										<li class="concept-item">
											<div class="concept-img" style="background:#d9d9d9  url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="concept-detail">#나홀로_내일로</div>
										</li>
										<li class="concept-item">
											<div class="concept-img" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="concept-detail">#나홀로_내일로</div>
										</li>
										<li class="concept-item">
											<div class="concept-img" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="concept-detail">#나홀로_내일로</div>
										</li>
									</ul>
								</div>
								<!-- 컨셉2 (concept-wrap) -->
								<div class="concept-wrap" style="margin-top:20px;">
									<div class="concept-title">"커플과 함께 떠나는 여행"에 대한 추천 코스</div>
									<ul class="concept-content">
										<li class="concept-item">
											<div class="concept-img" style="background:#d9d9d9  url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="concept-detail">#나홀로_내일로</div>
										</li>
										<li class="concept-item">
											<div class="concept-img" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="concept-detail">#나홀로_내일로</div>
										</li>
										<li class="concept-item">
											<div class="concept-img" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; width:100%; height:150px;"></div>
											<div class="concept-detail">#나홀로_내일로</div>
										</li>
									</ul>
								</div>
							</div>
							<!-- ./article-concept -->
							
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								<div class="article-title"><h2>핫한 SNS 게시물</h2></div>
								
								<!-- SNS 게시물 목록 (sns-content-list)  -->
								<div class="sns-content-list">
								
									<!-- 각 SNS 게시글 (sns-content-item) -->
									<div class="sns-content-item">
										<div class="sns-content-img col-50" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; height:300px;">게시글 이미지</div>
										<div class="col-45" style="padding:5px 0px;">
											<div class="sns-content-user">
												<img class="user-img" src="../img/header/default_profile_m.png">
												<div class="user-name">사용자 닉네임</div>
											</div>
											<div class="sns-content-body font-13">
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글
											</div>
											<div class="sns-content-reply font-13"><a href="#">3개의 댓글 모두보기</a></div>
											<ul class="icon-list">
												<li> <img class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
												<li> <img class="sns-icon sns-chat" src="../img/sns/chat.png" alt="댓글달기"> </li>
												<li> <img class="sns-icon sns-share" src="../img/sns/share.png" alt="공유하기"> </li>
											</ul>
											<div class="sns-heart-count font-13">좋아요 5개</div>
											<div class="sns-content-regDate font-13">작성일자</div>
										</div>
									</div> 
									
									<!-- 각 SNS 게시글 (sns-content-item) -->
									<div class="sns-content-item">
										<div class="sns-content-img col-50" style="background:#d9d9d9 url('../img/default.png') no-repeat center center/cover; height:300px;">게시글 이미지</div>
										<div class="col-45" style="padding:5px 0px;">
											<div class="sns-content-user">
												<img class="user-img" src="../img/header/default_profile_m.png">
												<div class="user-name">사용자 닉네임</div>
											</div>
											<div class="sns-content-body font-13">
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 
												게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글 게시물에 대한 글
											</div>
											<div class="sns-content-reply font-13"><a href="#">3개의 댓글 모두보기</a></div>
											<ul class="icon-list">
												<li> <img class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
												<li> <img class="sns-icon sns-chat" src="../img/sns/chat.png" alt="댓글달기"> </li>
												<li> <img class="sns-icon sns-share" src="../img/sns/share.png" alt="공유하기"> </li>
											</ul>
											<div class="sns-heart-count font-13">좋아요 5개</div>
											<div class="sns-content-regDate font-13">작성일자</div>
										</div>
									</div> 
									
								</div> 
							</div>
							<!-- article-sns-content -->
						</div>
						
						
						<div class="article-wrapper article-25">
							<!-- article-sns-user -->
							<div class="article-item article-sns-user">
								<div class="article-title"><h2>SNS 이용자 추천</h2></div> 
								<div class="sns-user-list">
									<!-- 추천 이용자1 -->
									<div class="sns-user-item">
										<div class="sns-user-profile">
											<img class="user-img" src="../img/header/default_profile_m.png">
											<div class="user-nameid">
												<span class="user-name">사용자 닉네임</span><br> 
												<span class="user-id">@UserId</span>
											</div>
										</div>
									</div>
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
			<%@include file="includes/footer.jsp"%>
			
		</div>
	</body>
</html>
