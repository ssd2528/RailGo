<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- The Modal -->
<div id="sns-write-modal" class="sns-write-modal">
	<!-- Modal content -->
	<div class="sns-modal-content">
		<h2 class="modal_title">게시물 작성</h2>
		<hr>
		<div>
			<textarea class="sns-write-content" name="content"
				placeholder="게시글을 작성해주세요"></textarea>
		</div>
		<form id="sns-form" class="sns-form" method="POST" action="/sns/sns">
			<div class="file-upload">
				<label for="file-name">사진/동영상</label> <input type="file"
					id="file-name">
			</div>
			<hr>
			<div class="button-wrapper">
				<button class="ok-button" type="submit">완료</button>
				<button class="close-button" class="file_input_hidden" type="button">닫기</button>
			</div>
		</form>

	</div>
</div>