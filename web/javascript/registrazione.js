$(document).ready(function () {
});

$("#re-password").on('focusout', function (e) {
    e.preventDefault()
    if ($("#password").val() !== $("#re-password").val()) {
        $("#Error").text("Le password non coincidono");
        $("#Error").show();
    } else if ($("#password").val() === $("#re-password").val()) {
        $("#Error").hide();
    }
});

$("#email").on('focusout', function (e) {
    e.preventDefault()
    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#email").val())) {
        $("#message").text("Formato email errato");
        $("#Error").show();
    } else {
        $("#Error").hide();
    }

});


//L'utente si registra
$("#registrati").on("click", function () {

    let controllo_password = false;
    if ($("#re-password").val() === $("#password").val()) {
        controllo_password = true;
    }

    let controllo_email = false;
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#email").val())) {
        controllo_email = true;
    }

    if ($("#nome")[0].checkValidity() && $("#cognome")[0].checkValidity() && $("#email")[0].checkValidity() && controllo_password && controllo_email) {

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
                password: password,
                fromBgt: false,
            },
            async: false,
            success: function () {
                alert("Registrazione avvenuta con successo. Ti stiamo portando alla home...")
                window.location.href = "/homeAuth"
            },
            error: function (result) {
                if (result.responseText === "NOTVALIDEMAIL") {
                    $("#message").text("Email già registrata, effettua il login");
                    $("#Error").show();
                } else {
                    $("#message").text("Errore durante l'invio del Form, riprova");
                    $("#Error").show();
                }
            }
        });

    }
    else {
        alert("Errore durante l'invio del form, si prega di riprovare")
    }
});

//Richiesta registrazione da parte del Bigliettaio
    $("#registraUtente").on("click", function () {
        let controllo_email = false;
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#email").val())) {
            controllo_email = true;
        }
        if ($("#nome")[0].checkValidity() && $("#cognome")[0].checkValidity() && $("#email")[0].checkValidity() && controllo_email) {

            var nome = $("#nome").val();
            var cognome = $("#cognome").val();
            var email = $("#email").val();
            var ruolo = $("#roleOption").val();

                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/apiRegistrazione",
                    data: {
                        nome: nome,
                        cognome: cognome,
                        email: email,
                        ruolo: ruolo,
                        fromBgt: true
                    },
                    async: false,
                    success: function () {
                        alert("Registrazione avvenuta con successo.")
                    },
                    error: function (result) {
                        if (result.responseText === "NOTVALIDEMAIL") {
                            $("#message").text("Email già registrata");
                            $("#Error").show();
                        } else {
                            $("#message").text("Errore durante l'invio del Form, riprova");
                            $("#Error").show();
                        }
                    }
                });

            } else {
                alert("Errore durante l'invio del form, si prega di riprovare")
            }
});
