// Password Length Validation
const passwordInput = document.getElementById('password');

function validatePasswordLength() {
    if (!passwordInput.value.includes(" ") && passwordInput.value.length >= 5) {
        passwordInput.setCustomValidity('');
    } else {
        passwordInput.setCustomValidity('Password too short, minimum 5 characters');
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