function addAllCategories(){
	$.ajax({
		url: 'restservices/categories',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		$.each(result, function(index, key) {
			var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\""+key.image+"\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">"+key.description+"</p><hr><a href=\"category.html?"+key.name+"\" class=\"btn btn-primary\">Open</a></div></div></div>";
			$(".row").append(products);
		});
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the categories. Try it again in 10 minutes."},{type: "danger"});
	})
};
