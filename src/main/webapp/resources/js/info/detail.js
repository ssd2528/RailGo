$(document).ready(function() { 
	$('#example').barrating({
		theme: 'fontawesome-stars' 
	});
	$('#example').barrating({
		theme: 'fontawesome-stars' 
		, initialRating: 1
	});
	$('#example').barrating({ 
		theme: 'fontawesome-stars' 
		, onSelect: function(value, text, event){ 
			// 별점 클릭 후 처리는 여기서 코드 
			// 선택한 별점 값을 value로 받음 
		} 
	});
});