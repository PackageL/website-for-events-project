// Search bar
let findButton = document.querySelector('.find-button');
let searchBar = document.querySelector('.search-bar');
findButton.addEventListener('click', function (event) {
    let searchValue = searchBar.value;
    window.location.href = "search-results.html?title=" + searchValue;
});
searchBar.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        let searchValue = searchBar.value;
        window.location.href = "search-results.html?title=" + searchValue;
        searchBar.value = '';
    }
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



