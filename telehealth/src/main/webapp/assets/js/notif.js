function loadDoc() {
	setInterval(function() {
		$.ajax({
			type: 'GET',
			url: "notification",
			success: function(data) {
				console.log(data);
			},
			error: function(err) {
				console.log(err);
			}
		})
	}, 10000);
}
loadDoc();
