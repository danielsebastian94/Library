
var myURLClient = 'https://g8ae90527c5e62a-reto1.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/client/client';


function getClients() {
//funcion GET
    $.ajax({
        // la URL para la petición
        url : myURLClient,
        type : 'GET',  //type especifica si será una petici0n POST o GET
        dataType : 'json',  //datatype el tipo de información que se espera de respuesta

        // success metodo o código a ejecutar si la petición es exitosa
        // la respuesta es pasada como argumento a la función a la variable json o como se llame
        success : function(clients) {
            //console.log(clients); //mostrar el contenido del parametro clients
            let cs=clients.items; //del objeto clients selecciona el atributo item
            //console.log(cs);//mostrar el contenido del parametro clients atributo item
            $("#clients").empty(); //deja vacios los elementos del DOM de clients
            for(let i=0;i<cs.length;i++){
                let k=cs[i].id+" "+cs[i].name+" "+cs[i].email+" "+cs[i].age+"<button onclick='deleteClient("+cs[i].id+")'>Borrar</button>";
                k+="<button onclick='getDetailClient("+cs[i].id+")'>Actualizar</button><br>";
                $("#clients").append(k); //append agrega el contenido de elementos que tiene #Clients
            }
        },

        // código a ejecutar si la peticion falla;
        // son pasados como argumentos a la funcion
        // jqXHR el objeto de la petición en crudo XMLHttpRequest,
        //String textStatus cadena  que muestra el tipo de error ocurrido argument (besides null) are "timeout", "error", "abort", and "parsererror"
        //String errorThrown un objeto de excepcion opcional recibe la parte textual del estado HTTP, ej "No encontrado" o "Error interno del servidor"
        error : function(xhr, status) {
            alert('ha sucedido un problema'); //cuando la peticion sea un error va mostrar este mensaje
        }
    });
}

function getClientInfo(){
    let idClient=$("#idClient").val(); //del documento html traer el valor que corresponde $("#idClient") 
    let nameClient=$("#nameClient").val();
    let emailClient=$("#emailClient").val();
    let ageClient=$("#ageClient").val();

    //client es un objeto y laS variable deben ser igual a como se creo en tabla client en APEX
    let client={
        id:idClient,
        name:nameClient,
        email:emailClient,
        age:ageClient
    };
    return client;
}
//function para limpiar los inputs
function cleanInputs(){
    $("#idClient").val(""); //se pasa a jquery el campo vacio "" lo que es un getter y cuando lleva valor es un setter 
    $("#nameClient").val("");
    $("#emailClient").val("");
    $("#ageClient").val("");
}

function saveClient(){
    let data=getClientInfo();        //data trae una cadena de datos
    let dataToSend=JSON.stringify(data); //dataToSend 
    console.log(data);   //el data genera un objeto para javascript
    console.log(dataToSend);  //genera una cadena de texto en formato JSON y contiene el objeto data
    
    $.ajax({
        // la URL para la petición
        url : myURLClient,
        type : 'POST',  //type de petici0n 
        contentType : 'application/json',  //contentype el tipo de contenido que vamos a enviar en el cuerpo
        data:dataToSend,

        success : function(clients) {
            cleanInputs();
            getClients();
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); 
        }
    });
}

function updateClient(){
    let data=getClientInfo();        //data trae una cadena de datos
    let dataToSend=JSON.stringify(data); //dataToSend 
    console.log(data);   //el data genera un objeto para javascript
    console.log(dataToSend);  //genera una cadena de texto en formato JSON y contiene el objeto data
    
    $.ajax({
        // la constante URL global de la petición
        url : myURLClient,
        type : 'PUT',  //type de petici0n 
        contentType : 'application/json',  //contentype el tipo de contenido que vamos a enviar en el cuerpo
        data:dataToSend,

        success : function(clients) {
            cleanInputs();
            getClients();
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); 
        }
    });
}

function deleteClient(idClient){   //aqui se recibe la data al crear la variable idClient
    
    let data={id:idClient};        //se recibe la data
    let dataToSend=JSON.stringify(data); //dataToSend 
    
    $.ajax({
        // la URL para la petición
        url : myURLClient,
        type : 'DELETE',  //type de petici0n 
        contentType : 'application/json',  //contentype el tipo de contenido que vamos a enviar en el cuerpo
        data:dataToSend,

        success : function(clients) {
            cleanInputs();
            getClients();
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); 
        }
    });
}

function getDetailClient(idClient){
    
    $.ajax({
        // la URL para la petición
        url : myURLClient+"/"+idClient,
        type : 'GET',  //type especifica si será una petici0n POST o GET
        dataType : 'json',  //datatype el tipo de información que se espera de respuesta

        success : function(clients) {
            let cs=clients.items; //del objeto clients selecciona el atributo item
            
            $("#idClient").val(cs[0].id); //se pasa a jquery el campo vacio "" lo que es un getter y cuando lleva valor es un setter 
            $("#nameClient").val(cs[0].name);
            $("#emailClient").val(cs[0].email);
            $("#ageClient").val(cs[0].age);
                  
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); //cuando la peticion sea un error va mostrar este mensaje
        }
    });
}