function addAllProducts(){
	$.ajax({
		url: 'restservices/products',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		$.each(result, function(index, key) {
			var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\""+key.image+"\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">"+key.description+"</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html?"+key.id+"\" class=\"btn btn-primary openProduct\">Open</a></div><div class=\"card-footer\"></div></div></div>";
			$(".row").append(products);
		});
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the products. Try it again in 10 minutes."},{type: "danger"});
	})
};

$(document).ready(function(){
	var search = sessionStorage.getItem("search");
	if (search == ""){
		addAllProducts();
	}else{
		$.ajax({
			url: 'restservices/products/searchproduct/' + search,
			type: 'GET'
		})
		.done(function(result) {
			console.log(result);
			$.each(result, function(index, key) {
				if (key.picture == null){
					picture = key.image;
				}else{
					picture =key.picture
				}
				var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\""+picture+"\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">"+key.description+"</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html?"+key.id+"\" class=\"btn btn-primary openProduct\">Open</a></div><div class=\"card-footer\"></div></div></div>";
				$(".row").append(products);
				sessionStorage.setItem("search","");
			});
		})
		.fail(function() {
			$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the products. Try it again in 10 minutes."},{type: "danger"});
		});
	}
	//}
})