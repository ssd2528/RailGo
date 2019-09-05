<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
							<!-- article-sns-user -->
							<div class="article-item article-sns-user">
								<div class="article-title"><h2>SNS 이용자 추천</h2></div> 
								<div class="sns-user-list">
									<!-- 추천 이용자 1(sns-user-item) -->
									<c:forEach var="recomMember" items="${recomMember}" varStatus="status">
									<div class="sns-user-item">
										<div class="sns-user-profile">
										<c:choose>
											<c:when test="${recomMemberAdd[status.index].profile != null}">
												<a href="../member/other_user_info?mem_code=${recomMember.mem_code}"><img class="user-img" src="/member/display?fileName=${recomMemberAdd[status.index].profile}"></a>
											</c:when>
											<c:when test="${recomMemberAdd[status.index].profile eq null}">
												<c:choose>
													<c:when test="${recomMember.gender eq null}">
														<a href="../member/other_user_info?mem_code=${recomMember.mem_code}"><img class="user-img" src="/img/member/default_profile_m.png"></a>
													</c:when>
													<c:when test="${recomMember.gender eq 'M'}">
														<a href="../member/other_user_info?mem_code=${recomMember.mem_code}"><img class="user-img" src="/img/member/default_profile_m.png"></a>
													</c:when>
													<c:when test="${recomMember.gender eq 'F'}">
														<a href="../member/other_user_info?mem_code=${recomMember.mem_code}"><img class="user-img" src="/img/member/default_profile_f.png"></a>
													</c:when>
												</c:choose>
											</c:when>
										</c:choose>
											<div class="user-nameid">
												<span class="user-name">사용자 닉네임</span><br> 
												<span class="user-id font-13">${recomMember.name}</span>
											</div>
										</div>
										<c:choose>
											<c:when test="${recomMemberAdd[status.index].address != null}">
												<div class="user-loc font-15"><img src="/img/member/info_location.png">${recomMemberAdd[status.index].address}</div>
											</c:when>
											<c:when test="${recomMemberAdd[status.index].address == null}">
												<div class="user-loc font-15"><img src="/img/member/info_location.png">정보 없음</div>
											</c:when>
										</c:choose>
										<span class="user-posting font-15"> 포스팅</span> &nbsp;&nbsp;&nbsp; <span class="user-follower font-15"> 팔로워</span>
										<div class="user-detail font-15">사용자 소개 글</div>
										<button class="follow-btn"><img src="/img/sns/add_follow.png">팔로우</button>
									</div>
									</c:forEach>
									<!-- ./sns-user-item -->
								</div>
							</div>
							<!-- ./article-sns-user -->