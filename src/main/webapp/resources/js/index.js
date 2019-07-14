$(document).ready(function(){
	// '어디로 가시나요' 클릭시 search-modal 보이게 하기
	$('.search-city').on('click', function(){ $('.search-modal').css('display', 'block'); });
	
	// search-modal에서 x버튼 클릭시 modal 안보이게 하기
	$('.closeBtn').on('click', function(){ $('.search-modal').css('display', 'none'); });
	
	// search-modal 외 검정바탕 클릭시 modal 안보이게 하기
	$('html').on('click', function(e){
		if(e.target.class === "search-modal") {
			$('.search-modal').css('display', 'none');
		}
	});
	
	
	// 지역별 중 하나 클릭 시 하위 도시들 보이게 하기 
	$('.region-item').bind('click change',function(){
		$(this).children('.region-city-list').toggleClass("show-city-list");
		$(this).children('.toggle-btn').text(
				$(this).children('.toggle-btn').text() == '<' ? '>' : '<'
		);
	});
});