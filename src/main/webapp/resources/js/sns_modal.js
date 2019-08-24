$(document).ready(function(){
	var sns_modal = $('#sns-modal');
	var closeBtn = $('.sns-close');
	// 게시글 상세보기
	$('.sns-content-modal').on('click', function(){
		var sns_code = $(this).closest('div.article-item').find('input[name="sns_code"]').val();
		console.log(sns_code);
		var param = {'sns_code':sns_code};
		$.ajax({
			url:'/sns/content',
			type: 'POST',
			data: JSON.stringify(param),
			contentType: 'application/json',
			dataType: 'json',
			success: function(result){
				console.log(result);
				$('.sns-writer-name').text(result.name);
				$('.sns-write-date').text(result.regDate);
				/*$('.sns-substance').text(result.content);*/
				for(var i = 0; i<result.imgList.length; i++){
					$('.sns-substance').text(result.imgList[i].fileName);
					console.log(result.imgList[i].fileName);
					console.log(result.imgList[i].uuid);
				}
			},
			error: function(xhr) {
				return false;
			}
		});
		sns_modal.css('display', 'block');
		$('html').css('overflow', 'hidden');
		$('.sns-modal-content').show();
	});
	
	// 게시글 쓰기 모달창
	$('.sns-write-img').on("click",function(){
		console.log('버튼 클릭');
		
		$("#sns-write-modal").css('display','flex');
		$('html').css('overflow', 'hidden');
		$('.modal-title').text('게시물 작성');	
		$('.sns-write-content').text('');
		$('.ok-button').text('작성');
		setTimeout(function() {
			$("#sns-write-modal").addClass('open');
		}, 1)
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});
	
	// 글 쓰기 및 수정
	$('.ok-button').click(function(e){
		if($('.ok-button').text()=='수정') {
			var snscode = $('#sns-forms').children('.sns_code').val();
			$('#sns-form').children('.sns_code').val(snscode);
			$('.mod_content').val($('.sns-write-content').val());
			$('.sns-form').attr('action','../sns/modify');
			$(this).closest('form').submit();
			alert("게시글 수정 완료");
		}else {
			// 이미지 업로드
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			for(var i = 0; i < files.length; i++){
				formData.append("uploadFile", files[i]);
			}
			$.ajax({
				url:'/sns/upload',
				processData: false,
				contentType: false, 
				data: formData,
				type: 'POST',
				dataType: 'json',
				success: function(result){
					console.log(result);
				}
			})
			alert("게시글 작성 완료");
		}
	});
	
	// 바깥 화면 클릭시 modal 창 닫기
	$('body').click(function(e){
		if($('#sns-write-modal').hasClass('open')){ // site 라는 특정영역이 열려있을 경우
			if(!$('#sns-write-modal').has(e.target).length){ // site에 클릭 이벤트가
				// 발생되어 있는게 없다면 아래
				// 내용을 실행.
				$('#sns-write-modal').removeClass('open');
				$('.sns-write-content').val('');
				$('body').css({'overflow':'auto', 'height':'100%'});
				setTimeout(function() {
					$('#sns-write-modal').css('display','none');
				}, 50)
			}
		}
	});
	
	// 모달창 닫기
	closeBtn.on('click', function(){
		sns_modal.css('display', 'none');
		$('form').each(function(){this.reset();})
		$('html').css('overflow', 'auto');
	});
	
	// 닫기 버튼 클릭시 modal 창닫기
	$('.close-button').click(function(e){
		$('#sns-write-modal').removeClass('open');
		$('.sns-write-content').val('');
		$('body').css({'overflow':'auto', 'height':'100%'});
		setTimeout(function() {
			$('#sns-write-modal').css('display','none');
		}, 50)
	});
	
	// 게시글 삭제
	$('.sns-content-delete').on('click', function(e) {
		var result = confirm("정말 삭제하시겠습니까? \n(삭제된 기록은 복구가 불가능합니다.)");
		if(result){
			$('.sns-form').attr('action','../sns/remove');
			$(this).closest('form').submit();
			alert("게시글 삭제 완료");
		}else if(!result){
			return false;
		}
	});
	
	var img_files=[];
	$('#sns_file').on('change', handleImgsFilesSelect);
});
//이미지 선택 시 썸네일 생성
function handleImgsFilesSelect(e){
	img_files=[];
	$('.sns-uploadResult ul').empty();
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
				img_html += "<a href=\"javascript:void(0);\" onclick=\"deleteImage("+index+")\" id=\"img-"+index+"\">삭제</a></li>";
			$('.sns-uploadResult ul').append(img_html);
			index++;
		}
		reader.readAsDataURL(f);
	});
}

// 썸네일 삭제
function deleteImage(index) {
	console.log("index: " + index);
	img_files.splice(index, 1);
	
	var img_id = "#img-"+ index;
	$(img_id).remove();
	$(img_id).hide();
	console.log(img_files);
	
}