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
	var dateStr = $('#startday').val();
	var sp = dateStr.split('/');
	var dateText = new Date(sp[0], sp[1], sp[2]);
	var day = $('#ticket').val();
	var planDateBox='plan-date-list1';	//현재 선택된 DAY의 class name을 담은 변수
	// ------ page init start ----------------
	calDate(dateText, day);
	$('.plan-date-box').children('.plan-date-list1').css('background-color','#009ce9');
	var str = $('.'+planDateBox).find('.region').text();
	setZoomforSelectedLoc(str);
	setTitleName(str);	
	// ------ page init end-------------------

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
		setTitleName(region);
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
	//지역을 선택하세요.의 지역 목록에서 지역 선택할때 발생하는 이벤트
	$('.city-item').on('click',function(){
		var name = $(this).text();
		$('.title-name').text(name+' ▼');
		$('.'+planDateBox).find('.region').text(name);
		toggleSelectCity();
		setZoomforSelectedLoc(name);
		setSelectedTheme('tour');
	});
	//숙박,식당,관광 테마 버튼 선택시 발생하는 이벤트
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
	//day-spot-item 클래스(Tour api를 이용해 가져온 정보들)를 호버에서 벗어날시 그에 맞는 마커를 활성화 시키는 이벤트
	$(document).on('mouseleave','.day-spot-item,.schedule-item-wrapper',function(){
		var id = $(this).attr('id');
		MarkerHoverColorChange(id,'out');
	});
	//city-list 에서 숙박 식당 관광 리스트들의 +버튼 눌렀을때 발생하는 이벤트
	$(document).on('click','.item-insert-box',function(){
		var id,img,name,addrStr,addr,mapxy;
		var day = null;
		for(i = 1; i <= 7; i++){
			var color = $('#day'+i).parent('li').css('background-color');
			if(color == 'rgb(0, 156, 233)'){
				day = 'day'+i;
				break;
			}else{continue;}
		}
		var edb = $('.empty-detail-box').css('display');
		if(edb == 'inline-block'){
			 $('.empty-detail-box').css('display','none');
		}
		id = $(this).parent().attr('id');
		img = $(this).parent().children('.item-img-box').children('.img-size').attr('src');
		name = $(this).parent().children('.item-info-box').text();
		addrStr = $(this).parent().children('.item-info-box').children('.item-sub').text();
		name = name.split(addrStr);
		name = name[0];
		addrStr = addrStr.split('조회수 :');
		addr = addrStr[0];
		mapxy =  $(this).parent().children('.item-img-box').attr('name');
		insertItemInScheduleDetailBox(id,img,name,addr,day,mapxy);
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
		let day = $('.'+planDateBox).children('.detail-box').attr('id');
		console.log(day);
		showTransitFind();
		let orimapxy = $('#126537').children('.schedule-item-img').attr('name');
		let destmapxy = $('#126747').children('.schedule-item-img').attr('name');
		orimapxy = orimapxy.split(',');
		destmapxy = destmapxy.split(',');
		calculateAndDisplayRoute(parseFloat(orimapxy[1]),parseFloat(orimapxy[0]),parseFloat(destmapxy[1]),parseFloat(destmapxy[0]));
	});
});
let map;
var markers = [];
var tourMarkers = [];
var stationLocations = [];
let directDisplay;
let directionsService;
//길 찾기를 눌렀을때 출발지와 도착지를 입력받기 위한 창 출력 메소드
function showTransitFind(){
	$('.transit-find').animate({
	      width: 'show'
	},80);
	$('.city-list').animate({
	      width: 'hide'
	},100);
}
//교통 길찾기 경로 거리 계산 메소드
function calculateAndDisplayRoute(originLat, originLng, destLat, destLng) {
	console.log(originLat+','+originLng+','+destLat+','+destLng);
	let oriLatLng = new google.maps.LatLng(originLat,originLng);
	let destLatLng = new google.maps.LatLng(destLat,destLng);
	console.log('oriLatLng : '+oriLatLng+', destLatLng : '+destLatLng);
	  // Retrieve the start and end locations and create a DirectionsRequest using
	let request = {
			origin : oriLatLng,
			destination : destLatLng,
			travelMode: google.maps.DirectionsTravelMode.TRANSIT
	};
	directionsService.route(request, function(response, status) {
	    if (status === google.maps.DirectionsStatus.OK) {
	    	console.log(response);
	    	directionsDisplay.setDirections(response);
	    	/*
	      document.getElementById('warnings-panel').innerHTML =
	          '<b>' + response.routes[0].warnings + '</b>';
	      directionsDisplay.setDirections(response);
	      showSteps(response, markerArray, stepDisplay, map);*/
	    } else {
	    	window.alert('해당 경로 길찾기를 할 수 없습니다.');
	    }
	});
}
//DAY1~7버튼 클릭시 schedule detail box 초기화 메소드
//선택된 일정 삭제 버튼 누를 시 더이상 남은 일정이 없을시 empty detail box 출력 기능.
function initScheduleDetailBox(day){
	let count = 0;
	$('.schedule-detail-box').children().hide();
	if(day === null){
		$('.schedule-detail-box').children('.empty-detail-box').show();
	}else{
		let items = $('.schedule-item-wrapper');
		for(let item of items){
			let itemDay = $(item).attr('name');
			if(day === itemDay){
				count++;
				$(item).show();
			}
		}
		if(count === 0){initScheduleDetailBox(null);}
	}
}
//city list에서 item 선택시 schedule detail box 안에 넣는 메소드
function insertItemInScheduleDetailBox(id,img,name,addr,day,mapxy){
	$('.item-detail-box').css('display','inline-block');
	$('.item-detail-box').append('<div id="'+id+'" name="'+day+'" class="schedule-item-wrapper">'
			+ '<div class="schedule-item-img" name="'+mapxy+'"><img style="width:60px; height:60px;" src="'+img+'" ></div>'
			+ '<div class="schedule-item-name">' + name
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
							myItem[i].title, myItem[i].firstimage,theme,myItem[i].contentid);
					$('.selected-theme-list').append('<div id="'+myItem[i].contentid+'" class="day-spot-item">'
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
	//$('#map').css('width', '100%');
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
		var mm = dateText.getMonth();
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
		var id = '#'+localmarker.id;
		var scroll_h = $('.selected-theme-list').scrollTop()+$(id).offset().top;
		$('.selected-theme-list').animate({
			scrollTop: scroll_h-277.5},500);
	});
	google.maps.event.addListener(localmarker, 'mouseover', function() {
		infoWindow.open(map, localmarker);
		MarkerHoverColorChange(localmarker.id,'in');
	});
	google.maps.event.addListener(localmarker, 'mouseout', function() {
		infoWindow.close();
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
function addTourMarker(xpos, ypos, tourContent, tourImg, theme, contentId) {
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
		content :'<div style="display:inline-block;font-size:15px;font-weight:bold;width:150px;margin-bottom:10px;">' + tourContent + '</div>' + '<br/>'
				+ '<div style="display:inline-block;margin-right:10px;">'
				+'<img style="width:150px;height:100px;" src="' + tourImg
				+ '"' + '<div>',
		map : map
	});
	tourMarkers.push(marker);
	tourMarkerListener(marker, infoWindow);
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
}
function setTourMarkerMapOnAll(map) {
	for (i = 0; i < tourMarkers.length; i++) {
		tourMarkers[i].setMap(map);
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