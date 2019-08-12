$(document).ready(function(){
<<<<<<< HEAD
	if(self.name=='parent') {
		self.name='main';
		self.location.reload();
	}
	
	$('.search-bed').on('click', function(){
		showModal('search-bed');
	});
	$('.search-hotplace').on('click', function(){
		showModal('search-hotplace');
	});
	$('.search-food').on('click', function(){
		showModal('search-food');
=======
	
	// '어디로 가시나요' 클릭시 search-modal 보이게 하기
	$('.search-city').on("click",function(){
		   $(".search-modal").css('display','flex');
		   setTimeout(function() {
		      $(".search-modal").addClass('show-modal');
		      $('.search-text').focus();
		   }, 1)
		   $('body').css({'overflow':'hidden', 'height':'100%'});
	});	
	// search-modal에서 x버튼 클릭시 modal 안보이게 하기
	$('.close-btn').on('click', function(){ 
		$('.search-modal').removeClass('show-modal'); 
		$('body').css({'overflow':'auto', 'height':'100%'});
        setTimeout(function() {
            $('.search-modal').css('display','none');
         }, 50)
	});
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
	   if($('.search-modal').hasClass('show-modal')){ // site 라는 특정영역이 열려있을 경우
	      if(!$('.search-modal').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
	         $('.search-modal').removeClass('show-modal');
	         $('body').css({'overflow':'auto', 'height':'100%'});
	         setTimeout(function() {
	            $('.search-modal').css('display','none');
	         }, 50)
	      }
	   }
	});
	// 지역별 중 하나 클릭 시 하위 도시들 보이게 하기 
	$('.region-item').bind('click change',function(){
		if( $(this).children('.region-city-list').length > 0 ) {
			$(this).children('.region-city-list').toggleClass("show-city-list");
			$('.region-item').not(this).toggleClass("show-region-item");
			$(this).children('.toggle-btn').text(
					$(this).children('.toggle-btn').text() == '<' ? '>' : '<'
			);
		} else {
			var areaName = $(this).text(); // 클릭한 도시명
			location.href='info/'+areaName;
		}
>>>>>>> branch 'master' of https://github.com/JaeHyeonKim19/RailGo
	});
	
});


function showModal(className){
	$('.search-modal').addClass('show-modal'); 
	$('.search-text').focus();
	$('body').css({'overflow':'hidden', 'height':'100%'});
	
	$('.search-city').addClass(className);
}