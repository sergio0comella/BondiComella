<%@ page import="it.bondicomella.lido.utente.model.Utente" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
