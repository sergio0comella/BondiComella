function setOptionsPostazione(postazione) {
    let idPostazione = (postazione.id).split('_');
    $("#postSelected").text(idPostazione[1]);
    $("#postSelected").val(idPostazione[1]);
}

function occupaPostazione(){

    $.ajax({
        type: 'PUT',
        dataType: 'JSON',
        data: {
            id : $("#postSelected").val(),
            stato : "OOOOO"
        },
        url: 'http://localhost:8080/lido/apiPostazioni',
        success: function (result) {
            location.reload();
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function liberaPostazione() {

}