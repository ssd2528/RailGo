<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="pagination">
		<fmt:parseNumber var="maxpage" integerOnly="true" value="${totalCount%12==0? totalCount/12 : totalCount/12+1}" />
		
		<c:set var="page" value="${currentPage}" />
		<c:if test="${currentPage eq null}">
			<c:set var="page" value="1" />
		</c:if>
		
		<input type="hidden" id="maxPage" value="${maxpage}"> <input type="hidden" id="currentPage" value="${page}">
		
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
					<li class="no no_before" value="${page-j}">${page - j}</li>
				</c:if>
			</c:forEach>
		</c:if>

		<!-- 현재 페이지 -->
		<li class="no selected">${page}</li>

		<!-- 다음 페이지 -->
		<c:if test="${page != maxpage}">
			<c:forEach var="i" begin="1" end="${next-1}">
				<c:if test="${maxpage >= page+i}">
					<li class="no no_before" value="${page+i}">${page+i}</li>
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
