
var myURLMessage = 'https://g8ae90527c5e62a-reto1.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/message/message';

function getMessages() {
//funcion GET
    $.ajax({
        // la URL para la petición
        url : myURLMessage,
        type : 'GET',  //type especifica si será una petici0n POST o GET
        dataType : 'json',  //datatype el tipo de información que se espera de respuesta

        // success metodo o código a ejecutar si la petición es exitosa
        // la respuesta es pasada como argumento a la función a la variable json o como se llame
        success : function(messages) {
            //console.log(messages); //mostrar el contenido del parametro messages
            let mg=messages.items; //del objeto messages selecciona el atributo item
            //console.log(cs);//mostrar el contenido del parametro messages atributo item
            $("#messages").empty(); //deja vacios los elementos del DOM de messages
            for(let i=0;i<mg.length;i++){
                let k=mg[i].id+" "+mg[i].messagetext+" "+"<button onclick='deleteMessage("+mg[i].id+")'>Borrar</button>";
                k+="<button onclick='getDetailMessage("+mg[i].id+")'>Actualizar</button><br>";
                $("#messages").append(k); //append agrega el contenido de elementos que tiene #messages
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

function getMessageInfo(){
    let idMessage=$("#idMessage").val(); //del documento html traer el valor que corresponde $("#idMessage") 
    let messageTextMessage=$("#messageTextMessage").val();
  
    //message es un objeto y laS variable deben ser igual a como se creo en tabla message en APEX
    let message={
        id:idMessage,
        messagetext:messageTextMessage
    };
    return message;
}
//function para limpiar los inputs
function cleanInputsMessages(){
    $("#idMessage").val(""); //se pasa a jquery el campo vacio "" lo que es un getter y cuando lleva valor es un setter 
    $("#messageTextMessage").val("");
}

function saveMessage(){
    let data=getMessageInfo();        //data trae una cadena de datos
    let dataToSend=JSON.stringify(data); //dataToSend 
    console.log(data);   //el data genera un objeto para javascript
    console.log(dataToSend);  //genera una cadena de texto en formato JSON y contiene el objeto data
    
    $.ajax({
        // la URL para la petición
        url : myURLMessage,
        type : 'POST',  //type de petici0n 
        contentType : 'application/json',  //contentype el tipo de contenido que vamos a enviar en el cuerpo
        data:dataToSend,

        success : function(messages) {
            cleanInputsMessages();
            getMessages();
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); 
        }
    });
}

function updateMessage(){
    let data=getMessageInfo();        //data trae una cadena de datos
    let dataToSend=JSON.stringify(data); //dataToSend 
    console.log(data);   //el data genera un objeto para javascript
    console.log(dataToSend);  //genera una cadena de texto en formato JSON y contiene el objeto data
    
    $.ajax({
        // la constante URL global de la petición
        url : myURLMessage,
        type : 'PUT',  //type de petici0n 
        contentType : 'application/json',  //contentype el tipo de contenido que vamos a enviar en el cuerpo
        data:dataToSend,

        success : function(messages) {
            cleanInputsMessages();
            getMessages();
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); 
        }
    });
}

function deleteMessage(idMessage){   //aqui se recibe la data al crear la variable idMessage
    
    let data={id:idMessage};        //se recibe la data
    let dataToSend=JSON.stringify(data); //dataToSend 
    
    $.ajax({
        // la URL para la petición
        url : myURLMessage,
        type : 'DELETE',  //type de petici0n 
        contentType : 'application/json',  //contentype el tipo de contenido que vamos a enviar en el cuerpo
        data:dataToSend,

        success : function(messages) {
            cleanInputsMessages();
            getMessages();
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); 
        }
    });
}

function getDetailMessage(idMessage){
    
    $.ajax({
        // la URL para la petición
        url : myURLMessage+"/"+idMessage,
        type : 'GET',  //type especifica si será una petici0n POST o GET
        dataType : 'json',  //datatype el tipo de información que se espera de respuesta

        success : function(messages) {
            let mg=messages.items; //del objeto messages selecciona el atributo item
            
            $("#idMessage").val(mg[0].id); //se pasa a jquery el campo vacio "" lo que es un getter y cuando lleva valor es un setter 
            $("#messageTextMessage").val(mg[0].messagetext);
        },

        // código a ejecutar si la peticion falla;
        error : function(xhr, status) {
            alert('ha sucedido un problema'); //cuando la peticion sea un error va mostrar este mensaje
        }
    });
}