<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<!-- header -->
			<header class="clearfix">
				<div class="header-inner clearfix">
					<!-- logo -->
					<div class="logo">
						<a href="/"><img src="/img/logo_default.png" alt="내일고"></a>
					</div>
					<!-- navi -->
					<div class="navi">
						<ul>
							<li><a href="#" class="alarm-img"><img src="/img/header/alarm.png" alt="알림"><br>알림</a></li>
							<li><a href="http://localhost:8080/sns/sns" class="sns-img"><img src="/img/header/sns.png" alt="SNS"><br>SNS</a></li>
							<li><a href="http://localhost:8080/planner" class="planner-img"><img src="/img/header/planner.png" alt="플래너"><br>플래너</a></li>
							<li class="member">
								<!-- 사용자가 로그인을 하지 않은 상태인 경우 -->
								<c:if test="${member eq null}">
									<span class="signin-btn">로그인</span>
								</c:if>
								<!-- 사용자가 로그인을 한 상태인 경우 -->
								<c:if test="${member ne null}">
									<c:choose>
										<c:when test="${memadd.profile != null}"><img id="member-img" src='/member/display?fileName=${memadd.profile}' alt="프로필"></c:when>
										<c:when test="${memadd.profile == null}">
											<c:choose>
												<c:when test="${member.gender eq 'M'}"><a href="http://localhost:8080/member/timeline" class="member-img">
												<img src="/img/member/default_profile_m.png" alt="프로필 남" ></a></c:when>
												<c:when test="${member.gender eq 'F'}"><a href="http://localhost:8080/member/timeline" class="member-img">
												<img src="/img/member/default_profile_f.png" alt="프로필 여" ></a></c:when>
											</c:choose>	
										</c:when>
									</c:choose>
									<ul class="member-submenu">
										<li><a href="http://localhost:8080/member/timeline" class="member-timeline">타임라인</a></li>
										<li><a href="http://localhost:8080/member/schedule" class="member-schedule">일정관리</a></li>
										<li><a href="http://localhost:8080/signout" class="member-logout">로그아웃</a></li>
									</ul>
								</c:if>
							</li>
						</ul>
					</div>
				</div>
			</header>