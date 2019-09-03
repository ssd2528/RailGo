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
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
</head>
<body>
	<!-- 비밀번호 재설정 (new-pwd-change)-->
	<div class="new-pwd-change">
		<div class="pwd-title">내일고</div>
		<div class="pwd-content">새로운 비밀번호를 설정해주세요.</div>
		<form>
			<input class="email" id="email" type="hidden" name="email" value="${email}">
			<input class="uuid" id="uuid" type="hidden" name="uuid" value="${uuid}">
			
			<input class="reuuid" id="reuuid" type="text" name="reuuid" placeholder="인증번호 입력">
			<input class="new-pwd" id="pwd" type="password" name="pwd" placeholder="새 비밀번호">
			<input class="new-pwd-chk" id="new-pwd_chk" type="password" name="repwd" placeholder="새 비밀번호 확인">
			
			<input class="pwd-change" type="submit" value="비밀번호 변경">
		</form>
		
	</div>
	<!-- ./비밀번호 재설정 (new-pwd-change)-->
	
	<script>
		$(function(){
			$.validator.addMethod('passwordCk',  function( value, element ) {
				return this.optional(element) ||  /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/.test(value);
			}); 
			console.log($('.uuid').val());
			
			//$('.pwd-change').on('click', function(){
				$('form').validate({
					rules:{
						reuuid:{required:true, equalTo:uuid},
						pwd:{
							required:true, 
							passwordCk :true,
							minlength:8, 
							maxlength:16
						},
						repwd:{required:true, equalTo:pwd}
					}, 
					messages:{
						reuuid:{required:'인증번호를 입력해주세요.', equalTo:'인증번호가 다릅니다. 다시 확인해주세요.'},
						pwd:{
							required:'비밀번호를 입력해주세요.', 
							passwordCk:'영문,숫자,특수문자의 조합으로 8~16자리이어야 합니다.',
							minlength:$.validator.format('비밀번호는 8~16자리이어야 합니다.'), 
							maxlength:$.validator.format('비밀번호는 8~16자리이어야 합니다.')
						},
						repwd:{required:'비밀번호를 입력해주세요.', equalTo:'비밀번호가 다릅니다. 다시 확인해주세요.'}
					},
					submitHandler:function(form){
						$.ajax({
							url:'../updatePassword',
							type:'post',
							dataType:'text',
							data:{email:$('#email').val(), pwd:$('#pwd').val()},
							success : function(result) {
								if(result == 'success') {
									alert('비밀번호를 변경했습니다. 새로운 비밀번호로 로그인해주세요.');
									location.href='../';
								}else if(result == 'error') {
									alert('error!!');
								}
							}
						});
					}
				}); // end of validate
			//});
		});
	</script>
</body>
</html>
