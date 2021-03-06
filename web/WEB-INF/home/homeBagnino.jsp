<%@ page import="it.bondicomella.lido.utente.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../data/header.jsp"/>
<body>
<div class="container mt-4">
    <% Utente ut = (Utente) request.getAttribute("utente"); %>
    <div class="row mt-5 mb-4">
        <div class="col-12 text-center">
            <h1>Riepilogo utente</h1>
        </div>
    </div>
    <div class="row row-cols-2 justify-content-center">
        <div class="col-4">
            <h4>Utente: <%=ut.getCognomeNome()%>
            </h4>
        </div>
        <div class="col-4">
            <h4>Ruolo: <%=ut.getRuolo()%>
            </h4>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-12 text-center">
            <h1>Menu</h1>
        </div>
        <div class="col-12">
            <button type="button" class="btn btn-info btn-lg btn-block mt-1 mb-1" onclick="redirectPage('listaPostazioni')" >
                Visualizza postazioni
            </button>
        </div>
        <div class="col-12">
            <button type="button" class="btn btn-info btn-lg btn-block mt-1 mb-1" onclick="redirectPage('home/listaPrenotazioni')" >
                Elenco Prenotazioni
            </button>
        </div>
    </div>
    <hr class="mt-4">
    <jsp:include page="nuovaPrenotazione.jsp"/>
</div>
</body>
<script>
    function redirectPage(pageName) {
        window.location.href = pageName;
    }
</script>
</html>