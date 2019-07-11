<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Member Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		<link href="../css/common.css" rel="stylesheet">
		<link href="../css/index.css" rel="stylesheet">
		<link href="../css/member.css" rel="stylesheet">
	</head>
	<body>
		<div class="wrap">
			<!-- header -->
			<%@include file="../includes/header.jsp"%>
			
			<!-- content -->
			<div class="content">
				<div class="content-wrapper">
				
				<div class="section-profilebg">
				</div>
					<!-- profile-info -->
					<div class="profile-info">
					
						<!-- profile-img -->
					<div class="profile-img clearfix">
						<img src="../img/header/default_profile_f.png" alt="프로필">
					</div>
						<!-- profile-detail -->
					
					</div>			
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-25">
							<!-- article-introduce -->
							<div class="article-item article-sns-user">
								소개 <br>
								소개 <br>소개 <br>소개 <br>소개 <br>소개 <br>소개 <br>소개 <br>
							</div>
							<!-- article-course -->
							<div class="article-item article-sns-user">
								SNS 이용자 추천 <br>
								SNS 이용자 추천 <br>SNS 이용자 추천 <br>SNS 이용자 추천 <br>SNS 이용자 추천 <br>SNS 이용자 추천 <br>
							</div>
							<!-- article-adsense -->
							<div class="article-item article-adsense">
								또드또스<br>
								또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>또드또스<br>
							</div>
						</div>
						
						<div class="article-wrapper article-75">
					
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								내 게시물
								CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>
								CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>CONTENT<br/>
							</div>
						</div>
						
					
					</div>
					
				</div>
			</div>
			
			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
			
		</div>
	</body>
</html>
