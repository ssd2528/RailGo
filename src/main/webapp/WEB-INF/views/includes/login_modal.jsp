<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<!-- The Modal -->
		<div id="Modal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<span class="close">&times;</span>
				<h2 class="modal-title">내일고 로그인</h2>

				<hr class="line">
				
				<!-- 로그인 모달 (sign-in) -->
				<div class="sign-in-group">
					<form id="signin-form" class="signin-form" method="POST" action="signin">
						<input class="inform" type='email' name='email' id='signin-email' class='signin-email' placeholder='이메일 주소'>
						<input class="inform" type='password' name='pwd' id='signin-pwd' class='signin-pwd' placeholder='비밀번호'>
						<div id="checkboxArea">
							<input type='checkbox' name="loginCookie" id="loginCookie" class="loginCookie"> <label for="loginCookie"></label>자동로그인
						</div>
						<div class="error_msg"></div>
						<button class="logBtn" type="submit">로그인</button>
						<br>
						<span class="password-find-tag">비밀번호 찾기</span> 
						<span class="sign-up-tag">회원가입</span>
					</form>
				</div>
				<hr class="line">
				<div class="sign-in-group">
					<!-- <button class="logFb" type="button">Facebook으로 로그인</button> -->
					<button class="signin-kakao" id="signin-kakao" type="button"> <img src="../img/member/kakao_signin.png"></button>
					<button class="logGe" type="button"><a href="${google_url}">Google으로 로그인</a></button>
				</div>
				<!-- ./로그인 모달 (sign-in) -->
				<!-- 비밀번호 찾기 모달 (password-find) -->
				<div class="password-find-group">
					<hr>
					<p>가입된 이메일 주소를 입력해주세요.<br> 메일을 확인하시고 절차에 따라주세요.</p>
					<form>
						<input class="inform-addr" type='text' name='addr' placeholder='이메일 주소'>
					</form>
					<hr>
					<div>
						<button class="password-find-btn" type="button">비밀번호 재설정</button>
					</div>
				</div>
				<!-- ./비밀번호 찾기 모달 (password-find) -->
				
				<!-- 회원가입 모달 (sign-up) -->
				<div class="sign-up">
					<hr>
					<div class="sign-up-group">
						<form name="signupF" method="POST" id="signup-form" class="signup-form" action="signup">
							<div class="infoArea">
								<input class="inform" type='text' name='name' id="name" placeholder='이름'>
								<input class="inform" type='email' name='email' id="email" placeholder='이메일 주소'> 
								<input class="inform" type='password' name='pwd' id="pwd" placeholder='비밀번호'> 
								<input class="inform" type='password' name='repwd' id="repwd" placeholder='비밀번호 확인'>
							</div>
							<div id="sex-checkboxArea">
								<label for="M"><input type='radio' id="M" name="gender" value="M" checked>남</label> &nbsp;&nbsp;&nbsp;
								<label for="F"><input type='radio' id="F" name="gender" value="F">여</label>	
							</div>
							<div id="agree-checkboxArea">
								<input type='checkbox' id="agree"> <label for="agree"></label>내일고 
								<a href="#" class="agree-tag">이용약관</a>과 
								<a href="#" class="agree-tag">개인정보 취급방침</a><br>을 확인하고 이에 동의합니다.
								<input type="submit" class="sign-up-btn" value="회원가입">
							</div>
						</form>
						<span class="password-find-tag">비밀번호 찾기</span> 
						<span class="sign-in-tag">로그인</span>
						<br>
					</div>
					<hr>
					<div class="sign-up-group">
						<button class="signin-kakao" id="signin-kakao" type="button"> <img src="../img/member/kakao_signin.png"></button>
						<button class="logGe" type="button">Google으로 로그인</button>
					</div>
				</div>
				<!-- ./회원가입 모달 (sign-up) -->
			</div>
			<!-- ./ modal-content -->
		</div>
		<!-- ./Modal -->