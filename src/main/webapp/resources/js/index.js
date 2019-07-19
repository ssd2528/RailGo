$(document).ready(function(){
	
	// '어디로 가시나요' 클릭시 search-modal 보이게 하기
	$('.search-city').on('click', function(){ 
		$('.search-modal').addClass('show-modal'); 
		$('.search-text').focus();
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});
	// search-modal에서 x버튼 클릭시 modal 안보이게 하기
	$('.close-btn').on('click', function(){ 
		$('.search-modal').removeClass('show-modal'); 
		$('body').css({'overflow':'auto', 'height':'100%'});
	});
	// search-modal 외 검정바탕 클릭시 modal 안보이게 하기
	window.onclick = function(event) {
		if (event.target == $('#search-modal')) {
		  $('.search-modal').removeClass('show-modal'); 
          $('body').css({'overflow':'auto', 'height':'100%'});
	   }
	}
	// 지역별 중 하나 클릭 시 하위 도시들 보이게 하기 
	$('.region-item').bind('click change',function(){
		$(this).children('.region-city-list').toggleClass("show-city-list");
		$('.region-item').not(this).toggleClass("show-region-item");
		$(this).children('.toggle-btn').text(
				$(this).children('.toggle-btn').text() == '<' ? '>' : '<'
		);
	});
});