$(document).ready(function(){
	var modal = $('#Modal'); // Get the modal
	var signinBtn = $('.signin-btn'); // Get the button that opens the modal
	var closeBtn = $('.close'); // Get the <span> element that closes the modal

	// When the user clicks on the button, open the modal 
	$('.signin-btn').on('click', function(){
		modal.css('display', 'block');
		$('html').css('overflow', 'hidden');
	}); 

	// When the user clicks on <span> (x), close the modal
	closeBtn.on('click', function(){
		modal.css('display', 'none');
		//location.reload();
		$('html').css('overflow', 'auto');
	}); 

	$('.password-find-tag').click(function() {
		$('.modal-title').text('내일고 비밀번호 찾기');
		$('.password-find-group').show();
		$('.sign-in-group').hide();
		$('.line').hide();
		$('.sign-up').hide();
	});

	$('.sign-up-tag').click(function() {
		$('.modal-title').text("내일고 회원가입");
		$('.sign-up').show();
		$('.sign-in-group').hide();
		$('.line').hide();
	});

	$('.sign-in-tag').click(function() {
		$('.modal-title').text("내일고 로그인");
		$('.sign-in-group').show();
		$('.line').show();
		$('.sign-up').hide();
		$('.password-find-group').hide();
	});
});

