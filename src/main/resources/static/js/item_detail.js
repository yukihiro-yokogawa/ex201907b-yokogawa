$(function(){
	calc_price();
	$(".size").on("change",function(){
		calc_price();
	});
	
	$(".checkbox").on("change",function(){
		calc_price();
	});
	
	$("#currynum").on("change",function(){
		calc_price();
	});
	
	function calc_price(){
		var size = $(".size:checked").val();
		var topping_count = $("#topping input.checkbox:checked").length;
		var curry_num = $("#currynum option:selected").val();
		console.log("topping_count = " +topping_count);
		if(size=="M"){
			var curry_price = $("#M").data('value');
			var topping_price = 200 * topping_count;
			console.log(topping_count)
		}else{
			var curry_price = $("#L").data('value');
			var topping_price = 300 * topping_count;
			console.log(topping_count)
		}
		var price = (curry_price + topping_price) * curry_num;
		$("#totalprice").text(price.toLocaleString());

	};
	
});