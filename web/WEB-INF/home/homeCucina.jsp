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
        <div class="col-12 text-center"> <!-- OGNI 12 COL finisce la row -->
            <h1>Menu</h1>
        </div>
    </div>
    <form >
        <div class="form-row justify-content-center">
            <div class="col-sm-4">
                <input type="text" class="form-control" id="nomePiatto" placeholder="Nuovo piatto"
                       name="nomePiatto" required>
            </div>
            <div class="col-sm-2">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrepend">€</span>
                    </div>
                    <input type="number" step="0.01" min="1" max="30" class="form-control" id="costoPiatto"
                           placeholder="Prezzo" name="costoPiatto" aria-describedby="inputGroupPrepend" required>
                </div>
            </div>
            <div class="col-2">
                <button id="inserisciPiatto" class="btn btn-success">Aggiungi alla lista</button>
            </div>
        </div>
    </form>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/aggiungiPiatto.js"
        crossorigin="anonymous"></script>
<script>
    function redirectPage(pageName) {
        window.location.href = pageName;
    }
</script>
</html>