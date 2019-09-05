$(document).ready(function() {
	console.log('jquery init');
	markers = [];
	tourMarkers = [];
   	stationLocations = [
   		['서울역', 37.553870, 126.969598],
   		['인천역', 37.476619, 126.616957],
   		['대전역', 36.332612, 127.434221],
   		['대구역', 35.876457, 128.596023],
   		['광주역', 35.164236, 126.909755],
   		['부산역', 35.115397, 129.042246],
   		['울산역', 35.551839, 129.138583],
   		['가평역', 37.814812, 127.510725],
   		['고양역', 37.643297, 126.789717],
   		['광명역', 37.416795, 126.884821],
   		['동두천역', 37.926926, 127.054949],
   		['수원역', 37.267641, 126.999517],
   		['안양역', 37.402957, 126.921996],
   		['양평역', 37.525974, 127.487582],
   		['평택역', 36.991453, 127.084960],
   		['화성역', 37.199660, 127.096964],
   		['강릉역', 37.764078, 128.901647],
   		['동해역', 37.498447, 129.123824],
   		['영월역', 37.182689, 128.480757],
   		['정선역', 37.388249, 128.671972],
   		['춘천역', 37.885234, 127.717022],
   		['태백역', 37.176406, 128.984153],
   		['평창역', 37.562608, 128.429876],
   		['단양역', 36.973523, 128.343552],
   		['영동역', 36.172376, 127.786254],
   		['제천역', 37.128293, 128.205311],
   		['충주역', 36.976175, 127.909109],
   		['대천역', 36.342001, 126.586724],
   		['조치원역', 36.601395, 127.296239],
   		['천안역', 36.809342, 127.146604],
   		['천안아산역', 36.794871, 127.104421],
   		['홍성역', 36.599820, 126.681146],
   		['경주역', 35.844652, 129.217977],
   		['구미역', 36.128753, 128.330755],
   		['김천구미역', 36.113834, 128.180963],
   		['신경주역', 35.798661, 129.138912],
   		['안동역', 36.580303, 128.734073],
   		['영주역', 36.811390, 128.625007],
   		['영천역', 35.966893, 128.917536],
   		['점촌역', 36.595722, 128.203211],
   		['춘양역', 36.938123, 128.919911],
   		['포항역', 36.072030, 129.341990],
   		['마산역', 35.236405, 128.577181],
   		['진주역', 35.150704, 128.118172],
   		['남원역', 35.411542, 127.361368],
   		['익산역', 35.940570, 126.946296],
   		['전주역', 35.850066, 127.161852],
   		['정읍역', 35.575883, 126.843204],
   		['곡성역', 35.283901, 127.303757],
   		['구례구역', 35.163734, 127.452589],
   		['나주역', 35.014533, 126.717058],
   		['목포역', 34.791451, 126.386613],
   		['순천역', 34.946114, 127.503281],
   		['여수엑스포역', 34.753143, 127.748964]
   	];
   	let plan_code = $('#plancode').val();
   	console.log('plan_code : '+plan_code);
	let dateStr = $('#startday').val();
	let sp;
	let dateText;
	// hash_tag 추출 후 hash_tag로 뷰 조절하기 위해 뽑음
	let item = $('#scheduleitem').val();
	let hashTag;
	if(item !== 'new'){
		item = JSON.parse(item);
		hashTag = item.planner.hash_tag;
	}else{hashTag = 'new';}
	console.log('hashTag : '+hashTag);
	// 플랜 생성시엔 날짜가 /형태 플랜 수정시는 날짜가 .으로 월과 일이 구분되어 split해주기 위한 코드 
	if(dateStr.indexOf("/") !== -1){
		sp = dateStr.split('/');
		console.log('sp / : '+sp[0]+','+sp[1]);
		dateText = new Date(sp[0], sp[1]-1, sp[2]);
	}else{
		sp = dateStr.split('.');
		console.log('sp . : '+sp[0]+','+sp[1]);
		 dateText = new Date(null, sp[0]-1, sp[1]);
		}
	var day = $('#ticket').val();
	var planDateBox = 'plan-date-list1';	//현재 선택된 DAY의 class name을 담은 변수
	// ------ page init start ----------------
	console.log(dateStr+','+dateText+','+day+','+plan_code);
	if(plan_code === 'new' || plan_code === '' || plan_code === 'undefined' || plan_code === null){
		console.log('plan_code : '+plan_code);
		createInit(dateText,day,planDateBox);
	}else{
		console.log('plan_code2 : '+plan_code);
		updateInit(dateText,day,planDateBox);
	}
	// ------ page init end-------------------
	//일정 제목 누를시 수정 가능한 input tag 출력 이벤트
	$('.plan-name').click(function(){
		$(this).hide();
		$('.plan-name-text').show();
		$('.plan-name-text-btn').show();
	});
	//일정 제목 눌렀을때 나오는 input tag와 확인 버튼 중 확인 버튼을 눌렀을때 이벤트
	$(document).on('click','.plan-name-text-btn',function(){
		if($('.plan-name-text').val() !== ''){
			$('.plan-name').text($('.plan-name-text').val());
			$('.plan-name-text').hide();
			$('.plan-name-text-btn').hide();
			$('.plan-name').show();
		}
	});
	// 저장&닫기 버튼 클릭 이벤트
	$('.closeBtn').click(function(){
		saveAndClose(plan_code,'none');
	});
	// 완료 버튼 클릭 이벤트 -> 완료된 일정에선 텍스트가 닫기임
	$('.completeBtn').click(function(){
		let items = $('.plan-date-box').children('li');
		let flag = false;
		for(let item of items){
			console.log(item);
			if($(item).css('visibility') === 'visible'){
				console.log($(item).children('.detail-box').children('.region').text());
				if($(item).children('.detail-box').children('.region').text() === '지역을 선택하세요.'){
					flag = false;
				}else{ flag = true;}
			}
		}
		if($(this).text() === '완료'){
			if(flag){			
				let subject = $('.plan-name').text();
				if(subject !== '일정 제목' && subject !== ''){
					$('.save-subject-input').val(subject);
				}
				//모달창 오픈
				$(".save-modal-wrapper").css('display','flex');
				   setTimeout(function() {
				      $(".save-modal-wrapper").addClass('open');
				 }, 1)
				$('body').css({'overflow':'hidden', 'height':'100%'});
			}else{alert('DAY일정을 모두 선택해 주세요.');}
		}else{
			window.location.href = "../";
		}
	});
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
		if($('.save-modal-wrapper').hasClass('open')){ // site 라는 특정영역이 열려있을 경우
		      if(!$('.save-modal-wrapper').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
		    	  removeModal();
		      }
		   }
	});
	// 닫기 버튼(x)을 눌렀을때 modal 창 닫기
	$('.save-title-wrapper').children('.close-btn').click(function(e){
		removeModal();
	});
	//modal에 있는 테마 필터 클릭 이벤트
	$('label').click(function(){
		let id = $(this).attr('name');
		if(id === 'checked'){
			$(this).attr('name',' ');
		}else{
			$(this).attr('name','checked');
		}
		let items = $('.save-theme-checks');
		let cnt = 0;
		for(i = 0; i < 5; i++){
			let abs = $(items[0]).children()[i];
			if($(abs).children('label').attr('name') !== 'checked'){
				$(abs).children().hide();
				cnt++;
			}
		}
		if(cnt === 5){
			for(i = 0; i < 5; i++){
				let abs = $(items[0]).children()[i];
				$(abs).children().show();
			}
		}	
	});
	//완료버튼을 누른 후 나오는 모달창(save-modal창)에 있는 완료 버튼 클릭 이벤트
	$('.complete-check-btn').click(function(){
		let subject = $('.save-subject-input').val();
		let hash_tag;
		if(subject === ''){
			alert('일정 제목을 작성해주세요.');
		}else{
			$('.plan-name').text(subject);
			let items = $('.save-theme-checks');
			let cnt = 0;
			for(i = 0; i < 5; i++){
				let abs = $(items[0]).children()[i];
				if($(abs).children('label').attr('name') !== 'checked'){
					cnt++;
				}else{
					hash_tag = $(abs).children('label').attr('for')
				}
			}
			if(cnt >= 5){ alert('테마를 하나 선택해주세요.');}
			else{
				removeModal();
				saveAndClose(plan_code,hash_tag);
			}
		}
	});
	// DAY 누를 시 도시 리스트(지역 선택 리스트) 출력
	$('.plan-date-box').children('li').on('click', function(){
		planDateBox = $(this).attr('class');
		let region = $(this).find('.region').text();
		let day = $(this).find('.day').text().toLowerCase();
		//city-list item들 초기화-------------------
		$('.selected-theme-list').children().remove();
		//색상 변경--------------------------------
		$('.plan-date-box').children().css('background-color', '#7F7F7F');
		$(this).css('background-color', '#009ce9');
		//city list에 지역 이름 세팅 and 지역이름 위치로 줌인
		setZoomforSelectedLoc(region);
		if(hashTag !== 'new'){
			//This is none working when this have hash tag name.
			if(hashTag === 'none'){
				setTitleName(region);
			}else{
				setMarkerScheduleItems(day);
			}
		}else{
			setTitleName(region);
		}
		//상세 스케쥴 구역 초기화-----------------------
		if(region === '지역을 선택하세요.'){initScheduleDetailBox(null);}
		else{initScheduleDetailBox(day);}
		toggleCityList(80,350,'show');
	});
	// > 버튼 누르면 검색 도시 리스트 출력
	$('.list-cover').on('click', function(){
		toggleCityList(80,350,'toggle');
	});
	// < 버튼 누르면 검색 도시 리스트 숨김
	$('.list-close').on('click', function(){
		toggleCityList(100,0,'toggle');
	});
	// 지역을 선택하세요를 눌렀을때 도시 목록 토글 메소드 호출 이벤트
	$('.title-name').on('click',function(){
		toggleSelectCity();
	});
	// 지역을 선택하세요.의 지역 목록에서 지역 선택할때 발생하는 이벤트
	$('.city-item').on('click',function(){
		var name = $(this).text();
		$('.title-name').text(name+' ▼');
		$('.'+planDateBox).find('.region').text(name);
		toggleSelectCity();
		setZoomforSelectedLoc(name);
		setSelectedTheme('tour');
		checkActiveListSearchText(); // ※
		$('.list-search-text').val(''); // ※
	});
	// 숙박,식당,관광 테마 버튼 선택시 발생하는 이벤트
	$('.list-theme-wrapper').children().on('click',function(){
		if($('.list-title').children('.title-name').text() == '지역을 선택하세요.▼'){
			alert('지역을 먼저 선택해주세요.');
		}else{
			var className = $(this).attr('class');
			if(className == 'list-theme-accom'){
				setSelectedTheme('accom');
			}else if(className == 'list-theme-food'){
				setSelectedTheme('food');
			}else{
				setSelectedTheme('tour');
			}
		}
	})
	//day-spot-item 클래스(Tour api를 이용해 가져온 정보들)를 호버할시 그에 맞는 마커를 활성화 시키는 이벤트
	$(document).on('mouseenter','.day-spot-item,.schedule-item-wrapper', function(){
		var id = $(this).attr('id');
		MarkerHoverColorChange(id,'in');
	});
	//day-spot-item 클래스(Tour api를 이용해 가져온 정보들)를 호버에서 벗어날시 그에 맞는 마커를 비활성화 시키는 이벤트
	$(document).on('mouseleave','.day-spot-item,.schedule-item-wrapper',function(){
		var id = $(this).attr('id');
		MarkerHoverColorChange(id,'out');
	});
	// city-list의 아이템들의 이미지나 제목을 눌렀을때 해당 infowindow 띄어주기 이벤트
	$(document).on('click','.item-img-box, .item-info-box, .schedule-item-img',function(){
		let id = $(this).parent().attr('id');
		showInfoWindowOfItemList(id);
	});

	//city-list 에서 숙박 식당 관광 리스트들의 +버튼 눌렀을때 발생하는 이벤트
	$(document).on('click','.item-insert-box',function(){
		var id,img,name,addrStr,addr,mapxy,areacode,sigungucode;
		var day = null;
		for(i = 1; i <= 7; i++){
			var color = $('#day'+i).parent('li').css('background-color');
			if(color == 'rgb(0, 156, 233)'){
				day = 'day'+i;
				break;
			}else{continue;}
		}
		initScheduleDetailBox($('.'+planDateBox).children('.detail-box').attr('id'));
		id = $(this).parent().attr('id');
		img = $(this).parent().children('.item-img-box').children('.img-size').attr('src');
		name = $(this).parent().children('.item-info-box').text();
		addrStr = $(this).parent().children('.item-info-box').children('.item-sub').text();
		name = name.split(addrStr);
		name = name[0];
		addrStr = addrStr.split('조회수 :');
		addr = addrStr[0];
		mapxy =  $(this).parent().children('.item-img-box').attr('name');
		areacode = ($(this).parent().attr('name')).split(',')[0];
		sigungucode = ($(this).parent().attr('name')).split(',')[1];
		insertItemInScheduleDetailBox(id,img,name,addr,day,mapxy,areacode,sigungucode);
	});
	//상세일정에 추가된 목록들에 삭제 이미지를 클릭했을때 목록에서 삭제하는 이벤트
	$(document).on('click','.delete-box',function(){
		let id = $(this).parent().attr('id');
		let day = $(this).parent().attr('name');
		$(this).parent().remove();
		initScheduleDetailBox(day);
		MarkerHoverColorChange(id,'out');
	});
	// 여행지 길 찾기 누를시 해당하는 DAY에 추가된 일정들의 거리를 계산해서 보여주기 위한 이벤트 메소드
	$('.get-directions').click(function(){
		let flag = setGetDirectionBtn(planDateBox);
		if(flag){
			let day = $('.'+planDateBox).children('.detail-box').attr('id');
			console.log(day);
			toggleTransitFind('show','hide');
		}else{
			alert('상세일정을 2개 이상 선택해주세요.');
		}
	});
	$(document).on('click','.schedule-item-wrapper',function(){
		let subject;
		let name = $(this).children('.schedule-item-img').attr('name');
		//schedule detail box에 있는 일정의 이름만 split하기 위한 변수.
		subject = ($(this).children('.schedule-item-name').text()).split($(this).children('.schedule-item-name').children('.schedule-item-addr').text());
		if($('.transit-find').css('display') === 'block'){
			if( $('.transit-origin').children('input').val() === ''){	//출발지의 input tag value가 공백일시
				$('.transit-origin').children('input').val(subject[0]);
				 $('.transit-origin').children('input').attr('name',name);
			}else{	// 나머지
				$('.transit-destination').children('input').val(subject[0]);
				$('.transit-destination').children('input').attr('name',name);
			}
		}
	});
	$(document).on('click','.transit-find-btn',function(){
		let origin = $('.transit-origin').children('input').val();
		let dest = $('.transit-destination').children('input').val();
		//console.log('origin : '+origin+', destination : '+dest);
		if(origin === '' || dest === ''){
			alert('출발지와 도착지 설정을 해주세요.');
		}else{
			setMapDirectionsDisplay(map);
			let orimapxy = $('.transit-origin').children('input').attr('name');
			let destmapxy = $('.transit-destination').children('input').attr('name');
	 		orimapxy = orimapxy.split(',');
	 		destmapxy = destmapxy.split(',');		
	 		calculateAndDisplayRoute(parseFloat(orimapxy[1]),parseFloat(orimapxy[0]),parseFloat(destmapxy[1]),parseFloat(destmapxy[0]));
		}
	});
	$(document).on('click','.return-btn',function(){
		if(hashTag !== 'new'){
			setMapDirectionsDisplay(null);
			map.setOptions({draggable: true});
			$('.transit-origin').children('input').val('');
			$('.transit-destination').children('input').val('');
			setTourMarker();
		}else{
			toggleTransitFind('hide','show');
			setMapDirectionsDisplay(null);
    		//경로 창 닫을때 그래그 금지 해제, 마크 표시
    		setTourMarkerMapOnAll(map);
    		map.setOptions({draggable: true});
		}
	});
	$('.transit-set-box').children('div').children('.reset-btn').click(function(){
		$(this).prev().val('');
	})
	$(document).on('click','.infowindow-detail-view',function(){
		let areacode = $(this).attr('name').split(',')[0];
		let sigungucode = $(this).attr('name').split(',')[1];
		let contentid = $(this).attr('name').split(',')[2];
		let areaName = getAreaName(areacode,sigungucode);
		console.log(areaName);
		window.open('/info/detailInfo?areaName='+areaName+'&contentid='+contentid);
	});
});
let map;
var markers = [];
var tourMarkers = [];
var infoWindows = [];
var stationLocations = [];
let directDisplay;
let directionsService;
let hashTag;
//city-list에 있는 tour api 목록들의 이미지나 제목을 눌렀을때 해당하는 info window 를 open해주기 위한 메소드
function showInfoWindowOfItemList(id){
	closeInfoWindow();	// 모든 infowindow close
	for(i = 0; i < infoWindows.length; i++){
		if(id == tourMarkers[i].id){
			infoWindows[i].open(map, tourMarkers[i]);
		}
	}
}
//지역 이름 반환 메소드
function getAreaName(areacode,sigungucode){
	let name = null;
	$.ajax({
		type : 'get',
		async : false,
		url : '/planner/detail?areacode='+areacode+'&sigungucode='+sigungucode,
		dataType : 'text',
		success : function(data) {
			name = data;
			//console.log(data);	
		},
		error : function(data, status, error) {
			alert('fail code :' + data.status + ', ' + data);
			console.log(data);
		}
	});
	return name;
}
// 모달 닫는 메소드
function removeModal(){
	$('.save-modal-wrapper').removeClass('open');
	setTimeout(function() {
		$(".save-modal-wrapper").css('display','none');
	}, 1)
	$('body').css({'overflow':'auto', 'height':'100%'});
}
//일정 수정을 눌렀을때 초기화 하는 메소드
function updateInit(dateText,day,planDateBox){
	let item = $('#scheduleitem').val();
	item = JSON.parse(item);
	hashTag = item.planner.hash_tag;
	console.log(item);
	calDate(dateText, day);
	for(i = 1; i <= day; i++){
		for(j = 0; j < day; j++){
			if($('.plan-date-list'+i).children('.detail-box').children('.date').text() === item.plannerDate[j].trip_date){
				$('.plan-date-list'+i).children('.detail-box').children('.region').text(item.plannerDate[j].region);
			}
		}
	}
	$('.return-btn').css('display','none'); // 초기값 안보이게 ※
	checkActiveListSearchText(); // ※
	$('.list-search-text').val(''); // ※
	$('.plan-name').text(item.planner.subject);
	for(let ips of item.plannerSchedule){
		let id = ips.content_id;
		let img = ips.content_img;
		let name = ips.content_name;
		let addr = ips.content_addr;
		let day = ips.days;
		let mapxy = ips.content_position;
		let areacode = ips.areacode;
		let sigungucode = ips.sigungucode;
		insertLoadItemInScheduleDetailBox(id,img,name,addr,day,mapxy,areacode,sigungucode);
	}
	$('.empty-detail-box').css('display','block');
	$('.empty-detail-box').text('<< DAY를 클릭하세요');
	// 완성된 일정일때 citylist 기능 막기위한 코드
	if(item.planner.hash_tag !== 'none'){
		$('.list-cover').remove();
		$('.city-list').remove();
		$('.transit-find').show();
		$('.return-btn').show();
		$('.closeBtn').hide();
		$('.completeBtn').text('닫기');
	}
}
//일정 만들기를 눌렀을때 초기화하는 메소드
function createInit(dateText,day,planDateBox){
	hashTag = 'none';
	let memberCode = $('.member-code').children('input').val();
	if(memberCode === '' || memberCode === null){
		alert('로그인 후 이용 가능한 서비스 입니다.');
		window.location.replace("../");
	}
	calDate(dateText, day);
	$('.plan-date-box').children('.plan-date-list1').css('background-color','#009ce9');
	var str = $('.'+planDateBox).find('.region').text();
	setZoomforSelectedLoc(str);
	setTitleName(str);	
	$('.return-btn').css('display','none'); // 초기값 안보이게 ※
	checkActiveListSearchText(); // ※
	$('.list-search-text').val(''); // ※
}
//저장하기 위해 현재 페이지의 데이터를 추출하는 메소드
function extractDataForSave(planCode,hash_tag){
	let saveJsonData = new Object();
	let dayArr = new Array();
	let detailItemArr = new Array();
	//let planCode = '001';
	let memberCode = $('.member-code').children('input').val();
	//여행 플래너 테이블에 저장할 배열에 넣음
	let subject = $('.plan-name').text();
	let planner = {
			'plan_code' : planCode,
			'mem_code' : memberCode,
			'subject' : subject,
			'hash_tag' : hash_tag
	};
	saveJsonData.planner = planner;
	//여행의 DAY들과 DAY들에 대한 날짜 위치를 배열에 넣음
	let items = $('.plan-date-box').children('li');	
	for(let item of items){
		if($(item).css('visibility') === 'visible'){
			let day = {	'plan_code' : planCode,
						'days' : $(item).children('.detail-box').children('.day').text(),
						'trip_date': $(item).children('.detail-box').children('.date').text(),
						'region': $(item).children('.detail-box').children('.region').text()
						};
			dayArr.push(day);
		}
	}
	saveJsonData.plannerDate = dayArr;
	//상세일정 데이터 배열에 넣음
	let detailScheduleItems = $('.item-detail-box').children();
	if(detailScheduleItems.length === 0){
		//saveJsonData.detailScheduleItem = 'none';
	}else{
		for(let item of detailScheduleItems){
			let detail = {
					'plan_code' : planCode,
					'content_id' : $(item).attr('id'),
					'days' : $(item).attr('name'),
					'content_position' : $(item).children('.schedule-item-img').attr('name'),
					'content_img' : $(item).children('.schedule-item-img').children('img').attr('src'),
					'content_name' : ($(item).children('.schedule-item-name').text().split($(item).children('.schedule-item-name').children('.schedule-item-addr').text()))[0] ,
					'content_addr' : $(item).children('.schedule-item-name').children('.schedule-item-addr').text(),
					'areacode' : ($(item).children('.schedule-item-name').attr('name')).split(',')[0],
					'sigungucode' : ($(item).children('.schedule-item-name').attr('name')).split(',')[1]
			};
			detailItemArr.push(detail);
		}
		saveJsonData.plannerSchedule = detailItemArr;
	}
	console.log(saveJsonData);
	return saveJsonData;
}
//저장&닫기를 했을때 계획들을 Ajax를 이용해 디비에 저장하는 메소드
function saveAndClose(plan_code,hash_tag){
	let param = extractDataForSave(plan_code,hash_tag);
	$.ajax({
		type : 'post',
		async : true,
		url : '/planner/plan/saveAndClose',
		dataType : 'text',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			if(data === 'save'){
				alert('저장을 완료했습니다.');
				window.location.replace("../");
			}
			
		},
		error : function(data, status, error) {
			alert('fail code :' + data.status + ', ' + data);
			console.log(data);
		}
	});
}
//교통 길찾기에 대한 결과를 google 맵에 보여 줄지 말지 결정하는 메소드
function setMapDirectionsDisplay(map){
	directionsDisplay.setMap(map);
	$('.transit-result-box').show();
	if(map !== null){directionsDisplay.setPanel(document.getElementById('transit-result-box'));}
	else{directionsDisplay.setPanel(null);}
}
//get-directions 버튼이 상세일정이 2개 이상일때만 활성화 시키는 메소드
function setGetDirectionBtn(planDateBox){
	let count = 0;
	let day = $('.'+planDateBox).children('.detail-box').attr('id');
	let items = $('.schedule-item-wrapper');
	for(let item of items){
		let itemDay = $(item).attr('name');
		if(day === itemDay){
			count++;
		}
	}
	if(count >= 2){return true;}
	else{ return false;}
}
//길 찾기를 눌렀을때 출발지와 도착지를 입력받기 위한 창 출력 메소드 / 파라메터 trans-find width , city-list width
function toggleTransitFind(transitWidth,listWidth){
	let transitSpeed;
	let displayreturnBtn;
	let listSpeed;
	let toggleCursor;
	//toggle을 위한 세팅
	if(transitWidth === 'show'){transitSpeed = 80; listSpeed = 100; displayreturnBtn = 'block'; toggleCursor = 'pointer';}
	else{transitSpeed = 100; listSpeed = 80; displayreturnBtn = 'none'; toggleCursor = 'default';}
	//출발지 도착지 text field 초기화
	$('.transit-origin').children('input').val('');
	$('.transit-destination').children('input').val('');
	
	$('.transit-find').animate({width : transitWidth},transitSpeed);
	$('.city-list').animate({width : listWidth},listSpeed);
	
	$('.return-btn').animate({width:transitWidth},transitSpeed); // ※
	$('.return-btn').css('display',displayreturnBtn); // return-btn 보이게하기 ※
	$('.schedule-item-wrapper').css('cursor',toggleCursor);
}
//길 찾기를 눌렀을때 출발지와 도착지를 입력받기 위한 창 닫기 메소드
function hideTransitFind(){
	$('.transit-find').animate({
	      width: 'hide'
	},80);
	$('.schedule-item-wrapper').css('cursor','default');
	setMapDirectionsDisplay(null);
	//DAY를 바꿨을때 경로 창 닫고 그래그 금지 해제, 마크 제거 후 초기화
	delTourMarkers();
	map.setOptions({draggable: true});
}

//교통 길찾기 경로 거리 계산 메소드
function calculateAndDisplayRoute(originLat, originLng, destLat, destLng) {
	console.log(originLat+','+originLng+','+destLat+','+destLng);
	let oriLatLng = new google.maps.LatLng(originLat,originLng);
	let destLatLng = new google.maps.LatLng(destLat,destLng);
	//console.log('oriLatLng : '+oriLatLng+', destLatLng : '+destLatLng);
	let request = {	//google transit route에 요청할 내용
			origin : oriLatLng,
			destination : destLatLng,
			travelMode: google.maps.DirectionsTravelMode.TRANSIT
	};
	directionsService.route(request, function(response, status) {
	    if (status === google.maps.DirectionsStatus.OK) {
	    	//console.log(response);
	    	//경로 보여줄때 그래그 금지, 마크 제거
	    	setTourMarkerMapOnAll(null);
	    	map.setOptions({draggable: false});
	    	directionsDisplay.setDirections(response);
	    } else {
	    	window.alert('해당 경로 길찾기를 할 수 없습니다.');
	    }
	});
}
//DAY1~7버튼 클릭시 schedule detail box 초기화 메소드
//선택된 일정 삭제 버튼 누를 시 더이상 남은 일정이 없을시 empty detail box 출력 기능.
function initScheduleDetailBox(day){
	let count = 0;
	if(day === null){
		$('.item-detail-box').hide();
		$('.schedule-detail-box').children('.empty-detail-box').show();
	}else{
		$('.schedule-detail-box').children('.empty-detail-box').hide();
		$('.item-detail-box').show();
		let items = $('.schedule-item-wrapper');
		items.hide();
		for(let item of items){
			let itemDay = $(item).attr('name');
			if(day === itemDay){
				count++;
				$(item).show();
			}
		}
		if(count === 0){initScheduleDetailBox(null);}
	}
	checkActiveListSearchText(); // ※
	$('.list-search-text').val(''); // ※
}
//city list에서 item 선택시 schedule detail box 안에 넣는 메소드
function insertItemInScheduleDetailBox(id,img,name,addr,day,mapxy,areacode,sigungucode){
	$('.empty-detail-box').css('display','none');
	$('.item-detail-box').css('display','inline-block');
	$('.item-detail-box').append('<div id="'+id+'" name="'+day+'" class="schedule-item-wrapper">'
			+ '<div class="schedule-item-img" style="cursor:pointer;"name="'+mapxy+'"><img style="width:60px; height:60px;" src="'+img+'" ></div>'
			+ '<div class="schedule-item-name" name="'+areacode+','+sigungucode+'">' + name
			+ '<div class="schedule-item-addr">'+ addr +'</div>'
			+ '</div>'
			+ '<div class="delete-box"title="삭제"><img style="width:15px;height:15px;" src="../img/planner/delete.png"></div>'
			+ '</div>');
}
// 계획중이던 일정 수정을 눌렀을때 schedule detail box에 저장했던 일정들 뿌려쥬는 메소드
function insertLoadItemInScheduleDetailBox(id,img,name,addr,day,mapxy,areacode,sigungucode){
	let display;
	display = 'none';
	$('.empty-detail-box').css('display','none');
	$('.item-detail-box').css('display','inline-block');
	$('.item-detail-box').append('<div id="'+id+'" name="'+day+'" class="schedule-item-wrapper" style="display:'+display+';">'
			+ '<div class="schedule-item-img"  style="cursor:pointer;" name="'+mapxy+'"><img style="width:60px; height:60px;" src="'+img+'" ></div>'
			+ '<div class="schedule-item-name" name="'+areacode+','+sigungucode+'">' + name
			+ '<div class="schedule-item-addr">'+ addr +'</div>'
			+ '</div>'
			+ '<div class="delete-box"title="삭제"><img style="width:15px;height:15px;" src="../img/planner/delete.png"></div>'
			+ '</div>');
}
//마커 호버,도시 리스트 시 marker focus 메소드
function MarkerHoverColorChange(id,action){	// action parameter -> mouseenter면 in / mouseleave면 out
	var url;
	var markerUrl;
	var sizex;
	var sizey;
	var zindex;
	for(i = 0; i < tourMarkers.length; i++){
		if(id == tourMarkers[i].id){
			if(action == 'in'){
				url = (tourMarkers[i].getIcon().url).split('.png');
				markerUrl = url[0]+'_hover.png';
				sizex = 40;
				sizey = 50;
				zindex = 9999;
			}else{
				url = (tourMarkers[i].getIcon().url).split('_hover.png');
				markerUrl = url[0]+'.png';
				sizex = 30;
				sizey = 40;
				zindex = 999;
			}
			var icon = {
				url : markerUrl,
				scaledSize: new google.maps.Size(sizex,sizey),
				origin: new google.maps.Point(0,0),
				anchor: new google.maps.Point(0,0),
			}
			tourMarkers[i].setZIndex(zindex);
			tourMarkers[i].setIcon(icon);
		}
	}	
}
//지도를 드래그를 이용해 위치를 이동 하였을때 발생하는 이벤트를 처리하는 메소드.
function dragMapEvent(){
	if(hashTag !== 'none'){
		// function none working;
	}else{
		if(map.getZoom() > 10){
			var accomColor = $('.list-theme-wrapper').children('.list-theme-accom').css('border-color');	//색칠된 rgb(0, 156, 233)
			var foodColor = $('.list-theme-wrapper').children('.list-theme-food').css('border-color');	//색칠된 rgb(0, 156, 233)
			var tourColor = $('.list-theme-wrapper').children('.list-theme-tour').css('border-color');	//색칠된 rgb(0, 156, 233)
			if(accomColor === 'rgb(0, 156, 233)'){setSelectedTheme('accom');}
			else if(foodColor === 'rgb(0, 156, 233)'){setSelectedTheme('food');}
			else if(tourColor === 'rgb(0, 156, 233)'){setSelectedTheme('tour');}
			else{}
		}
	}
}
// 지역을 선택하세요.의 지역 목록에서 지역 선택할때 지역에 따라 지도를 해당 지역으로 줌인 해주는 메소드
function setZoomforSelectedLoc(locName){
	if(locName !== null){
		for(i = 0; i < stationLocations.length; i++){
			var nameIndex = stationLocations[i][0];
			if(nameIndex.indexOf(locName) !== -1){
				map.setCenter({lat: stationLocations[i][1], lng: stationLocations[i][2]});
				map.setZoom(13);
				break;
			}
		}
	}
}

//DAY1~DAY7 선택시 or 지역을 선택했을 시, 지역이 선택이 되어있는 상태면 기본 값으로 숙박이 선택되게 하는 메소드
//로그인 후 저장된 일정이 불러와질 경우 오류 야기할 수 있으니 미래의 나야! 주의깊게 보셈!
function setSelectedTheme(theme){	//파라메터 -> accom, food, tour 문자열이 들어옴
	$('.list-theme-wrapper').children('.list-theme-accom').css('background-image','url(../img/planner/bed.png)');
	$('.list-theme-wrapper').children('.list-theme-food').css('background-image','url(../img/planner/food.png)');
	$('.list-theme-wrapper').children('.list-theme-tour').css('background-image','url(../img/planner/hotplace.png)');
	$('.list-theme-wrapper').children('div').css('border-color','#7f7f7f');
	if(theme === 'accom'){
		$('.list-theme-wrapper').children('.list-theme-accom').css('background-image','url(../img/planner/bed_hover.png)');
		$('.list-theme-wrapper').children('.list-theme-accom').css('border-color','#009CE9');
		themeAjaxInfo('accom');
	}else if(theme === 'food'){
		$('.list-theme-wrapper').children('.list-theme-food').css('background-image','url(../img/planner/food_hover.png)');
		$('.list-theme-wrapper').children('.list-theme-food').css('border-color','#009CE9');
		themeAjaxInfo('food');
	}else if(theme === 'tour'){
		$('.list-theme-wrapper').children('.list-theme-tour').css('background-image','url(../img/planner/hotplace_hover.png)');
		$('.list-theme-wrapper').children('.list-theme-tour').css('border-color','#009CE9');
		themeAjaxInfo('tour');
	}else{	//default -> accom
		if(theme == null){
			//null
		}else{
			$('.list-theme-wrapper').children('.list-theme-accom').css('background-image','url(../img/planner/bed_hover.png)');
		}
	}
}
// 테마가 정해졌을때 ajax를 이용해 해당 테마에 대한 Tour API 정보를 가져오는 메소드
function themeAjaxInfo(theme){	//파라메터 -> accom, food, tour 문자열이 들어옴
	var x = calPosLng();
	var y = calPosLat();
	console.log('x : '+x+', y: '+y+', theme: '+theme);
	var param = {
			'theme' : theme,
			'xpos' : x,
			'ypos' : y
		};
	$.ajax({
		type : 'post',
		async : true,
		url : '/planner/plan/dataForTheme',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			var viewOrTel;
			$('.day-spot-item').remove();
			$('.item-img-box').remove();
			$('.item-info-box').remove();
			$('.item-insert-box').remove();
			console.log(data.response.body.items.item);
			var myItem = data.response.body.items.item;
			if (myItem == null) {
			} else {
				delTourMarkers();
				for (i = 0; i < myItem.length; i++) {
					//32 39 제외는 관광이므로 viewcount를 content에 삽입
					if(parseInt(myItem[i].contenttypeid) == 32 || parseInt(myItem[i].contenttypeid) == 39){
						viewOrTel = '조회수 : ' + myItem[i].readcount + '<br/>' + 'tel : ' + myItem[i].tel;
					}else{
						viewOrTel = '조회수 : ' + myItem[i].readcount;
					}
					if(myItem[i].firstimage == null){myItem[i].firstimage = '../img/default.png';}
					addTourMarker(myItem[i].mapx, myItem[i].mapy,
							myItem[i].title, myItem[i].firstimage,myItem[i].addr1,viewOrTel,theme,myItem[i].contentid,myItem[i].areacode,myItem[i].sigungucode);
					$('.selected-theme-list').append('<div id="'+myItem[i].contentid+'" name="'+myItem[i].areacode+','+myItem[i].sigungucode+'" class="day-spot-item">'
							+'<div class="item-img-box" name="'+myItem[i].mapx+','+myItem[i].mapy+'"><img class="img-size" src='+myItem[i].firstimage+'>'
							+'</div><div class="item-info-box">'+myItem[i].title+'<div class="item-sub">'+myItem[i].addr1+'<br/>'+viewOrTel+'</div></div>'
							+'<div class="item-insert-box"><img style="width:30px; height:30px" src="../img/planner/wh_plus.png"></div></div>');
				}
				setTourMarker();
			}
		},
		error : function(data, status, error) {
			alert('code :' + data.status + ', ' + data);
			console.log(data);
		},complete:function(){
			$('.selected-theme-list').scrollTop(0);
		}
	});
}
function setMarkerScheduleItems(day){
	let items = $('.schedule-item-wrapper');
	console.log(day);
	for(let item of items){
		if(day === $(item).attr('name')){
			let mapxy = $(item).children('.schedule-item-img').attr('name');
			mapxy = mapxy.split(',');
			let subject = ($(item).children('.schedule-item-name').text()).split($(item).children('.schedule-item-name').children('.schedule-item-addr').text());
			let addr1 = $(item).children('.schedule-item-name').children('.schedule-item-addr').text();
			let img =  $(item).children('.schedule-item-img').children('img').attr('src');
			let id = $(item).attr('id');
			let areacode = ($(item).children('.schedule-item-name').attr('name')).split(',')[0];
			let sigungucode = ($(item).children('.schedule-item-name').attr('name')).split(',')[1];
			addTourMarker(mapxy[0],mapxy[1],subject[0],img,addr1,1985,'complete',id, areacode, sigungucode);
		}
	}
	setTourMarker();
}
//list-title의 자식 title-name 텍스트 설정 메소드(DAY1~DAY7버튼 눌렀을때 정보에 따라 변경됨)
function setTitleName(loc){
	if(loc == '지역을 선택하세요.'){
		$('.title-name').text(loc + '▼');
		setSelectedTheme(null);
	}else{
		$('.title-name').text(loc + ' ▼');
		setSelectedTheme('tour');
		
	}
}
//list-title의 자식 title-name 텍스트 설정 메소드(DAY1~DAY7버튼 눌렀을때 정보에 따라 변경됨)
function setTitleinsertName(loc){
	$('.title-name').text(loc + ' ▼');
	setSelectedTheme(null);
}	
//도시 선택시 상->하 토글 메소드
function toggleSelectCity(){
	if($('.select-city-title').css('display') == 'none'){
		$('.city-list-body').css('opacity','0.5');
	}else{
		$('.city-list-body').css('opacity','1.0');
	}
	$('.select-city-title').slideToggle('fast');
}
//도시리스트 좌->우 토글 메소드
function toggleCityList(setTime,mapPos,tog){
	var width;
	// list 출력시 속도 80, 감출 때 속도 100 /  list 출력시 map 위치 350px, 감출 때 map 위치 0px
	$('.city-list').animate({
	      width: tog
	},setTime);
	if(parseInt(mapPos) == 350){ // > 클릭 시 
		$('#map').attr('style', 'width:-webkit-calc(100% - 350px) !important; width:calc(100% - 350px) !important; position:relative; overflow:hidden;');
	}else if(parseInt(mapPos) == 0){ // < 클릭 시 
		$('#map').attr('style', 'width: 100% !important; position:relative; overflow:hidden;');
	}
	google.maps.event.trigger(map, 'resize');
	map.setCenter(map.getCenter());
	$('#map').css('left', mapPos+'px');
}
// DAY의 날짜 설정 메소드.
function calDate(dateText, day) {
	for (i = 1; i <= day; i++) {
		if (i === 1) {/* do not plus date */
		} else {
			dateText.setDate(dateText.getDate() + 1);
		}
		var mm = dateText.getMonth()+1;
		mm = (mm < 10) ? '0' + mm : mm;
		var dd = dateText.getDate();
		dd = (dd < 10) ? '0' + dd : dd;
		$('#day' + i).children('.date').text(mm + '.' + dd);
		if (i === 1) {
			$('.plan-date').children('.start').text(mm + '.' + dd);
		}
		if (i == day) {
			$('.plan-date').children('.end').text(mm + '.' + dd);
		}
		$('.plan-date-box').children('.plan-date-list' + i).css('visibility',
				'visible');
	}
}
// 마커 클릭 이벤트
function markerListener(localmarker, infoWindow) {
	google.maps.event.addListener(localmarker, 'click', function() {
		map.setCenter(localmarker.getPosition());
		map.setZoom(13);
		infoWindow.close();
	});
	google.maps.event.addListener(localmarker, 'mouseover', function() {
		infoWindow.open(map, localmarker);
	});
	google.maps.event.addListener(localmarker, 'mouseout', function() {
		infoWindow.close();
	});
}
function tourMarkerListener(localmarker, infoWindow) {
	google.maps.event.addListener(localmarker, 'click', function() {
		//스트롤 top이 277.5에 위치하게 하기
		//selected-theme-list 의 top값은 260.5
		closeInfoWindow();	// 모든 infowindow close
		infoWindow.open(map, localmarker);
		var id = '#'+localmarker.id;
		var scroll_h = $('.selected-theme-list').scrollTop()+$(id).offset().top;
		$('.selected-theme-list').animate({
			scrollTop: scroll_h-277.5},500);
	});
	google.maps.event.addListener(localmarker, 'mouseover', function() {
		//infoWindow.open(map, localmarker);
		MarkerHoverColorChange(localmarker.id,'in');
	});
	google.maps.event.addListener(localmarker, 'mouseout', function() {
		//infoWindow.close();
		MarkerHoverColorChange(localmarker.id,'out');
	});
}
// 맵에 주요 역 정보 마커를 찍어주는 메소드 - 파라메터는 갯수
function addStationsMarker(num) {
	for (i = 0; i < num; i++) {
		var marker = new google.maps.Marker({
			id : i,
			position : new google.maps.LatLng(stationLocations[i][1],
					stationLocations[i][2]),
			map : map,
		// icon:{ url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png" }
		});
		var infoWindow = new google.maps.InfoWindow({
			content : stationLocations[i][0],
			map : map
		});
		markers.push(marker);
		markerListener(marker, infoWindow);
	}
}
// 맵의 줌이 13 이상일때 해당 구역의 숙/관/식들의 마커를 추가해주는 메소드 - 파라메터는 갯수
function addTourMarker(xpos, ypos, tourContent, tourImg, address, viewOrTel, theme, contentId, areacode, sigungucode) {
	// console.log('x : '+xpos+', y : '+ypos+', c: '+tourContent);
	var imgUrl = "../img/planner/"+theme+"_marker.png";
	var marker = new google.maps.Marker({
		id : contentId,
		position : new google.maps.LatLng(ypos, xpos),
		map : map,
		zIndex : 999,
		icon : {
			url : imgUrl,
			scaledSize: new google.maps.Size(30,40),
			origin: new google.maps.Point(0,0),
			anchor: new google.maps.Point(0,0)			
		}
	});
	var infoWindow = new google.maps.InfoWindow({
		content :'<div id="'+contentId+'" class="infowindow" style="display:inline-block;font-size:15px;font-weight:bold;width:150px;margin-bottom:10px;">' + tourContent + '</div>' + '<br/>'
				+'<div style="display:inline-block;font-size:10px;width:150px;">' + address + '</div>' + '<br/>'
				+'<div style="display:inline-block;font-size:10px;width:150px;margin-bottom:7px;">' +'조회수 : ' + viewOrTel + '</div>' + '<br/>'
				+'<div style="display:inline-block;margin-right:10px;">'
				+'<img style="width:150px;height:100px;" src="' + tourImg
				+ '"' + '<div>'  + '<br/>'
				+'<div class="infowindow-detail-view" name="'+areacode+','+sigungucode+','+contentId+'" style="margin-left:50px;background-color:#009CE9;padding:7px 11px;border:none;color:white;text-align:center;text-decoration:none;display:inline-block;font-size:10px;cursor:pointer;">자세히보기</div>&nbsp;',
		map : map
	});
	tourMarkers.push(marker);
	infoWindows.push(infoWindow);
	tourMarkerListener(marker, infoWindow);
}
function closeInfoWindow(){
	for(i = 0; i < infoWindows.length;i++){
		infoWindows[i].close();
	}
}
// 맵에 투어 정보 마커들의 갯수 조절 메소드(zoom이 확대 축소 될때마다 호출됨)
function setStationMarker(num) {
	setMapOnAll(null);
	setMapOnNum(num);
}
// 맵에 역 정보 마커들의 갯수 조절 메소드(zoom이 확대 축소 될때마다 호출됨)
function setTourMarker() {
	for (i = 0; i < tourMarkers.length; i++) {
		tourMarkers[i].setMap(map);
	}
}
function delTourMarkers() {
	setTourMarkerMapOnAll(null);
	tourMarkers = [];
	infoWindows = [];
}
function setTourMarkerMapOnAll(map) {
	for (i = 0; i < tourMarkers.length; i++) {
		tourMarkers[i].setMap(map);
		infoWindows[i].setMap(map);
	}
}
// 모든 역 마커들의 map을 셋팅
function setMapOnAll(map) {
	for (i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}
// 역 마커들의 map을 셋팅
function setMapOnNum(num) {
	for (i = 0; i < num; i++) {
		markers[i].setMap(map);
	}
}
// 마커들을 모두 배열에서 제거
function deleteMarkers() {
	setMapOnAll(null);
	markers = [];
}
function calPosLat() {
	// 북,동이 더 큼
	var aNorth = map.getBounds().getNorthEast().lat();
	var aSouth = map.getBounds().getSouthWest().lat();
	var cenLat = aSouth + (aNorth - aSouth) / 2;
	return cenLat;
}
function calPosLng() {
	// 북,동이 더 큼
	var aEast = map.getBounds().getNorthEast().lng();
	var aWest = map.getBounds().getSouthWest().lng();
	var cenLng = aWest + (aEast - aWest) / 2;
	return cenLng;
}
function setLocMarker() {
	var x = calPosLng();
	var y = calPosLat();
	var param = {
		'xpos' : x,
		'ypos' : y
	};
	// alert( JSON.stringify(param));
	$.ajax({
		type : 'post',
		async : true,
		url : '/planner/map/locData',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(param),
		success : function(data) {
			console.log(data.response.body.items.item);
			var myItem = data.response.body.items.item;
			if (myItem == null) {
			} else {
				delTourMarkers();
				for (i = 0; i < myItem.length; i++) {
					if(myItem[i].firstimage == null){myItem[i].firstimage = '../img/default.png';}
					addTourMarker(myItem[i].mapx, myItem[i].mapy,
							myItem[i].title, myItem[i].firstimage,null);
				}
				setTourMarker();
			}
		},
		error : function(data, status, error) {
			alert('code :' + data.status + ', ' + data);
			console.log(data);
		}
	});
}

// 장소검색 활성화 메소드 ※
function checkActiveListSearchText(){
	if($('.title-name').text()=='지역을 선택하세요.▼') {
		$('.list-search-text').attr('readonly',true);
		$('.list-search-text').css({'outline': 'none', 'cursor':'default'});
	}else {
		$('.list-search-text').attr('readonly',false);
		$('.list-search-text').css({'outline':'', 'cursor':'text'});
	}
}

$(document).ready(function(){
	$('.list-search-text').keyup(function(){
		var areaName = $('.title-name').text().replace(' ▼', '');
		var searchText = $('.list-search-text').val();
		//console.log(areaName + ", " + searchText);
		if(searchText.length >= 2){
			$.ajax({
				url:'/planner/searchKeyword',
				type:'get',
				data:{areaName:areaName, keyword:searchText},
				dataType:'json',
				contentType : 'application/json',
				success:function(data){
					var viewOrTel;
					$('.day-spot-item').remove();
					$('.item-img-box').remove();
					$('.item-info-box').remove();
					$('.item-insert-box').remove();
					//console.log(data.response.body.items.item);
					var myItem = data.response.body.items.item;
					if (myItem == null) {
					} else {
						delTourMarkers();
						for (i = 0; i < myItem.length; i++) {
							//32 39 제외는 관광이므로 viewcount를 content에 삽입
							if(parseInt(myItem[i].contenttypeid) == 32 || parseInt(myItem[i].contenttypeid) == 39){
								viewOrTel = '조회수 : ' + myItem[i].readcount + '<br/>' + 'tel : ' + myItem[i].tel;
							}else{
								viewOrTel = '조회수 : ' + myItem[i].readcount;
							}
							if(myItem[i].firstimage == null){myItem[i].firstimage = '../img/default.png';}
							
							
							var theme; var contenttypeid=myItem[i].contenttypeid; 
							if(contenttypeid==12 || contenttypeid==14 || contenttypeid==15 || contenttypeid==28 || contenttypeid==38) theme='tour';
							else if(contenttypeid==32) theme='accom';
							else if(contenttypeid==39) theme='food';
							addTourMarker(myItem[i].mapx, myItem[i].mapy, myItem[i].title, myItem[i].firstimage,myItem[i].addr1,viewOrTel,theme,myItem[i].contentid, myItem[i].areacode, myItem[i].sigungucode);
							
							$('.selected-theme-list').append('<div id="'+myItem[i].contentid+'" class="day-spot-item">'
									+'<div class="item-img-box" name="'+myItem[i].mapx+','+myItem[i].mapy+'"><img class="img-size" src='+myItem[i].firstimage+'>'
									+'</div><div class="item-info-box">'+myItem[i].title+'<div class="item-sub">'+myItem[i].addr1+'<br/>'+viewOrTel+'</div></div>'
									+'<div class="item-insert-box"><img style="width:30px; height:30px" src="../img/planner/wh_plus.png"></div></div>');
						}
						setTourMarker();
					}
				}
			});
		}
	});
});
