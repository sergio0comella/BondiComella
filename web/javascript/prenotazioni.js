$(document).ready(function () {
    $("#riepilogoButton").on('click', function (e) {
        e.preventDefault();
        let codice = $("#codicePrenotazione").val();
        if (codice === undefined || codice === '') {
            alert("Inserire un codice");
        } else {
            getPrenotazioneByCodice(codice, (response) => {
                $("#utentePrenotazione").html(response.utente);
                $("#oraInizio").html(response.prenotazione.oraInizio);
                $("#oraFine").html(response.prenotazione.oraFine);
                $("#dataPrenotazione").html((response.prenotazione.dataPrenotazione.toUpperCase()));
                let isPagata = response.prenotazione.pagata === "1" ? 'Sì' : 'No';
                $("#statoPagamento").html(isPagata);
                if(response.prenotazione.pagata){
                    $("#pagaPrenotazione").hide();
                }
                $(".idPrenotazione").val(response.prenotazione.id);
                $("#riepilogoPrenotazione").modal('show');
            });
        }
    });

    $("#filtro").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#tabellaPrenotazioni tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $('.basicAutoComplete').autoComplete({
        resolverSettings: {
            url: 'http://localhost:8080/apiUtente'
        },
    });
});


function confermaPrenotazione() {
    let idPrenotazione = $(".idPrenotazione").val();
    console.log(idPrenotazione);
    $.ajax({
        type: "PUT",
        url: 'http://localhost:8080/infoPrenotazioni?id=' + idPrenotazione + '&tipo=CONFERMA',
        success: function () {
            alert("Prenotazione confermata");
        },
        error: function () {
            alert("Postazione NON occupata, riprovare");
        }
    });
}

function pagaPrenotazione() {
    let idPrenotazione = $(".idPrenotazione").val();
    $.ajax({
        type: "PUT",
        url: 'http://localhost:8080/infoPrenotazioni?id=' + idPrenotazione + '&tipo=PAGA',
        success: function () {
            alert("Pagamento effettuato con successo");
        },
        error: function () {
            alert("Pagamento non riuscito");
        }
    });
}


function getPrenotazioneByCodice(codice, callback) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/infoPrenotazioni?codice=' + codice,
        dataType: 'json',
        success: function (result) {
            callback(result);
        },
        error: function (error) {
            console.log("Impossibile eseguire l'operazione")
        }
    });
}

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

    $("#prenotaButton").hide();
    $("#loadingPrenota").show();

    let dataPrenotazione = $("#dateValue").val();
    let oraInizioPrenotazione = $("#timeStartValue").val();
    let oraFinePrenotazione = $("#timeEndValue").val();
    let postazione = $("#idPostazione").val();
    let isPagato = $("#isPagato option:selected").val();
    let emailUtente = $("#emailUtente").val();

    let data = {
        dataPrenotazione: dataPrenotazione,
        oraInizio: oraInizioPrenotazione,
        oraFine: oraFinePrenotazione,
        postazione: postazione,
        isPagato: isPagato,
        emailUtente: emailUtente,
    };

    let isBgt = $("#isBgt").val() === 'true';

    if (isBgt) {

        if (emailUtente === undefined || emailUtente === "") {
            $("#loadingPrenota").hide();
            $("#prenotaButton").show();
            alert("Inserire una mail valida");
            return;
        }

        checkEmailExist(emailUtente, (esito) => {
            if (!esito) {
                $("#loadingPrenota").hide();
                $("#prenotaButton").show();
                alert("L'email inserita non è associata ad alcun utente.");
            } else {
                if (isBgt) data.isPagato = 1;
                addPrenotazione(data);
            }
        });
    }else{
        addPrenotazione(data);
    }
}

function addPrenotazione(data){
    $.ajax({
        url: 'http://localhost:8080/apiPrenotazioni',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(data),
        cache: false,
        success: function (result) {
            $('#prenotazioneModal').modal('toggle');
            $("#prenotaButton").show();
            $("#loadingPrenota").hide();
            alert(result.message);
            location.reload();
        },
        error: function () {
            $("#loadingPrenota").hide();
            $("#prenotaButton").show();
            alert("Qualcosa è andato storto, invitiamo a riprovare");
        }
    })
}

function checkEmailExist(email, callback) {
    $.ajax({
        url: 'http://localhost:8080/apiUtente',
        type: 'GET',
        success: function (result) {
            if (result.indexOf(email) > 0) {
                callback(true);
            } else {
                callback(false);
            }
        },
        error: function () {
            alert("Qualcosa è andato storto");
        }
    })

}