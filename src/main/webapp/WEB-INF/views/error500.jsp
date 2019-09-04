<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>[RailGo] 에러 페이지</title> 
	<link rel="icon" href="/img/favicon.ico">
	<link href="../css/error_404_500.css" rel="stylesheet">
	<script src="https://use.fontawesome.com/releases/v5.9.0/js/all.js"></script>
	<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
	<!-- 에러페이지 (error500)-->
	<div class="error500">
		<i class="fas fa-sad-tear icon">.</i>
		<span class="error500-title">500</span>
		<div class="error500-content">서버 스크립트에 문제가 생겼습니다.</div>
		<div>원격지 서버가 응답 시간을 초과하였습니다.<br>이용에 불편을 드려 죄송합니다.</div>
		
		<button class="error500-button" type="button" onclick="location.href='/'">메인페이지로</button>
	</div>
	<!-- ./에러페이지 (error500)-->
</body>
</html>
