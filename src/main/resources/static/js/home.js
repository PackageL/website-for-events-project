// Search bar
let findButton = document.querySelector('.find-button');
let searchBar = document.querySelector('.search-bar');
findButton.addEventListener('click', function() {
    let searchValue = searchBar.value;
    window.location.href = "search-results.html?title=" + searchValue;
});

// check the login status
function checkLoginStatus() {
    let loggedInText = document.getElementById("loggedIn");
    let email = '<?php echo $_SESSION["email"]; ?>';
    if (email) {
        loggedInText.innerHTML = "Logged in as: " + email;
    } else {
        loggedInText.innerHTML = "";
    }
}
// check login status on page load
window.addEventListener('load', checkLoginStatus);

// Hamburger menu
let hamburgerButton = document.querySelector('.hamburger-button');
let menu = document.querySelector('.menu-container');
hamburgerButton.addEventListener('click', function() {
    menu.classList.toggle('menu-open');
});

document.addEventListener('click', function(event) {
    if (!event.target.closest('.menu-container') && menu.classList.contains('menu-open')) {
        menu.classList.remove('menu-open');
    }
});

// Header visibility on scroll
let header = document.querySelector('.header');
let previousScroll = 0;
window.addEventListener('scroll', function() {
    // let currentScroll = window.pageYOffset;
    // if (previousScroll > currentScroll) {
    //     header.style.top = '0';
    // } else {
    //     header.style.top = '-70px';
    // }
    // previousScroll = currentScroll;
    let currentScroll = window.pageYOffset;
    if (currentScroll <= 50) {
        header.classList.remove('header-hidden');
    } else if (currentScroll > previousScroll) {
        header.classList.add('header-hidden');
    } else {
        header.classList.remove('header-hidden');
    }
    previousScroll = currentScroll;
});

