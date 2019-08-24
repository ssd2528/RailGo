// 각 컨텐츠 클릭 시 detail.jsp로 이동
$(document).ready(function(){
	$('.food-item').click(function(){
		$(this).find('.detailForm').submit();
	});
	$('.bed-item').click(function(){
		$(this).find('.detailForm').submit();
	});
});