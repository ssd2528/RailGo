$(document).ready(function(){
	
	$('input[type="checkbox"][name="chk_season"]').click(function(){
		
		if ($(this).prop('checked')) {
			
			$('input[type="checkbox"][name="chk_season"]').prop('checked',false);
			
			$(this).prop('checked', true);
			
		}
		
	})
	
	$('input[type="checkbox"][name="chk_price"]').click(function(){
		
		if ($(this).prop('checked')) {
			
			$('input[type="checkbox"][name="chk_price"]').prop('checked',false);
			
			$(this).prop('checked', true);
			
		}
		
	})
	
	$('input[type="checkbox"][name="chk_category"]').click(function(){
		
		if ($(this).prop('checked')) {
			
			$('input[type="checkbox"][name="chk_category"]').prop('checked',false);
			
			$(this).prop('checked', true);
			
		}
		
	})
	
	$('.checkbox-more-category').click(function(){
		
		$('.checkbox-category-hide').toggle();
		$('.checkbox-more-category').toggle();
		$('.checkbox-fold-category').toggle();
	})
	
	$('.checkbox-fold-category').click(function(){
		
		$('.checkbox-category-hide').toggle();
		$('.checkbox-more-category').toggle();
		$('.checkbox-fold-category').toggle();
	})
	
	$('.checkbox-more-theme').click(function(){
		
		$('.checkbox-theme-hide').toggle();
		$('.checkbox-more-theme').toggle();
		$('.checkbox-fold-theme').toggle();
	})
	
	$('.checkbox-fold-theme').click(function(){
		
		$('.checkbox-theme-hide').toggle();
		$('.checkbox-more-theme').toggle();
		$('.checkbox-fold-theme').toggle();
	})
});