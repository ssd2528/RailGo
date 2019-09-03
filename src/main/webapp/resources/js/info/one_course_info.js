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