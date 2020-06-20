function setOptionsPostazione(postazione, stato) {

    let libera = $("#freeButton");
    let occupa = $("#busyButton");

    switch (stato) {
        case 'O':
            occupa.attr("disabled", true);
            libera.attr("disabled", false);
            break;
        case 'L':
            occupa.attr("disabled", false);
            libera.attr("disabled", true);
            break;
        case 'P':
            occupa.attr("disabled", false);
            libera.attr("disabled", true);
            break;
        default:
            occupa.attr("disabled", true);
            libera.attr("disabled", true);
            break;
    }

    let idPostazione = (postazione.id).split('_');
    let postazioneSelected = $(".postSelected");
    let postazioneText = $("#idPostazione");
    postazioneSelected.text('#' + idPostazione[1]);
    postazioneSelected.val(idPostazione[1]);

    if (postazioneText !== undefined && postazioneText !== '') {
        postazioneText.text('#' + idPostazione[1]);
        postazioneText.val(idPostazione[1]);
    }
}

function editStatoPostazione(stato) {
    let postazione = $(".postSelected").val();
    if (postazione === undefined || postazione === '') {
        alert('Selezionare una postazione')
    } else {
        if (stato === 'O') {
            this.checkOccupaPostazione();
        } else if (stato === 'L') {
            this.liberaPostazione();
        }
    }
}

function checkOccupaPostazione() {
    let id = $(".postSelected").val();
    if ($("#post_" + id).hasClass("prenotata")) {
        getPrenotazioniGiornaliereInPostazione(id, (confirm) => {
            if (confirm) {
                occupaPostazione(id);
            }
        });
    }else{
        occupaPostazione(id);
    }
}

function getPrenotazioniGiornaliereInPostazione(id, callback) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/infoPrenotazioni?id=' + id,
        dataType: 'json',
        success: function (result) {
            let message = "La postazione è già prenotata negli orari: "
            $.each(result, function (key, value) {
                message += " \n" + value.oraInizio + " - " + value.oraFine
            });
            callback(confirm(message));
        },
        error: function (error) {
            console.log("Impossibile eseguire l'operazione")
        }
    });
}

function occupaPostazione(id) {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/apiPostazioni?id=' + id + '&stato=O',
        success: function () {
            //let postazione = $("#post_" + $(".postSelected").val());
            //postazione.addClass('btn-danger');
            location.reload();
        },
        error: function (error) {
            console.log("Impossibile eseguire l'operazione");
        }
    })
}

function liberaPostazione() {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/apiPostazioni?id=' + $(".postSelected").val() + '&stato=L',
        success: function () {
            //let postazione = $("#post_" + $(".postSelected").val());
            // postazione.removeClass('btn-danger');
            // postazione.removeClass('btn-warning');
            location.reload();
        },
        error: function (error) {
            console.log("Impossibile eseguire l'operazione")
        }
    })
}

