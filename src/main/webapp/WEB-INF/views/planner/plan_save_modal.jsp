<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="save-modal-wrapper">
	<div class="save-modal-content">
		<div class="save-title-wrapper">
			<div class="save-title">일정만들기 완료</div>
			<div class="close-btn">&times;</div>
		</div>
		<div class="save-content">
			<div class="save-subject-wrapper">
				<div class="save-subject">여행 제목</div>
				<input type="text" class="save-subject-input" maxlength="15">
			</div>
			<div class="save-theme-wrapper">
				<div class="save-theme-text">여행 테마</div>
				<div class="save-theme-checks">
					<div class="checks etrans" id="checks-solo">
  						<input type="checkbox" id="theme-solo"> 
  						<label for="theme-solo">나홀로</label> 
					</div>
					<div class="checks etrans" id="checks-duo">
  						<input type="checkbox" id="theme-duo"> 
  						<label for="theme-duo">단둘이</label>
					</div>
					<div class="checks etrans" id="checks-squad">
  						<input type="checkbox" id="theme-squad"> 
  						<label for="theme-squad">셋이상</label> 
					</div>
					<div class="checks etrans" id="checks-eating">
  						<input type="checkbox" id="theme-eating"> 
  						<label for="theme-eating">먹방</label> 
					</div>
					<div class="checks etrans" id="checks-healing">
  						<input type="checkbox" id="theme-healing"> 
  						<label for="theme-healing">힐링</label> 
					</div>
				</div>
			</div>
			<div class="complete-check-btn">완료</div>
		</div>
	
	</div>

</div>