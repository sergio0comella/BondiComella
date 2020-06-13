<%@ page import="it.bondicomella.lido.utente.model.Utente" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 05/06/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% %>
<html>
<jsp:include page="../data/header.jsp" />
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
            List<Utente> utenti = (List<Utente>) request.getAttribute("utenti");
            for(Utente utente : utenti){
        %>
        <tr>
            <td>
                <%= utente.getEmail() %>
            </td>
            <td>
                <%= utente.getCognomeNome() %>
            </td>
            <td>
                <%= utente.getRuolo() %>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
