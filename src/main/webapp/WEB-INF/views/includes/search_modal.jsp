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
						<li class="region-city-town">가평</li>
						<li class="region-city-town">광명</li>
						<li class="region-city-town">광운대</li>
						<li class="region-city-town">동두천</li>
						<li class="region-city-town">서울</li>
						<li class="region-city-town">송도</li>
						<li class="region-city-town">수서</li>
						<li class="region-city-town">수원</li>
						<li class="region-city-town">양평</li>
						<li class="region-city-town">영등포</li>
						<li class="region-city-town">용산</li>
						<li class="region-city-town">의정부</li>
						<li class="region-city-town">죽전</li>
						<li class="region-city-town">청량리</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">강원권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-town">강릉</li>
						<li class="region-city-town">동해</li>
						<li class="region-city-town">민둥산</li>
						<li class="region-city-town">영월</li>
						<li class="region-city-town">춘천</li>
						<li class="region-city-town">태백</li>
						<li class="region-city-town">평창</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">충청권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-town">단양</li>
						<li class="region-city-town">대전</li>
						<li class="region-city-town">대천</li>
						<li class="region-city-town">서대전</li>
						<li class="region-city-town">영동</li>
						<li class="region-city-town">제천</li>
						<li class="region-city-town">조치원</li>
						<li class="region-city-town">천안</li>
						<li class="region-city-town">천안아산</li>
						<li class="region-city-town">충주</li>
						<li class="region-city-town">홍성</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">전라권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-town">곡성</li>
						<li class="region-city-town">광주</li>
						<li class="region-city-town">광주송정</li>
						<li class="region-city-town">구례구</li>
						<li class="region-city-town">나주</li>
						<li class="region-city-town">남원</li>
						<li class="region-city-town">목포</li>
						<li class="region-city-town">순천</li>
						<li class="region-city-town">여수엑스포</li>
						<li class="region-city-town">익산</li>
						<li class="region-city-town">전주</li>
						<li class="region-city-town">정읍</li>
					</ul>
				</li>
				<li class="region-item">
					<span class="region-name">경상권</span> <span class="toggle-btn"> > </span>
					<ul class="region-city-list">
						<li class="region-city-town">경주</li>
						<li class="region-city-town">구미</li>
						<li class="region-city-town">김천(구미)</li>
						<li class="region-city-town">대구</li>
						<li class="region-city-town">동대구</li>
						<li class="region-city-town">마산</li>
						<li class="region-city-town">부산</li>
						<li class="region-city-town">부전</li>
						<li class="region-city-town">신경주</li>
						<li class="region-city-town">안동</li>
						<li class="region-city-town">영주</li>
						<li class="region-city-town">영천</li>
						<li class="region-city-town">울산</li>
						<li class="region-city-town">점촌</li>
						<li class="region-city-town">진주</li>
						<li class="region-city-town">춘양</li>
						<li class="region-city-town">포항</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>