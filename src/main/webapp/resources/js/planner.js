$(document).ready(function(){
	var alarm = $('.alarm-img');
	var sns = $('.sns-img'); 
	var planner = $('.planner-img');
	var member = $('.member-img');	
	var tagName;
	var filterArr = new Array();
	// planner 항시 이미지 변환
	$('.planner-img img').attr('src', '../img/header/planner_clicked.png');
	planner.css('color', '#009CE9');
	
	// sns hover시 이미지 변환 
	sns.hover(function(){
		$('.sns-img img').attr('src', '../img/header/sns_clicked.png');
		sns.css('color', '#009CE9');
	}, function(){
		$('.sns-img img').attr('src', '../img/header/sns.png');
		sns.css('color', 'black');
	});
	// 필터 선택시 해당 필터 옵션 숨김 기능.
	$('#capital-tag, #gangwon-tag, #choongchung-tag, #jeonla-tag, #gyeongsang-tag').click(function(){
		tagName = this;
		$('#city').hide();
		$('#city-option').hide();
	});
	$('#third-day-tag,#fifth-day-tag,#seventh-day-tag').click(function(){
		tagName = this;
		$('#days').hide();
		$('#days-option').hide();
	});
	$('#summer-tag,#winter-tag').click(function(){
		tagName = this;
		$('#period').hide();
		$('#period-option').hide();
	});
	$('#solo-tag,#duo-tag,#squad-tag,#eatting-tag,#healing-tag').click(function(){
		tagName = this;
		$('#theme').hide();
		$('#theme-option').hide();
	});
});