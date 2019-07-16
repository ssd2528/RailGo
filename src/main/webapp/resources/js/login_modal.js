// Get the modal
var modal = document.getElementById('Modal');

// Get the button that opens the modal
var btn = document.getElementById('button');

// Get the <span> element that closes the modal
var span = document.getElementsByClassName('close')[0];

// When the user clicks on the button, open the modal 
btn.onclick = function() {
	modal.style.display = 'block';
	$('html').css('overflow', 'hidden');
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	modal.style.display = 'none';
	location.reload();
	$('html').css('overflow', 'auto');
}

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