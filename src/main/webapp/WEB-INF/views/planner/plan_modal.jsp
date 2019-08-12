<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="modal-wrapper" class="modal-wrapper">
	<div class="plan-modal-content">
		<div class="plan-create-text">일정 만들기</div>
		<div class="plan-option-wrap">
			<div class="plan-option-text">1.티켓을 선택해주세요.</div>
			<div id="plan-option-days" class="plan-option-days-wrapper">
				<div id="third-days-option" class="plan-option-tickets">3일권</div>
				<div id="fifth-days-option" class="plan-option-tickets">5일권</div>
				<div id="seventh-days-option" class="plan-option-tickets">7일권</div>
			</div>
			<div class="plan-option-text">2.여행 시작 날짜를 선택해주세요.</div>
			<div class="plan-option-days-wrapper">
				<!--  <div id="datepicker" class="plan-option-days">1999-01-01</div> -->
				<input type="text" id="datepicker" class="plan-option-days" placeholder="2019/07/17" disabled="" readonly>
				<div class="plan-option-days"
					style="border: none; width: 5%; cursor: default;">~</div>
				<div class="plan-option-days" style="cursor: default">2019/07/17</div>
			</div>
			<div class="plan-option-text">3.몇 명이서 가시나요.</div>
			<div class="plan-option-people">
				<div class="plan-option-minus">-</div>
				<div class="plan-option-number">1</div>
				명
				<div class="plan-option-plus">+</div>
			</div>
			<div class="plan-option-member-wrapper">
				<div class="plan-option-text">4.친구를 초대해보세요.</div>
				<input type="text" class="plan-option-people" placeholder="닉네임검색">
			</div>
			<div class="plan-option-button-wrapper">
				<div id="plan-option-ok-btn" class="plan-option-button">확인</div>
				<div id="plan-option-close-btn" class="plan-option-button">닫기</div>
			</div>
		</div>
	</div>
</div>
<!--  fade section -->
<div id="mask"></div>