<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- profile-background -->
<div class="section-back-img clearfix">
	<!-- <a href="#"><img id="back-img-update" src="../img/member/edit.png" alt="프로필 사진 수정"
	onmouseover="this.src='../img/member/edit_hover.png'" onmouseout="this.src='../img/member/edit.png'"></a> -->
	<c:choose>
		<c:when test="${memadd.backImage != null}">
			<img id="section-back-img" alt="프로필 바탕화면" src='/member/display?fileName=${memadd.backImage}'>
		</c:when>
		<c:when test="${memadd.backImage == null}">
			<img id="section-back-img" alt="프로필 바탕화면" src="../img/info/서울1.jpg"></c:when>
	</c:choose>
	<div class='uploadDiv'>
		<label class="uplabel" for='uploadFile'>
		<img id="back-img-update" src="../img/member/edit.png" name="back-img-update" 
		onmouseover="this.src='../img/member/edit_hover.png'" onmouseout="this.src='../img/member/edit.png'">
		</label>
		<input type="hidden" name="mem_code" value="${member.mem_code}">
		<input type="hidden" name="address" value="${memadd.address}">
		<input type="hidden" name="job" value="${memadd.job}">
		<input type="hidden" name="birth" value="${memadd.birth}">
		<input type="hidden" name="interest" value="${memadd.interest}">
		<input type="hidden" name="profile" value="${memadd.profile}">
        <input type='file' id='uploadFile' name='backImage' multiple>
	</div>
</div>
<!-- /profile-background -->

<!-- profile-info -->
<div class="profile-info">
	<!-- profile-img -->
	<div class="profile-img clearfix">
		<c:choose>
			<c:when test="${memadd.profile != null}">
				<div class='uploadDiv2'>
					<label class="uplabel2" for='uploadFile2'>
					<img id="profile-img" src='/member/display?fileName=${memadd.profile}' alt="프로필">
					</label>
					<input type="hidden" name="mem_code2" value="${member.mem_code}">
					<input type="hidden" name="address2" value="${memadd.address}">
					<input type="hidden" name="job2" value="${memadd.job}">
					<input type="hidden" name="birth2" value="${memadd.birth}">
					<input type="hidden" name="interest2" value="${memadd.interest}">
					<input type="hidden" name="backImage2" value="${memadd.backImage}">
			        <input type='file' id='uploadFile2' name='profile2' multiple>
				</div>
			</c:when>
			<c:when test="${memadd.profile == null}">
				<c:choose>	
					<c:when test="${member.gender eq 'M'}"><img id="profile-img" src="../img/member/default_profile_m.png" alt="프로필"></c:when>
					<c:when test="${member.gender eq 'F'}"><img id="profile-img" src="../img/member/default_profile_f.png" alt="프로필"></c:when>
				</c:choose>
			</c:when>
		</c:choose>	
	</div>
	<!-- /profile-img -->

	<!-- profile-detail -->
	<div class="profile-detail">
		<div class="row1">
			<span class="user-name">사용자 닉네임</span>
			<span>포스팅</span>
			<span>팔로워</span>
			<span>팔로잉</span>
		</div>
		<div class="row2">
			<span class="user-id">${member.name}</span>
			<span class="posting">0</span>
			<span class="follower">0</span>
			<span class="following">0</span>
		</div>
		<!--  <img alt="Test" src='/member/display?fileName=나홀로_기차.jpg'>-->
	</div>
	<!-- /profile-detail -->

	<!-- tab-list -->
	<div class="tab-list">
		<a href="../member/timeline" class="timeline">타임라인</a> 
		<a href="../member/schedule" class="schedule">일정관리</a>
	</div>
	<!-- /tab-list -->
</div>
<!-- /profile-info -->