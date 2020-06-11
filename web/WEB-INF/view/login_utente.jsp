<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.bondicomella.lido.utente.model.Utente" %>
<html>
<jsp:include page="../data/header.jsp" />
<body>
<%  Utente utente = (Utente) session.getAttribute("utente"); %>


<div class="card text-white bg-info mb-3" style="max-width: 18rem;">
    <div class="card-body">
        <h5 class="card-title">Salve sig: <%= utente.getNome() %></h5>
        <p class="card-text">Ruolo: <%= utente.getRuolo()%></p>
    </div>
</div>

    <div>
        Prenota Postazione
        Modifica o Elimina Prenotazione
        Effettua un ordine al bar
    </div>


</body>
</html>
