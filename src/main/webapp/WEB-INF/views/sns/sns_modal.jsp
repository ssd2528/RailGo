<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- The Modal -->
<div id="sns-write-modal" class="sns-write-modal">
	<!-- Modal content -->
	<div class="sns-modal-content">
		<h2 class="modal-title">게시글</h2>
		<hr>
		<form id="sns-form" class="sns-form" method="POST" action="">
			<input type="hidden" name="sns_code" class="sns_code" value="">
			<input type="hidden" name="mod_content" class="mod_content" value="">
			<div>
				<textarea class="sns-write-content" name="content"
					placeholder="게시글을 작성해주세요"></textarea>
				<input type="hidden" name="mem_code" value="${member.mem_code}">
			</div>
			<hr>

			<div class="button-wrapper">
				<button class="ok-button" type="submit">완료</button>
				<button class="close-button" type="button">닫기</button>
			</div>
		</form>
	</div>
</div>