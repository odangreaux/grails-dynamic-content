CKEDITOR.on('currentInstance', function(event) {
	var instance = CKEDITOR.currentInstance;
	if (instance) {
		window.lastInstance = instance;
	} else {
		var data = window.lastInstance.getData();
		var key = $(window.lastInstance.element).attr("key");
		$.ajax({
  			type: "POST",
  			url: '<g:createLink controller="dynamicContent" action="contentChanged" />',
  			data: {key: key, content: data}
		});
	}
});