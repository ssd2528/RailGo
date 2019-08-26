$(document).ready(function(){
	var alarm = $('.alarm-img');
	var sns = $('.sns-img'); 
	var planner = $('.planner-img');
	var member = $('.member-img');	
	
	// sns 항시 이미지 변환
	$('.sns-img img').attr('src', '../img/header/sns_clicked.png');
	sns.css('color', '#009CE9');
	sns.closest('li').css('border-bottom', 'solid 3px #009CE9');
	
	// planner hover시 이미지 변환 
	planner.hover(function(){
		$('.planner-img img').attr('src', '../img/header/planner_clicked.png');
		planner.css('color', '#009CE9');
	}, function(){
		$('.planner-img img').attr('src', '../img/header/planner.png');
		planner.css('color', 'black');
	});
	
	// 이미지 슬라이더
	$('.bxslider').bxSlider({
        speed: 500,  // 애니메이션 속도
        mode: 'horizontal', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
        pager: false,
        slideHeight: 500,
        infiniteLoop: false,
        hideControlOnEnd: true
    });
});