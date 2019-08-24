<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!-- SNS MODAL -->
	<div id="sns-modal" class="sns-modal">
		<!-- SNS CONTENT -->
		<div class="sns-modal-content">
			<div class="sns-modal-imgs">
				<img src="../img/default.png">
			</div>
			<span class="sns-close">&times;</span>
			<div class="sns-article-right">
				<div class="sns-content">
					<div class="sns-content-write">
						<img class="sns-writer-profile" src="../img/member/default_profile_f.png">
						<div class="sns-write-box">
							<div class="sns-write-explain">
								<span class="sns-writer-name"></span>님이 리뷰를 남기셨습니다.
							</div>
							<div class="sns-write-date">
							</div>
						</div>
					</div>
				</div>
				
				<hr class="sns-line">
				
				<div class="sns-content-substance">
					<div class="sns-substance"></div>
				</div>
				<div class="sns-reply-container">
					<div class="sns-reply-box">
						<img class="sns-replyer-profile" src="../img/member/default_profile_f.png">
						<table class="sns-reply-content">
							<tr>
								<th class="sns-reply-writer">이호균</th>
								<td class="sns-reply-substance">와 재밌었겠다.</td>
							</tr>
							<tr>
								<th class="sns-reply-reply">
									<span class="sns-reply-like">좋아요</span>
									<span class="sns-reply-write">답글달기</span>
								</th>
								<td class="sns-reply-date">2019년 8월 12일 18:32</td>
							</tr>
						</table>
					</div>
					<div class="sns-reply-box">
						<img class="sns-replyer-profile" src="../img/member/default_profile_f.png">	
						<table class="sns-reply-content">
							<tr>
								<th class="sns-reply-writer">이호균</th>
								<td class="sns-reply-substance">와 재밌었겠다.</td>
							</tr>
							<tr>
								<th class="sns-reply-reply">
									<span class="sns-reply-like">좋아요</span>
									<span class="sns-reply-write">답글달기</span>
								</th>
								<td class="sns-reply-date">2019년 8월 12일 18:32</td>
							</tr>
						</table>
					</div>					
				</div>
				<div class="sns-bottom">
					<hr class="sns-line">
					<ul class="sns-icon-list">
						<li> <img class="sns-icon sns-heart" src="../img/sns/heart.png" alt="좋아요"> </li>
						<li> <img class="sns-icon sns-chat" src="../img/sns/chat.png" alt="댓글달기"> </li>
						<li> <img class="sns-icon sns-share" src="../img/sns/share.png" alt="공유하기"> </li>
					</ul>
					<div class="sns-reply-register">
						<textarea class="sns-reply-textarea" rows="1" name='reply'
								placeholder="&nbsp;&nbsp; 댓글 달기..."></textarea>
						<input type="submit" class="sns-reply-btn" value="게시"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- SNS WRITE -->
	<div id="sns-write-modal" class="sns-write-modal">
		<div class="sns-modal-write">
			<h2 class="modal-title">게시글</h2>
			<hr>
			<form id="sns-form" class="sns-form" method="POST" action="/sns/sns">
				<input type="hidden" name="sns_code" class="sns_code" value="">
				<input type="hidden" name="mod_content" class="mod_content" value="">
				<div>
					<textarea class="sns-write-content" name="content"
						placeholder="게시글을 작성해주세요"></textarea>
					<input type="hidden" name="mem_code" value="${member.mem_code}">
				</div>
				<hr>
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
			</form>
		</div>
	</div>		