//일정만들기 버튼 클릭시 모달창 오픈
$(document).ready(function(){
	$('.sns-write-img').on("click",function(){
		console.log('버튼 클릭');
		
		$("#sns-write-modal").css('display','flex');
		$('.modal-title').text('게시물 작성');	
		$('.sns-write-content').text('');
		$('.ok-button').text('작성');
		setTimeout(function() {
			$("#sns-write-modal").addClass('open');
		}, 1)
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});
	
	
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
		if($('#sns-write-modal').hasClass('open')){ // site 라는 특정영역이 열려있을 경우
			if(!$('#sns-write-modal').has(e.target).length){ // site에 클릭 이벤트가
				// 발생되어 있는게 없다면 아래
				// 내용을 실행.
				$('#sns-write-modal').removeClass('open');
				$('.sns-write-content').val('');
				$('body').css({'overflow':'auto', 'height':'100%'});
				setTimeout(function() {
					$('#sns-write-modal').css('display','none');
				}, 50)
			}
		}
	});
	// 닫기 버튼 클릭시 modal 창닫기
	$('.close-button').click(function(e){
		$('#sns-write-modal').removeClass('open');
		$('.sns-write-content').val('');
		$('body').css({'overflow':'auto', 'height':'100%'});
		setTimeout(function() {
			$('#sns-write-modal').css('display','none');
		}, 50)
	});
	
	// 글 작성 완료 alert창
	$('.ok-button').click(function(e){
		if($('.ok-button').text()=='수정') {
			var snscode = $('#sns-forms').children('.sns_code').val();
			$('#sns-form').children('.sns_code').val(snscode);
			$('.mod_content').val($('.sns-write-content').val());
			$('.sns-form').attr('action','../sns/modify');
			$(this).closest('form').submit();
			alert("게시글 수정 완료");
		}else {
			alert("게시글 작성 완료");
		}
	});
});

