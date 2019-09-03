$(document).ready(function(){
	var alarm = $('.alarm-img');
	var sns = $('.sns-img'); 
	var planner = $('.planner-img');
	var member = $('.member-img');	
	
	// sns hover시 이미지 변환 
	sns.hover(function(){
		$('.sns-img img').attr('src', '../img/header/sns_clicked.png');
		sns.css('color', '#009CE9');
	}, function(){
		$('.sns-img img').attr('src', '../img/header/sns.png');
		sns.css('color', 'black');
		
	});
	
	// planner hover시 이미지 변환 
	planner.hover(function(){
		$('.planner-img img').attr('src', '../img/header/planner_clicked.png');
		planner.css('color', '#009CE9');
	}, function(){
		$('.planner-img img').attr('src', '../img/header/planner.png');
		planner.css('color', 'black');
	});
	
});

//페이지 로딩
$(window).on('load', function (){
	 $("#loading").fadeOut(500);
});