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
	// 일정만들기 버튼 클릭시 모달창 오픈
	$('.planner-info-btn-wrapper').children('.create-plan-btn').on("click",function(){
		   $("#modal-wrapper").css('display','flex');
		   setTimeout(function() {
		      $("#modal-wrapper").addClass('open');
		   }, 1)
		   $('body').css({'overflow':'hidden', 'height':'100%'});
		});
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
	   if($('#modal-wrapper').hasClass('open')){ // site 라는 특정영역이 열려있을 경우
	      if(!$('#modal-wrapper').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
	         $('#modal-wrapper').removeClass('open');
	         $('body').css({'overflow':'auto', 'height':'100%'});
	         setTimeout(function() {
	            $('#modal-wrapper').css('display','none');
	         }, 50)
	      }
	   }
	});
	// 모달창 닫기 버튼 클릭시 modal 창 닫기
	$("#plan-option-close-btn").on("click",function(){
	   $('#modal-wrapper').removeClass('open');
	   setTimeout(function() {
	      $("#modal-wrapper").css('display','none');
	   }, 1)
	   $('body').css({'overflow':'auto', 'height':'100%'});
	});
	// 티켓 선택 했을때 박스 시그니처 색으로 칠하기
	$('#plan-option-days').children().click(function(){
		$('#plan-option-days').children().css('background-color', 'white');
		$('#plan-option-days').children().css('color', '#7f7f7f');
		$(this).css('background-color', '#009ce9');
		$(this).css('color', 'white');
		$('#datepicker').attr('disabled',false);
		$('#datepicker').val('첫 날');
		$('#last-option-days').text('마지막 날');
	});
	//인원수 체크
	$('.plan-option-people').children('.plan-option-plus,.plan-option-minus').click(function(){
		if($(this).attr('class') ==='plan-option-plus'){
			$('.plan-option-number').text(parseInt($('.plan-option-number').text()) + 1);
		}else{
			if(parseInt($('.plan-option-number').text()) > 1){
				$('.plan-option-number').text(parseInt($('.plan-option-number').text()) - 1);
			}
		}
		if(parseInt($('.plan-option-number').text()) >= 2){
			$('.plan-option-member-wrapper').show();
		}else{
			$('.plan-option-member-wrapper').hide();
		}
	});
	
	//달력 버튼 누를 시 달력 Jquery UI 생성
	$('#datepicker').datetimepicker({
		format:'Y/m/d',
		minDate:0,
		timepicker:false,
		onSelectDate:function(dateText,inst){ 
			$('#last-option-days').text('');
			var year = dateText.getFullYear();
			var month = dateText.getMonth() + 1;
			var day = dateText.getDate();
			var ymd = new Date(year,month,day);
			if($('#third-days-option').css('color') === 'rgb(255, 255, 255)'){
				ymd.setDate(ymd.getDate() + 2);
				var mm = ymd.getMonth(); mm = (mm < 10) ? '0' + mm : mm;
				var dd = ymd.getDate(); dd = (dd < 10) ? '0' + dd : dd;	
			}else if($('#fifth-days-option').css('color') === 'rgb(255, 255, 255)'){
				ymd.setDate(ymd.getDate() + 4);
				var mm = ymd.getMonth(); mm = (mm < 10) ? '0' + mm : mm;
				var dd = ymd.getDate(); dd = (dd < 10) ? '0' + dd : dd;	
			}else{
				ymd.setDate(ymd.getDate() + 6);
				var mm = ymd.getMonth(); mm = (mm < 10) ? '0' + mm : mm;
				var dd = ymd.getDate(); dd = (dd < 10) ? '0' + dd : dd;		
			}
			$('#last-option-days').text( ymd.getFullYear() +'/'+ mm +'/'+ dd);
		}
	});
	
});