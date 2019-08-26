$(document).ready(function(){
	$('.schedule').css('border-bottom','4px solid #009CE9');
	$('.timeline').css('border-bottom','none');
	$('.timeline').css('font-weight','normal');
	$('.timeline').css('width','60px');
	$('.timeline').css('height','24px');
	$('.tab-list a').css('margin','0px 30px 0px 30px');
	init();
});
function init(){
	loadPlannedSchedule();
}
function loadPlannedSchedule(){
	let mem_code = $('.uploadDiv').children('input').val();
	let name = $('.row2').children('.user-id').text();
	let param = {'mem_code':mem_code};
	console.log(mem_code);
	$.ajax({
		type : 'post',
		async : true,
		url : '/member/schedule/getScheduleList',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			if(data === '' || data === null){
				$('.schedule-lists').append('<div class="empty-schedule-list">계획 중인 일정이 없습니다.</div>');
			}else{
				console.log(data);
				let items = data;
				console.log(items);
				for(let item of items){
					let startdate = item.plannerDate[0].trip_date;
					let lastdate;
					for(let day of item.plannerDate){
						lastdate = day.trip_date;
					}
					$('.schedule-lists').append('<div class="schedule-list">'
							+'<ul>'
							+'<li><img class="schedule-list-img" src="'+item.plannerSchedule[0].content_img+'"></li>'
							+'<li><div class="schedule-name">'+item.planner.subject+'</div></li>'
							+'<li><div class="schedule-date">'+startdate+' ~ '+lastdate+'</div></li>'
							+'<li><a href="#" class="MnD" style="margin-left:70px;">수정</a></li>'
							+'<li><div class="MnD">|</div></li>'
							+'<li><a href="#" class="MnD">삭제</a><br/></li>'
							+'<br/><li><img class="schedule-userImg" src="'+$('#profile-img').attr('src')+'"></li>'
							+'<li><div class="schedule-userName">'+name+'</div></li>'
							+'</ul>'
							+'</div>'
					);
				}
			}
		},
		error : function(data, status, error) {
			alert('fail code :' + data.status + ', ' + data);
			console.log(data);
		}
	});
}
/*
				
								<div class="schedule-list">
                                        <ul>
                                            <li><img class="schedule-list-img" src="/img/member/winter.jpeg"></li>
                                            <li><div class="schedule-name">여행 이름</div></li>
                                            <li><div class="schedule-date">(2019.07.03~2019.07.05)</div></li>
                                            <li><a href="#" class="MnD">수정</a></li>
                                            <li><div class="MnD">|</div></li>
                                            <li><a href="#" class="MnD">삭제</a><br></li>
                                            <li><img class="schedule-userImg" src="/img/member/default_profile_m.png"></li>
                                            <li><div class="schedule-userName">사용자 닉네임</div></li>
                                        </ul>
                                </div>
                         
 */
