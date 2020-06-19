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
                alert("Impossibile modificare la prenotazione.");
                console.log(error);
            }
        })
    }
}

/**
 * Gestione datepicker prenotazioni
 * **/
$.fn.datetimepicker.Constructor.Default = $.extend({}, $.fn.datetimepicker.Constructor.Default, {
    icons: {
        time: 'far fa-clock',
        date: 'far fa-calendar',
        up: 'fas fa-arrow-up',
        down: 'fas fa-arrow-down',
        previous: 'fas fa-chevron-left',
        next: 'fas fa-chevron-right',
        today: 'far fa-calendar-check-o',
        clear: 'far fa-trash',
        close: 'far fa-times'
    }
});

$(function () {
    let timeStart = $("#timeStart");
    let timeEnd = $("#timeEnd");
    let datePicker = $("#datePicker");

    datePicker.datetimepicker({
        locale: 'it',
        useCurrent: false,
        format: 'DD/MM/YYYY',
        stepping: 60,
        minDate: Date.now(),
    });

    timeStart.datetimepicker({
        format: 'HH:mm',
        stepping: 60,
        locale: 'it',
        minDate: Date.now()
    });
    timeEnd.datetimepicker({
        format: 'HH:mm',
        stepping: 60,
        locale: 'it',
        minDate: Date.now()
    })


    timeStart.on("change.datetimepicker", function (e) {
        timeEnd.datetimepicker('minDate', e.date.add(1, 'h'));
    });
    timeEnd.on("change.datetimepicker", function (e) {
        timeStart.datetimepicker('maxDate', e.date);
    });
});

/**
 * Invio della prenotazione
 **/
function sendPrenotazione() {

    $("#loading").show();
    $("#postazioneSelected").hide();

    let dataPrenotazione = $("#dateValue").val();
    let oraInizioPrenotazione = $("#timeStartValue").val();
    let oraFinePrenotazione = $("#timeEndValue").val();
    let postazione = $("#idPostazione").val();
    let isPagato = $("#isPagato option:selected").val();
    let emailUtente = '';

    let data = {
        dataPrenotazione: dataPrenotazione,
        oraInizio: oraInizioPrenotazione,
        oraFine: oraFinePrenotazione,
        postazione: postazione,
        isPagato: isPagato,
        emailUtente: emailUtente,
    };

    $.ajax({
        url: 'http://localhost:8080/apiPrenotazioni',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(data),
        cache:false,
        success: function (result) {
            alert(result.message);
            $("#loading").hide();
            $("#postazioneSelected").show();
        },
        error: function () {
            $("#loading").hide();
            $("#postazioneSelected").show();
            alert("err");
        }
    })
}
