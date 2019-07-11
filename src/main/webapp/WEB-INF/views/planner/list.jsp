<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>[RailGo] Planner list Page</title>
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
		<link href="../css/common.css" rel="stylesheet">
		<link href="../css/index.css" rel="stylesheet">
		<link href="../css/planner.css" rel="stylesheet">
	</head>
</head>
<body>
	<div class="wrap">
		<!-- header -->
		<%@include file="../includes/header.jsp"%>
		<!--  body  -->
		<div class="content">
			<div class="content-wrapper">
				<!-- 여행 플래너 설명  -->
				<div class="planner-info-img">
					<div class="planner-info">
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<%@include file="../includes/footer.jsp" %>
	</div>
</body>
</html>