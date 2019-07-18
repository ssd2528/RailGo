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
					<form>
						<input class="inform" type='text' name='addr' placeholder='이메일 주소'>
						<input class="inform" type='password' name='pwd' placeholder='비밀번호'>
					</form>
					<form id="checkboxArea">
						<input type='checkbox' id="autologin"> <label for="autologin"></label>자동로그인
					</form>
					<button class="logBtn" type="button">로그인</button>
					<br>
					<a href="#" class="password-find-tag">비밀번호 찾기</a> 
					<a href="#" class="sign-up-tag">회원가입</a>
				</div>
				<hr class="line">
				<div class="sign-in-group">
					<button class="logFb" type="button">Facebook으로 로그인</button>
					<button class="logGe" type="button">Google으로 로그인</button>
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
						<form name="signupF" method="get" class="signup-form">
							<div class="infoArea">
								<input class="inform" type='text' name='name' placeholder='이름'>
								<input class="inform" type='text' name='email' placeholder='이메일 주소'> 
								<input class="inform" type='text' name='pwd' placeholder='비밀번호'> 
								<input class="inform" type='text' name='pwd-repeat' placeholder='비밀번호 확인'>
							</div>
							<div id="sex-checkboxArea">
								<label for="male"><input type='radio' id="male" name="gender" value="male">남</label> &nbsp;&nbsp;&nbsp;
								<label for="female"><input type='radio' id="female" name="gender" value="female">여</label>	
							</div>
							<div id="agree-checkboxArea">
								<input type='checkbox' id="agree"> <label for="agree"></label>내일고 
								<a href="#" class="agree-tag">이용약관</a>과 <a href="#" class="agree-tag">개인정보 취급방침</a>을 확인하고 이에 동의합니다.
								<input type="submit" class="sign-up-btn" value="회원가입">
							</div>
						</form>
						
						
						<br>
						<a href="#" class="password-find-tag">비밀번호 찾기</a> 
						<a href="#" class="sign-in-tag">로그인</a>
					</div>
					<hr>
					<div class="sign-up-group">
						<button class="logFb" type="button">Facebook으로 로그인</button>
						<button class="logGe" type="button">Google으로 로그인</button>
					</div>
				</div>
				<!-- ./회원가입 모달 (sign-up) -->
			</div>
			<!-- ./ modal-content -->
		</div>
		<!-- ./Modal -->