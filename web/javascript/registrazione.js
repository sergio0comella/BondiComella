$(document).ready(function() {

    $("#bottone").on("click",function () {

        if($("#nome")[0].checkValidity() && $("#cognome")[0].checkValidity() && $("#email")[0].checkValidity() &&
            $("#password").val() == $("#re-password").val()){

            console.log($("#nome").val() + $("#cognome").val() + $("#email").val() + $("#password").val());

            var user = {
                "nome" : $("#nome").val(),
                "cognome" : $("#cognome").val(),
                "email" : $("#email").val(),
                "password" : $("#password").val(),
            }

            console.log(user);

            $.ajax({
                type: "POST",
                url: "apiRegistrazione",
                contentType:"application/json;charset=UTF-8",
                data:JSON.stringify(user),
                dataType:"json",
                success : function () {
                    console.log("giusto")
                },
                error : function () {
                    console.log("sbaglaito")
                }
            })

        }else {
            console.log("dati non validi");
         }
    });
});