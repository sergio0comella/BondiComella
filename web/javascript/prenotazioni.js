function deletePrenotazione(prenotazione){
//    let confirm = confirm("Vuoi annullare la prenotazione?");
    if(true) {
        let idPrenotazione = prenotazione.id.split('_')[0];
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/apiPrenotazioni',
            data: "idPrenotazione="+idPrenotazione,
            success: function () {
                prenotazione.addClass('table-dark');
            },
            error: function (error) {
                console.log(error)
            }
        })
    }
}