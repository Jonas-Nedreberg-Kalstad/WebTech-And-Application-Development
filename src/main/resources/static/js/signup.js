// Email Validation
const emailInput = document.getElementById('email');
const emailError = document.getElementById('email-error');

emailInput.addEventListener('input', function () {
    if (!emailInput.validity.valid) {
        emailError.style.display = 'block';
    } else {
        emailError.style.display = 'none';
    }
});

// Password Length Validation
const passwordInput = document.getElementById('password');

function validatePasswordLength() {
    if (!passwordInput.value.includes(" ") && passwordInput.value.length >= 5) {
        passwordInput.setCustomValidity('');
    } else {
        passwordInput.setCustomValidity('Password too short');
    }
}

passwordInput.addEventListener('input', validatePasswordLength);

// Password Match Validation
const confirmPasswordInput = document.getElementById('confirmPassword');

function validatePasswordMatch() {
    if (passwordInput.value !== confirmPasswordInput.value) {
        confirmPasswordInput.setCustomValidity("Passwords don't match");
    } else {
        confirmPasswordInput.setCustomValidity('');
    }
}

passwordInput.addEventListener('input', validatePasswordMatch);
confirmPasswordInput.addEventListener('input', validatePasswordMatch);