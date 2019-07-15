<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="search-modal" class="search-modal">
	<div class="modal-content">
		<span class="close-btn">&times;</span>
		<div class="search-text">
			어디로 가시나요?
			<!-- <input class="search-text" type="text" placeholder="어디로 가시나요?"> -->
		</div>
		<div class="city-list-wrap">
			<!-- 지역별 -->
			<ul class="region-list">
				<li class="region-item">
					<span class="region-name">수도권</span>  <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-item">가평</li>
						<li class="region-city-item">광명</li>
						<li class="region-city-item">광운대</li>
						<li class="region-city-item">동두천</li>
						<li class="region-city-item">서울</li>
						<li class="region-city-item">송도</li>
						<li class="region-city-item">수서</li>
						<li class="region-city-item">수원</li>
						<li class="region-city-item">양평</li>
						<li class="region-city-item">영등포</li>
						<li class="region-city-item">용산</li>
						<li class="region-city-item">의정부</li>
						<li class="region-city-item">죽전</li>
						<li class="region-city-item">청량리</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">강원권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-item">강릉</li>
						<li class="region-city-item">동해</li>
						<li class="region-city-item">민둥산</li>
						<li class="region-city-item">영월</li>
						<li class="region-city-item">춘천</li>
						<li class="region-city-item">태백</li>
						<li class="region-city-item">평창</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">충청권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-item">단양</li>
						<li class="region-city-item">대전</li>
						<li class="region-city-item">대천</li>
						<li class="region-city-item">서대전</li>
						<li class="region-city-item">영동</li>
						<li class="region-city-item">제천</li>
						<li class="region-city-item">조치원</li>
						<li class="region-city-item">천안</li>
						<li class="region-city-item">천안아산</li>
						<li class="region-city-item">충주</li>
						<li class="region-city-item">홍성</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">전라권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-item">곡성</li>
						<li class="region-city-item">광주</li>
						<li class="region-city-item">광주송정</li>
						<li class="region-city-item">구례구</li>
						<li class="region-city-item">나주</li>
						<li class="region-city-item">남원</li>
						<li class="region-city-item">목포</li>
						<li class="region-city-item">순천</li>
						<li class="region-city-item">여수엑스포</li>
						<li class="region-city-item">익산</li>
						<li class="region-city-item">전주</li>
						<li class="region-city-item">정읍</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">경상권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-item">경주</li>
						<li class="region-city-item">구미</li>
						<li class="region-city-item">김천(구미)</li>
						<li class="region-city-item">대구</li>
						<li class="region-city-item">동대구</li>
						<li class="region-city-item">마산</li>
						<li class="region-city-item">부산</li>
						<li class="region-city-item">부전</li>
						<li class="region-city-item">신경주</li>
						<li class="region-city-item">안동</li>
						<li class="region-city-item">영주</li>
						<li class="region-city-item">영천</li>
						<li class="region-city-item">울산</li>
						<li class="region-city-item">점촌</li>
						<li class="region-city-item">진주</li>
						<li class="region-city-item">춘양</li>
						<li class="region-city-item">포항</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>