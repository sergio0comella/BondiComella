function setOptionsPostazione(postazione) {
    let idPostazione = (postazione.id).split('_');
    $(".postSelected").text('#' + idPostazione[1]);
    $(".postSelected").val(idPostazione[1]);

    if($("#idPostazione") !== undefined){
        $("#idPostazione").text('#' + idPostazione[1]);
        $("#idPostazione").val(idPostazione[1]);
    }
}

function editStatoPostazione(stato){
    let postazione = $(".postSelected").val();
    if(postazione === undefined || postazione === ''){
        alert('Selezionare una postazione')
    }else{
        if(stato === 'O'){
            this.occupaPostazione();
        }else if(stato === 'L'){
            this.liberaPostazione();
        }
    }
}

function occupaPostazione(){    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/apiPostazioni?id=' + $(".postSelected").val() + '&stato=O',
        success: function () {
            let postazione =  $("#post_"+$(".postSelected").val());
           postazione.addClass('btn-danger');
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function liberaPostazione() {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/apiPostazioni?id=' + $(".postSelected").val() + '&stato=L',
        success: function () {
            let postazione =  $("#post_"+$(".postSelected").val());
            postazione.removeClass('btn-danger');
            postazione.removeClass('btn-warning');
        },
        error: function (error) {
            console.log(error)
        }
    })
}