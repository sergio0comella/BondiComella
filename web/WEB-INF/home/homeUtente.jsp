<%@ page import="it.bondicomella.lido.utente.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../data/header.jsp" />
<link rel="stylesheet" type="text/css" href="../../style/testo.css">
<body>
<br>
<%  Utente ut = (Utente) request.getAttribute("utente"); %>
<br>
<div class="row ">

<div class="card border-primary offset-1" style="width: 20rem; height: 10rem">
    <div class="card-header">Info</div>
    <div class="card-body text-primary">
        <div class="row">
            <p class="offset-2">Nome: <%= ut.getNome()%></p>
        </div>
    <div class="row">
            <p class="offset-2">Email: <%= ut.getEmail()%></p>
    </div>
    </div>
</div>

<div class="card border-dark offset-1" style="width: 25rem;">
    <div class="card-header">Menù</div>
    <div class="card-body">
        <div class="row">
                <button type="button" class="btn btn-primary btn-lg btn-block mt-1 mb-1">
                    Effettua una prenotazione
                </button>
                <jsp:include page="nuovaPrenotazione.jsp" />
        </div>

        <div class="row">
                <button type="button" class="btn btn-primary btn-lg btn-block mt-1 mb-1">
                    Visualizza/Elimina Prenotazione
                </button>
        </div>
        <div class="row">
                <button type="button" class="btn btn-primary btn-lg btn-block mt-1 mb-1">
                    Effettua un ordine al bar
                </button>
        </div>
    </div>
</div>

</div>
</body>
<script>
    function newPrenotazione() {
        console.log("qui")
        $('#modalNuovaPrenotazione').on('shown.bs.modal', function () {
            $('#exampleInputEmail1').trigger('focus')
        })
    }
</script>
</html>
