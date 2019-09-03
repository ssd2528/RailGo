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
	for(var plannerItem of PlannerList) {
		var planner = plannerItem.planner;				
		var planner_subject = planner.subject;	// 일정 제목	
		
		var plannerDate = plannerItem.plannerDate;
		var planner_course = ''; // 일정에 대한 코스 가공
		for(var i in plannerDate) {
			if(plannerDate[i].region == '지역을 선택하세요.') plannerDate[i].region = (parseInt(i)+1)+'일차';
			
			if(plannerDate[plannerDate.length-1] == plannerDate[i]) planner_course += plannerDate[i].region;
			else planner_course += plannerDate[i].region + ' - ';
		}
		var plannerDate_tripdateFirst = plannerDate[0].trip_date;	// 첫번째 날짜
		var plannerDate_tripdateLast = plannerDate[plannerDate.length-1].trip_date; // 마지막 날짜
		var plannerDate_length = plannerDate.length; // 총 일정 수
		
		var plannerSchedule = plannerItem.plannerSchedule;	
		var plannerSchedule_firstImage = plannerSchedule[0].content_img;
		
		var html = '<li class="concept-item">'
				+	'<div class="concept-img" style="background:#d9d9d9  url('+plannerSchedule_firstImage+') no-repeat center center/cover; width:100%; height:150px;"></div>'
				+	'<div class="concept-detail">'
				+		'<div class="concept-detail-subject">'+planner_subject+'</div>'
				+		'<div class="concept-detail-region">'+planner_course+'</div>'
				+		'<div class="concept-detail-tripdate">'+plannerDate_tripdateFirst + ' ~ ' + plannerDate_tripdateLast+ ' (' + plannerDate_length+'일 코스)</div>'
				+	'</div>'
				+'</li>';
		$(conceptType).append(html);
	}
	
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