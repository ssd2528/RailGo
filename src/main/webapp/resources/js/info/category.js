// CheckBox 
$(document).ready(function(){
	var category = $('#category').val();
	var areaName = $('#areaName').val();
	
	/********************************* [숙박, 맛집] CheckBox - Category *********************************/
	$('input[type="checkbox"][name="chk_category"]').click(function(){
		if ($(this).prop('checked')) {
			$('input[type="checkbox"][name="chk_category"]').prop('checked',false);
			$(this).prop('checked', true);
			
			var cat3 = $(this).closest('div').find('.cat3Name').attr('id'); // cat3 code
			if(cat3 == 'all') {
				location.href='http://localhost:8080/info/'+category+'/'+areaName;
			}else {
				$.ajax({
					url:'http://localhost:8080/info/filter/'+category+'/'+areaName+'/'+cat3,
					type:'POST',
					dataType:"html",
					success:function(data) {
						$('#article-info').remove();
						$('#article-info-wrapper').html(data);
						//history.pushState(null, null, '/info/filter/'+category+'/'+areaName+'/'+cat3);
					},
					error:function() {
						alert('페이지 로드 중 문제가 발생하였습니다.');
						return false;
					}
				});
			}
		}
	});
	
	
	/********************************* [관광명소] CheckBox - ContentType *********************************/
	$('#checkbox-cat1').hide(); // 초기 값 
	$('#checkbox-cat2').hide(); // 초기 값
	$('#checkbox-cat3').hide(); // 초기 값
	
	$('input[type="checkbox"][name="chk_contentType"]').click(function(){
		if ($(this).prop('checked')) {
			$('input[type="checkbox"][name="chk_contentType"]').prop('checked',false);
			$(this).prop('checked', true);
			
			var contentTypeId = $(this).attr('id');
			if(contentTypeId == 'all') { // 전체 클릭 시 
				location.href='http://localhost:8080/info/'+category+'/'+areaName;
				$('#checkbox-cat3').slideUp();	$('#checkbox-cat2').slideUp();	$('#checkbox-cat1').slideUp();
			} else {
				// Change Category List
				$.ajax({
					url:'http://localhost:8080/info/filter/'+category+'/'+areaName,
					type:'GET',
					data:{contentTypeId:contentTypeId},
					dataType:'html',
					success:function(data){
						$('#article-info').remove();
						$('#article-info-wrapper').html(data);
					},
					error:function() {
						alert('페이지 로드 중 문제가 발생하였습니다.');
						return false;
					}
				});
				
				// Show checkbox-cat1 
				$.ajax({
					url:'http://localhost:8080/info/find/cat1',
					type:'GET',
					data:{contentTypeId:contentTypeId},
					success:function(data){
						var str='<div class="checkbox-chk">'
								+'<input type="checkbox" id="111" name="chk_cat1" checked/>'
								+'<label for="111"><span class="chkbox"></span>전체</label>'
								+'</div>';
						$.each(data, function(index, dataObject){
							str += '<div class="checkbox-chk">';
							str += '<input type="checkbox" id="'+dataObject.cat1+'" data-code="'+dataObject.cat1+'" name="chk_cat1" />';
							str += '<label for="'+dataObject.cat1+'"><span class="chkbox"></span>'+dataObject.cat1Name+'</label>';
							str += '</div>';
						});
						str +='<hr class="checkbox-hr">';
						//console.log(html);
						$('#checkbox-cat1List-block').html(str);
					}
				});
				$('#checkbox-cat1').slideDown();	
				$('#checkbox-cat2').slideUp(2000);	$('#checkbox-cat2').hide();
				$('#checkbox-cat3').slideUp(2000);	$('#checkbox-cat3').hide(); 
			}
			
		}
		
	});
	
	
	/********************************* [관광명소] CheckBox - 대분류 Cat1List *********************************/
	$(document).on('click','input[type="checkbox"][name="chk_cat1"]',function(){
		if ($(this).prop('checked')) {
			$('input[type="checkbox"][name="chk_cat1"]').prop('checked',false);
			$(this).prop('checked', true);
		}
		var cat1=$(this).attr('id');		var contentTypeId=$('input[type="checkbox"][name="chk_contentType"]:checked').attr('data-code');	
		var showUrl='';
		
		console.log('Cat1List CheckBox 클릭 (cat1:'+cat1+', contentTypeId:'+contentTypeId+')');
		if(cat1 == '111'){
			console.log('대분류-전체');
			showUrl='http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId;
			$('#checkbox-cat3').slideUp();	$('#checkbox-cat2').slideUp();	
		} else {
			console.log('대분류-'+cat1);
			showUrl='http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1;
			
			// Show checkbox-cat2
			$.ajax({
				url:'http://localhost:8080/info/find/cat2',
				type:'GET',
				data:{contentTypeId:contentTypeId, cat1:cat1},
				success:function(data){
					var str='<div class="checkbox-chk">'
						+'<input type="checkbox" id="222" name="chk_cat2" checked/>'
						+'<label for="222"><span class="chkbox"></span>전체</label>'
						+'</div>';
					$.each(data, function(index, dataObject){
						str += '<div class="checkbox-chk">';
						str += '<input type="checkbox" id="'+dataObject.cat2+'" data-code="'+dataObject.cat2+'" name="chk_cat2" />';
						str += '<label for="'+dataObject.cat2+'"><span class="chkbox"></span>'+dataObject.cat2Name+'</label>';
						str += '</div>';
					});
					str +='<hr class="checkbox-hr">';
					$('#checkbox-cat2List-block').html(str);
				}
			});
			$('#checkbox-cat2').slideDown();
			$('#checkbox-cat3').slideUp(2000);		$('#checkbox-cat3').hide(); 
		}
		
		// Show Category List
		$.ajax({
			url:showUrl,
			type:'GET',
			dataType:'html',
			success:function(data){
				$('#article-info').remove();
				$('#article-info-wrapper').html(data);
			},
			error:function() {
				alert('페이지 로드 중 문제가 발생하였습니다.');
				return false;
			}
		});
	});
	
	
	/********************************* [관광명소] CheckBox - 중분류 Cat2List *********************************/
	$(document).on('click','input[type="checkbox"][name="chk_cat2"]',function(){
		if ($(this).prop('checked')) {
			$('input[type="checkbox"][name="chk_cat2"]').prop('checked',false);
			$(this).prop('checked', true);
		}
		var cat2=$(this).attr('id');		
		var cat1=$('input[type="checkbox"][name="chk_cat1"]:checked').attr('data-code');
		var contentTypeId=$('input[type="checkbox"][name="chk_contentType"]:checked').attr('data-code');	
		var showUrl='';
		
		console.log('Cat2List CheckBox 클릭 (cat2:'+cat2+', cat1:'+cat1+', contentTypeId:'+contentTypeId+')');
		if(cat2 == '222'){
			console.log('중분류-전체');
			showUrl='http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1;
			$('#checkbox-cat3').slideUp();		
		} else {
			console.log('중분류-'+cat2);
			showUrl='http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2;
			
			// Show checkbox-cat3
			$.ajax({
				url:'http://localhost:8080/info/find/cat3',
				type:'GET',
				data:{contentTypeId:contentTypeId, cat2:cat2},
				success:function(data){
					var str='<div class="checkbox-chk">'
						+'<input type="checkbox" id="333" name="chk_cat3" checked/>'
						+'<label for="333"><span class="chkbox"></span>전체</label>'
						+'</div>';
					$.each(data, function(index, dataObject){
						str += '<div class="checkbox-chk">';
						str += '<input type="checkbox" id="'+dataObject.cat3+'" data-code="'+dataObject.cat3+'" name="chk_cat3" />';
						str += '<label for="'+dataObject.cat3+'"><span class="chkbox"></span>'+dataObject.cat3Name+'</label>';
						str += '</div>';
					});
					str +='<hr class="checkbox-hr">';
					$('#checkbox-cat3List-block').html(str);
				}
			});
			$('#checkbox-cat3').slideDown();
		}
		
		// Show Category List
		$.ajax({
			url:showUrl,
			type:'GET',
			dataType:'html',
			success:function(data){
				$('#article-info').remove();
				$('#article-info-wrapper').html(data);
			},
			error:function() {
				alert('페이지 로드 중 문제가 발생하였습니다.');
				return false;
			}
		});
	});
	
	
	/********************************* [관광명소] CheckBox - 소분류 Cat3List *********************************/
	$(document).on('click','input[type="checkbox"][name="chk_cat3"]',function(){
		if ($(this).prop('checked')) {
			$('input[type="checkbox"][name="chk_cat3"]').prop('checked',false);
			$(this).prop('checked', true);
		}
		var cat3=$(this).attr('id');	
		var cat2=$('input[type="checkbox"][name="chk_cat2"]:checked').attr('data-code');
		var cat1=$('input[type="checkbox"][name="chk_cat1"]:checked').attr('data-code');
		var contentTypeId=$('input[type="checkbox"][name="chk_contentType"]:checked').attr('data-code');	
		var showUrl='';
		
		console.log('Cat3List CheckBox 클릭 (cat3:'+cat3+'cat2:'+cat2+', cat1:'+cat1+', contentTypeId:'+contentTypeId+')');
		if(cat3 == '333'){
			console.log('소분류-전체');
			showUrl='http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2;
		} else {
			console.log('소분류-'+cat3);
			showUrl='http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2+'&cat3='+cat3;
		}
		
		// Show Category List
		$.ajax({
			url:showUrl,
			type:'GET',
			dataType:'html',
			success:function(data){
				$('#article-info').remove();
				$('#article-info-wrapper').html(data);
			},
			error:function() {
				alert('페이지 로드 중 문제가 발생하였습니다.');
				return false;
			}
		});
	});	
	
	/*$('.checkbox-more-category').click(function(){
		$('.checkbox-category-hide').toggle();
		$('.checkbox-more-category').toggle();
		$('.checkbox-fold-category').toggle();
	});
	$('.checkbox-fold-category').click(function(){
		$('.checkbox-category-hide').toggle();
		$('.checkbox-more-category').toggle();
		$('.checkbox-fold-category').toggle();
	});
	
	$('.checkbox-more-theme').click(function(){
		$('.checkbox-theme-hide').toggle();
		$('.checkbox-more-theme').toggle();
		$('.checkbox-fold-theme').toggle();
	});
	$('.checkbox-fold-theme').click(function(){
		$('.checkbox-theme-hide').toggle();
		$('.checkbox-more-theme').toggle();
		$('.checkbox-fold-theme').toggle();
	});*/
	
});


// Arrange
$(document).ready(function(){
	var category = $('#category').val();
	var areaName = $('#areaName').val();
	var cat3 = $('#cat3').val();
	var contentTypeId = $('#contentTypeId').val();
	var arrange = $('#arrange').val();
	
	$('.arrange').on('click',function(){
		var arrange = $(this).attr('id');	console.log('clicked arrange : ' + arrange);
		var url = '';
		if(cat3 == '' || cat3 == undefined) { // [숙박,관광명소,맛집] No Filtering
			console.log('[숙박,관광명소,맛집] No CheckBox Filtering Arrange');
			url = 'http://localhost:8080/info/'+category+'/'+areaName;
		}else {
			url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'/'+$('#cat3').val();
		}
		$.ajax({
			url:url,
			type:'GET',
			data:{arrange:arrange},
			dataType:'html',
			success:function(data){
				$('#article-info').remove();
				$('#article-info-wrapper').html(data);
			},
			error:function() {
				alert('페이지 로드 중 문제가 발생하였습니다.');
				return false;
			}
		});
	});
	
	$(document).on('click','.arrange_after',function(){
		var arrange = $(this).attr('id');	console.log('after clicked arrange : ' + arrange);
		
		if(category=='accom' || category=='food'){	/***** [숙박,맛집] *****/
			console.log('[숙박/음식] After Arrange : ' + arrange);
			if($('#cat3').val()=='' || $('#cat3').val()==undefined) {
				console.log('[숙박/음식] 전체 체크박스 클릭');
				location.href='http://localhost:8080/info/'+category+'/'+areaName;
			}else{
				console.log('[숙박/음식] cat3 : ' + $('#cat3').val());
				url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'/'+$('#cat3').val();

			}
		}else if(category=='hotplace') {  /***** [관광명소] *****/
			console.log('[관광명소] After Arrange : ' + arrange);
			var contentTypeId = $('#contentTypeId').val();	var cat1=$('#cat1').val();	var cat2=$('#cat2').val();	var cat3=$('#cat3').val();
			if(cat3=='' || cat3==undefined){
				if(cat2=='' || cat2==undefined){
					if(cat1=='' || cat1==undefined){
						if(contentTypeId=='' || contentTypeId==undefined){ 
							console.log('[관광명소] 타입-전체');
							url = 'http://localhost:8080/info/'+category+'/'+areaName;
						}else{
							console.log('[관광명소] 타입-'+ contentTypeId);
							url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId;
						}
					}else{
						console.log('[관광명소] 타입-'+contentTypeId+'/cat1-'+cat1);
						url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1;
					}
				}else{
					console.log('[관광명소] 타입-'+contentTypeId+'/cat1-'+cat1+'/cat2-'+cat2);
					url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2;
				}
			}else{
				console.log('[관광명소] 타입-'+contentTypeId+'/cat1-'+cat1+'/cat2-'+cat2+'/cat3-'+cat3);
				url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2+'&cat3='+cat3;
			}
		}

		$.ajax({
			url:url,
			type:'GET',
			data:{arrange:arrange},
			dataType:'html',
			success:function(data){
				$('#article-info').remove();
				$('#article-info-wrapper').html(data);
			},
			error:function() {
				alert('페이지 로드 중 문제가 발생하였습니다.');
				return false;
			}
		});
	});
});


// Pagination
$(document).ready(function(){
	var category = $('#category').val();
	var areaName = $('#areaName').val();
	
	$('li.no_before').on('click',function(){
		console.log("## Pagination");
		$('li.no_before').removeClass('selected');
		$(this).addClass('selected');
		var page = $(this).text();
		location.href="http://localhost:8080/info/"+category+"/"+areaName+"/"+page;
	});
});


// Pagination (동적으로 생성된 category_list.jsp)
$(document).ready(function(){
	var category = $('#category').val();
	var areaName = $('#areaName').val();
	
	$(document).on('click','li.no_after',function(){
		console.log("## After Pagination");
		$('li.no_after').removeClass('selected');
		$(this).addClass('selected');

		var arrange = $('#arrange').val();
		var contentTypeId = $('#contentTypeId').val();	var cat1=$('#cat1').val();	var cat2=$('#cat2').val();	var cat3=$('#cat3').val();
		var page = $(this).text();
		
		var url = ''; 	var type = '';
		if(category=='accom' || category=='food'){	/***** [숙박,맛집] *****/
			console.log('[숙박/맛집] Pagination 버튼 클릭 ' + page);
			if($('#cat3').val()=='' || $('#cat3').val()==undefined){
				console.log('cat3-전체')
				url = 'http://localhost:8080/info/'+category+'/'+areaName+'/'+page;
				type = 'GET';
			}else {
				var cat3=$('#cat3').val();
				console.log('cat3-'+cat3);
				url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'/'+cat3+'/'+page;
				type = 'POST';
			}
		}else if(category=='hotplace'){	/***** [관광명소] *****/
			console.log('[관광명소] Pagination 버튼 클릭 ' + page);
			if(cat3=='' || cat3==undefined){
				if(cat2=='' || cat2==undefined){
					if(cat1=='' || cat1==undefined){
						if(contentTypeId=='' || contentTypeId==undefined){ 
							console.log('contentTypeId 없음');
							url = 'http://localhost:8080/info/'+category+'/'+areaName+'/'+page;
							type = 'GET';
						}else {
							console.log('contentTypeId 있음' + contentTypeId); // 카테고리만 클릭되어 있고 원하는 페이지 클릭 시
							url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&pageNo='+page;
							type = 'GET';
						}
					}else {
						console.log('contentTypeId-'+contentTypeId+' / cat1-'+cat1);
						url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&pageNo='+page;
						type = 'GET';
					}
				}else {
					console.log('contentTypeId-'+contentTypeId+' / cat1-'+cat1+' / cat2-'+cat2);
					url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2+'&pageNo='+page;
					type = 'GET';
				}
			}else {
				console.log('contentTypeId-'+contentTypeId+' / cat1-'+cat1+' / cat2-'+cat2+' / cat3-'+cat3);
				url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2+'&cat3='+cat3+'&pageNo='+page;
				type = 'GET';
			}
		}
		
		$.ajax({
			url:url,
			type:type,
			data:{arrange:arrange},
			dataType:"html",
			success:function(data) {
				$('#article-info').remove();
				$('#article-info-wrapper').html(data);
			},
			error:function() {
				alert('페이지 로드 중 문제가 발생하였습니다.');
				return false;
			}
		});
		
	});
	
	// Pagination - next/prev 버튼 클릭
	$(document).on('click','#nextBtn, #prevBtn',function(){
		
		console.log('[Pagination] next/prev 버튼 클릭');
		var page = $(this).val(); console.log('page : '+page);
		var cat3 = $('#cat3').val(); 
		
		/***** [숙박,맛집] *****/
		if(category=='accom' || category=='food'){
			if(cat3=='' || cat3==undefined) { // 카테고리-전체
				console.log('[숙박/맛집] 카테고리 - 전체')
				if($('#arrange').val()=='' || $('#arrange').val()==undefined) {
					console.log('arrange 없음');
					location.href='http://localhost:8080/info/'+category+'/'+areaName+'/'+page;
				}else {
					console.log('arrange 있음 : ' + $('#arrange').val());
					url = 'http://localhost:8080/info/'+category+'/'+areaName+'/'+page+'?arrange='+$('#arrange').val();
					type = 'GET';
				}
				
			}else { // 카테고리 - cat3
				console.log('[숙박/맛집] 카테고리 - '+cat3);
				var url='';		var type='';
				if($('#arrange').val()=='' || $('#arrange').val()==undefined) {
					console.log('arrange 없음');
					url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'/'+cat3+"/"+page;
					type = 'POST';
				}else {
					console.log('arrange 있음 : ' + $('#arrange').val());
					url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'/'+cat3+"/"+page+'?arrange='+$('#arrange').val();
					type = 'GET';
				}
			}
		}else if(category=='hotplace') { /***** [관광명소] *****/
			var contentTypeId = $('#contentTypeId').val();	var cat1=$('#cat1').val();	var cat2=$('#cat2').val();	var cat3=$('#cat3').val(); 
			var arrange=$('#arrange').val();
			console.log('[관광명소]');
			if(cat3=='' || cat3==undefined){ // cat3 소분류 체크박스 비활성화
				if(cat2=='' || cat2==undefined){ // cat2 중분류 체크박스 비활성화
					if(cat1=='' || cat1==undefined){ // cat1 대분류 체크박스 비활성화
						if(contentTypeId=='' || contentTypeId==undefined){ // contentType 관광명소 체크박스 - 전체
							console.log('contentTypeId 없음');
							if(arrange==''||arrange==undefined){
								location.href = 'http://localhost:8080/info/'+category+'/'+areaName+'/'+page;
							}else {
								url='http://localhost:8080/info/'+category+'/'+areaName+'/'+page+'?arrange='+arrange;
								type='GET';
							}
						}else { // contentType 관광명소 타입 선택 (+ 대분류 체크박스 '전체'로 활성화)
							console.log('contentTypeId 있음' + contentTypeId); // 카테고리만 클릭되어 있고 원하는 페이지 클릭 시
							url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?arrange='+arrange+'&contentTypeId='+contentTypeId+'&pageNo='+page;
							type = 'GET';
						}
					}else { // 관광명소타입 + 대분류 체크박스 선택 (+ 중분류 체크박스 '전체'로 활성화)
						console.log('contentTypeId-'+contentTypeId+' / cat1-'+cat1);
						url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?arrange='+arrange+'&contentTypeId='+contentTypeId+'&cat1='+cat1+'&pageNo='+page;
						type = 'GET';
					}
				}else { // 관광명소타입 + 중분류 체크박스 선택 (+ 소분류 체크박스 '전체'로 활성화)
					console.log('contentTypeId-'+contentTypeId+' / cat1-'+cat1+' / cat2-'+cat2);
					url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?arrange='+arrange+'&contentTypeId='+contentTypeId+'&cat1='+cat2+'&cat2='+cat2+'&pageNo='+page;
					type = 'GET';
				}
			}else { // 관광명소타입 + 소분류 체크박스 선택 
				console.log('contentTypeId-'+contentTypeId+' / cat1-'+cat1+' / cat2-'+cat2+' / cat3-'+cat3);
				url = 'http://localhost:8080/info/filter/'+category+'/'+areaName+'?arrange='+arrange+'&contentTypeId='+contentTypeId+'&cat1='+cat1+'&cat2='+cat2+'&cat3='+cat3+'&pageNo='+page;
				type = 'GET';
			}
		}
			
		$.ajax({
			url:url,
			type:type,
			dataType:"html",
			success:function(data) {
				$('#article-info').remove();
				$('#article-info-wrapper').html(data);
			},
			error:function() {
				alert('페이지 로드 중 문제가 발생하였습니다.');
				return false;
			}
		});
		
	});
	
	
});