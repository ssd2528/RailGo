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
					<div class="sectoin-search clearfix">
						<img class="main-img" src="../img/default_summer.png" alt="여름">
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
