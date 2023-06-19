window.addEventListener("load", function () {

    obtenerNombreUsuario();
    obtenerTurnos();
    obtenerOdontologos();
        
    /* obtener nombre usuario */

    function obtenerNombreUsuario() {

        const id = localStorage.getItem("id");
        const url = "http://localhost:8080/pacientes/" + id;
    
        const settings = {
        method: "GET"
        };
    
        console.log("Consultando el usuario");
    
        fetch(url, settings)
        .then(response => {
            return response.json();
        })
        .then(data => {
            const userName = document.querySelector("#user-name");
            userName.innerHTML = data.nombre;
        })
        .catch(err => console.log(err))
    
        };
    
    
        /* obtener turnos */
    
        function obtenerTurnos() {
    
            const id = localStorage.getItem("id");
            const url = "http://localhost:8080/turnos/listarTurno" + id;
        
            const settings = {
            method: "GET"
            };
        
            console.log("Consultando turnos");
        
            fetch(url, settings)
            .then(response => {
                return response.json();
            })
            .then(data => {
                renderizarTurnos(data)
            })
            .catch(err => console.log(err))
        
        };
    
    
        /* renderizar turnos */
    
        function renderizarTurnos(data) {
            const divTurnos = document.querySelector("#cards-turnos");
            divTurnos.innerHTML = "";
    
            data.forEach(turno => {
                divTurnos.innerHTML += `
                <div class="turno">
                <h3 id="fecha-turno">${turno.fechaAlta}</h3>
                <h4 id="">${turno.odontologo.nombre} + " " + ${turno.odontologo.apellido}</h4>
                </div>
                `
            });
        }
    
    
        /* btn crear turno */
    
        const btnNewTurno = document.querySelector("#new-turno");
    
        btnNewTurno.addEventListener("click", function(e) {
    
            e.preventDefault();
    
            location.replace("./crearTurno.html");
    
        })
        
        
        /* obtener odontologos */
    
        function obtenerOdontologos() {
            const divOdont = document.querySelector("#select-odontologos");
            divOdont = "";
    
            const url = "http://localhost:8080/odontologos/listarOdontologos";
        
            const settings = {
            method: "GET"
            };
        
            fetch(url, settings)
            .then(response => {
                return response.json();
            })
            .then(data => {
                data.forEach(odont => {
                    divOdont.innerHTML += `
                    <select id="${odont.id}">${odont.nombre} + " " + ${odont.apellido}</select>
                    `
                });
            })
            .catch(err => console.log(err))
        }
    
    
        /* crear turno */
    
        function crearTurno() {
            const objeto =     {
                paciente : {"id" : localStorage.getItem("id")},
                odontologo : {"id" : 1},
                "fechaHora" : "2023-10-20"
            }
            
            console.log(objeto);
            
            const settings = {
                method : "POST",
                body : JSON.stringify(objeto),
                headers : {
                    "Content-Type": "application/json"
                }
            }
            
            realizarRegister(settings);
            formulario.reset();
        }
})




