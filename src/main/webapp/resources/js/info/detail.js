$(document).ready(function() {
	// 리뷰 별점 입력
	$('#example').barrating({
		theme: 'fontawesome-stars' 
	});
	$('#example').barrating({
		theme: 'fontawesome-stars' 
		, initialRating: 1
	});
	
	// 근처 item 클릭시 detail로 이동
	$('.recommend-list').click(function(){
		$(this).find('.detailForm').submit();
	});
	
	// 게시글 댓글 등록 이후 새로고침
	$('.review-register-btn').click(function(){
		if($('.mem_code').val()==null){
			alert("세션이 만료되었습니다. 다시 로그인 해주세요.");
			return;
		}
        if($('.review-textarea').val().length < 10) {
            alert("10글자 이상 입력해주세요.");
            return;
        }	
        
		var formData = $('.review-form').serialize();
		$.ajax({
			url:'/info/insertReview', 
			data: formData,
			type: 'POST',
			success: function(data){
				console.log(data);
			}
		});
		$('.page-reload').submit();
		sendAlarm($('.review-textarea').val()); // 알람기능 보내기 ※
		
		
	});
	
	// 이미지 업로드
	$("#uploadBtn").on("click", function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		console.log(files);
		for(var i = 0; i < files.length; i++){
			formData.append("uploadFile", files[i]);
		}
		$.ajax({
			url:'/info/upload',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result){
				console.log(result);
			}
		})
	})
	var img_files=[];
	$('#review_file').on('change', handleImgsFilesSelect);
	
	// 리뷰 삭제
	$('.review-del-btn').on('click', function(){
		var rCode = $(this).closest('div.review-table').find('input[name="review-rCode"]').val();
		console.log(rCode);
		var param = {'r_code':rCode};
		var check = confirm("정말 삭제하시겠습니까? \n(삭제된 리뷰는 복구가 불가능합니다.)");
		if(check){
			$.ajax({
				url:'/info/deleteReview',
				data: JSON.stringify(param),
				contentType: 'application/json',
				type: 'POST',
				dataType: 'text',
				success: function(result){
					console.log(result);
				}
			})

			$('.page-reload').submit();
		}else if(!check){
			return false;
		}
	});
});
// 이미지 선택 시 썸네일 생성
function handleImgsFilesSelect(e){
	img_files=[];
	$('.review-uploadResult ul').empty();
	var index = 0;
	var files = e.target.files;
	var fileSize = e.target.files[0].size;
	var filesArr = Array.prototype.slice.call(files);
	var maxSize = 5242880; //5MB
	
	if(filesArr.length>=5) {
		alert("파일의 갯수는 4개를 초과할 수 없습니다.");
		$('#review_file').val("");
		return;
	}
	filesArr.forEach(function(f){
		if(f.size > maxSize){
			alert("용량제한을 초과하였습니다. (5MB)");
			return;
		}
		if(!f.type.match('image.*')){
			alert("이미지 파일만 등록이 가능합니다!");
			return;
		}
		
		img_files.push(f);
		
		var reader = new FileReader();
		reader.onload = function(e) {
			var img_html = "<li><img src=\""+e.target.result+"\"id=\"img-"+index+"\"/>";
			$('.review-uploadResult ul').append(img_html);
			index++;
		}
		reader.readAsDataURL(f);
	});
}



// 알람 보내기 기능
var websocket;
websocket = new WebSocket("ws://localhost:8080/socket"); // 웹 소켓을 지정한 URL로 연결
websocket.onopen = function(){ console.log("연결 성공"); }
websocket.onmessage = function(e) {
	var text = e.data;
	console.log(text);
}
websocket.onclose = function(e) { console.log("연결 끊김"); }
function sendAlarm(review) {
	websocket.send(review);
}