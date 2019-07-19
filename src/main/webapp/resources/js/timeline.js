//소개 창에 hover시 수정아이콘 출력
$(document).ready(function(){
	$('#profileUpdate').css('visibility', 'hidden');
	$('.article-sns-user').hover(function(){
		$('#profileUpdate').css('visibility', 'visible');
	},function(){
		$('#profileUpdate').css('visibility', 'hidden');
	})
});

//프로필사진에 hover시 수정아이콘 출력
$(document).ready(function(){
	$('#profilePicUpdate').css('visibility', 'hidden');
	$('.section-profilebg').hover(function(){
		$('#profilePicUpdate').css('visibility', 'visible');
	},function(){
		$('#profilePicUpdate').css('visibility', 'hidden');
	})
});

$(document).ready(function(){
    $("#current").click(function(){
        var submenu = $(this).next("ul");

        // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
        if( submenu.is(":visible") ){
            submenu.slideUp();
        }else{
            submenu.slideDown();
        }
    })
});
$(document).ready(function(){
	$(".likeImg").click(function(){
		$(".likeImg").attr('src','../img/planner/heart_clicked.png')
	})	
});
