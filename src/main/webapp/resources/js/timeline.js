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
});


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


