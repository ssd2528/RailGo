$(document).ready(function(){
	/***** 		section-search 		*****/
	// '어디로 가시나요' 클릭시 search-modal 보이게 하기
	$('.search-city').on('click',function(){
		   $('.search-modal').css('display','flex');
		   
		   setTimeout(function() {
		      $(".search-modal").addClass('show-modal');
		      $('.search-text').focus();
		   }, 1);
		   $('body').css({'overflow':'hidden', 'height':'100%'});
	});	
	
	// search-modal에서 x버튼 클릭시 modal 안보이게 하기
	$('.close-btn').on('click', function(){ 
		$('.search-modal').removeClass('show-modal'); 
		$('body').css({'overflow':'auto', 'height':'100%'});
        setTimeout(function() {
            $('.search-modal').css('display','none');
         }, 50);
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

$(document).ready(function(){
	$('#infoLinkForm').on('submit', function(){
		$('.wrap-loading').css('display', 'block');
		return true;
	});
});
// LinkHandler(areaName)
function infoLinkHandler(areaName){
	if($('.search-city').hasClass('search-bed')){
		$('#infoLinkForm').attr('action','/info/accom/'+areaName);
		$('#infoLinkForm').submit();
		//location.href='../../info/accom/'+areaName;
	}else if($('.search-city').hasClass('search-hotplace')){
		$('#infoLinkForm').attr('action','/info/hotplace/'+areaName);
		$('#infoLinkForm').submit();
		//location.href='../info/hotplace/'+areaName;
	}else if($('.search-city').hasClass('search-food')){
		$('#infoLinkForm').attr('action','/info/food/'+areaName);
		$('#infoLinkForm').submit();
		//location.href='../../info/food/'+areaName;
	}else {
		$('#infoLinkForm').attr('action','/info/'+areaName);
		$('#infoLinkForm').submit();
		//location.href='../../info/'+areaName;
	}
}