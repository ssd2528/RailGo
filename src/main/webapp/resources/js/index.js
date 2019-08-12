$(document).ready(function(){
	
	// '어디로 가시나요' 클릭시 search-modal 보이게 하기
	$('.search-city').on("click",function(){
		   $(".search-modal").css('display','flex');
		   setTimeout(function() {
		      $(".search-modal").addClass('show-modal');
		      $('.search-text').focus();
		   }, 1)
		   $('body').css({'overflow':'hidden', 'height':'100%'});
	});	
	// search-modal에서 x버튼 클릭시 modal 안보이게 하기
	$('.close-btn').on('click', function(){ 
		$('.search-modal').removeClass('show-modal'); 
		$('body').css({'overflow':'auto', 'height':'100%'});
        setTimeout(function() {
            $('.search-modal').css('display','none');
         }, 50)
	});
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
	   if($('.search-modal').hasClass('show-modal')){ // site 라는 특정영역이 열려있을 경우
	      if(!$('.search-modal').has(e.target).length){ // site에 클릭 이벤트가 발생되어 있는게 없다면 아래 내용을 실행.
	         $('.search-modal').removeClass('show-modal');
	         $('body').css({'overflow':'auto', 'height':'100%'});
	         setTimeout(function() {
	            $('.search-modal').css('display','none');
	         }, 50)
	      }
	   }
	});
	// 지역별 중 하나 클릭 시 하위 도시들 보이게 하기 
	$('.region-item').bind('click change',function(){
		if( $(this).children('.region-city-list').length > 0 ) {
			$(this).children('.region-city-list').toggleClass("show-city-list");
			$('.region-item').not(this).toggleClass("show-region-item");
			$(this).children('.toggle-btn').text(
					$(this).children('.toggle-btn').text() == '<' ? '>' : '<'
			);
		} else {
			var areaName = $(this).text(); // 클릭한 도시명
			location.href='info/'+areaName;
		}
	});
	
	// search-modal에서 지역들 중 도시 클릭했을 때,
	$('.region-city-item').on('click', function(){
		var areaName = $(this).text(); // 클릭한 도시명
		location.href='info/'+areaName;
	});
	// 하위 도시 클릭시 파라미터 전달
	$('.region-city-town').on('click', function(){
		var city = $(this).text();
		
		var url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=9hi5gaFATBRP5Ao7ugWapwfwEF4hOqDiWVFbI1dwctd5kqmpjkUIE7tjPbD9Sqh6a2kxKi4X7b%2F%2FugubodKq4A%3D%3D&numOfRows=3&MobileApp=RailGo&MobileOS=ETC&arrange=P&contentTypeId=39&areaCode=1&_type=json";
		$.getJSON(url, null, function(data, status){
            alert(status);
            $.each(data, function(index, entry){
                alert(entry.body.items.item[0].contentid);
                for(var i=0; i<3; i++){
                	var form = { 
                			addr1 : entry.body.items.item[i].title, 
                			id : entry.body.items.item[i].contentid
                	};
                };
                $.ajax({ 
                	url: "/info/"+city, 
                	method: "post", 
                	type: "json", 
                	contentType: "application/json", 
                	data: JSON.stringify(form), 
                	success: function(data) { 
                		console.log(data); 
                	} 
                });
            });
        });
		//location.href = "/info/"+city;
	});
});