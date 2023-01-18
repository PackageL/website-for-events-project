// Search bar
let findButton = document.querySelector('.find-button');
let searchBar = document.querySelector('.search-bar');
findButton.addEventListener('click', function (event) {
    let searchValue = searchBar.value;
    window.location.href = "view-event.html?title=" + searchValue;
});
searchBar.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        let searchValue = searchBar.value;
        window.location.href = "view-event.html?title=" + searchValue;
        searchBar.value = '';
    }
});

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip({
        placement: 'top',
        template: '<div class="tooltip" role="tooltip" style="background-color: #f1f1f1;"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
    });
});



