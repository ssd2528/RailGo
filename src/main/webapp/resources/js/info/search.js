$(document).ready(function(){
	var keyword = $('#keyword').val();
	var currentPage = $('#currentPage').val();
	
	// category-tab 클릭 시
	$('.hotplace-tab').on('click', function(){
		searchFilter(keyword, '관광명소', currentPage);
		$('.category-tab').removeClass('selected-tab'); $(this).addClass('selected-tab');
	});
	$('.accom-tab').on('click', function(){
		searchFilter(keyword, '숙박', currentPage);
		$('.category-tab').removeClass('selected-tab'); $(this).addClass('selected-tab');
	});
	$('.food-tab').on('click', function(){
		searchFilter(keyword, '맛집', currentPage);
		$('.category-tab').removeClass('selected-tab'); $(this).addClass('selected-tab');
	});
	
	
	// pagination 클릭 시 
	$(document).on('click', '.pagination li', function(){
		var clickedNo = $(this).val(); console.log(clickedNo);
		var category = $('#category').val(); console.log(category);
		searchFilter(keyword, category, clickedNo);
		$('.pagination li').removeClass('selected'); $(this).addClass('selected');
	});
	
	
	$(document).on('click', '.result-item-box', function(){
		$(this).find('.detailLinkForm').submit();
	});
});


function searchFilter(keyword, category, currentPage){
	console.log(keyword + ' / ' + category + ' / ' + currentPage);
	if(currentPage==undefined) currentPage=1;
	console.log(keyword + ' / ' + category + ' / ' + currentPage);
	$.ajax({
		url:'/search/filter',
		type:'post',
		data:{keyword:keyword, category:category, currentPage:currentPage},
		dataType:'html',
		success:function(data){
			$('#selected-category-result-inner-box').remove();
			$('#selected-category-result-box').html(data);
		},
		error:function(error){
			console.log('error :  ' + error);
			alert('페이지를 로드하는 중 문제가 발생했습니다.');
			return false;
		}
	});
}


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