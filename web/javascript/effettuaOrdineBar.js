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
    console.log(listaOrdini);
});

function getIDbyExplode(stringID) {
    let id = stringID.split("_");
    return id[1];
}


$('#effettuaOrdine').click(function () {
    console.log("-------------------");
    console.log(listaOrdini);
    console.log("----------------------");
   let ordini = JSON.stringify(listaOrdini);
   console.log(ordini);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/ordinazione",
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
    console.log(id)
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/ordinazione?id='+id,
        success: function () {
            location.reload();
        },
        error: function (error) {
            console.log("Impossibile eseguire l'operazione")
        }
    })
}
