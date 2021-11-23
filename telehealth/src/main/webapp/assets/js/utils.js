var pass = true;
var p = document.getElementById("password")
var rp = document.getElementById("re-type-pass")
var e = document.getElementById("visibility")
var errPass = document.getElementById("errorPass")
var errContact = document.getElementById("errorContact")
var errAge = document.getElementById("errorAge")
var contact = document.getElementById("contact")
var ageField = document.getElementById("age")
var emailField = document.getElementById("email")
var emailError = document.getElementById("errorEmail")
var err = document.getElementById("errMessage")

var reg = false;

/*dateConfig = {
	maxDate: "2021-01",
	dateFormat: "m/d/Y"
}
flatpickr("input[type=date]", dateConfig);*/

var cleave = new Cleave('#contact', {
	phone: true,
	phoneRegionCode: 'PH'
});

function errorStyle(el, m, res) {
	if (el.value !== "") {
		if (!res) {
			el.innerHTML = m;
			el.style.color = "#f55";
			el.style.display = "block";
			return false
		}
		el.style.display = "none";
		return true
	} else {
		el.style.display = "none";
	}
}

function calcAge(e) {
	var res = !parseInt(e.value) < 1;
	return errorStyle(errAge, "Age is not valid", res);
}

const isValidPhone = (e) => {
	var res = new RegExp(/^09\d{2}\s\d{3}\s\d{4}/).test(e.value);
	return errorStyle(errContact, "Phone number is not valid", res)
}

$(document).ready(function() {
	$("#email").change(function() {
		var val = emailField.value
		if (val !== "") {
			$.ajax({
			type: 'POST',
			data: { email: val },
			url: "usercheck",
			success: function(result) {
				if (result == "error") {
					$('#errorEmail').html("This email already exist!")
				}else {
					$('#errorEmail').hide();
				}
			}
		});
		}
	});
})

$("#register-form").submit(function(e) {
	e.preventDefault();

	var form = $(this);
	var url = form.attr('action')
	
	if (rp.value !== p.value ){
		modal('error', "Registration failed!")
	}else {
		$.ajax({
			type: "POST",
		url: url,
		data: form.serialize(),
		success: function(result) {
			if (result == 'success') {
				modal(result, "Registration Successfull", "Please check your email for verifcation to activate your account, Thank you!");
				setTimeout(() => location.href = "login", 2000)
			} else {
				modal("error", "Registration failed please check")
			}
		}
	});
	}
	
});




function isPasswordSame(e) {
	var res = e.value === p.value;
	return errorStyle(errPass, "Password must be same", res)
}


function checkField() {
	if (!isValidPhone(contact) ||
		!isPasswordSame(rp) || !calcAge(ageField) ||
		!isValidEmail(emailField)) {
		return false
	}
	return true
}

e.addEventListener('click', function() {
	if (pass) {
		p.setAttribute("type", "text");
		rp.setAttribute("type", "text");
		e.innerHTML = "visibility_off"
	} else {
		p.setAttribute("type", "password");
		rp.setAttribute("type", "password");
		e.innerHTML = "visibility"
	}
	pass = !pass;
})