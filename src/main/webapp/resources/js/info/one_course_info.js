$(document).ready(function() {
	var firstimage = $('#firstimage-url').val();
	$('.firstimage').css('background', '#FFFFFF url("'+firstimage+'") no-repeat center center/cover');
	
	$('.course-item').hover(function(){
		$(this).find('.course-item-detail-btn').css('display','block');
	}, function(){
		$(this).find('.course-item-detail-btn').css('display','none');
	});
	
	// 코스 중 상세 코스 하나 클릭 시 상세 페이지(detail.jsp)로 이동
	$('.course-item').on('click', function(){
		$(this).find('.detailForm').submit();
	});
	
	// 다른 일일코스 중 추천코스 하나 클릭 시 클릭한 추천 코스에 대한 페이지(one_course_info.jsp)로 이동
	$('.recommend-item').on('click', function(){
		$(this).find('.courseForm').submit();
	});
});


//Search Keyword
$(document).ready(function(){
	$('#search-keyword').keydown(function(e){
		if(e.keyCode==13){
			var keyword = $('#search-keyword').val();
			$('#searchForm').attr('action', '/search/'+keyword);
			$('#searchForm').submit();
		}
	});
	
	$('.fa-search').on('click', function(){
		var keyword = $('#search-keyword').val();
		$('#searchForm').attr('action', '/search/'+keyword);
		$('#searchForm').submit();
	});
});

// course-item hover 이벤트
$(document).ready(function(){
	$(document).on('mouseenter','.course-item',function(){
		var test = $(this).find('.course-item-subdetailimg').attr('src');
		$(this).find('.course-item-subname').css('text-decoration', 'underline');
		if(test!='/img/default.png'){
			$(this).find('.course-item-subdetailimg').css('-webkit-transform', 'scale(1.1)');
		}
	});
	$(document).on('mouseleave','.course-item',function(){
		$(this).find('.course-item-subname').css('text-decoration', 'none');
		$(this).find('.course-item-subdetailimg').css('-webkit-transform', 'scale(1)');
	});
});	

// recommend-item hover 이벤트
$(document).ready(function(){
	$(document).on('mouseenter','.recommend-item',function(){
		var test = $(this).find('.recommend-img').attr('src');
		$(this).find('.recommend-title').css('text-decoration', 'underline');
		if(test!='/img/default.png'){
			$(this).find('.recommend-img img').css('-webkit-transform', 'scale(1.1)');
		}
	});
	$(document).on('mouseleave','.recommend-item',function(){
		$(this).find('.recommend-title').css('text-decoration', 'none');
		$(this).find('.recommend-img img').css('-webkit-transform', 'scale(1)');
	});
});