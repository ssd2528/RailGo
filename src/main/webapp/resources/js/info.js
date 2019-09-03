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