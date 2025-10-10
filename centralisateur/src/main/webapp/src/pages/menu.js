window.openMenu = function() {
    document.getElementById("sideMenu").style.width = "280px";
    document.getElementById("menu-button").style.display = "none";
}

window.closeMenu = function() {
    document.getElementById("sideMenu").style.width = "0";
    document.getElementById("menu-button").style.display = "block";
}
