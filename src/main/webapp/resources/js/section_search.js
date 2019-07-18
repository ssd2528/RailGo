$(document).ready(function(){
	/***** 		section-search 		*****/
	var searchAccom = $('.search-accom');
	var searchPlace = $('.search-place');
	var searchFood = $('.search-food');
	// 숙박 hover시
	searchAccom.hover(function(){
		$('.search-accom img').attr('src', '../img/main/bed_hover.png');
		$('.search-accom *').css('color', '#000000');
	},function(){
		$('.search-accom img').attr('src', '../img/main/bed.png');
		$('.search-accom *').css('color', '#595959');
	});
	// 관광명소 hover시 
	searchPlace.hover(function(){
		$('.search-place img').attr('src', '../img/main/hotplace_hover.png');
		$('.search-place *').css('color', '#000000');
	},function(){
		$('.search-place img').attr('src', '../img/main/hotplace.png');
		$('.search-place *').css('color', '#595959');
	});
	// 맛집 hover시
	searchFood.hover(function(){
		$('.search-food img').attr('src', '../img/main/food_hover.png');
		$('.search-food *').css('color', '#000000');
	},function(){
		$('.search-food img').attr('src', '../img/main/food.png');
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