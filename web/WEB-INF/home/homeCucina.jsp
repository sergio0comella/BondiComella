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
            <form method="post">
                <div class="form-row">
                    <div class="col-md-3 mb-3 offset-2">
                        <label for="nomePiatto">Piatto</label>
                        <input type="text" class="form-control" id="nomePiatto" placeholder="Pasta al ragù" name="nomePiatto" required>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="costoPiatto">Costo  €</label>
                        <input type="number" step="0.01" min="1" max="30" class="form-control" id="costoPiatto" placeholder="5.99"  name="costoPiatto" required>
                    </div>
                </div>
            </form>
            <button id="inserisciPiatto"  class="btn btn-info mb-3 mt-4">Aggiungi alla lista</button>
        </div>
        </div>
        <div class="col-12">
            <button type="button" class="btn btn-info btn-lg btn-block mt-1 mb-1" onclick="redirectPage('/menu')">
                Modifica Menù del giorno
            </button>
        </div>
    </div>
    <hr class="mt-4">
    <div class="row">
        <div class="col-12 text-center">
            <h1>Ordinazioni da fare</h1>
            //TODO
        </div>
    </div>

    <jsp:include page="nuovaPrenotazione.jsp"/>

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/aggiungiPiatto.js" crossorigin="anonymous"></script>
<script>
    function redirectPage(pageName) {
        window.location.href = pageName;
    }
</script>
</html>