<%@ page import="it.bondicomella.lido.biglietteria.model.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.bondicomella.lido.utente.model.Utente" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 09/06/2020
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../data/header.jsp" />
<body>
<div class="container mt-5">
    <table class="table">
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
        <tbody>
        <%
        Map<Prenotazione, Utente> prenotazioni = (Map<Prenotazione, Utente>) request.getAttribute("prenotazioni");
        for(Map.Entry<Prenotazione, Utente> pt : prenotazioni.entrySet()){
        %>
        <% String rowColor = "";
            if(pt.getKey().isAnnullata()){
                 rowColor = "table-dark";
            } else if(pt.getKey().isPagata()) {
                 rowColor = "table-success";
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
                    <%= new SimpleDateFormat("dd/MM/yyyy").format(pt.getKey().getDataOraInizio())%>
                </td>
                <td>
                    <%= new SimpleDateFormat("HH:mm").format(pt.getKey().getDataOraInizio()) %>
                </td>
                <td>
                    <%= new SimpleDateFormat("HH:mm").format(pt.getKey().getDataOraFine()) %>
                </td>
                <td>
                    <button class="btn btn-sm btn-danger" id="pren_<%=pt.getKey().getId()%>" onclick="deletePrenotazione(this)" <%=pt.getKey().isAnnullata() ? "disabled" : ""%> >Annulla</button>
                </td>
            </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/prenotazioni.js" crossorigin="anonymous"></script>

</html>
