$(document).ready(function(){
	//소개 창에 hover시 수정아이콘 출력
	$('#profileUpdate').css('visibility', 'hidden');
	$('#profilePicUpdate').css('visibility', 'hidden');
	
	$('.article-sns-user').hover(function(){
		$('#profileUpdate').css('visibility', 'visible');
	},function(){
		$('#profileUpdate').css('visibility', 'hidden');
	})
	//프로필사진에 hover시 수정아이콘 출력
	$('.section-profilebg').hover(function(){
		$('#profilePicUpdate').css('visibility', 'visible');
	},function(){
		$('#profilePicUpdate').css('visibility', 'hidden');
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
    })
    
    //좋아요 클릭시 하트이미지 바꿈
	$(".likeImg").click(function(){
		var img = $(this).attr('src');
		if(img === '../img/planner/heart_normal.png'){
			$(".likeImg").attr('src','../img/planner/heart_clicked.png');
		}else{
			$(".likeImg").attr('src','../img/planner/heart_normal.png');
		}
	});
});

