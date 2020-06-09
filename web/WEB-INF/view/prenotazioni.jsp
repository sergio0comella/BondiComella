<%@ page import="it.bondicomella.lido.biglietteria.model.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="it.bondicomella.lido.utente.controller.UtenteController" %><%--
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
            <th scope="col">Email</th>
            <th scope="col">Utente</th>
            <th scope="col">Ruolo</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Prenotazione> prenotazioni = (List<Prenotazione>) request.getAttribute("prenotazioni");
            for(Prenotazione pt : prenotazioni){
        %>
        <tr>
            <td>
                <%= pt.getDataOraInizio() %>
            </td>
            <td>
                <%= pt.getDataOraFine() %>
            </td>
            <td>
                <%= pt.getId() %>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
