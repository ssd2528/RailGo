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
	var mem_code = $('.mem-code').children('input').val();
	if(mem_code === '' || mem_code === null){
		mem_code = 'guest';
	}
	// planner 항시 이미지 변환(헤더에 있는 플래너 이미지 호버상태 유지)
	$('.planner-img img').attr('src', '../img/header/planner_clicked.png');
	planner.css('color', '#009CE9');
	planner.closest('li').css({'border-bottom':'solid 3px #009CE9', 'padding-bottom':'3px'});
	
	loadScheduleOtherUsers(mem_code,'none','none','none');
	// sns hover시 이미지 변환 
	sns.hover(function(){
		$('.sns-img img').attr('src', '../img/header/sns_clicked.png');
		sns.css('color', '#009CE9');
	}, function(){
		$('.sns-img img').attr('src', '../img/header/sns.png');
		sns.css('color', 'black');
	});
	//하트 누를시 이미지 변환 -> ajax 추가 필요 
	$(document).on('click','.calendar-text-like',function(){
		if(mem_code === 'guest'){alert('로그인 후 이용 가능 서비스입니다.');}
		else{
			let src = $(this).children('.like-img').attr("src");
			let plan_code = $(this).attr('name');
			if(src == "../img/sns/heart.png"){	//좋아요하지 않은 일정을 클릭했을때
				$(this).children('.like-img').attr('src','../img/sns/heart_clicked.png');
				likeOtherPlannerSchedule(mem_code,plan_code,'like');
			}else{	//이미 좋아요 누른 일정을 또 클릭
				$(this).children('.like-img').attr('src','../img/sns/heart.png');
				likeOtherPlannerSchedule(mem_code,plan_code,'unlike');
			}
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
		}else{	//여행 테마 옵션중 하나 or 여러개 선택시
			$(themeTag).css('color','#7f7f7f');		
			$('#f-theme').children('.f-text').text(tagVal);
			$('#f-theme').show();	
		}
		$(this).css('color','#009CE9');	
		$('.paging-form').children('.start').val('1');
		$('.paging-form').children('.end').val('6');
		$('.calendar-lists-wrapper').children().remove();
		checkedFilterAndLoadList(mem_code);
	});
	// 선택된 필터 옵션 x 클릭시 버튼 없애기 or 파란색 칠해진 옵션 다시 복구하기 
	$('#f-city,#f-days,#f-period,#f-theme').click(function(){
		var id = $(this).attr('id');
		if(id === 'f-city'){
			$(cityTag).css('color','#7f7f7f');
		}else if(id === 'f-days'){
			$(daysTag).css('color','#7f7f7f');
		}else{
			$(themeTag).css('color','#7f7f7f');		
		}
		$(this).hide();
		$(this).find('span').text('');
		$('.paging-form').children('.start').val('1');
		$('.paging-form').children('.end').val('6');
		$('.calendar-lists-wrapper').children().remove();
		checkedFilterAndLoadList(mem_code);
	});
	// 일정만들기 버튼 클릭시 모달창 오픈 - 로그인 되어 있는 상태 시 
	$('.planner-info-btn-wrapper').children('#create-plan-btn').on("click",function(){
		$("#modal-wrapper").css('display','flex');
		   setTimeout(function() {
		      $("#modal-wrapper").addClass('open');
		 }, 1)
		 $('body').css({'overflow':'hidden', 'height':'100%'});
	});
	// 일정만들기 버튼 클릭시 모달창 오픈 - 로그인 되어 있지 않은 상태 시 
	$('.planner-info-btn-wrapper').children('#before-create-plan-btn').on("click",function(){
		$('#login-modal').css('display', 'block');
		$('#login-modal .modal-title').text('내일고 로그인');
		$('label.error').css('display', 'none');
		$('.error_msg').css('display', 'none');
		$('.sign-in-group').show();
		$('.line').show();
		$('.sign-up').hide();
		$('.password-find-group').hide();
		$('html').css('overflow', 'hidden');
	});
	
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
	   if($('#modal-wrapper').hasClass('open')){ // site 라는 특정영역이 열려있을 경우
	      if(!$('#modal-wrapper').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
	    	  removeModal();
	      }
	   }
	});
	// 모달창 닫기 버튼 클릭시 modal 창 닫기
	$("#plan-option-close-btn").on('click',function(){
		removeModal();
	});
	function removeModal(){
		$('#modal-wrapper').removeClass('open');
		setTimeout(function() {
			$("#modal-wrapper").css('display','none');
		}, 1)
		$('body').css({'overflow':'auto', 'height':'100%'});
	}
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
			var month = dateText.getMonth();
			var day = dateText.getDate();
			var ymd = new Date(year,month,day);
			if($('#third-days-option').css('color') === 'rgb(255, 255, 255)'){
				ymd.setDate(ymd.getDate() + 2);
				var mm = ymd.getMonth()+1; mm = (mm < 10) ? '0' + mm : mm;
				var dd = ymd.getDate(); dd = (dd < 10) ? '0' + dd : dd;	
			}else if($('#fifth-days-option').css('color') === 'rgb(255, 255, 255)'){
				ymd.setDate(ymd.getDate() + 4);
				var mm = ymd.getMonth()+1; mm = (mm < 10) ? '0' + mm : mm;
				var dd = ymd.getDate(); dd = (dd < 10) ? '0' + dd : dd;	
			}else{
				ymd.setDate(ymd.getDate() + 6);
				var mm = ymd.getMonth()+1; mm = (mm < 10) ? '0' + mm : mm;
				var dd = ymd.getDate(); dd = (dd < 10) ? '0' + dd : dd;		
			}
			$('#last-option-days').text( ymd.getFullYear() +'/'+ mm  +'/'+ dd);
		}
	});
	//모달창 확인 버튼 누를 시 조건 확인
	$('#plan-option-ok-btn').on('click',function(){
		var color = 'rgb(0, 156, 233)';	//rgb(0, 156, 233) - 시그니처 블루 색
		var tt = $('#third-days-option').css('background-color');
		var ft = $('#fifth-days-option').css('background-color'); 
		var st = $('#seventh-days-option').css('background-color');
		var cal = $('#last-option-days').text();
		var ticket;
		var startday;
		if(tt != color && ft != color && st != color){
			alert('티켓을 선택해주세요.');
		}else{
			if(tt === color){ticket = '3';}
			else if(ft === color){ticket = '5';}
			else if(st === color){ticket = '7';}
			if(cal === '마지막 날'){alert('날짜를 선택해주세요.');
			}else{
				startday = $('#datepicker').val();
				$('#item').attr('value','new');
				$('#tickets').attr('value',ticket);
				$('#startday').attr('value',startday);
				$('#plancode').attr('value','new');		
				$('#plan-form').submit();
			}
		}
	});
	//나의 일정 보기 버튼 클릭 이벤트
	$('.planner-info-btn-wrapper').children('.my-plan-btn').click(function(){
		let mem = $('.mem-code').children('input').val();
		if(mem === '' || mem === null){
			$('#login-modal').css('display', 'block');
			$('#login-modal .modal-title').text('내일고 로그인');
			$('label.error').css('display', 'none');
			$('.error_msg').css('display', 'none');
			$('.sign-in-group').show();
			$('.line').show();
			$('.sign-up').hide();
			$('.password-find-group').hide();
			$('html').css('overflow', 'hidden');
		}else{
			window.location.href = '../member/schedule';
		}
	});
	$('.more-btn').click(function(){
		checkedFilterAndLoadList(mem_code);
	});
});
// 다른 내일러 일정 좋아요 and 좋아요 취소 기능 메소드
function likeOtherPlannerSchedule(mem_code,plan_code,likeOrUnlike){
	let param = {"mem_code":mem_code , "plan_code":plan_code, "likeOrUnlike":likeOrUnlike};
	let state;
	$.ajax({
		type:'post',
		async:false,
		url:'planner/likeOrUnlike',
		dataType:'text',
		contentType:'application/json',
		data:JSON.stringify(param),
		success: function(data){
			console.log(data);
			state = data;
		},
		eror: function(data){
			console.log(data);
		}
	});
	if(state == 'likeState' || state == 'unlikeState'){
		return state;
	}
}
// 필터 옵션 선택이나 선택된 필터 옵션 x 눌렀을때 필터옵션들 검사해서 내일러 스케쥴 리스트 출력해주는 메소드
function checkedFilterAndLoadList(mem_code){
	let cityfilter;
	let datefilter;
	let themefilter;
	if($('#f-city').css('display') === 'block'){
		cityfilter = $('#f-city').children('.f-text').text();
	}else{cityfilter = 'none';}
	if($('#f-days').css('display') === 'block'){
		datefilter = $('#f-days').children('.f-text').text();
	}else{datefilter = 'none';}
	if($('#f-theme').css('display') === 'block'){
		themefilter = $('#f-theme').children('.f-text').text();
		if(themefilter === '나홀로'){themefilter = 'theme-solo';}
		else if(themefilter === '단둘이'){themefilter = 'theme-duo';}
		else if(themefilter === '셋이상'){themefilter = 'theme-squad';}
		else if(themefilter === '먹방'){themefilter = 'theme-eating';}
		else{themefilter = 'theme-healing'}
	}else{
		themefilter = 'none';
	}
	console.log('1 :'+cityfilter+' 2: '+datefilter+' 3: '+themefilter);
	loadScheduleOtherUsers(mem_code,cityfilter,datefilter,themefilter);
}
function loadScheduleOtherUsers(mem_code,city,date,theme){
	let start = $('.paging-form').children('.start').val();
	let end = $('.paging-form').children('.end').val();
	let param = {'mem_code':mem_code,'start':start,'end':end, 'city':city, 'date':date, 'theme':theme};
	console.log(param);
	$.ajax({
		type : 'post',
		async : false,
		url: 'planner/getOtherUsersScheduleList',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			console.log(data);
			if(data === null || data === 'fail'){
				if($('.calendar-lists-wrapper').children().length === 0){
					$('.calendar-lists-wrapper').append('<div class="calendar-lists" style="margin-left:33.6%;border:none;font-size:22px;color:#464545">'
						+'<img style="margin-top:50px;width:200px;height:200px;" src="../img/planner/dg_warning.png"><br>'+'다른 내일러들의 일정이 없습니다.'+'</div>');
				}
			}else{
				 $('.paging-form').children('.start').val(parseInt(start)+6);
				 $('.paging-form').children('.end').val(parseInt(end)+6);
				for(let item of data){
					let DayScheduleArr = new Array();
					let name = getNameOfSchedule(item.planner.mem_code);
					let img = '../img/default.png';
					let hashTagText;
					let course = new String();
					let sortDate = new Array();
					let startdate;
					let enddate;
					if(item.planner.hash_tag === 'theme-solo'){hashTagText = '#나홀로';}
					else if(item.planner.hash_tag === 'theme-duo'){hashTagText = '#단둘이';}
					else if(item.planner.hash_tag === 'theme-squad'){hashTagText = '#셋이상';}
					else if(item.planner.hash_tag === 'theme-eating'){hashTagText = '#먹방';}
					else {hashTagText = '#힐링';}
					let likeOrUnlikeState = likeOtherPlannerSchedule(mem_code,item.planner.plan_code,'getlikeOrNot');
					let likeImg;
					console.log(likeOrUnlikeState);
					if(likeOrUnlikeState === 'likeState'){
						likeImg = '../img/sns/heart_clicked.png';
					}else{
						likeImg = '../img/sns/heart.png';
					}
					/*for(let i of item.plannerDate){
						if(i.region === '지역을 선택하세요.'){continue;}
						course += (i.region+' - ');			
					}
					if(course.length >= 1){course = course.substr(0,course.length-3);}
					else{course = '일정이 없습니다.';}*/
					
					for(let i of item.plannerSchedule){
						if(i.content_img !== 'undefined' && i.content_img !== null && i.content_img !== ''){img = i.content_img; break;}
						else{img = '../img/default.png';}
					}
					for(let i of item.plannerSchedule){
						let str = i.days + ':' + i.content_name;
						DayScheduleArr.push(str);
					}
					for(let day of item.plannerDate){
						sortDate.push(day.trip_date);
					}
					sortDate.sort();
					startdate = sortDate[0];
					for(let index of sortDate){
						enddate = index;
					}
					//console.log(startdate+' ~ '+enddate);
					$('.calendar-lists-wrapper').append('<div class="calendar-lists">'
							+'<form class="schedule-list">'
							+'<input type="hidden" class="startdate" value="'+startdate+'">'
							+'<input type="hidden" id="'+item.planner.plan_code+'" class="item" value="">'
							+'<input type="hidden" id="" class="enddate" value="'+enddate+'">'
							+'<input type="hidden" id="" class="day-schedule-str" value="'+DayScheduleArr+'">'
							+'<input type="hidden" id="" class="tickets" value="'+item.plannerDate.length+'">'
							+'<input type="hidden" id="" class="plancode" value="'+item.planner.plan_code+'">'
							+'</form>'
							+'<div class="calendar-map" style="background: #FFFFFF url(\''+img+'\') no-repeat center center/cover;"></div>'
							+'<div class="calendar-text-wrapper">'
							+'<div class="calendar-text-subject">'+item.planner.subject+'</div>'
							+'<div class="calendar-text-tag">'+hashTagText+'</div>'
							+'<div class="calendar-text-writer">'+name+'</div>'
							+'<div class="calendar-like-number-wrapper">'
							+'<div class="calendar-text-like" name="'+item.planner.plan_code+'" ><img class="like-img" src="'+likeImg+'"></img></div>'
							+'</div>'
							+'</div>'
							+'</div>'
					);
					$('.schedule-list').children('#'+item.planner.plan_code).attr('value',JSON.stringify(item));
				}
				let items = $('.schedule-list').children('.item');
			}
		},
		error : function(data, status, error) {
			if($('.calendar-lists-wrapper').children().length === 0){
			$('.calendar-lists-wrapper').append('<div class="calendar-lists" style="margin-left:33.6%;border:none;font-size:22px;color:#464545"">'
					+'<img style="margin-top:50px;width:200px;height:200px;" src="../img/planner/dg_warning.png"><br>'+'다른 내일러들의 일정이 없습니다.'+'</div>');
			}
			console.log('status : '+status);
			console.log(data);
		}
	});
}
//다른 내일러 일정 목록 호출시 각 일정의 이름을 불러오는 메소드
function getNameOfSchedule(mem_code){
	let param = {'mem_code' : mem_code};
	let returnName;
	$.ajax({
		type : 'post',
		async : false,
		url: 'planner/getUserNameScheduleList',
		dataType : 'text',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			returnName = data;
		},
		error : function(data, status, error) {
			console.log('status : '+status);
			console.log(data);
			returnName = '이름을 발견하지 못했습니다.';
		}
	});
	return returnName;
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