var id;
var estado;

var btnLlamar = document.getElementById("llamar")
btnLlamar.addEventListener("click", function () {
    axios.get("http://localhost:4567/prueba")
    .then(function (response) {
        alert(response);
    })
    .catch(function (error) {
        console.log(error);
    })
})

var btnPagina = document.getElementById("pagina")
btnPagina.addEventListener("click",function () {
    axios.get("http://localhost:4567/pagina")
    .then(function (response) {
        if(response.data.access =="ok")
            window.location.replace(response.data.page)
        else
            alert("datos equivocados");
    })
    .catch(function (error) {
        console.log(error);
    })
})

var btnPagina2 = document.getElementById("pagina2")
btnPagina2.addEventListener("click",function () {
    let params = new URLSearchParams();
    params.append("status", estado);
    params.append("id", id)
    window.location.replace("http://127.0.0.1:5500/pagina.html?"+params)
})

var btnUsuarios = document.getElementById("usuarios")
btnUsuarios.addEventListener("click", function () {
    axios.get("http://localhost:4567/usuarios")
    .then(function (response) {
        console.log(response.data);
        usuarios(response.data);
    })
    .catch(function (error) {
        console.log(error);
    })
})

var btnRegistrar = document.getElementById("registrar")
btnRegistrar.addEventListener("click", function () {
    axios.post("http://localhost:4567/usuario", {
        email : document.getElementById("email").value,
        password : document.getElementById("password").value
    })
    .then(function (response) {
        alert("mensaje: usuario creado "+response.data.status+" con id: "+response.data.id);
        id = response.data.id;
        estado=response.data.status;
    })
    .catch(function (error) {
        console.log(error);
    })
})

function usuarios(u) {
    let listTareas = document.getElementById("tareas")
    let text = "";
    for (let i = 0; i < u.length; i++) {
        let tarea = document.createElement("li");
        tarea.textContent = u[i].email + " " + u[i].id;
        listTareas.appendChild(tarea);
    }
}