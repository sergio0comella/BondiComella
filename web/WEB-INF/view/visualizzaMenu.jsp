<%@ page import="it.bondicomella.lido.cucina.model.Menu" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../WEB-INF/data/header.jsp" />
<body>
<div class="container mt-5">
    <h1>Menu del giorno</h1>
    <table class="table mt-5">
        <thead class="thead-light">
        <tr>
            <th scope="col">Piatto</th>
            <th scope="col">Prezzo</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Menu> menu = (List<Menu>) request.getAttribute("menuDelGiorno");
            for(Menu m : menu){
        %>
        <tr>
            <td>
                <%= m.getNomePiatto() %>
            </td>
            <td>
                 € <%= m.getCosto() %>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
