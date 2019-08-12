$(document).ready(function(){
// open the modal 
/*$(function(){
	$('.calendar-map').click(function () {
		 $('#modal').show();
		 $('html').css('overflow', 'hidden');
	});
});*/
	// open the modal 
	$('.calendar-map').on("click",function(){
		   $("#modal").css('display','flex');
		   setTimeout(function() {
		      $("#modal").addClass('show');
		   }, 1)
		   $('body').css({'overflow':'hidden', 'height':'100%'});
		});
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
	   if($('#modal').hasClass('show')){ // site 라는 특정영역이 열려있을 경우
	      if(!$('#modal').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
	         $('#modal').removeClass('show');
	         $('body').css({'overflow':'auto', 'height':'100%'});
	         setTimeout(function() {
	            $('#modal').css('display','none');
	         }, 50)
	      }
	   }
	});
// close the modal
/*$(function(){
	$('.close-button').click(function () {
		 $('#modal').hide();
		 $('html').css('overflow', 'auto');
	});
});*/
	// close the modal 
	$(".close-button").on("click",function(){
		   $('#modal').removeClass('show');
		   setTimeout(function() {
		      $("#modal").css('display','none');
		   }, 1)
		   $('body').css({'overflow':'auto', 'height':'100%'});
		});
});