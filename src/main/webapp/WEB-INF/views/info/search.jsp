<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] Info Search Page</title>
	<link rel="icon" href="/img/favicon.ico">
	
	<!-- CSS -->
	<link href="/css/font-awesome.min.css" rel="stylesheet">
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/info/search.css" rel="stylesheet">
	<link href="/css/login_modal.css" rel="stylesheet">
	
	
	<!-- JavaScript -->
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
	<script src="/js/fontawesome.js" type="text/javascript"></script>
	<script src="/js/header.js" type="text/javascript"></script>
	<script src="/js/info/search.js" type="text/javascript"></script>
	<script src="/js/login_modal.js" type="text/javascript"></script>
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
</head>
<body>
	<!-- login_modal -->
	<%@include file="../includes/login_modal.jsp"%>

	<c:set var="areaName" value="${areaName}"></c:set> <input type="hidden" id="areaName" value="${areaName}">
	
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>

		<!-- content -->
		<div class="content">
			<div class="content-wrapper">

				<!-- section-main -->
				<div class="section-main clearfix">
					<!-- article-wrapper -->
					<div class="article-wrapper">
						
						<div class="search-keyword">
							'${keyword}'에 대한 검색결과 
							<!-- article-search -->
						<form id="searchForm" action="search" method="post">
							<div class="search-container">
								<input type="text" name="keyword" class="search-keyword" id="search-keyword" placeholder="검색"> <i class="fa fa-search icon"></i>
							</div>
						</form>
						</div>
						<input type="hidden" id="keyword" value="${keyword}">
						
						<!-- keword-result-wrapper -->
						<div class="keword-result-wrapper">
							<!-- 카테고리 탭(category-tabs) -->
							<div class="category-tabs">
								<div class="category-tab hotplace-tab selected-tab">관광명소</div>
								<div class="category-tab accom-tab">숙&nbsp;&nbsp;박</div>
								<div class="category-tab food-tab">맛&nbsp;&nbsp;집</div>
							</div>
							<!-- ./category-tabs -->
							
							<!-- 선택된 카테고리 결과 목록(selected-category-result-box) -->
							<div id="selected-category-result-box">
								<input type="hidden" id="category" value="관광명소">
								<div id="selected-category-result-inner-box">
									<div class="total-count">검색결과&nbsp;&nbsp; 총 <span style="color:#eb7d31; font-size:17px; font-weight:bold;"> ${totalCount} </span> 개</div>
									<c:if test="${list eq null}">
										<div class="do-not-exist" style="height:510px; text-align:center;">
											<i class="fas fa-exclamation-circle"></i><br>
											<div style="font-size:30px; margin-top:10px; color:#7f7f7f;">검색결과가 없습니다.</div>
										</div>
									</c:if>
									<c:if test="${list ne null}">
										<ul class="result-list-box">
											<c:forEach items="${list}" var="item" varStatus="status">
												<c:if test="${status.count%4 == 1}">
													<div class="category-result-box-row">
												</c:if>
													<li class="result-item-box">
														<div class="result-item-img">
															<c:if test="${item.firstimage eq null}"><img class="default-img" src="/img/default.png"></c:if>
															<c:if test="${item.firstimage ne null}"><img src="${item.firstimage}"></c:if>
														</div>
														<div class="result-item-info-box">
															<div class="result-item-title">${item.title}</div>
															<div class="result-item-category">${item.cat1} &gt; ${item.cat2} &gt; ${item.cat3}</div>
															<div class="result-item-readcount">조회수 : ${item.readcount}</div>
														</div>
														<form class="detailLinkForm" action="../info/detail/${item.title}" method="post" style="display:none;">
															<input type="hidden" value="${item.contentid}" name="contentid"> 
															<input type="hidden" value="${item.contenttypeid}" name="contenttypeid">
															<input type="hidden" value="${item.mapx}" name="mapx">
															<input type="hidden" value="${item.mapy}" name="mapy">
															<c:if test="${item.areaName eq '정보없음'}">
																<input type="hidden" value="${fn:substringBefore(item.addr1, ' ')}" name="areaName">
															</c:if>
															<c:if test="${item.areaName ne '정보없음'}">
																<input type="hidden" value="${item.areaName}" name="areaName">
															</c:if>
															<input type="hidden" value="관광명소" name="category">
														</form>
													</li>
												<c:if test="${status.count%4 == 0}">	
													</div>
												</c:if>
											</c:forEach>
										</ul>
										<!-- pagination -->
										<div class="paging"><%@include file="pagination.jsp"%></div>
									</c:if>
									
									
								</div>
							</div>
							<!-- ./선택된 카테고리 결과 목록(selected-category-result-box) -->
							
						</div>
						<!-- ./keword-result-wrapper -->
					</div>
					<!-- ./article-wrapper -->
				</div>
				<!-- ./section-main -->
				
			</div>
		</div>

		<!-- footer -->
		<%@include file="../includes/footer.jsp"%>

	</div>
	
</body>
</html>
