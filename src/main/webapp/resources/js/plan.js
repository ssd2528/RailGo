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
	var planDateBox='plan-date-list1';
	// ------ page init start ----------------
	calDate(dateText, day);
	$('.plan-date-box').children('.plan-date-list1').css('background-color','#009ce9');
	toggleCityList(80,350,'show');
	var str = $('.'+planDateBox).find('.region').text();
	setZoomforSelectedLoc(str);
	setTitleName(str);
	// ------ page init end-------------------
	
	//DAY1~DAY7 버튼 누를 때 색상 변경 이벤트
	$('.plan-date-box').children().on('click', function() {// #009ce9
		$('.plan-date-box').children().css('background-color', '#7F7F7F');
		$(this).css('background-color', '#009ce9');
		var locName = $(this).find('.region').text();
		planDateBox = $(this).attr('class');
		setZoomforSelectedLoc(locName);
		setTitleName(locName);
	});
	// > 버튼 누르면 검색 도시 리스트 출력
	$('.list-cover').on('click', function(){
		toggleCityList(80,350,'toggle');
	});
	// DAY 누를 시 도시 리스트(지역 선택 리스트) 출력
	$('.plan-date-box').children('li').on('click', function(){
		toggleCityList(80,350,'show');
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
		$('.title-name').text(name);
		$('.'+planDateBox).find('.region').text(name);
		toggleSelectCity();
		setZoomforSelectedLoc(name);
		setSelectedTheme('accom');
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
});
var map;
var markers = [];
var tourMarkers = [];
var stationLocations = [];
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
					if(myItem[i].firstimage == null){myItem[i].firstimage = '../img/default.png';}
					addTourMarker(myItem[i].mapx, myItem[i].mapy,
							myItem[i].title, myItem[i].firstimage);
					$('.selected-theme-list').append('<div class="day-spot-item">'
							+'<div class="item-img-box"><img class="img-size" src='+myItem[i].firstimage+'>'
							+'</div><div class="item-info-box">'+myItem[i].title+'<div class="item-sub">'+myItem[i].addr1+'<br/>'+myItem[i].tel+'</div></div>'
							+'<div class="item-insert-box"><img style="width:30px; height:30px" src="../img/planner/wh_plus.png"></div></div>');
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
//list-title의 자식 title-name 텍스트 설정 메소드(DAY1~DAY7버튼 눌렀을때 정보에 따라 변경됨)
function setTitleName(loc){
	if(loc == '지역을 선택하세요.'){
		$('.title-name').text(loc + '▼');
		setSelectedTheme(null);
	}else{
		$('.title-name').text(loc);
		setSelectedTheme('accom');
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
	// list 출력시 속도 80, 감출 때 속도 100 /  list 출력시 map 위치 350px, 감출 때 map 위치 0px
	$('.city-list').animate({
	      width: tog
	},setTime);
	$('.map-wrap').css('left', mapPos+'px');
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
		map.setCenter(localmarker.getPosition());
		infoWindow.close();
	});
	google.maps.event.addListener(localmarker, 'mouseover', function() {
		infoWindow.open(map, localmarker);
	});
	google.maps.event.addListener(localmarker, 'mouseout', function() {
		infoWindow.close();
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
function addTourMarker(xpos, ypos, tourContent, tourImg) {
	// console.log('x : '+xpos+', y : '+ypos+', c: '+tourContent);
	var marker = new google.maps.Marker({
		position : new google.maps.LatLng(ypos, xpos),
		map : map,
		icon : {
			url : "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
		}
	});
	var infoWindow = new google.maps.InfoWindow({
		content : '<h2 style="size:10px;">' + tourContent + '</h2>' + '<br/>'
				+ '<img style="width:250px;height:180px;" src="' + tourImg
				+ '"' + '</img>',
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
							myItem[i].title, myItem[i].firstimage);
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