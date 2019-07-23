$(document).ready(function(){
	var modal = $('#Modal'); // Get the modal
	var signinBtn = $('.signin-btn'); // Get the button that opens the modal
	var closeBtn = $('.close'); // Get the <span> element that closes the modal
	// When the user clicks on the button, open the modal 
	$('.signin-btn').on('click', function(){
		modal.css('display', 'block');
		$('.modal-title').text("내일고 로그인");
		$('.sign-in-group').show();
		$('.line').show();
		$('.sign-up').hide();
		$('.password-find-group').hide();
		$('html').css('overflow', 'hidden');
	}); 
	// When the user clicks on <span> (x), close the modal
	closeBtn.on('click', function(){
		modal.css('display', 'none');
		$('form').each(function(){this.reset();})
		$('label.error').css('display', 'none');
		//location.reload();
		$('html').css('overflow', 'auto');
		
	}); 
	$('.password-find-tag').click(function() {
		$('form').each(function(){this.reset();})
		$('.modal-title').text('내일고 비밀번호 찾기');
		$('.password-find-group').show();
		$('.sign-in-group').hide();
		$('.line').hide();
		$('.sign-up').hide();
	});
	$('.sign-up-tag').click(function() {
		$('form').each(function(){this.reset();})
		$('label.error').css('display', 'none');
		$('.modal-title').text("내일고 회원가입");
		$('.sign-up').show();
		$('.sign-in-group').hide();
		$('.line').hide();
	});
	$('.sign-in-tag').click(function() {
		$('form').each(function(){this.reset();})
		$('.modal-title').text("내일고 로그인");
		$('.sign-in-group').show();
		$('.line').show();
		$('.sign-up').hide();
		$('.password-find-group').hide();
	});
	

	// 카카오 로그인 버튼 클릭 시 
	$(document).on('click', '#signin-kakao', function(){
		window.name = "parent";
		window.open("https://kauth.kakao.com/oauth/authorize?client_id=96efae73b25f4b5afa4875a7d4a8839f&redirect_uri=http://localhost:8080/kakaoSignin&response_type=code", "kakao_signin", "width=400, height=700");
		
		return false;
	});
	
});


$(function(){
	// 회원가입 Validate
	$.validator.addMethod("passwordCk",  function( value, element ) {
		return this.optional(element) ||  /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/.test(value);
	}); 
	$('#signup-form').validate({
		rules:{
			name:{required:true},
			email:{
				required:true, 
				email:true, 
				remote:{url:'/checkEmail', type:'post'}
			},
			pwd:{
				required:true, 
				passwordCk :true,
				minlength:8, 
				maxlength:16
			},
			repwd:{required:true, equalTo:pwd}
		}, 
		messages:{
			name:{required:'이름을 입력해주세요.'},
			email:{
				required:'이메일을 입력해주세요.', 
				email:'잘못된 이메일 형식입니다.', 
				remote: $.validator.format('입력하신 {0}는 이미 존재하는 이메일입니다.')
			},
			pwd:{
				required:'비밀번호를 입력해주세요.', 
				passwordCk:'비밀번호는 영문,숫자,특수문자의 조합으로 8~16자리이어야 합니다.',
				minlength:$.validator.format('비밀번호는 8~16자리이어야 합니다.'), 
				maxlength:$.validator.format('비밀번호는 8~16자리이어야 합니다.')
			},
			repwd:{required:'비밀번호를 입력해주세요.', equalTo:'비밀번호가 다릅니다. 다시 확인해주세요.'}
		},
		submitHandler:function(form){
			if($("input:checkbox[id='agree']").is(":checked")) {
				form.submit();
			}else {
				alert('약관에 동의하셔야 회원가입이 가능합니다.');
			}
		}
	});
	
	
	// 로그인 Validate
	$('#signin-form').validate({
		rules: {
			email:{required:true, email:true},
			pwd:{required:true}
		},
		messages: {
			email:{required:'이메일을 입력하세요.', email:'올바른 이메일 주소를 입력하세요.'},
			pwd : {required:'비밀번호를 입력하세요.'}
		},
		submitHandler:function(form){
			var email = $('#signin-email').val();
			var pwd = $('#signin-pwd').val();
			var loginCookie = $('#loginCookie').is(':checked');
			//alert(loginCookie);
			$.ajax({
				url:'signin',
				type:'post',
				dataType:'text',
				data:{email:email, pwd:pwd, loginCookie:loginCookie},
				success : function(result) {
					if(result == 'success') {
						alert('로그인 성공!');
						location.href="../";
					}else if(result == 'does not exit') {
						$('.error_msg').html('존재하지 않는 아이디입니다.');
					}else if(result == 'do not match') {
						$('.error_msg').html('아이디와 비밀번호가 일치하지 않습니다.');
					}
				}
			});
		}
	});
	
	
});

