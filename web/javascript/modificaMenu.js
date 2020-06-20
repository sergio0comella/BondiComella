$(document).ready(function () {
    $(".piatto:checkbox:checked").each(function () {
        if ($(this).is(':checked')) {
            ItemsActive.push(this.id);
        }
    });
    $(".piatto:checkbox:not(:checked)").each(function () {
        if ($(this).not(':checked')) {
            ItemsNotActive.push(this.id);
        }
    });

});

let ItemsActive = [];
let ItemsNotActive = [];

$(".piatto").on("change", function () {
    if ($(this).is(':checked') && !ItemsActive.includes(this.id)) {
        ItemsActive.push(this.id);
    } else if ($(this).not(':checked') && ItemsActive.includes(this.id)) {
        const index = ItemsActive.indexOf(this.id);
        if (index > -1) {
            ItemsActive.splice(index, 1);
        }
        console.log(ItemsNotActive);
    }
});

$(".piatto").on("change", function () {
    if ($(this).not(':checked') && !ItemsNotActive.includes(this.id)) {
        ItemsNotActive.push(this.id);

    } else if ($(this).not(':checked') && ItemsNotActive.includes(this.id)) { //Quindi qui se faccio l'uncheck
        const index = ItemsNotActive.indexOf(this.id);                         // ed è negli elementi disattivati
        if (index > -1) {                                                       // lo rimuovo... Ne sei sicuro???
            ItemsNotActive.splice(index, 1);
        }
    }
});
/**
 * Possibile refactor per semplificare il codice:
 *
 * UNA sola on change contenente
 *
 *
    if($(this).is(':checked')){
        if(!ItemsActive.includes(this.id))  ItemsActive.push(this.id);
        if(ItemsNotActive.includes(this.id))  {
            const index = ItemsNotActive.indexOf(this.id);
            if (index > -1) {
                ItemsNotActive.splice(index, 1);
            }
        }
    }
    else if($(this).not(':checked')){
        if(!ItemsNotActive.includes(this.id)) ItemsNotActive.push(this.id);
        if(ItemsActive.includes(this.id))  {
            const index = ItemsActive.indexOf(this.id);
            if (index > -1) {
                ItemsActive.splice(index, 1);
            }
        }
    }

 *Due condizioni superiori: check e uncheck
 * per ogni condizione due sotto condizioni: una gestisce l'array degli attivi e l'altro quello dei disattivi.
 */

$('#modificaMenu').click(function () {
    let dataJsonActive = JSON.stringify(ItemsActive);
    let dataJsonNotActive = JSON.stringify(ItemsNotActive)
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/menu",
        data: {
            ItemsActive: dataJsonActive,
            ItemsNotActive: dataJsonNotActive
        },
        async: false,
        success: function () {
            alert("Menù modificato")
        },
        error: function () {
            alert("Menù non modificato")
        }
    });
});








