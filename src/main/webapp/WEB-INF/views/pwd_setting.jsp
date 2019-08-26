<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] 비밀번호 재설정 </title>
	<link rel="icon" href="/img/favicon.ico">
	<link href="/css/pwd_setting.css" rel="stylesheet">
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
</head>
<body>
	<!-- 비밀번호 재설정 (new-pwd-change)-->
	<div class="new-pwd-change">
		<div class="pwd-title">내일고</div>
		<div class="pwd-content">새로운 비밀번호를 설정해주세요.</div>
		<form>
			<input class="email" type="hidden" name="email" value="${email}">
			<input class="uuid" type="hidden" name="uuid" value="${uuid}">
			
			<input class="uuid-chk" type="text" name="uuid-chk" placeholder="인증번호 입력"><br>
			<input class="new-pwd" type="password" name="pwd" placeholder="새 비밀번호"><br> 
			<input class="new-pwd-chk" type="password" name="pwd-chk" placeholder="새 비밀번호 확인">
			
			<input class="pwd-change" type="submit" value="비밀번호 변경">
		</form>
		
	</div>
	<!-- ./비밀번호 재설정 (new-pwd-change)-->
</body>
</html>
