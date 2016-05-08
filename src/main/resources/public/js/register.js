$(document).ready(function () {
	// Hide all the loaders
	$('#register-email-ajax-loader').hide();
	$('#register-student-email-ajax-loader').hide();
	$('#register-registration-number-ajax-loader').hide();
	
	// Register validation events for fields that make AJAX calls
	$('#register-email').on('blur', testEmailValidity);
	$('#register-student-email').on('blur', testStudentEmailValidity);
	$('#register-registration-number').on('blur', testRegistrationNumberValidity);

	// Apply validation framework.
	$('#registeration-form').validator();
	
});

function testEmailValidity () {
	var email = $('#register-email').val();
	var url = '/register/validate?query=isValidEmail';
	$.ajax ({
		url: url,
		type: 'POST',
		data: {
			"email": email
		},
		beforeSend: function () {
			$('#register-email-ajax-loader').show();
		},
		complete: function () {
			$('#register-email-ajax-loader').hide();
		},
		success: function (data) {
			if (data) {
				$('#register-email').closest('.form-group').addClass('has-success');
			} else {
				$('#register-email').closest('.form-group').addClass('has-error');
				$('#register-error-message-email').show();
				$('#register-error-message-email').text("This email id has already been registered.");
			}
		}
	});
}

function testStudentEmailValidity () {
	var email = $('#register-student-email').val();
	var url = '/register/validate?query=isValidStudentEmail';
	$.ajax ({
		url: url,
		type: 'POST',
		data: {
			"studentEmail": email
		},
		beforeSend: function () {
			$('#register-student-email-ajax-loader').show();
		},
		complete: function () {
			$('#register-student-email-ajax-loader').hide();
		},
		success: function (data) {
			if (data) {
				$('#register-student-email').closest('.form-group').addClass('has-success');
			} else {
				$('#register-student-email').closest('.form-group').addClass('has-error');
				$('#register-error-message-student-email').show();
				$('#register-error-message-student-email').text("This student email id has already been registered.");
			}
		}
	});
}

function testRegistrationNumberValidity () {
	var regNo = $('#register-registration-number').val();
	var url = '/register/validate?query=isValidRegistrationNumber';
	$.ajax ({
		url: url,
		type: 'POST',
		data: {
			"registrationNumber": regNo
		},
		beforeSend: function () {
			$('#register-registration-number-ajax-loader').show();
		},
		complete: function () {
			$('#register-registration-number-ajax-loader').hide();
		},
		success: function (data) {
			if (data) {
				$('#register-registration-number').closest('.form-group').addClass('has-success');
			} else {
				$('#register-registration-number').closest('.form-group').addClass('has-error');
				$('#register-error-message-registration-number').show();
				$('#register-error-message-registration-number').text("This registration number has already been registered.");
			}
		}
	});
}