var btnProbar = document.getElementById("Probar");
btnProbar.addEventListener("click", function() {

    alert("Hola");

    axios.get("http://localhost:4567/prueba", {



    })
    .then(function(response) {
        console.log(response);
    })
    .catch(function(error) {
        console.log(error);
    })
});