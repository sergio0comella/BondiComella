<%@ page import="it.bondicomella.lido.biglietteria.model.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.bondicomella.lido.utente.model.Utente" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../data/header.jsp" />
<body>
<div class="container mt-5">
    <%if(request.isUserInRole("BGN") || request.isUserInRole("BGT")){%>
    <h3>Verifica Prenotazione</h3>
    <div class="row justify-content-center mt-3 mb-5">
        <div class="col-6">
            <input type="text" class="form-control" id="codicePrenotazione" maxlength="8" minlength="8" placeholder="Codice">
        </div>
        <div class="col-6">
            <button class="btn btn-block btn-info" data-toggle="modal" id="riepilogoButton">Verifica Prenotazione</button>
        </div>
    </div>
    <%}%>
    <h1 class="text-center"> Elenco Prenotazioni </h1>
    <input class="form-control mb-3 mt-3 col-4 offset-8" id="filtro" type="text" placeholder="Filtra prenotazioni">
    <hr class="mt-3 mb-3">
    <div class="dropdown-divider"></div>
    <div class="row justify-content-center font-italic">
        <div class="col-1">
            Legenda:
        </div>
        <div class="col-2">
            Bianco = Da pagare
        </div>
        <div class="col-2" style="color: grey;">
            Grigio = Annullato
        </div>
        <div class="col-2" style="color: green;">
            Verde = Pagato
        </div>
        <div class="col-2" style="color: red;">
            Rosso = confermata
        </div>
    </div>
    <div class="dropdown-divider"></div>
    <table class="table mt-4">
        <thead class="thead-light">
        <tr>
            <th scope="col">Utente</th>
            <th scope="col">Email</th>
            <th scope="col">N. Postazione</th>
            <th scope="col">Data</th>
            <th scope="col">Dalle ore</th>
            <th scope="col">Alle ore</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody id="tabellaPrenotazioni">
        <%
        Map<Prenotazione, Utente> prenotazioni = (Map<Prenotazione, Utente>) request.getAttribute("prenotazioni");
        for(Map.Entry<Prenotazione, Utente> pt : prenotazioni.entrySet()){
        %>
        <% String rowColor = "";
            if(pt.getKey().isAnnullata() == 1){
                 rowColor = "table-dark";
            } else if(pt.getKey().isPagata() && pt.getKey().isAnnullata()==0) {
                 rowColor = "table-success";
            } else if(pt.getKey().isAnnullata() == 2){
                rowColor ="table-danger";
            }%>
            <tr class="<%=rowColor%>">
                <td>
                    <%= pt.getValue().getCognomeNome() %>
                </td>
                <td>
                    <%= pt.getValue().getEmail() %>
                </td>
                <td class="text-center">
                    #<%= pt.getKey().getFkIdPostazione() %>
                </td>
                <td>
                    <%= pt.getKey().getDataPrenotazione()%>
                </td>
                <td>
                    <%= pt.getKey().getOraInizio() %>
                </td>
                <td>
                    <%= pt.getKey().getOraFine() %>
                </td>
                <%if(!request.isUserInRole("BGN")){%>
                <td>
                    <button class="btn btn-sm btn-danger" id="pren_<%=pt.getKey().getId()%>" onclick="deletePrenotazione(this)" <%=pt.getKey().isAnnullata()== 1 ? "disabled" : ""%> >Annulla</button>
                </td>
                <%}%>
            </tr>
        <%}%>
        </tbody>
    </table>
    <jsp:include page="riepilogoPrenotazione.jsp" />
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/prenotazioni.js" crossorigin="anonymous"></script>
    <script>
    </script>
</html>
