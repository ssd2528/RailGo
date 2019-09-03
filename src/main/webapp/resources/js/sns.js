$(document).ready(function(){
	var alarm = $('.alarm-img');
	var sns = $('.sns-img'); 
	var planner = $('.planner-img');
	var member = $('.member-img');	
	
	$('.sns-img img').attr('src', '../img/header/sns_clicked.png');
	sns.css('color', '#009CE9');
	sns.closest('li').css('border-bottom', 'solid 3px #009CE9');

	// planner hover시 이미지 변환 
	planner.hover(function(){
		$('.planner-img img').attr('src', '../img/header/planner_clicked.png');
		planner.css('color', '#009CE9');
	}, function(){
		$('.planner-img img').attr('src', '../img/header/planner.png');
		planner.css('color', 'black');
	});

	// 게시글 수정
	$('.sns-content-edit').on('click', function(){
		var snsForm = $(this).closest('form');
		var snsContent = snsForm.find('.sns-content-body').text();
		//console.log(snsContent);
		$('#sns-write-modal').css('display','flex');
		$('.modal-title').text('게시물 수정');
		$('.sns-write-content').val(jQuery.trim(snsContent));
		$('.ok-button').text('수정');
		setTimeout(function() {
			$("#sns-write-modal").addClass('open');
		}, 1)
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});

	// 게시글 삭제
	$('.sns-content-delete').on('click', function(e) {
		$('.sns-form').attr('action','../sns/remove');
		$(this).closest('form').submit();
		alert("게시글 삭제 완료");
	});

	// 더보기 버튼 클릭시
	$(".article-sns-content").slice(0, 5).show(); // 처음에 게시글 5개만 보여주기
	$("#more-button").click(function(e){ // 더보기 버튼 클릭 이벤트
		e.preventDefault(); // 현재 이벤트의 기본 동작을 중단한다
		$(".article-sns-content:hidden").slice(0, 5).show(); // 숨김 설정된 다음 게시글 5개를 표시
		if($(".article-sns-content:hidden").length == 0){ // 숨겨진 게시글이 있는지 체크
			$(this).hide('fast'); // 마지막 글 이후 더보기 버튼 안보이게 하기
		}
	});
});