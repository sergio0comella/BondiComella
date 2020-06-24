$("#inserisciPiatto").on("click",function () {
    if ($("#nomePiatto")[0].checkValidity() && $("#costoPiatto")[0].checkValidity()){
        var nomePiatto = $("#nomePiatto").val();
        var costo= $("#costoPiatto").val();
        console.log(nomePiatto);
        console.log(costo);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/BondiComella/aggiungiPiatto",
            data: {
                nomePiatto: nomePiatto,
                costo: costo
            },
            async: false,
            success: function () {
                alert("Piatto inserito nella lista")
            },
            error: function (result) {
                if (result.responseText === "NOTVALIDNOME") {
                    alert("Piatto già esistente nella lista")
                }
            }
        });
    }else{
        alert("Ricontrollare il form, il costo minimo è 1, il massimo 30")
    }
});