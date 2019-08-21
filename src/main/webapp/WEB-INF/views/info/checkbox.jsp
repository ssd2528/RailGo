<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
				
					
<!-- checkbox-area(article-item) -->
<div class="article-item">

	<%-- <input type="hidden" name="categoryName" id="categoryName" value="${category}" /> --%>
	
	<!-- 1. 숙박 또는 음식을 클릭했을 때, -->
	<c:if test="${category eq '숙박' || category eq '맛집'}">
		<!-- checkbox-block -->
		<div class="checkbox-block">
			<div class="checkbox-title">카테고리</div>
				<!-- '전체' 체크박스 -->
				<div class="checkbox-chk">
					<input type="checkbox" id="all" data-code="all" name="chk_category" checked/>
				    <label for="all"><span class="chkbox"></span><span class="cat3Name" id="all">전체</span></label>
			    </div>
				<c:forEach items="${cat3List}" var="cat3Item">
					<div class="checkbox-chk">
						<input type="checkbox" id="${cat3Item.cat3Name}" data-code="${cat3Item.cat3}" name="chk_category" ${cat3Item.cat3 == cat3 ? "checked" : "" }/>
					    <label for="${cat3Item.cat3Name}"><span class="chkbox"></span><span class="cat3Name" id="${cat3Item.cat3}">${cat3Item.cat3Name}</span></label>
				    </div>
			    </c:forEach>
		    <hr class="checkbox-hr">
		</div>
	</c:if>
	
	<!-- 2. 관광명소를 클릭했을 때, -->
	<c:if test="${category eq '관광명소'}">
		<!-- 카테고리 체크박스(checkbox-block) -->
		<div class="checkbox-block" id="checkbox-contentType">
			<div class="checkbox-title">관광명소 타입</div>
				<!-- '전체' 체크박스 -->
				<div class="checkbox-chk">
					<input type="checkbox" id="all" name="chk_contentType" checked/>
				    <label for="all"><span class="chkbox"></span><span class="contentTypeName" id="all">전체</span></label>
			    </div>
				<c:forEach items="${contentTypeList}" var="contentTypeItem">
					<div class="checkbox-chk">
						<input type="checkbox" id="${contentTypeItem.contentTypeId}" data-code="${contentTypeItem.contentTypeId}" name="chk_contentType" />
					    <label for="${contentTypeItem.contentTypeId}"><span class="chkbox"></span>${contentTypeItem.contentTypeName}</label>
				    </div>
			    </c:forEach>
		    <hr class="checkbox-hr">
		</div>
		
		<!-- [대분류] checkbox-block -->
		<div class="checkbox-block" id="checkbox-cat1">
			<div class="checkbox-title">대분류</div>
		    <div id="checkbox-cat1List-block"></div>
		</div>
		
		<!-- [중분류] checkbox-block -->
		<div class="checkbox-block" id="checkbox-cat2">
			<div class="checkbox-title">중분류</div>
		    <div id="checkbox-cat2List-block"></div>
		</div>
		
		<!-- [소분류] checkbox-block -->
		<div class="checkbox-block" id="checkbox-cat3">
			<div class="checkbox-title">소분류</div>
		    <div id="checkbox-cat3List-block"></div>
		</div>
	</c:if>
	
	
	
</div>
<!-- ./checkbox-area(article-item) -->