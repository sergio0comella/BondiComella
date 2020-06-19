 $(document).ready(function () {
    $(".piatto:checkbox:checked").each(function () {
        if($(this).is(':checked')){
            ItemsActive.push(this.id);
        }
    });
     $(".piatto:checkbox:not(:checked)").each(function () {
         if($(this).not(':checked')){
             ItemsNotActive.push(this.id);
         }
     });

 });

 let ItemsActive= [];
 let ItemsNotActive = [];

$(".piatto").on("change", function () {
        if($(this).is(':checked') && !ItemsActive.includes(this.id)) {
            ItemsActive.push(this.id);

        }else if($(this).not(':checked') && ItemsActive.includes(this.id)){
            const index = ItemsActive.indexOf(this.id);
            if (index > -1) {
                ItemsActive.splice(index, 1);
            }
            console.log(ItemsNotActive);
        }
    });

 $(".piatto").on("change", function () {
     if($(this).not(':checked') && !ItemsNotActive.includes(this.id)) {
         ItemsNotActive.push(this.id);

     }else if($(this).not(':checked') && ItemsNotActive.includes(this.id)){
         const index = ItemsNotActive.indexOf(this.id);
         if (index > -1) {
             ItemsNotActive.splice(index, 1);
         }
     }
 });



    $('#modificaMenu').click(function() {
        let dataJsonActive = JSON.stringify(ItemsActive);
        let dataJsonNotActive = JSON.stringify(ItemsNotActive)
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/menu",
            data: {
                ItemsActive:dataJsonActive,
                ItemsNotActive:dataJsonNotActive
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








