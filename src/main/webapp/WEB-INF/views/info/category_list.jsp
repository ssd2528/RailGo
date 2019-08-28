<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="response-box">
	<input type="hidden" name="cat1" id="cat1" class="cat1" value="${cat1}" />
	<input type="hidden" name="cat2" id="cat2" class="cat2" value="${cat2}" />
	<input type="hidden" name="cat3" id="cat3" class="cat3" value="${cat3}" />
	<input type="hidden" name="contentTypeId" id="contentTypeId" class="contentTypeId" value="${contentTypeId}" />
	<%-- <c:set value="${contentTypeId}" var="contentTypeId" /> --%>
	<input type="hidden" name="arrange" id="arrange" class="arrange" value="${arrange}" />
</div>

<fmt:parseNumber var="maxpage" integerOnly="true" value="${totalCount%10==0? totalCount/10 : totalCount/10+1}" />
		
		<c:set var="page" value="${currentPage}" />
		<c:if test="${currentPage eq null}">
			<c:set var="page" value="1" />
		</c:if>
		
		<input type="hidden" value="${maxpage}"> <input type="hidden" value="${page}">
		
		<!-- page maxpage를 넘었을 경우 제한 -->
		<c:if test="${page > maxpage}">
			<c:set var="page" value="${maxpage}" />
		</c:if>

		<!-- 페이지를 10개씩 나누기 위해 현재 페이지에 보여줄 max값 설정 -->
		<fmt:formatNumber value="${page/10 - (page/10 % 1)}" type="pattern" pattern="0" var="full" />
		<c:set var="full" value="${full * 10}" />

		<!-- prev(전페이지), next(다음페이지) 개수 설정 -->
		<c:set var="scope" value="${page%10}" />
		<c:choose>
			<c:when test="${scope == 0}">
				<c:set var="prev" value="10" />
				<c:set var="next" value="1" />
			</c:when>
			<c:when test="${scope < 11}">
				<c:set var="prev" value="${scope}" />
				<c:set var="next" value="${11-scope}" />
			</c:when>
		</c:choose>

		<!-- prev 버튼 -->
		<c:if test="${page > 10}">
			<fmt:formatNumber value="${(page-1)/10 - (((page-1)/10) % 1)}" type="pattern" pattern="0" var="prevb" />
			<c:set value="${(prevb-1)*10 + 1}" var="prevb" />
		</c:if>

<c:if test="${totalCount==0}">
	<div class="article-item article-info" id="artice-info" style="height:510px; text-align:center;">
		<i class="fas fa-exclamation-circle"></i><br>
		<div style="font-size:30px; margin-top:10px; color:#7f7f7f;">검색결과가 없습니다.</div>
	</div>
</c:if>

<c:if test="${totalCount!=0}">
	<c:if test="${category=='hotplace' && contentTypeId=='12' && cat1==''}"><c:set var="totalCount" value="${totalCount-1}"/></c:if>

	<!-- article-info -->
	<div class="article-item article-info" id="artice-info">
		<div class="info-top">
			검색결과 총 <span class="result-amount">${totalCount}</span>개
			<span class="info-group">
				<span class="arrange arrange_after ${arrange=='C'? 'arrange-chk' : ''}" id="C">최신순</span> / <span class="arrange arrange_after ${arrange=='B'? 'arrange-chk' : ''}" id="B">조회순</span>
			</span>							
		</div>
	
		<!-- info-list -->
		<c:forEach items="${list}" var="item" varStatus="status">
			<input type="hidden" value="${status.count}">
			<c:if test="${(status.count>=((currentPage-1)*10+1) && status.count<=currentPage*10) || category=='accom' || category=='food'}">
				<div class="info-list">
					<c:choose>
						<c:when test="${item.firstimage eq null}"> 
							<img class="info-list-img" src="/img/default.png">
						</c:when>
						<c:when test="${item.firstimage ne null}"> 
							<img class="info-list-img" src="${item.firstimage}">
						</c:when>
					</c:choose>
						
					<div class="info-list-data">
						<a href="/info/detail"><h2 class="info-list-title">${item.title}</h2></a>
						<div class="info-explain">${item.overview}</div>
						<div class="info-category">
							<i class="fas fa-map-marker-alt"></i> ${item.addr1} &nbsp; ${item.addr2}
							&nbsp;&nbsp;
							<i class="fas fa-list"></i> ${item.cat3}
						</div>
						<!-- <div class="info-rating">★★★★</div> -->
						<div class="info-readcount">[조회수 : ${item.readcount}]</div>
					</div>
				</div>
				<hr class="checkbox-hr">
			</c:if>
		</c:forEach>
		
		
		
		<!-- pagination -->
		<div class="paging">
			<ul class="pagination">
				<fmt:parseNumber var="maxpage" integerOnly="true" value="${totalCount%10==0? totalCount/10 : totalCount/10+1}" />
				
				<c:set var="page" value="${currentPage}" />
				<c:if test="${currentPage eq null}">
					<c:set var="page" value="1" />
				</c:if>
				
				<input type="hidden" value="${maxpage}"> <input type="hidden" value="${page}">
				
				<!-- page maxpage를 넘었을 경우 제한 -->
				<c:if test="${page > maxpage}">
					<c:set var="page" value="${maxpage}" />
				</c:if>
		
				<!-- 페이지를 10개씩 나누기 위해 현재 페이지에 보여줄 max값 설정 -->
				<fmt:formatNumber value="${page/10 - (page/10 % 1)}" type="pattern" pattern="0" var="full" />
				<c:set var="full" value="${full * 10}" />
		
				<!-- prev(전페이지), next(다음페이지) 개수 설정 -->
				<c:set var="scope" value="${page%10}" />
				<c:choose>
					<c:when test="${scope == 0}">
						<c:set var="prev" value="10" />
						<c:set var="next" value="1" />
					</c:when>
					<c:when test="${scope < 11}">
						<c:set var="prev" value="${scope}" />
						<c:set var="next" value="${11-scope}" />
					</c:when>
				</c:choose>
		
				<!-- prev 버튼 -->
				<c:if test="${page > 10}">
					<fmt:formatNumber value="${(page-1)/10 - (((page-1)/10) % 1)}" type="pattern" pattern="0" var="prevb" />
					<c:set value="${(prevb-1)*10 + 1}" var="prevb" />
					<li id="prevBtn" class="prev button pagination-prev" value="${prevb}">이전</li>
				</c:if>
		
				<!-- 전 페이지 -->
				<c:if test="${page != 1}">
					<c:set var="j" value="${prev}" />
					<c:forEach var="i" begin="1" end="${prev-1}">
						<c:set var="j" value="${j - 1}" />
						<c:if test="${(page - j) > 0}">
							<li class="no no_after">${page - j}</li>
						</c:if>
					</c:forEach>
				</c:if>
		
				<!-- 현재 페이지 -->
				<li class="no no_after selected">${page}</li>
		
				<!-- 다음 페이지 -->
				<c:if test="${page != maxpage}">
					<c:forEach var="i" begin="1" end="${next-1}">
						<c:if test="${maxpage >= page+i}">
							<li class="no no_after">${page+i}</li>
						</c:if>
					</c:forEach>
				</c:if>
		
				<!-- next 버튼 -->
				<c:if test="${maxpage >= page + next}">
					<fmt:formatNumber value="${(page-1)/10 - (((page-1)/10) % 1)}" type="pattern" pattern="0" var="nextb" />
					<c:set value="${(nextb+1)*10 + 1}" var="nextb" />
					<li id="nextBtn" class="next button pagination-next" value="${nextb}">다음</li>
				</c:if>
		</ul>
		
		</div>
	</div>
	<!-- ./article-info -->
	
</c:if>