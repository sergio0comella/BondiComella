$(document).ready(function () {

});

$("#re-password").on('focusout', function(e){
    e.preventDefault()
    if( $("#password").val() != $("#re-password").val()){
        $("#Error").text("Le password non coincidono");
        $("#Error").show();
    }else if($("#password").val() == $("#re-password").val()){
        $("#Error").hide();
    }
});

$("#email").on('focusout', function(e){
    e.preventDefault()
    if( !/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#email").val()) ){
        $("#message").text("Formato email errato");
        $("#Error").show();
    }else{
        $("#Error").hide();
    }

});



    $("#bottone").on("click", function () {
    if ($("#re-password").val() != $("#password").val()) {
        var controllo_password = false;
    }else{
        var controllo_password = true;
    }
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#email").val())){
        var controllo_email=true;
    }else{
        var controllo_email=false;
    }


    if ($("#nome")[0].checkValidity() && $("#cognome")[0].checkValidity() && $("#email")[0].checkValidity() && controllo_password && controllo_email) {

        console.log($("#nome").val() + $("#cognome").val() + $("#email").val() + $("#password").val());

        var nome = $("#nome").val();
        var cognome = $("#cognome").val();
        var email = $("#email").val();
        var password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/apiRegistrazione",
            data: {
                nome: nome,
                cognome: cognome,
                email: email,
                password: password
            },
            async: false,
            success: function () {
                window.location.href="/homeAuth"
            },
            error: function (result) {
                if(result.responseText === "NOTVALIDEMAIL"){
                    $("#message").text("Email già registrata, effettua il login");
                    $("#Error").show();
                }else{
                    $("#message").text("Errore durante l'invio del Form, riprova");
                    $("#Error").show();
                }
            }
        })

    } else {
        alert("Errore durante l'invio del form, si prega di riprovare")
    }
});
