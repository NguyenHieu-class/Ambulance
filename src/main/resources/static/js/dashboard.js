// Simple client-side validation for forms with the 'needs-validation' class

window.addEventListener('DOMContentLoaded', function () {
    const forms = document.querySelectorAll('form.needs-validation');
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
});
