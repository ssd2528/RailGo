<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<input type="hidden" id="category" value="${category}">

<!-- 선택된 카테고리 결과 목록(selected-category-result-inner-box) -->
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
							<c:if test="${item.firstimage eq null}"><img src="/img/default.png"></c:if>
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
							<input type="hidden" value="${fn:substringBefore(item.addr1, ' ')}" name="areaName">
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
<!-- ./선택된 카테고리 결과 목록(selected-category-result-inner-box) -->