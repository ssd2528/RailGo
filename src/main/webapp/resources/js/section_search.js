$(document).ready(function(){
	/***** 		section-search 		*****/
	// '어디로 가시나요' 클릭시 search-modal 보이게 하기
	$('.search-city').on('click', function(){ 
		$('.search-modal').addClass('show-modal'); 
		$('.search-text').focus();
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});
	// search-modal에서 x버튼 클릭시 modal 안보이게 하기
	$('.close-btn').on('click', function(){ 
		closeModal();
		$('.search-city').attr('class', 'search-city');
	});
	// search-modal 외 검정바탕 클릭시 modal 안보이게 하기
	window.onclick = function(event) {
		if (event.target == $('#search-modal')) {
			closeModal();
	   }
	}
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
			closeModal();
			infoLinkHandler(areaName);
		}
	});
	
	// search-modal에서 지역들 중 도시 클릭했을 때
	$('.region-city-item').on('click', function(){
		var areaName = $(this).text(); // 클릭한 도시명
		closeModal();
		infoLinkHandler(areaName);
	});
	
	if($('#areaName').val()!=undefined){
		$('.section-search').css('background', '#FFFFFF url("/img/info/'+$('#areaName').val()+'.jpg") no-repeat center center/cover');
	}
	
	var searchBed = $('.search-bed');
	var searchPlace = $('.search-hotplace');
	var searchFood = $('.search-food');
	// 숙박 hover시
	searchBed.hover(function(){
		$('.search-bed img').attr('src', '/img/main/bed_hover.png');
		$('.search-bed *').css('color', '#000000');
	},function(){
		$('.search-bed img').attr('src', '/img/main/bed.png');
		$('.search-bed *').css('color', '#595959');
	});
	// 관광명소 hover시 
	searchPlace.hover(function(){
		$('.search-hotplace img').attr('src', '/img/main/hotplace_hover.png');
		$('.search-hotplace *').css('color', '#000000');
	},function(){
		$('.search-hotplace img').attr('src', '/img/main/hotplace.png');
		$('.search-hotplace *').css('color', '#595959');
	});
	// 맛집 hover시
	searchFood.hover(function(){
		$('.search-food img').attr('src', '/img/main/food_hover.png');
		$('.search-food *').css('color', '#000000');
	},function(){
		$('.search-food img').attr('src', '/img/main/food.png');
		$('.search-food *').css('color', '#595959');
	});
	
	
	/***** 		section-main 		*****/
	/* article-course */
	var articleCourse = $('.article-course');
	$('.course-wrap').hide(); // 기본적으로 '초보자 코스추천' 부분은 보이지 않게 
	$('.show-course').on('click', function(){
		$(this).hide();
		$('.course-wrap').show();
	});
	$('.close-btn').on('click', function(){ articleCourse.hide(); });
	
});



// close Modal
function closeModal(){
	$('.search-modal').removeClass('show-modal');
	$('body').css({'overflow':'auto', 'height':'100%'});
}

// LinkHandler(areaName)
function infoLinkHandler(areaName){
	if($('.search-city').hasClass('search-bed')){
		location.href='../../info/accom/'+areaName;
	}else if($('.search-city').hasClass('search-hotplace')){
		location.href='../info/hotplace/'+areaName;
	}else if($('.search-city').hasClass('search-food')){
		location.href='../../info/food/'+areaName;
	}else {
		location.href='../../info/'+areaName;
	}
	
}