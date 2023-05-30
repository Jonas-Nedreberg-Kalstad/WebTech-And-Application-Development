const observer = new IntersectionObserver((entries, observer) => {
    entries.forEach((entry) => {
        console.log(entry);
        if (entry.isIntersecting && !entry.target.classList.contains('show')) {
            entry.target.classList.add('show');
            observer.unobserve(entry.target); // Disconnect the observer after the animation is triggered
        } else {
            entry.target.classList.remove('show');
        }
    });
});

const hiddenElements = document.querySelectorAll('.hidden');
hiddenElements.forEach((el) => observer.observe(el));

