<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<!-- SNS WRITE -->
	<div id="sns-write-modal" class="sns-write-modal">
		<div class="sns-modal-write">
			<h2 class="modal-title">게시글</h2>
			<hr>
			<input type="hidden" name="sns_code" class="sns_code" value="">
			<div>
				<textarea class="sns-write-content" name="content"
					placeholder="게시글을 작성해주세요"></textarea>
				<input type="hidden" name="mem_code" value="${member.mem_code}">
			</div>
			
			<div class="sns-img-upload">
				<label for="sns_file">+</label>
				<input type='file' id="sns_file" name='uploadFile' multiple/>
			</div>
			<!-- 썸네일 처리 부분 -->
			<div class="sns-uploadResult">
				<ul>
				</ul>
			</div>
		
			<div class="button-wrapper">
				<button class="ok-button" type="submit">완료</button>
				<button class="close-button" type="button">닫기</button>
			</div>
		</div>
	</div>