const modal = (res, message, text = '') => {
	Swal.fire({
		icon: res,
		title: message,
		text: text,
		showConfirmButton: false,
		timer: 3000
	})
}

const modalRedirect = (title, html) => {
	let timerInterval;
	Swal.fire({
		icon: 'success',
		title: title,
		html: html,
		timer: 4000,
		timerProgressBar: true,
		didOpen: () => {
			Swal.showLoading()
			const b = Swal.getHtmlContainer().querySelector('b')
			timerInterval = setInterval(() => {
			}, 1000)
		},
		willClose: () => {
			clearInterval(timerInterval)
		}
	}).then((result) => {
		if (result.dismiss === Swal.DismissReason.timer) {
			location.href = "patient-dashboard"
		}
	})
	
}
