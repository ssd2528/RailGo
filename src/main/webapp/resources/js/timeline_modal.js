$(document).ready(function(){
	// 모달창 오픈
	$('#sns-modify').on('click',function(){
		$('#hide_li').hide();
		$('#myModal').css('display','flex');
		setTimeout(function() {
			$("#myModal").addClass('open');
		}, 1)
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
	   if($('#myModal').hasClass('open')){ // site 라는 특정영역이 열려있을 경우
	      if(!$('#myModal').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
	         $('#myModal').removeClass('open');
	         $('body').css({'overflow':'auto', 'height':'100%'});
	         setTimeout(function() {
	            $('#myModal').css('display','none');
	         }, 50)
	      }
	   }
	});
	$('#modalCloseBtn').on('click',function(){
		$('#myModal').css('display','none');
		setTimeout(function() {
			$("#myModal").removeClass('open');
		}, 1)
		$('body').css({'overflow':'auto', 'height':'100%'});
	});
});