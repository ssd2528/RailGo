<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] Info Category Page</title>
	<link rel="icon" href="/img/favicon.ico">
	
	<!-- CSS -->
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/section_search.css" rel="stylesheet">
	<link href="/css/info/category.css" rel="stylesheet">
	<link href="/css/login_modal.css" rel="stylesheet">
	
	<!-- JS -->
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
	<script src="https://use.fontawesome.com/releases/v5.9.0/js/all.js"></script>
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="/js/login_modal.js" type="text/javascript"></script>
	<script src="/js/section_search.js" type="text/javascript"></script>
	<script src="/js/header.js" type="text/javascript"></script>
	<script src="/js/info/category.js"></script>
	
</head>
<body>
	<c:if test="${not empty msg}">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</c:if>
	<!-- login_modal -->
	<%@include file="../includes/login_modal.jsp"%>
	
	
	<input type="hidden" name="category" id="category" class="category" value="${category}" />
	<input type="hidden" name="areaName" id="areaName" class="areaName" value="${areaName}" />
	<input type="hidden" name="currentPage" id="currentPage" class="currentPage" value="${currentPage}" />
	
	<c:choose>
		<c:when test="${category eq 'accom'}"> <c:set var="category" value="숙박"/> </c:when>
		<c:when test="${category eq 'hotplace'}"> <c:set var="category" value="관광명소"/> <c:set var="totalCount" value="${totalCount}"/></c:when>
		<c:when test="${category eq 'food'}"> <c:set var="category" value="맛집"/> </c:when>
	</c:choose>


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
					<a href="http://localhost:8080/info/food/${areaName}"><li class="${category eq '맛집' ? 'menu-clicked' : ''}">맛 &nbsp;&nbsp;&nbsp;집</li></a>
				</ul>
			</div>
		</div>

		<!-- category-info -->
		<div class="category-info-top">
			<div class="category-info-box">
				<br>
				<div class="category-category">
					<a href="http://localhost:8080/info/${areaName}"><span class="link-areaName">${areaName}</span></a> 
					<i class="fas fa-angle-right"></i>
					<span>${category}</span>
				</div>
				<br> <br> <br>
				<div class="category-name">
					<h1>${areaName} ${category}</h1>
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
					<!-- <div class="search-container">
						<input type="text" class="search-keyword" name="keyword" placeholder="검색"> <a href="#"><i class="fa fa-search icon"></i></a>
					</div> -->
					
					<!-- checkbox -->
					<%@include file="checkbox.jsp"%>
				</div>


				<div class="article-wrapper article-75" id="article-info-wrapper">

					<!-- article-info -->
					<div class="article-item article-info" id="artice-info">
						<div class="info-top">
							검색결과 총 <span class="result-amount">${totalCount}</span>개
							<span class="info-group">
								<span class="arrange ${arrange=='C'? 'arrange-chk' : ''}" id="C">최신순</span> / <span class="arrange ${arrange=='B'? 'arrange-chk' : ''}" id="B">조회순</span>
							</span>							
						</div>

						<!-- info-list -->
						<c:forEach items="${list}" var="item" varStatus="status">
							<c:if test="${(status.count >= ((currentPage-1)*10 + 1) && status.count <= currentPage*10) || category=='숙박' || category=='맛집'}">
								<div class="info-list">
									<form class="detailForm" method="post" action="../detail/${item.title}" style="display:none;">
										<input type="hidden" value="${item.contentid}" name="contentid"> 
										<input type="hidden" value="${item.contenttypeid}" name="contenttypeid">
										<input type="hidden" value="${item.mapx}" name="mapx">
										<input type="hidden" value="${item.mapy}" name="mapy">
										<input type="hidden" value="${areaName}" name="areaName">
										<input type="hidden" value="${category}" name="category">
									</form>
									<c:choose>
										<c:when test="${item.firstimage eq null}"> 
											<img class="info-list-img" src="/img/default.png">
										</c:when>
										<c:when test="${item.firstimage ne null}"> 
											<img class="info-list-img" src="${item.firstimage}">
										</c:when>
									</c:choose>
										
									<div class="info-list-data">
										<h2 class="info-list-title">${item.title}</h2>
										<div class="info-explain">${item.overview}</div>
										<div class="info-category">
											<i class="fas fa-map-marker-alt"></i> ${item.addr1} &nbsp; ${item.addr2}
											&nbsp;&nbsp;
											<i class="fas fa-list"></i> ${item.cat3}
										</div>
										<div class="info-readcount">[조회수 : ${item.readcount}]</div>
										<!-- <div class="info-rating">★★★★</div> -->
									</div>
								</div>
								<hr class="checkbox-hr">
							</c:if>
						</c:forEach>
						
						<!-- pagination -->
						<div class="paging"><%@include file="pagination.jsp"%></div>
					</div>
					<!-- ./article-info -->
					
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
