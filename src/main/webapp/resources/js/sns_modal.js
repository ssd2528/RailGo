$(document).ready(function(){
	var sns_modal = $('#sns-modal');
	var closeBtn = $('.sns-close');
	
	// 게시글 쓰기 모달창
	$('.sns-write-img').on("click",function(){
		$('input[name="uploadFile"]').val('');
		
		$('.sns-uploadResult ul li').remove();
		$("#sns-write-modal").css('display','flex');
		$('html').css('overflow', 'hidden');
		$('.modal-title').text('게시글 작성');	
		$('.sns-write-content').text('');
		$('.ok-button').text('작성');
		$('label[for="sns_file"]').css('display', 'inline-block');
		setTimeout(function() {
			$("#sns-write-modal").addClass('open');
		}, 1)
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});
	
	// 글 수정 모달창
	$('.sns-content-edit').click(function(){
		var sns_code = $(this).closest('div.article-item').find('input[name="sns_code"]').val();
		formData = new FormData;
		formData.append('sns_code', sns_code);
		
		$.ajax({
			url:'../sns/modifyContent',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result){
				console.log(result);
				var content = result.content.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
				$('.sns-write-content').val(content);
			}
		});
		
		$("#sns-write-modal").css('display','flex');
		$('html').css('overflow', 'hidden');
		$('.modal-title').text('게시글 수정');
		$('.ok-button').text('수정');
		$('.sns_code').val(sns_code);
		$('label[for="sns_file"]').css('display', 'none');
		
		var html = '<li>수정시에는 이미지 변경을 하실 수 없습니다.</li>';
		$('.sns-uploadResult ul').append(html)
		setTimeout(function() {
			$("#sns-write-modal").addClass('open');
		}, 1)
		$('body').css({'overflow':'hidden', 'height':'100%'});
	});
	
	// 글 쓰기 및 수정
	$('.ok-button').click(function(e){
		// 글 수정
		if($('.ok-button').text()=='수정') {
			formData = new FormData();
			
			var sns_code = $('input[name="sns_code"]').val();
			var text = $('.sns-write-content').val(); 
			var content = text.replace(/(\n|\r\n)/g, '<br>');
			var mem_code = $('input[name="mem_code"]').val();
			
			console.log(content);
			console.log(sns_code);
			console.log(mem_code);
			
			formData.append('content', content);
			formData.append('sns_code', sns_code);
			formData.append('mem_code', mem_code);
			
			$.ajax({
				url:'../sns/modify',
				processData: false,
				contentType: false, 
				data: formData,
				type: 'POST',
				dataType: 'text',
				success: function(result){
					console.log(result);
					alert("게시글 수정 완료");
					location.href='../sns/sns';
				}
			});
			
		// 글 쓰기 및 이미지 업로드	
		}else {
			var mem_code = $('input[name="mem_code"]').val();
			var text = $('.sns-write-content').val();
			var content = text.replace(/(\n|\r\n)/g, '<br>');
			writeData = new FormData();
			writeData.append('mem_code', mem_code);
			writeData.append('content', content);
			
			if($('.sns-write-content').val().length < 10) {
	            alert('내용은 10글자 이상 입력해주세요.');
	            return false;
		    }
			if(img_files.length==0){
				alert('이미지가 한장 이상 존재해야 합니다.');
				return false;
			}
			var formData = new FormData();
			for(var i=0, len=img_files.length; i<len; i++){
				var uploadFile = 'image_'+i;
				formData.append('uploadFile', img_files[i]);
			}
			$.ajax({
				url:'/sns/register',
				processData: false,
				contentType: false, 
				data: writeData,
				type: 'POST',
				dataType: 'text',
				success: function(result){
					console.log(result);
				}
			});
			$.ajax({
				url:'/sns/upload',
				processData: false,
				contentType: false, 
				data: formData,
				type: 'POST',
				dataType: 'text',
				success: function(result){
					console.log(result);
				}
			})
			alert("게시글 작성 완료");
			location.href='../sns/sns';
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
	
	$('#sns_file').on('change', handleImgsFilesSelect);
	
});
var img_files=[];
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
			var img_html = "<li><img src=\""+e.target.result+"\"id=\"img-"+index+"\"/></li>";
			$('.sns-uploadResult ul').append(img_html);
			index++;
		}
		reader.readAsDataURL(f);
	});
}