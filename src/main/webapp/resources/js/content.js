$(document).ready(function(){
	var alarm = $('.alarm-img');
	var sns = $('.sns-img'); 
	var planner = $('.planner-img');
	var member = $('.member-img');	
	
	$('.sns-img img').attr('src', '../img/header/sns_clicked.png');
	sns.css('color', '#009CE9');
	sns.closest('li').css('border-bottom', 'solid 3px #009CE9');
	
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
				html += '</div>';
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
			url:'/sns/insertReply',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result){
				location.href='../sns/content?sns_code='+result.sns_code;
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
		var sns_code = $('.reply-snsCode').val();
		var mem_code = $('.reply-memCode').val();
		var like_count = $('.sns-heart-count');
		var count = +($('span.like-count').text());
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
			url:'/sns/snsLike',
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
			url:'/sns/commDelete',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType : 'text',
			success: function(data){
				location.href='../sns/content?sns_code='+sns_code;
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
		formData.append('comm_code', reply_commCode);
		
		$.ajax({
			url:'/sns/commDelete',
			processData: false,
			contentType: false, 
			data: formData,
			type: 'POST',
			dataType : 'text',
			success: function(data){
				location.href='../sns/content?sns_code='+sns_code;
			}
		})
	});
});