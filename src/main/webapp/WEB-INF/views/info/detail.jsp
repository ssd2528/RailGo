<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] Info Detail Page</title>
	<link rel="icon" href="/img/favicon.ico">
	
	<!-- CSS -->
	<!-- <link href="/css/font-awesome.min.css" rel="stylesheet"> -->
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/section_search.css" rel="stylesheet">
	<link href="/css/info/detail.css" rel="stylesheet">
	<link href="/css/login_modal.css" rel="stylesheet">
	
	<!-- JS -->
	<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
	<script src="/js/fontawesome.js" type="text/javascript"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.js"></script>
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="/js/login_modal.js" type="text/javascript"></script>
	<script src="/js/section_search.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/info/detail.rating.min.js"></script>
	<script src="/js/info/detail.js"></script>
	
</head>
<body>
	<c:if test="${not empty msg}">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</c:if>
	<!-- login_modal -->
	<%@include file="../includes/login_modal.jsp"%>
	
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>

		<!-- menu-bar -->
		<div class="menu-bar">
			<!-- navi -->
			<div class="menu-navi">
				<ul class="menu-bar-ul">
					<li><a><span class="search-city">${areaName} &nbsp;▼</span></a></li>
					<a href="http://localhost:8080/info/accom/${areaName}"><li class="${category eq '숙박' ? 'menu-clicked' : ''}">숙 &nbsp;&nbsp;&nbsp; 박</li></a>
					<a href="http://localhost:8080/info/hotplace/${areaName}"><li class="${category eq '관광명소' ? 'menu-clicked' : ''}">관광명소</li></a>
					<a href="http://localhost:8080/info/food/${areaName}"><li class="${category eq '음식점' ? 'menu-clicked' : ''}">맛 &nbsp;&nbsp;&nbsp;집</li></a>
				</ul>
			</div>
		</div>
		
		<c:choose>
			<c:when test="${category eq '숙박'}"> <c:set var="categoryEng" value="accom"/> </c:when>
			<c:when test="${category eq '관광명소'}"> <c:set var="categoryEng" value="hotplace"/> </c:when>
			<c:when test="${category eq '음식점'}"> <c:set var="categoryEng" value="food"/> </c:when>
		</c:choose>
				
		<!-- place-info -->
		<div class="place-info-top">
			<div class="place-info-box">
				<br>
				<div class="place-category">
					<a href="http://localhost:8080/info/${areaName}"><span class="link">${areaName}</span></a> <i class="fas fa-angle-right"></i>
					<a href="http://localhost:8080/info/${categoryEng}/${areaName}"><span class="link">${category}</span></a> <i class="fas fa-angle-right"></i>
					<c:if test="${category eq '관광명소'}"><span class="link">${detail.cat1}</span> <i class="fas fa-angle-right"></i></c:if>
					<span>${detail.cat3}</span> <i class="fas fa-angle-right"></i>
					<span>${detail.title}</span>
				</div>
				<br> <br> <br>
				<div class="place-name">
					<h1>${detail.title}</h1>
				</div>
				<div class="place-info">
					<h4 class="overview">${detail.overview}</h4>
				</div>
				<br> <br>
				<ul class="info-text-ul">
					<li><i class="fas fa-map-marker-alt"></i> &nbsp;${detail.addr1}</li>
					<li><i class="fas fa-list"></i> &nbsp; ${detail.cat3}</li>
					<c:if test="${category ne '관광명소'}"> <li><i class="fas fa-phone"></i> &nbsp; ${detail.tel}</li> </c:if> <!-- 관광명소는 전화번호를 제공X -->
					<span class="info-text-media"></span>
					<c:if test="${opentime ne null}"> <li class="time"><i class="fas fa-clock"></i> &nbsp; <span>${opentime}</span></li></c:if> <!-- 맛집 -->
					<c:if test="${chkintime ne null}"> <li class="time"><i class="fas fa-clock"></i> &nbsp; <span>체크인: ${chkintime}, 체크아웃: ${chkouttime}</span></li> </c:if> <!-- 숙박 -->
					
					<li><i class="fas fa-star"></i> &nbsp; ${ratingAvg eq 'NaN' ? '평점없음' : ratingAvg} </li>
				</ul>
			</div>
		</div>
	</div>

	<!-- image -->
	<div class="info-img-container">
	<c:if test="${detail.firstimage ne null}">
		<div class="info-imgs">
			<img class="info-img1" src="${detail.firstimage}">
			<c:if test="${img1 ne null}"><img class="info-img2" src="${img1}"></c:if>
			<c:if test="${img1 eq null}"><img class="info-img2" src="/img/default.png"></c:if>
			<c:if test="${img2 ne null}"><img class="info-img3" src="${img2}"></c:if>
			<c:if test="${img2 eq null}"><img class="info-img3" src="/img/default.png"></c:if>
		</div>
	</c:if>
	<c:if test="${detail.firstimage eq null}">
		<div class="info-img-null">
			<div class="null-alert">
				<i class="fas fa-exclamation-circle"></i>
				<div class="null-text">제공된 이미지가 없습니다.</div>
			</div>
		</div>
	</c:if>
	</div>
	<!-- content -->
	<div class="content">
		<div class="content-wrapper">

			<!-- section-main -->
			<div class="section-main clearfix">
				<div class="article-wrapper article-75">

					<!-- article-review-content -->
					<div class="article-item">
						<div class="review-title"><h2>리뷰</h2></div><br>

						<!-- review-board -->
						<c:if test="${empty reList}">
							<div class="review-null">
								<div class="null-alert">
									<i class="fas fa-exclamation-circle"></i>
									<div class="null-text">아직 리뷰가 없습니다.</div>
								</div>
							</div>
						</c:if>
						<c:if test="${!empty reList}">
							<c:forEach items="${reList}" var="reList">
								<div class="review-table">
									<c:if test="${null ne reList.profile}">
										<img class="reviewer-img" src="${reList.profile}">
									</c:if>
									<c:if test="${null eq reList.profile}">
										<c:if test="${'M' eq reList.gender}"><img class="reviewer-img" src="/img/info/default_profile_m.png"></c:if>
										<c:if test="${'F' eq reList.gender}"><img class="reviewer-img" src="/img/info/default_profile_f.png"></c:if>
									</c:if>
									<table>
										<tr>
											<th>${reList.name}</th>
											<td>
												${reList.review}
												<c:if test="${member.mem_code == reList.mem_code}">
													<input type="hidden" name="review-rCode" value="${reList.r_code}">
													<div class="review-del-btn">삭제</div>
												</c:if>
											</td>
										</tr>
										<tr>
											<th class="review-rev">
												<c:forEach begin="1" end="${reList.rating}">
													<i class="fas fa-star"></i>
												</c:forEach>
											</th>
											<td class="review-rdate">
												<fmt:formatDate pattern="yyyy년 MM월 dd일 HH:mm" value="${reList.regDate}"/>
											</td>
										</tr>
									</table>
								</div>
								<c:if test="${!empty reList.imgList}">
									<div class="review-imgs">
										<c:forEach items="${reList.imgList}" var="imgList">
											<img class='review-img' src='/info/display?fileName=${imgList.imagePath}/s_${imgList.uuid}_${imgList.fileName}'>
										</c:forEach>
									</div>
								</c:if>
								<c:if test="${empty reList.imgList}">
									<div class="review-img-empty">
									</div>
								</c:if>
							</c:forEach>
						</c:if>

						<!-- review-write -->	
						<div class="review-register">
							<h2>리뷰 달기</h2>
						</div>
						<c:if test="${member ne null}">
							<form class="review-form" role="role" action="../info/insertReview" method="post">
								<input type="hidden" class="r_code" value="${detail.contentid}" name="contentid" />
								<input type="hidden" class="mem_code" value="${member.mem_code}" name="mem_code" />
								<div class="review-table">
									
									<c:choose>
										<c:when test="${memadd.profile != null}">
											<img class="reviewer-img" src='/member/display?fileName=${memadd.profile}' alt="프로필">
										</c:when>
										<c:when test="${memadd.profile == null}">
											<c:choose>
												<c:when test="${member.gender eq 'M'}">
													<img class="reviewer-img" src="/img/member/default_profile_m.png" alt="프로필 남" >
												</c:when>
												<c:when test="${member.gender eq 'F'}">
													<img class="reviewer-img" src="/img/member/default_profile_f.png" alt="프로필 여" >
												</c:when>
											</c:choose>	
										</c:when>
									</c:choose>
									
									<div class="review-rating">
										<select id="example" name="rating">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div>
								</div>
								<div class="review-register-box">
									<textarea class="review-textarea" rows="3" name="review" placeholder="&nbsp;&nbsp; 리뷰를 남겨주세요."></textarea>
									<div class="review-grid-right">
										<button type="button" class="review-register-btn" id="uploadBtn">등록</button>
										<label for="review_file">사진 업로드</label>
										<input type='file' id="review_file" name='uploadFile' multiple/>
									</div>
								</div>
							</form>
							<form class="page-reload" method="post" action="../detail/${detail.title}">
								<input type="hidden" value="${detail.contentid}" name="contentid">
								<input type="hidden" value="${detail.contenttypeid}" name="contenttypeid">
								<input type="hidden" value="${detail.mapx}" name="mapx">
								<input type="hidden" value="${detail.mapy}" name="mapy">
								<input type="hidden" value="${areaName}" name="areaName">
								<input type="hidden" value="${category}" name="category">
							</form>
							<!-- 썸네일 처리 부분 -->
							<div class="review-uploadResult">
								<ul>
								</ul>
							</div>
						</c:if>
						
						<!-- 비 로그인시 리뷰 테이블 처리 -->
						<c:if test="${member eq null}">
							<div class="non-login-alert">로그인 하시고 리뷰를 남겨보세요!</div>
						</c:if>
					</div>
				</div>
				
				<div class="article-wrapper article-25">
					<!-- article-search -->
					<!-- <div class="search-container">
						<input type="text" class="search-keyword" name="keyword" placeholder="검색"> <a href="#"><i class="fa fa-search icon"></i></a>
					</div> -->
					<!-- recommend-area -->
					<div class="article-item">
						<h3>근처 다른 ${detail.cat2}</h3>
						<ul class="recommend-lists">
							<c:forEach items="${locList}" var="locList">
								<li class="recommend-list">
									<form class="detailForm" method="post" action="../detail/${locList.title}">
									<input type="hidden" value="${locList.contentid}" name="contentid">
									<input type="hidden" value="${locList.contenttypeid}" name="contenttypeid">
									<input type="hidden" value="${locList.mapx}" name="mapx">
									<input type="hidden" value="${locList.mapy}" name="mapy">
									<input type="hidden" value="${areaName}" name="areaName">
									<input type="hidden" value="${category}" name="category">
									<div class="recommend-area">
									<c:if test="${locList.firstimage eq null}">
										<a href="#"><img class="recommend-img" src="/img/default.png"></a>
									</c:if>
									<c:if test="${locList.firstimage ne null}">
										<a href="#"><img class="recommend-img" src="${locList.firstimage}"></a>
									</c:if>
										<div class="recommend-info">
											<span class="recommend-title">${locList.title}</span> 
											<span class="recommend-category">${locList.cat3}</span> 
											<span class="recommend-readcount">[조회수: ${locList.readcount}]</span>
											<!-- <span class="recommend-rating">★★★★</span> -->
										</div>
									</div>
									</form>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!-- article-adsense -->
					<div class="article-item article-adsense">
						에드센스<br> 에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>에드센스<br>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- search-modal -->
	<%@include file="../includes/search_modal.jsp"%>
	<!-- footer -->
	<%@include file="../includes/footer.jsp"%>

</body>
</html>
