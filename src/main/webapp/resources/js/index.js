$(document).ready(function(){
	if(self.name=='parent') {
		self.name='main';
		self.location.reload();
	}
	
	$('.search-bed').on('click', function(){
		showModal('search-bed');
	});
	$('.search-hotplace').on('click', function(){
		showModal('search-hotplace');
	});
	$('.search-food').on('click', function(){
		showModal('search-food');
	});
});
function showModal(className){
	$('.search-modal').css('display','flex');
	setTimeout(function() {
	      $(".search-modal").addClass('show-modal');
	      $('.search-text').focus();
	   }, 1)
	$('body').css({'overflow':'hidden', 'height':'100%'});
	
	$('.search-city').addClass(className);
}


// Search Keyword
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


// 나홀로 컨셉 추천 부분
$(document).ready(function(){
	// 컨셉1. 나홀로 떠나는 여행
	var plannerListBySolo = $('#plannerListBySolo').val();
	plannerListBySolo = JSON.parse(plannerListBySolo);
	$('#plannerListBySolo').val(plannerListBySolo);
	insertConceptList(plannerListBySolo, '.concept-solo');	
	
	// 컨셉2. 맛있는 음식들과 떠나는 여행
	var plannerListByEating = $('#plannerListByEating').val();
	plannerListByEating = JSON.parse(plannerListByEating);
	$('#plannerListByEating').val(plannerListByEating);
	insertConceptList(plannerListByEating, '.concept-eating');
});

function insertConceptList(PlannerList, conceptType) {
	let count=0;
	for(let plannerItem of PlannerList) {
		count = count+1;	if(count==4) return; // 3개만 뽑기 (과부하 걸릴 거 같긴한데.. shuffle로 랜덤하게 뽑기위해 ㅠ)
		
		var planner = plannerItem.planner;		
		var name = getNameOfSchedule(planner.mem_code);
		
		var plannerDate = plannerItem.plannerDate;
		var planner_course = ''; // 일정에 대한 코스 가공
		for(var i in plannerDate) {
			if(plannerDate[i].region == '지역을 선택하세요.') plannerDate[i].region = (parseInt(i)+1)+'일차';
			if(plannerDate[plannerDate.length-1] == plannerDate[i]) planner_course += plannerDate[i].region;
			else planner_course += plannerDate[i].region + ' - ';
		}

		var plannerSchedule = plannerItem.plannerSchedule;	
		var plannerSchedule_firstImage = plannerSchedule[0].content_img;
		let DayScheduleArr = new Array();
		for(let i of plannerSchedule){
			let str = i.days + ':' + i.content_name;
			DayScheduleArr.push(str);
		}
		
		var hashTagText; // 해시태그 가공
		if(conceptType == '.concept-solo') hashTagText='#나홀로';
		else if(conceptType == '.concept-eating') hashTagText='#먹방';
		
		var html = '<li class="concept-item">'
/*				+	'<div class="concept-img" style="background:#d9d9d9  url('+plannerSchedule_firstImage+') no-repeat center center/cover; width:100%; height:170px;"></div>'
				+	'<div class="concept-detail">'
				+		'<div class="concept-detail-subject">'+planner.subject+'</div>'
				+		'<div class="concept-detail-region">'+planner_course+'</div>'
				+		'<div class="concept-detail-tripdate">'+ plannerDate[0].trip_date + ' ~ ' + plannerDate[plannerDate.length-1].trip_date + ' (' + plannerDate.length +'일 코스)</div>'
				+	'</div>'*/
				
				+	'<form class="schedule-list">'
				+		'<input type="hidden" class="startdate" value="'+plannerDate[0].trip_date+'">'
				+		'<input type="hidden" id="'+planner.plan_code+'" class="item" value="">'
				+		'<input type="hidden" id="" class="enddate" value="'+plannerDate[plannerDate.length-1].trip_date+'">'
				+		'<input type="hidden" id="" class="day-schedule-str" value="'+DayScheduleArr+'">'
				+		'<input type="hidden" id="" class="tickets" value="'+plannerDate.length+'">'
				+		'<input type="hidden" id="" class="plancode" value="'+planner.plan_code+'">'
				+	'</form>'
				
				+	'<div class="calendar-map concept-img" style="background: #FFFFFF url(\''+plannerSchedule_firstImage+'\') no-repeat center center/cover; width:100%; height:170px;"></div>'
				+	'<div class="concept-detail">'
				+		'<div class="concept-detail-subject">'+planner.subject+'</div>'
				+		'<div class="concept-detail-region">'+planner_course+'</div>'
				+		'<div class="concept-detail-tripdate">'+ plannerDate[0].trip_date + ' ~ ' + plannerDate[plannerDate.length-1].trip_date + ' (' + plannerDate.length +'일 코스)</div>'
				+	'</div>'
				
				+	'<div class="calendar-text-wrapper" style="display:none">'
				+		'<div class="calendar-text-subject">'+planner.subject+'</div>'
				+		'<div class="calendar-text-tag">'+hashTagText+'</div>'
				+		'<div class="calendar-text-writer">'+name+'</div>'
				+		'<div class="calendar-like-number-wrapper">'
				+			'<div class="calendar-text-like" ><img class="like-img" src="../img/sns/heart.png"></img></div>'
				+		'</div>'
				+	'</div>'
				
				+'</li>';
		$(conceptType).append(html);
		$('#'+planner.plan_code).val(JSON.stringify(plannerItem));
	}
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


// bxSlider
$(document).ready(function(){
	// 이미지 슬라이더
	$('.bxslider').bxSlider({
        speed: 500,  // 애니메이션 속도
        mode: 'horizontal', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
        pager: false,
        slideHeight: 500,
        infiniteLoop: false,
        hideControlOnEnd: true
    });
	// SNS 상세보기
	$('.sns-content-modal').on('click', function(){
		var sns_code = $(this).closest('div.sns-content-item').find('input[name="sns_code"]').val();
		location.href='/sns/content?sns_code='+sns_code;
	});
	
	// 좋아요 버튼 누르기
	$(document).on('click','#sns-heart',function(){
		var mem_code = $('.reply-memCode').val();
		if(mem_code==''){
			alert('로그인을 해주세요.');
			return false;
		}
		
		console.log('좋아욧~');
		var sns_code = $(this).closest('div.sns-content-item').find('input[name="sns_code"]').val();
		var mem_code = $(this).closest('div.sns-content-item').find('input[name="mem_code"]').val();
		var like_count = $(this).closest('div.sns-content-item').find('.sns-heart-count');
		var count = +($(this).closest('div.sns-content-item').find('span.like-count').text());
		var check = '';
		
		// 좋아요 추가
		if($(this).attr('class') === 'sns-icon sns-heart'){
			$(this).attr('src', '/img/sns/heart_clicked.png');
			$(this).attr('class', 'sns-icon sns-heart-clicked');
			$(this).closest('div.sns-content-item').find('span.like-count').text(count+1);
			if((count+1)==1){
				$(like_count).css('display', 'block');
			}
			check = 'plus';
		// 좋아요 취소	
		}else{
			$(this).attr('src', '/img/sns/heart.png');
			$(this).attr('class', 'sns-icon sns-heart');
			$(this).closest('div.sns-content-item').find('span.like-count').text(count-1);
			if((count-1)==0){
				$(like_count).css('display', 'none');
			}
			check = 'minus';
		}
		formData = new FormData();
		formData.append('check', check);
		formData.append('sns_code', sns_code);
		formData.append('mem_code', mem_code);
		console.log(mem_code);
		console.log(sns_code);
		console.log(check);
		$.ajax({
			url:'/sns/snsLike',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType : 'text',
			success: function(data){
				console.log(data);
			}
		})
	});
});