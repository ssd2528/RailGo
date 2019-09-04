$(document).ready(function(){
	let mem_code = $('.uploadDiv').children('input').val();
	$('.schedule').css('border-bottom','4px solid #009CE9');
	$('.timeline').css('border-bottom','none');
	$('.timeline').css('font-weight','normal');
	$('.timeline').css('width','60px');
	$('.timeline').css('height','24px');
	$('.tab-list a').css('margin','0px 30px 0px 30px');
	init();
	
	//삭제 or 수정 버튼 클릭시 발생하는 이벤트
	$(document).on('click','.m-btn,.d-btn',function(){
		let funcName = $(this).attr('name');
		let plan_code = $(this).parent().parent().parent().parent().attr('name');
		if(funcName === 'insert'){
			insertScheduleItem(plan_code);
		}else if(funcName === 'delete'){
			deleteScheduleItem(plan_code);
		}else if(funcName === 'detail-view'){
			insertScheduleItem(plan_code);
		}else{
			likeOtherPlannerSchedule(mem_code,plan_code,'unlike');
		}
	});
	$('.my-schedule').click(function(){
		$('.schedule-lists').children('div').remove();
		$('.paging-form').children('.start').val('1');
		$('.paging-form').children('.end').val('6');
		$(this).css('color','#009CE9');
		$('.complete-schedule').css('color','black');
		$('.like-schedule').css('color','black');
		loadPlannedSchedule('insert');
	});
	$('.complete-schedule').click(function(){
		$('.schedule-lists').children('div').remove();
		$('.paging-form').children('.start').val('1');
		$('.paging-form').children('.end').val('6');
		$(this).css('color','#009CE9');
		$('.my-schedule').css('color','black');
		$('.like-schedule').css('color','black');
		loadPlannedSchedule('complete');
	});
	$('.like-schedule').click(function(){
		$('.schedule-lists').children('div').remove();
		$('.paging-form').children('.start').val('1');
		$('.paging-form').children('.end').val('6');
		$(this).css('color','#009CE9');
		$('.my-schedule').css('color','black');
		$('.complete-schedule').css('color','black');
		loadPlannedSchedule('like');
	});
	$('.more-btn').click(function(){
		let state;
		if($('.my-schedule').css('color') === 'rgb(0, 156, 233)'){
			state = 'insert';
		}else if ($('.complete-schedule').css('color') === 'rgb(0, 156, 233)'){
			state = 'complete';
		}else{
			state = 'like';
		}
		loadPlannedSchedule(state);
	});
	
	$(document).on('click','.like-img',function(){
		
	});
});
let ScheduleJsonItem = new Array();
let posting = 0;
//schedule 페이지 로드시 초기 설정 메소드
function init(){
	let mem_code = $('.uploadDiv').children('input').val();
	if(mem_code === '' || mem_code === null){window.location.replace("../");}
	loadPlannedSchedule('insert');
	getPosting(mem_code);
}
// 포스팅 갯수 메소드
function getPosting(mem_code){
	param = {'mem_code' : mem_code};
	$.ajax({
		type : 'post',
		async : true,
		url : '/member/schedule/getPosting',
		dataType : 'text',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data){
			posting = data;
			console.log(data);
			$('.posting').text(posting);
		},error : function(data){
			console.log(data);
		}
	});
}
//다른 내일러 일정 좋아요 and 좋아요 취소 기능 메소드
function likeOtherPlannerSchedule(mem_code,plan_code,likeOrUnlike){
	let param = {"mem_code":mem_code , "plan_code":plan_code, "likeOrUnlike":likeOrUnlike};
	let state;
	$.ajax({
		type:'post',
		async:false,
		url:'../planner/likeOrUnlike',
		dataType:'text',
		contentType:'application/json',
		data:JSON.stringify(param),
		success: function(data){
			console.log(data);
			alert('좋아요 취소가 완료되었습니다.');
			$('.schedule-lists').children('div').remove();
			$('.paging-form').children('.start').val('1');
			$('.paging-form').children('.end').val('6');
			loadPlannedSchedule('like');
		},
		eror: function(data){
			console.log(data);
		}
	});
}
// 해당 계정의 일정 목록들 불러오는 ajax 메소드
function loadPlannedSchedule(scheduleState){
	let start = $('.paging-form').children('.start').val();
	let end = $('.paging-form').children('.end').val();
	let mem_code = $('.uploadDiv').children('input').val();
	let name = $('.row2').children('.user-id').text();
	let param = {'mem_code':mem_code,'start':start,'end':end};
	let url;
	/* 여행 일정 없을때의 문장*/
	let str;
	if(scheduleState === 'insert'){
		str = '계획중인 여행 일정이 없습니다.'
		url = '/member/schedule/getScheduleList';
	}else if(scheduleState === 'complete'){
		str = '완성된 여행 일정이 없습니다.'
		url = '/member/schedule/getScheduleList';
	}else{
		str = '좋아요한 여행 일정이 없습니다.'
		url = '/member/schedule/getLikeScheduleList';
	}
	console.log(mem_code);
	$.ajax({
		type : 'post',
		async : true,
		url : url,
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			
			if(data === 'fail' || data === null){
				console.log($('.schedule-lists').children().length);
				if($('.schedule-lists').children().length === 0){
					$('.schedule-lists').css('margin-left','33%');
					$('.schedule-lists').append('<div class="dg_warning-div">'
							+ '<img class="dg_warning" src="../img/planner/dg_warning.png"><br>'
							+ '<a class="dg_warning-txt">'+str+'</a>'
							+'</div>');
					$('.more-btn').hide();
				}else{
					$('.more-btn').show();
				}
			}else{
				 $('.paging-form').children('.start').val(parseInt(start)+6);
				 $('.paging-form').children('.end').val(parseInt(end)+6);
				console.log(data);
				let items = data;
				
				for(let item of items){
					ScheduleJsonItem.push(item);
					let sortDate = new Array();
					let startdate;
					let lastdate;
					let thumbnailImg;
					let mBtnName;
					let mBtnText;
					let mBtnDelText;
					let appendHashTag;
					let hashTagText;
					let delOrUnlikeText;
					let margin;
					if(item.planner.hash_tag === 'none'){
						mBtnName = 'insert';
						mBtnText = '수정';
						appendHashTag = '';
					}else{
						if(scheduleState == 'like'){mBtnDelText = 'unlike';delOrUnlikeText = '좋아요 취소'; margin = 'margin-left: 130px;';}
						else{mBtnDelText='delete';delOrUnlikeText = '삭제'; margin = 'margin-left: 82px;';}
						//일정에 해시태그를 표시하기 위한 구별 코드
						if(item.planner.hash_tag === 'theme-solo'){hashTagText = '나홀로';}
						else if(item.planner.hash_tag === 'theme-duo'){hashTagText = '단둘이';}
						else if(item.planner.hash_tag === 'theme-squad'){hashTagText = '셋이상';}
						else if(item.planner.hash_tag === 'theme-eating'){hashTagText = '먹방';}
						else {hashTagText = '힐링';}
						mBtnName = 'detail-view';
						mBtnText = '일정 상세 보기';
						appendHashTag = '<div class="hash-tag" style="'+margin+'">'+'#'+hashTagText+'</div>';
					}
					//진행중 , 완료 일정 구분 하기 위한 코드
					if(scheduleState === 'insert' && item.planner.hash_tag !== 'none'){continue;}
					if(scheduleState === 'complete' && item.planner.hash_tag === 'none'){continue;}
					
					for(let day of item.plannerDate){
						sortDate.push(day.trip_date);
					}
					sortDate.sort();
					startdate = sortDate[0];
					for(let index of sortDate){
						lastdate = index;
					}
					if(item.plannerSchedule.length === 0){
						thumbnailImg = "../img/default.png";
					}else{
						thumbnailImg = item.plannerSchedule[0].content_img;
					}
					$('.schedule-lists').css('margin-left','0');
						$('.schedule-lists').append(
								'<div class="schedule-list" name="'+item.planner.plan_code+'">'
								+'<img class="schedule-list-img" src="'+thumbnailImg+'">'
								+'<div class="schedule-text-wrapper">'
								+'<div class="schedule-name">'+item.planner.subject+'</div>'
								+'<div class="schedule-date">('+startdate+' ~ '+lastdate+')</div>'
								+'<div class="name-wrapper">'
								+'<img class="schedule-userImg" border-radius src="'+$('#profile-img').attr('src')+'">'
								+'<div class="schedule-userName">'+name+'</div>'
								+'<div class="btn-wrapper">'
								+'<div class="m-btn" name="'+mBtnName+'" style="cursor:pointer;">'+mBtnText+'</div>'
								+'&nbsp;|&nbsp; <div class="d-btn" name="'+mBtnDelText+'" style="cursor:pointer;">'+delOrUnlikeText+'</div>'
								+ appendHashTag
								+'</div>'
								+'</div>'
								+'<div>'
						);
				}
				//$('.row2').children('.posting').text(posting);
				if($('.schedule-lists').children().length === 0){
					$('.schedule-lists').css('margin-left','33%');
					$('.schedule-lists').append('<div class="dg_warning-div">'
							+ '<img class="dg_warning" src="../img/planner/dg_warning.png"><br>'
							+ '<a class="dg_warning-txt">'+str+'</a>'
							+'</div>');
					$('.more-btn').hide();
				}else{
					$('.more-btn').show();
				}
			}
		},
		error : function(data, status, error) {
			console.log($('.schedule-lists').children().length);
			if($('.schedule-lists').children().length === 0){
				$('.schedule-lists').css('margin-left','33%');
				$('.schedule-lists').append('<div class="dg_warning-div">'
						+ '<img class="dg_warning" src="../img/planner/dg_warning.png"><br>'
						+ '<a class="dg_warning-txt">'+str+'</a>'
						+'</div>');
				$('.more-btn').hide();
			}else{
				$('.more-btn').show();
			}
			console.log(data);
		}
	});
}

//일정 수정 버튼 클릭시 수정 페이지로 이동하는 메소드
function insertScheduleItem(plan_code){
	for(let item of ScheduleJsonItem){
		if(plan_code === item.planner.plan_code){
			let sortD = new Array();
			for(let day of item.plannerDate){
				sortD.push(day.trip_date);
			}
			sortD.sort();
			let startday = sortD[0];
			$('#item').attr('value',JSON.stringify(item));
			$('#tickets').attr('value',item.plannerDate.length);
			$('#startday').attr('value',startday);	
			$('#plancode').attr('value',plan_code);
			$('#schedule-json-data').submit();
			break;
		}
	}
}
//일정 삭제 버튼 클릭시 일정을 삭제하는 메소드
function deleteScheduleItem(plan_code){
	let mem_code = $('.uploadDiv').children('input').val();
	let param = {'plan_code' : plan_code};
	$.ajax({
		type : 'post',
		async : true,
		url : '/member/schedule/deleteScheduleList',
		dataType : 'text',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			console.log(data);
			if(data === 's'){
				alert('삭제가 완료되었습니다.');
				let state;
				console.log($('.complete-schedule').css('color'));
				if($('.my-schedule').css('color') === 'rgb(0, 156, 233)'){state = 'insert';}
				if($('.complete-schedule').css('color') === 'rgb(0, 156, 233)'){state = 'complete';}
				if($('.like-schedule').css('color') === 'rgb(0, 156, 233)'){state = 'like';}
				getPosting(mem_code);
				$('.schedule-lists').children('div').remove();
				$('.paging-form').children('.start').val('1');
				$('.paging-form').children('.end').val('6');
				loadPlannedSchedule(state);
				
			}else{alert('삭제를 실패하였습니다.');}
		},
		error : function(data, status, error) {
			//alert('fail code :' + data.status + ', ' + data);
			console.log(data);
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