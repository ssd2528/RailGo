$(document).ready(function(){
	var alarm = $('.alarm-img');
	var sns = $('.sns-img'); 
	var planner = $('.planner-img');
	var member = $('.member-img');	
	var tagVal;
	var tagParent;
	var src;
	var cityTag = '#capital-tag, #gangwon-tag, #choongchung-tag, #jeonla-tag, #gyeongsang-tag';
	var daysTag = '#third-day-tag,#fifth-day-tag,#seventh-day-tag';
	var periodTag = '#summer-tag,#winter-tag';
	var themeTag = '#solo-tag,#duo-tag,#squad-tag,#eatting-tag,#healing-tag';
	var fThemes = '#f-theme0,#f-theme1,#f-theme2,#f-theme3,#f-theme4';
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
	//하트 누를시 이미지 변환 -> ajax 추가 필요 
	$('.calendar-lists-wrapper').find('.calendar-text-like').click(function(){
		var src = $('.like-img').attr("src");
		if(src == "../img/sns/heart.png"){
			$('.calendar-text-like').children('.like-img').attr('src','../img/sns/heart_clicked.png');
		}else{
			$('.calendar-text-like').children('.like-img').attr('src','../img/sns/heart.png');
		}
	});
	// 필터 옵션 선택시 필터 row에 추가 -> ajax 추가 필요
	$(cityTag+','+daysTag+','+periodTag+','+themeTag).click(function(){
		tagVal = $(this).text();
		tagParent = $(this).closest('td').attr('id');
		
		if(tagParent === 'city-option'){	// 여행도시 옵션중 하나 선택시
			$(cityTag).css('color','#7f7f7f');	
			$('#f-city').children('.f-text').text(tagVal);
			$('#f-city').show();
		}else if(tagParent === 'days-option'){	//여행일 옵션중 하나 선택시
			$(daysTag).css('color','#7f7f7f');		
			$('#f-days').children('.f-text').text(tagVal);
			$('#f-days').show();	
		}else if(tagParent === 'period-option'){	//여행 시기 옵션중 하나 선택시
			$(periodTag).css('color','#7f7f7f');		
			$('#f-period').children('.f-text').text(tagVal);
			$('#f-period').show();
		}else{	//여행 테마 옵션중 하나 or 여러개 선택시
			if($(this).attr('id') === 'solo-tag'){
				$('#f-theme0').find('span').text(tagVal);
				$('#f-theme0').show();
			}else if($(this).attr('id') === 'duo-tag'){
				$('#f-theme1').find('span').text(tagVal);
				$('#f-theme1').show();
			}else if($(this).attr('id') === 'squad-tag'){
				$('#f-theme2').find('span').text(tagVal);
				$('#f-theme2').show();
			}else if($(this).attr('id') === 'eatting-tag'){
				$('#f-theme3').find('span').text(tagVal);
				$('#f-theme3').show();
			}else if($(this).attr('id') === 'healing-tag'){
				$('#f-theme4').find('span').text(tagVal);
				$('#f-theme4').show();
			}else{
				return false;
			}
		}
		$(this).css('color','#009CE9');	
	});
	// 선택된 필터 옵션 x 클릭시 버튼 없애기 or 파란색 칠해진 옵션 다시 복구하기 
	$('#f-city,#f-days,#f-period'+','+fThemes).click(function(){
		var id = $(this).attr('id');
		if(id === 'f-city'){
			$(cityTag).css('color','#7f7f7f');
		}else if(id === 'f-days'){
			$(daysTag).css('color','#7f7f7f');
		}else if(id === 'f-period'){
			$(periodTag).css('color','#7f7f7f');
		}else{
			var themeTxt = $(this).find('span').text();
			if(themeTxt === $('#solo-tag').text()){
				$('#solo-tag').css('color','#7f7f7f');
			}else if(themeTxt === $('#duo-tag').text()){
				$('#duo-tag').css('color','#7f7f7f');
			}else if(themeTxt === $('#squad-tag').text()){
				$('#squad-tag').css('color','#7f7f7f');
			}else if(themeTxt === $('#eatting-tag').text()){
				$('#eatting-tag').css('color','#7f7f7f');
			}else{
				$('#healing-tag').css('color','#7f7f7f');
			}			
		}
		$(this).hide();
		$(this).find('span').text('');
	});
});