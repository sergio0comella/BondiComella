<%@ page import="it.bondicomella.lido.biglietteria.model.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="it.bondicomella.lido.utente.controller.UtenteController" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 09/06/2020
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../data/header.jsp" />
</head>
<body>
<div class="container mt-5">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Utente</th>
            <th scope="col">Inizio Prenotazione</th>
            <th scope="col">Fine Prenotazione</th>
        </tr>
        </thead>
        <tbody>
        <%
            Map<Prenotazione, String> prenotazioni = (Map<Prenotazione, String>) request.getAttribute("prenotazioni");
            for(Map.Entry<Prenotazione, String> pt : prenotazioni.entrySet()){
        %>
        <tr>
            <td>
                <%= pt.getValue() %>
            </td>
            <td>
                <%= pt.getKey().getDataOraInizio().getTime()%>
            </td>
            <td>
                <%= pt.getKey().getDataOraFine() %>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
