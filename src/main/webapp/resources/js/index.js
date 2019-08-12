$(document).ready(function(){
	if(self.name=='parent') {
		self.name='main';
		self.location.reload();
	}
	
	$('.search-bed').on('click', function(){
		showModal('search-bed');
	});
	$('.search-hotplace').on('click', function(){
		showModal('search-hotplace');
	});
	$('.search-food').on('click', function(){
		showModal('search-food');
	});
});


function showModal(className){
	$('.search-modal').css('display','flex');
	setTimeout(function() {
	      $(".search-modal").addClass('show-modal');
	      $('.search-text').focus();
	   }, 1)
	$('body').css({'overflow':'hidden', 'height':'100%'});
	
	$('.search-city').addClass(className);
}