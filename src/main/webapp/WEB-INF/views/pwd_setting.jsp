<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>[RailGo] 비밀번호 재설정 </title>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
<link href="../css/pwd_setting.css" rel="stylesheet">
<!-- <link href="../css/index.css" rel="stylesheet"> -->
<script src="<c:url value='/resources/jquery-3.4.1.min.js'/>"></script>
</head>
<body>
	<!-- 비밀번호 재설정 (new-pwd-change)-->
	<div class="new-pwd-change">
		<div class="pwd-title">내일고</div>
		<div class="pwd-content">새로운 비밀번호를 설정해주세요.</div>
		<form>
			<input class="new-pwd" type='password' name='new-pwd' placeholder='새 비밀번호'><br> 
			<input class="new-pwd-check" type='password' name='new-pwd-check' placeholder='새 비밀번호 확인'>
		</form>

		<button class="pwd-change" type="button">비밀번호 변경</button>
	</div>
	<!-- ./비밀번호 재설정 (new-pwd-change)-->
</body>
</html>
