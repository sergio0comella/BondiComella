function deletePrenotazione(prenotazione) {
    if (confirm("Vuoi annullare la prenotazione?")) {
        let idPrenotazione = prenotazione.id.split('_')[1];
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/apiPrenotazioni?idPrenotazione=' + idPrenotazione,
            success: function () {
                $("#" + prenotazione.id).parent().parent().addClass('table-dark');
                $("#" + prenotazione.id).attr('disabled', true);
            },
            error: function (error) {
                console.log(error)
            }
        })
    }
}