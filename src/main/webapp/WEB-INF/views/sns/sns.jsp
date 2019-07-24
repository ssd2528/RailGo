<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] SNS Page</title>
		<!-- CSS -->
		<link href="../css/common.css" rel="stylesheet">
		<link href="../css/sns.css" rel="stylesheet">
		<link href="../css/article_sns_user.css" rel="stylesheet">
		<link href="../css/login_modal.css" rel="stylesheet">
		<link href="../css/sns_modal.css" rel="stylesheet">
		
		<!-- JavaScript -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="../js/sns.js" type="text/javascript"></script>
		<script src="../js/login_modal.js" type="text/javascript"></script>
		<script src="../js/sns_modal.js" type="text/javascript"></script>
		<script src="../js/jquery.validate.min.js" type="text/javascript"></script>
	</head>
	<body>
		<c:if test="${not empty authMsg}">
			<script type="text/javascript">alert('${authMsg}');</script>
		</c:if>
		<!-- login_modal -->
		<%@include file="../includes/login_modal.jsp"%>
		
		<div class="wrap">
			<!-- header -->
			<%@include file="../includes/header.jsp"%>
			
			<!-- content -->
			<div class="content">
				<!-- content-wrapper -->
				<div class="content-wrapper">
					<!-- section-main -->
					<div class="section-main clearfix">
						<div class="article-wrapper article-75">
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								<div class="sns-content-user">
									<img class="user-img" src="../img/header/default_profile_m.png">
									<div class="user-name">사용자 닉네임</div>
								</div>
								<img class="sns-img" src="../img/default.png" alt="SNS이미지">
								<div class="sns-content-body">
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
								<div class="sns-content-reply"><a href="#">3개의 댓글 모두보기</a></div>
								<ul class="icon-list">
									<li> <img class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
									<li> <img class="sns-icon sns-chat" src="../img/sns/chat.png" alt="댓글달기"> </li>
									<li> <img class="sns-icon sns-share" src="../img/sns/share.png" alt="공유하기"> </li>
								</ul>
								<div class="sns-heart-count">좋아요 5개</div>
								<div class="sns-content-regDate">작성일자</div>
							</div>
							<!-- ./article-sns-content -->
							
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								<div class="sns-content-user">
									<img class="user-img" src="../img/header/default_profile_m.png">
									<div class="user-name">사용자 닉네임</div>
								</div>
								<img class="sns-img" src="../img/default.png" alt="SNS이미지">
								<div class="sns-content-body">
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
								<div class="sns-content-reply"><a href="#">3개의 댓글 모두보기</a></div>
								<ul class="icon-list">
									<li> <img class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
									<li> <img class="sns-icon sns-chat" src="../img/sns/chat.png" alt="댓글달기"> </li>
									<li> <img class="sns-icon sns-share" src="../img/sns/share.png" alt="공유하기"> </li>
								</ul>
								<div class="sns-heart-count">좋아요 5개</div>
								<div class="sns-content-regDate">작성일자</div>
							</div>
							<!-- ./article-sns-content -->
							<!-- article-sns-content -->
							<div class="article-item article-sns-content">
								<div class="sns-content-user">
									<img class="user-img" src="../img/header/default_profile_m.png">
									<div class="user-name">사용자 닉네임</div>
								</div>
								<img class="sns-img" src="../img/default.png" alt="SNS이미지">
								<div class="sns-content-body">
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
								<div class="sns-content-reply"><a href="#">3개의 댓글 모두보기</a></div>
								<ul class="icon-list">
									<li> <img class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
									<li> <img class="sns-icon sns-chat" src="../img/sns/chat.png" alt="댓글달기"> </li>
									<li> <img class="sns-icon sns-share" src="../img/sns/share.png" alt="공유하기"> </li>
								</ul>
								<div class="sns-heart-count">좋아요 5개</div>
								<div class="sns-content-regDate">작성일자</div>
							</div>
							<!-- ./article-sns-content -->
						</div>
						<div class="article-wrapper article-25">
							<!-- article-sns-user -->
							<%@include file="../includes/article_sns_user.jsp"%>
							<!-- ./article-sns-user -->
							
							<!-- article-adsense -->
							<div class="article-item article-adsense">
								<div class="article-title"><h2>에드센스</h2></div><br>
								에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>
							</div>
							<!-- ./article-adsense -->
						</div>
					<!-- 글 작성 (+버튼) -->
					<img class="sns-write-img" src="../img/sns/plus.png" alt="작성">
					<!-- ./글 작성 (+버튼) -->
					</div>
					<!-- ./section-main -->
				</div>
				<!-- ./content-wrapper -->
			</div>
			<!-- ./content -->
			
			<!-- sns write modal -->
			<%@include file="sns_modal.jsp" %>	
			<!-- footer -->
			<%@include file="../includes/footer.jsp"%>
		</div>
	</body>
</html>