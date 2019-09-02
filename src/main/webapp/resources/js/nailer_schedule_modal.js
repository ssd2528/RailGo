$(document).ready(function(){
	// open the modal 
	$('.calendar-map').on("click",function(){
		   $("#modal").css('display','flex');
		   setTimeout(function() {
		      $("#modal").addClass('show');
		   }, 1)
		   $('body').css({'overflow':'hidden', 'height':'100%'});
		   fillTextScheduleInfo(this);
		});
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
	   if($('#modal').hasClass('show')){ // site 라는 특정영역이 열려있을 경우
	      if(!$('#modal').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
	    	  closeModal();
	      }
	   }
	});
	// close the modal 
	$(".close-button").on("click",function(){
		closeModal();
	});
	$('.detail-view-button').on('click',function(){
		closeModal();
		$('#list-schedule-json-data').submit();
	});
});
// 모달 종료 메소드
function closeModal(){
	 $('#modal').removeClass('show');
     $('body').css({'overflow':'auto', 'height':'100%'});
     setTimeout(function() {
        $('#modal').css('display','none');
     }, 50)
}
// 모달 띄울때 정보들 채우는 메소드
function fillTextScheduleInfo(data){
	let img = $(data).css('background-image');
	img = img.split('"')[1];
	let name = $(data).parent().children('.calendar-text-wrapper').children('.calendar-text-writer').text();
	let subject = $(data).parent().children('.calendar-text-wrapper').children('.calendar-text-subject').text();
	let hash_tag = $(data).parent().children('.calendar-text-wrapper').children('.calendar-text-tag').text();
	let enddate = $(data).prev().children('.enddate').val();
	let scheduleArr = $(data).prev().children('.day-schedule-str').val();
	let splitArr = scheduleArr.split(',');
	//자세히 보기시 넘기기 위한 데이터를 저장 할 변수 목록들
	let jsonData = $(data).prev().children('.item').val();
	let startdate = $(data).prev().children('.startdate').val();
	let tickets = $(data).prev().children('.tickets').val();
	let plancode = $(data).prev().children('.plancode').val(); 
	console.log(data);
	console.log('plan code :'+plancode);
	$('.modal_title').text(name+'님의 일정');
	$('.map-img').attr('src',img);
	$('.schedule-title').text('제목 : '+subject);
	$('.schedule-theme').text(hash_tag);
	$('.schedule').text('일정 : '+startdate+' ~ '+enddate);
	if(scheduleArr.length === 0 || scheduleArr === null || scheduleArr === 'undefined'){
		$('.nailer-schedule-group').children().remove();
		$('.nailer-schedule-group').append('<div style="text-align:center; font-size:20px; margin-top:50px;"> 일정 목록이 없습니다. </div>');
	}else{
		$('.nailer-schedule-group').children().remove();
		let dayArr = new Array();
		let courseArr = new Array();
		for(i = 0; i < 7; i++){courseArr[i] = '코스 : ';}
		for(let item of splitArr){
			for(i = 1; i <= 7; i ++){
				if(item.indexOf('day'+i) !== -1){
					dayArr[i-1] = true;		
					courseArr[i-1] += item.split(':')[1]+' - ';
				}
			}
		}
		for(i = 0; i < 7; i++){courseArr[i] = courseArr[i].substr(0,courseArr[i].length-3);}
		let cnt = 1;
		for(let item of dayArr){
			$('.nailer-schedule-group').append('<div class="day">DAY'+cnt+'</div>'
					+'<div class="main-schedule" >'+courseArr[cnt-1]+'</div>');
			cnt++;
		}
	}	
	console.log(jsonData);
	//jsonData = JSON.parse(jsonData);
	//console.log(jsonData);
	//fillFormData(jsonData,startdate,tickets,plancode);
}
// 자세히 보기를 누를 시 데이터 전달을 위해 form tag에 data 채워넣는 메소드
function fillFormData(jsonData,startdate,tickets,plancode){
	$('#list-schedule-json-data').children('.item').attr('value',JSON.stringify(jsonData));
	$('#list-schedule-json-data').children('.tickets').attr('value',tickets);
	$('#list-schedule-json-data').children('.startday').attr('value',startdate);	
	$('#list-schedule-json-data').children('.plancode').attr('value',plancode);
}