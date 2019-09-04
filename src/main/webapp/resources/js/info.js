// 각 컨텐츠 클릭 시 detail.jsp로 이동
$(document).ready(function(){
	$('.food-item').click(function(){
		$(this).find('.detailForm').submit();
	});
	$('.bed-item').click(function(){
		$(this).find('.detailForm').submit();
	});
});


$(document).ready(function(){
	// 일일 코스 추천 중, 컨텐츠 하나 클릭 시 상세코스정보 페이지(one_course_info.jsp)로 이동
	$('.one-course-item').on('click', function(){
		$(this).find('.courseForm').submit();
	});
});

// food-item hover 이벤트
$(document).ready(function(){
	$(document).on('mouseenter','.food-item',function(){
		$(this).find('.fname').css('text-decoration', 'underline');
		$(this).find('.food-img img').css('-webkit-transform', 'scale(1.1)');
	});
	$(document).on('mouseleave','.food-item',function(){
		$(this).find('.fname').css('text-decoration', 'none');
		$(this).find('.food-img img').css('-webkit-transform', 'scale(1)');
	});
});	

// bed-item hover 이벤트
$(document).ready(function(){
	$(document).on('mouseenter','.bed-item',function(){
		$(this).find('.bname').css('text-decoration', 'underline');
		$(this).find('.bed-img img').css('-webkit-transform', 'scale(1.1)');
	});
	$(document).on('mouseleave','.bed-item',function(){
		$(this).find('.bname').css('text-decoration', 'none');
		$(this).find('.bed-img img').css('-webkit-transform', 'scale(1)');
	});
});	

// course-item hover 이벤트
$(document).ready(function(){
	$(document).on('mouseenter','.one-course-item',function(){
		$(this).find('.one-course-title').css('text-decoration', 'underline');
	});
	$(document).on('mouseleave','.one-course-item',function(){
		$(this).find('.one-course-title').css('text-decoration', 'none');
	});
});	


//Search Keyword
$(document).ready(function(){
	$('#search-keyword').keydown(function(e){
		if(e.keyCode==13){
			var keyword = $('#search-keyword').val();
			$('#searchForm').attr('action', '../search/'+keyword);
			$('#searchForm').submit();
		}
	});
	
	$('.fa-search').on('click', function(){
		var keyword = $('#search-keyword').val();
		$('#searchForm').attr('action', '../search/'+keyword);
		$('#searchForm').submit();
	});
});