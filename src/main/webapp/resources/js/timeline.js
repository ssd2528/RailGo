//타임라인, 일정관리 탭 css
$(document).ready(function(){
	$('.timeline').css('border-bottom','4px solid #009CE9');
	$('.schedule').css('border-bottom','none');
});

// 소개 창에 hover시 수정아이콘 출력
$(document).ready(function(){
	$('.profileUpdate').css('visibility', 'hidden');
	$('.article-sns-user1').hover(function(){
		$('.profileUpdate').css('visibility', 'visible');
	},function(){
		$('.profileUpdate').css('visibility', 'hidden');
	})
	$('.article-sns-user2').hover(function(){
		$('.profileUpdate').css('visibility', 'visible');
	},function(){
		$('.profileUpdate').css('visibility', 'hidden');
	})
});
// 프로필사진에 hover시 수정아이콘 출력
$(document).ready(function(){
	$('#back-img-update').css('visibility', 'hidden');
	$('.section-back-img').hover(function(){
		$('#back-img-update').css('visibility', 'visible');
	},function(){
		$('#back-img-update').css('visibility', 'hidden');
	})
 
   $("#current").click(function(){
        var submenu = $(this).next("ul");
        // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
        if( submenu.is(":visible") ){
            submenu.slideUp();
        }else{
            submenu.slideDown();
        }
    })
    $('#sns-modify, #sns-delete').hover(function(){
    	$(this).css('color','#ed7d31');
    },function(){
    	$(this).css('color','black');
    });
    
    // 좋아요 클릭시 하트이미지 바꿈
	$(".likeImg").click(function(){
		var img = $(this).attr('src');
		if(img === '../img/planner/heart_normal.png'){
			$(".likeImg").attr('src','../img/planner/heart_clicked.png');
		}else{
			$(".likeImg").attr('src','../img/planner/heart_normal.png');
		}
	});
	
	//팔로우 확인 및 팔로우 버튼 바꾸기
	var followExist = $("input[name='followExist']").val();
	console.log(followExist);
	
	if(followExist == 0){
		$(".follow").css('background-color','#009CE9');
		$(".follow").css('color','white');
	}else if(followExist == 1){
		$(".follow").css('background-color','#ffffff');
		$(".follow").css('color','black');
		$(".follow").css('border','2px solid #009CE9');
		$(".follow").val('팔로우 해제');
	}
	
	// 팔로우버튼 클릭시 팔로우 및 언팔로우
	$(".follow").click(function(){
		
		var formData = new FormData();
		var formData2 = new FormData();
		var color = $(this).css('background-color');
		
		var mem_code = $("input[name='mem_code']").val();
		var following = $("input[name='following']").val();
			
			console.log(mem_code);
			console.log(following);
			console.log(followExist);
			
			for (var key of formData.keys()) {

				  console.log(key);

				}

			for (var value of formData.values()) {

			  console.log(value);

			}
			
		//console.log(color);
		if(followExist == 0){
			
			formData.append("mem_code",mem_code);
			formData.append("following",following);
			
			formData2.append("mem_code",mem_code);
			
				$.ajax({
				url:'/member/following',
				data:formData,
				type:'POST',
				//async: false,
				contentType: false,
				processData: false,
				success: function(result){
					 console.log(result);
					 //alert("팔로우");
					//window.location.replace('/member/other_user_info?mem_code='+following);
				},
				error:function(jqXHR, textStatus, errorThrown){
		            alert("앙 에러띠 \n" + textStatus + " : " + errorThrown);
		        }
			}); //$.ajax
				
				$.ajax({
				url:'/member/other_user_info',
				data:formData2,
				type:'POST',
				//async: false,
				contentType: false,
				processData: false,
				success: function(result){
					 //alert("변경");
					window.location.replace('/member/other_user_info?mem_code='+following);
				},
				error:function(jqXHR, textStatus, errorThrown){
		            alert("에러띠 \n" + textStatus + " : " + errorThrown);
		        }
			}); //$.ajax
			
		}else if(followExist == 1){
			
			formData.append("mem_code",mem_code);
			formData.append("following",following);
			
			formData2.append("mem_code",mem_code);
			
			$.ajax({
				url:'/member/unfollow',
				data:formData,
				type:'POST',
				contentType: false,
				processData: false,
				success: function(result){
					// alert("언팔로우");
					//window.location.replace('/member/other_user_info?mem_code='+following);
				},
				error:function(jqXHR, textStatus, errorThrown){
		            alert("앙 에러띠\n" + textStatus + " : " + errorThrown);
		        }
			}); //$.ajax
			
			$.ajax({
				url:'/member/other_user_info',
				data:formData2,
				type:'POST',
				//async: false,
				contentType: false,
				processData: false,
				success: function(result){
					// alert("변경");
					window.location.replace('/member/other_user_info?mem_code='+following);
				},
				error:function(jqXHR, textStatus, errorThrown){
		            alert("에러띠 \n" + textStatus + " : " + errorThrown);
		        }
			}); //$.ajax
		}
		
	});
	// 추가정보 수정중 텍스트필드 클릭시 값 비우기
	$(".profile-text1").click(function(){
		$(".profile-text1").val('');
	});
	$(".profile-text2").click(function(){
		$(".profile-text2").val('');
	});
	$(".profile-text3").click(function(){
		$(".profile-text3").val('');
	});
	$(".profile-text4").click(function(){
		$(".profile-text4").val('');
	});
	
	var sns = $('.sns-img'); 
	
	// sns 항시 이미지 변환
	$('.sns-img img').attr('src', '/img/header/sns.png');
	sns.css('color', '#000000');
	sns.closest('li').css('border-bottom', 'none');
	
	// SNS 상세보기
	$('.sns-content-modal').on('click', function(){
		var sns_code = $(this).closest('div.article-item').find('input[name="sns_code"]').val();
		location.href='../member/content?sns_code='+sns_code;
	});
	
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
			url:'../member/modifyContent',
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
				url:'../member/modify',
				processData: false,
				contentType: false, 
				data: formData,
				type: 'POST',
				dataType: 'text',
				success: function(result){
					console.log(result);
					alert("게시글 수정 완료");
					location.href='../member/timeline';
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
				url:'/member/register',
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
				url:'/member/upload',
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
			location.href='../member/timeline';
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
			$('.sns-form').attr('action','../member/remove');
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


// 추가정보 수정 클릭시 정보가 텍스트필드로 바뀜
$(document).ready(function(){
	$(".article-sns-user2").css("display","none");
	$(".profileUpdate").click(function(){
		$(".article-sns-user1").css("display","none");
		$(".article-sns-user2").css("display","");
		$(".profile-text1").selectRange(1);
	})
});
$.fn.selectRange = function(start, end) {

    return this.each(function() {

         if(this.setSelectionRange) {

             this.focus();

             this.setSelectionRange(start, end);

         } else if(this.createTextRange) {

             var range = this.createTextRange();

             range.collapse(true);

             range.moveEnd('character', end);

             range.moveStart('character', start);

             range.select();

         }

     });

 };
 // 엔터 submit
 function press(f){
	 if(f.keyCode == 13){ // javascript에서는 13이 enter키를 의미함
		 profile-content.submit(); // formname에 사용자가 지정한 form의 name입력
	 } 
 }
 // 배경화면 변경
 $(document).ready(function(){
	 $("#uploadFile").on("change", function(e) {
		 
		var formData = new FormData();
		
		var backImage = $("input[name='backImage']");
		var mem_code = $("input[name='mem_code']").val();
		var address = $("input[name='address']").val();
		var job = $("input[name='job']").val();
		var birth = $("input[name='birth']").val();
		var interest = $("input[name='interest']").val();
		var profile = $("input[name='profile']").val();
		
		var files = backImage[0].files;
		
		console.log(files);
		
		// add filedate to formdata
		for(var i = 0; i < files.length; i++){
			
			formData.append("uploadFile",files[i]);
			formData.append("mem_code",mem_code);
			formData.append("address",address);
			formData.append("job",job);
			formData.append("birth",birth);
			formData.append("interest",interest);
			formData.append("profile",profile);
			
		}
		
		$.ajax({
			url: '/member/updateMemImage',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			success: function(result){
				 //alert("변경 완료");
				window.location.replace('/member/timeline');
			}
		}); // $.ajax
		
	 });
 });
 // 프로필사진 변경
$(document).ready(function(){
		 $("#uploadFile2").on("change", function(e) {
			 
			var formData = new FormData();
			
			var profile = $("input[name='profile2']");
			var mem_code = $("input[name='mem_code2']").val();
			var address = $("input[name='address2']").val();
			var job = $("input[name='job2']").val();
			var birth = $("input[name='birth2']").val();
			var interest = $("input[name='interest2']").val();
			var backImage = $("input[name='backImage2']").val();
			
			var files = profile[0].files;
			
			console.log(files);
			
			// add filedate to formdata
			for(var i = 0; i < files.length; i++){
				
				formData.append("uploadFile",files[i]);
				formData.append("mem_code",mem_code);
				formData.append("address",address);
				formData.append("job",job);
				formData.append("birth",birth);
				formData.append("interest",interest);
				formData.append("backImage",backImage);
			}
			
			$.ajax({
				url: '/member/updateMemImage',
				processData: false,
				contentType: false,
				data: formData,
				type: 'POST',
				success: function(result){
					// alert("변경 완료");
					window.location.replace('/member/timeline');
				}
			}); // $.ajax
			
		 });
	});

$(document).ready(function(){
	var alarm = $('.alarm-img');
	var planner = $('.planner-img');
	var member = $('.member-img');	
	
	// planner hover시 이미지 변환 
	planner.hover(function(){
		$('.planner-img img').attr('src', '../img/header/planner_clicked.png');
		planner.css('color', '#009CE9');
	}, function(){
		$('.planner-img img').attr('src', '../img/header/planner.png');
		planner.css('color', 'black');
	});
	
	// 이미지 슬라이더
	$('.bxslider').bxSlider({
        speed: 500,  // 애니메이션 속도
        mode: 'horizontal', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
        pager: false,
        slideHeight: 500,
        infiniteLoop: false,
        hideControlOnEnd: true
    });
	
	// 리플 버튼 활성화
	$('.sns-reply-textarea').on('input', function(){
		if($('.sns-reply-textarea').val().length==0){
			$('.sns-reply-btn').attr('disabled',true);
			$('.sns-reply-btn').css('color','#96ccff');
			$('.sns-reply-btn:hover').css('cursor','default');
		}else {
			$('.sns-reply-btn').attr('disabled',false);
			$('.sns-reply-btn').css('color','#009CE9');
		}
	})
	
	// 대댓글 버튼 활성화
	$(document).on('input','.sns-rereply-textarea',function(){
		if($('.sns-rereply-textarea').val().length==0){
			$('.sns-rereply-btn').attr('disabled',true);
			$('.sns-rereply-btn').css('color','#96ccff');
			$('.sns-rereply-btn:hover').css('cursor','default');
		}else {
			$('.sns-rereply-btn').attr('disabled',false);
			$('.sns-rereply-btn').css('color','#009CE9');
		}
	})
	
	// 엔터키 댓글 입력처리
	$('.sns-reply-textarea').keypress(function(event){
		if(event.which==13){
			$('.sns-reply-btn').click();
			$('.sns-reply-textarea').val('');
			$('.sns-reply-btn').attr('disabled',true);
			$('.sns-reply-btn').css('color','#96ccff');
			$('.sns-reply-btn:hover').css('cursor','default');
			return false;
		}
	});
	
	// 엔터키 대댓글 입력처리
	$(document).on('keypress','.sns-rereply-textarea',function(event){
		if(event.which==13){
			$('.sns-rereply-btn').click();
			$('.sns-rereply-textarea').val('');
			$('.sns-rereply-btn').attr('disabled',true);
			$('.sns-rereply-btn').css('color','#96ccff');
			$('.sns-rereply-btn:hover').css('cursor','default');
			$('.rerebox').css('display', 'none');
			return false;
		}
	});
	
	// 댓글 입력
	$('.sns-reply-btn').on('click', function(){
		var content = $('.sns-reply-textarea').val();
		var mem_code = $('.reply-memCode').val();
		var sns_code = $('.reply-snsCode').val();
		var comm_code = 0;
		
		console.log(content);
		console.log(mem_code);
		console.log(sns_code);
		
		
		if(content.length < 5) {
            alert('내용은 5글자 이상 입력해주세요.');
            return false;
	    }
		if(mem_code==''){
			alert('로그인을 해주세요.');
			return false;
		}
		
		formData = new FormData();
		formData.append('content', content);
		formData.append('mem_code', mem_code);
		formData.append('sns_code', sns_code);
		formData.append('comm_code', comm_code);
		
		var html = '';
		
		$.ajax({
			url:'/sns/insertReply',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result){
				html += '<div class="sns-rereply-append">';
				html += '<div class="sns-reply-box">';
				if(result.profile!=null){
					html += '<img class="sns-replyer-profile" src="/member/display?fileName='+ result.profile +'" alt="프로필">';
				}else if(result.profile==null){
					if(result.gender=='M'){
						html += '<img class="sns-replyer-profile" src="/img/member/default_profile_m.png" alt="프로필 남" >';
					}else if(result.gender=='F'){
						html += '<img class="sns-replyer-profile" src="/img/member/default_profile_f.png" alt="프로필 여" >';
					}
				}
				html += '<table class="sns-reply-content">';
				html += '<tr>';
				html += '<th class="sns-reply-writer">'+ result.name +'</th>';
				html += '<td class="sns-reply-substance">'+ result.content +'</td>';
				html += '<td class="delete-btn reply-delete-btn">삭제</td>';
				html += '</tr>';
				html += '<tr>';
				html += '<th class="sns-reply-reply">';
				html += '<span class="sns-reply-write">답글달기</span></th>';
				html += '<td class="sns-reply-date">';
				html += result.regDate;
				html += '</td>';
				html += '</tr>';
				html += '</table>';
				html += '<input type="hidden" class="reply-commCode" name="comm_code" value="'
				html += result.comm_code;
				html +=	'">';
				html += '</div></div>';
				$('.sns-reply-container').append(html);
			}
		})
		$('.sns-reply-textarea').val('');
		$('.sns-reply-btn').attr('disabled',true);
		$('.sns-reply-btn').css('color','#96ccff');
		$('.sns-reply-btn:hover').css('cursor','default');
	});
	
	// 말풍선 클릭시 텍스트에어리어 포커스
	$('.sns-chat').on('click', function(){
		$('.sns-reply-textarea').focus();
	});
	
	// 답글달기 클릭시 테이블 생성
	$(document).on('click','span.sns-reply-write',function(){
		$('.rerebox').css('display', 'none');
		console.log('click');
		var gender = $('input[name="member-gender"]').val();
		var profile = $('input[name="member-profile"]').val();
		
		var reReply = $(this).closest('div.sns-rereply-append');
		var originCode = $(this).closest('div.sns-reply-box').find('input[name="comm_code"]').val();
		var html = '';
		console.log(originCode);
		
		html += '<div class="sns-rereply rerebox">';
		html += '<div class="sns-rereply-left">';
		html += '<img class="sns-rereply-icon" src="/img/sns/rereply.png">';
		if(profile!=''){
			html += '<img class="sns-rereplyer-profile" src="/member/display?fileName='+ profile +'" alt="프로필">';
		}else if(profile==''){
			if(gender=='M'){
				html += '<img class="sns-rereplyer-profile" src="/img/member/default_profile_m.png" alt="프로필 남" >';
			}else if(gender=='F'){
				html += '<img class="sns-rereplyer-profile" src="/img/member/default_profile_f.png" alt="프로필 여" >';
			}else {
				html += '<img class="sns-rereplyer-profile" src="/img/member/default_profile_m.png" alt="프로필 남" >';
			}
		}
		html += '</div>';
		html += '<span class="sns-rereply-input">';
		html += '<textarea class="sns-rereply-textarea" rows="1" name="rereply" placeholder="&nbsp;&nbsp; 대댓글 달기..."></textarea>';
		html += '<input type="button" class="sns-rereply-btn" value="게시" />';
		html += '</span>';
		html += '</div>';
		reReply.append(html);
	});
	
	// 대댓글 입력
	$(document).on('click','.sns-rereply-btn',function(){
		$('.rerebox').css('display', 'none');
		var content = $('.sns-rereply-textarea').val();
		var mem_code = $('.reply-memCode').val();
		var sns_code = $('.reply-snsCode').val();
		var comm_code = $(this).closest('div.sns-rereply-append').find('input[name="comm_code"]').val();
		var rereply_append = $(this).closest('div.sns-rereply-append>div:last');

		console.log(content);
		console.log(sns_code);
		console.log(mem_code);
		console.log(comm_code);
		
		if(content.length < 5) {
            alert('내용은 5글자 이상 입력해주세요.');
            return false;
	    }
		
		if(mem_code==''){
			alert('로그인을 해주세요.');
			return false;
		}
		
		formData = new FormData();
		formData.append('content', content);
		formData.append('mem_code', mem_code);
		formData.append('sns_code', sns_code);
		formData.append('comm_code', comm_code);
		
		var html = '';
		
		$.ajax({
			url:'/member/insertReply',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result){
				location.href='../member/content?sns_code='+result.sns_code;
			}
		})
	});
	
	// 좋아요 클릭
	$(document).on('click','#sns-heart',function(){
		var mem_code = $('.reply-memCode').val();
		if(mem_code==''){
			alert('로그인을 해주세요.');
			return false;
		}
		
		console.log('좋아욧~');
		var sns_code = $(this).closest('div.article-item').find('input[name="sns_code"]').val();
		var like_count = $(this).closest('div.article-item').find('.sns-heart-count');
		var count = +($(this).closest('div.article-item').find('span.like-count').text());
		var check = '';
		
		// 좋아요 추가
		if($(this).attr('class') === 'sns-icon sns-heart'){
			$(this).attr('src', '../img/sns/heart_clicked.png');
			$(this).attr('class', 'sns-icon sns-heart-clicked');
			$('span.like-count').text(count+1);
			if((count+1)==1){
				$(like_count).css('display', 'inline');
			}
			check = 'plus';
		// 좋아요 취소	
		}else{
			$(this).attr('src', '../img/sns/heart.png');
			$(this).attr('class', 'sns-icon sns-heart');
			$('span.like-count').text(count-1);
			if((count-1)==0){
				$(like_count).css('display', 'none');
			}
			check = 'minus';
		}
		formData = new FormData();
		formData.append('check', check);
		formData.append('sns_code', sns_code);
		formData.append('mem_code', mem_code);
		console.log(mem_code);
		console.log(sns_code);
		console.log(check);
		$.ajax({
			url:'/member/snsLike',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType : 'text',
			success: function(data){
				console.log(data);
			}
		})
	});
	
	// 댓글 삭제
	$(document).on('click','.reply-delete-btn',function(){
		var mem_code = $('.reply-memCode').val();
		var sns_code = $('.reply-snsCode').val();
		if(mem_code==''){
			alert('로그인을 해주세요.');
			return false;
		}
		
		var reply_commCode = $(this).closest('div.sns-reply-box').find('input[name="comm_code"]').val();
		
		console.log(reply_commCode);
		
		formData = new FormData();
		formData.append('comm_code', reply_commCode);
		
		$.ajax({
			url:'/member/commDelete',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType : 'text',
			success: function(data){
				location.href='../member/content?sns_code='+sns_code;
			}
		})
	});
	
	// 대댓글 삭제
	$(document).on('click','.rereply-delete-btn',function(){
		var mem_code = $('.reply-memCode').val();
		var sns_code = $('.reply-snsCode').val();
		if(mem_code==''){
			alert('로그인을 해주세요.');
			return false;
		}
		
		var rereply_commCode = $(this).closest('div.sns-rereply-right').find('input[name="comm_code"]').val();
		
		console.log(rereply_commCode);
		
		formData = new FormData();
		formData.append('comm_code', rereply_commCode);
		
		$.ajax({
			url:'/member/commDelete',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType : 'text',
			success: function(data){
				location.href='../member/content?sns_code='+sns_code;
			}
		})
	});
	
	// x버튼 클릭 -> sns 페이지 이동
	$('.close').on('click', function(){
		location.href="../member/timeline";
	});
});


