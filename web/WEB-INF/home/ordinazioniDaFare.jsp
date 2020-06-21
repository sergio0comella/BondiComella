<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.bondicomella.lido.cucina.model.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="it.bondicomella.lido.utente.controller.UtenteController" %>
<%@ page import="it.bondicomella.lido.cucina.controller.MenuController" %>

<div class="container mt-5">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Utente</th>
            <th scope="col">Piatto</th>
            <th scope="col">Quantità</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordinazioni");
            UtenteController utenteController = new UtenteController();
            MenuController menuController = new MenuController();
            for (Ordine o : ordini) {
                int id = o.getId();
        %>
        <tr>
            <td>
                <%= utenteController.getUtenteById(o.getIdUtente()).getCognomeNome() %>
            </td>
            <td>
                 <%= menuController.getMenuById(o.getIdMenu()).getNomePiatto() %>
            </td>
            <td>
               <%= o.getQuantita()%>
            </td>
            <td>
                <div class="form-check">
                    <button type="button" class="btn btn-primary" id="<%=id%>" onclick="completaOrdinazione(<%=id%>)">Completato</button>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/effettuaOrdineBar.js" crossorigin="anonymous"></script>


