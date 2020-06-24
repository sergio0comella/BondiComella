let listaOrdini = {};

//la quantità viene inserita nell'indice dell'array che corrisponde all'id del prodotto selezionato

$(".quantita").on("change",function () {
    let id = getIDbyExplode(this.id);
    if(this.value == 0){
        $("#row_"+id).removeClass("table-success");
        delete listaOrdini[id];
    }else{
        $("#row_"+id).addClass("table-success");
        listaOrdini[id] = this.value;
    }
});

function calcolaTotale() {
    $("#totale").val(0);
    $.each(listaOrdini, function(i, quantita) {
        let selector = ".table #row_" + i + " .costo"
        let costoUnitario = $(selector).text().trim().split(' ')[1];

        let oldTotale = parseFloat($("#totale").val());
        $("#totale").val(oldTotale +  quantita * parseFloat(costoUnitario));
        let newTotale = $("#totale").val();
        $("#totale").html(newTotale);
    });


}

function getIDbyExplode(stringID) {
    let id = stringID.split("_");
    return id[1];
}


$('#effettuaOrdine').click(function () {
   let ordini = JSON.stringify(listaOrdini);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/BondiComella/ordinazione",
        dataType:"json",
        contentType:"application/json",
        data : ordini,
        success: function (result) {
            alert(result.message);
        },
        error: function () {
            alert("Errore durante il processo dell'ordine")
        }
    });
});

function completaOrdinazione(id) {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/BondiComella/ordinazione?id='+id,
        success: function () {
            location.reload();
        },
        error: function (error) {
            console.log("Impossibile eseguire l'operazione")
        }
    })
}
