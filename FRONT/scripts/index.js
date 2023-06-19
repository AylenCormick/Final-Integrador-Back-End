window.addEventListener("load", function () {
    const btnnt = document.querySelector("#btn-sol-turno");

    btnnt.addEventListener("click", function (e) {

        e.preventDefault();

        location.replace("./pages/login.html");
        
    })
})