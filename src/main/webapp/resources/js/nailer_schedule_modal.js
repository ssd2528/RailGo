// open the modal 
$(function(){
	$('.calendar-map').click(function () {
		 $('#Modal').show();
		 $('html').css('overflow', 'hidden');
	});
});

// close the modal
$(function(){
	$('.close-button').click(function () {
		 $('#Modal').hide();
		 $('html').css('overflow', 'auto');
	});
});

