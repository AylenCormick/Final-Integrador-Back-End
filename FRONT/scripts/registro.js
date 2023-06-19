window.addEventListener('load', function () {

    /* variables globales */
    const nombre = document.querySelector("#input-nombre");
    const apellido = document.querySelector("#input-apellido");
    const dni = document.querySelector("#input-dni");
    const calle = document.querySelector("#input-calle");
    const altura = document.querySelector("#input-altura");
    const localidad = document.querySelector("#input-localidad");
    const contrasenia = document.querySelector("#input-contrasenia");
    const confContrasenia = document.querySelector("#input-rep-contrasenia");
    const formulario = document.forms[0];
    const url = "http://localhost:8080/pacientes/registrar";
    
    
    formulario.addEventListener('submit', function (event) {
    
        event.preventDefault();

        // if (renderizarErrores(nombre.value, apellido.value, email.value, contrasenia.value, confContrasenia.value)) {
        //     crearUsuario();
        // } 

        crearUsuario();
    
    });
    

    /* armar json y settings */
    
    function crearUsuario() {
        const objeto = {
                id : 1,
                nombre : nombre,
                apellido : apellido,
                dni : dni,
                domicilio : {
                    calle : calle,
                    altura : altura,
                    localidad : localidad
                },
                fechaAlta: ""
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
    

    /* hacer post */

    function realizarRegister(settings) {
        
        fetch(url, settings)
            .then (respuesta => {
                console.log(respuesta);
                if (!respuesta.ok) {
                    alert("Alguno de los datos es incorrecto");
                } 
                return respuesta.json();
            })
            .then (data => {
                console.log(data);
                if (data.jwt) {
                    localStorage.setItem("id", JSON.stringify(data.id));
                    location.replace("./home.html");
                }
            })
            .catch(error => {
                console.log("Promesa rechazada");
                console.log(error);
            })
        
    };  
    });