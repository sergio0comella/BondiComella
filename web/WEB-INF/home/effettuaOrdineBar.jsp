<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.bondicomella.lido.cucina.model.Menu" %>
<%@ page import="java.util.List" %>
<html>
<jsp:include page="../data/header.jsp" />
<div class="container mt-5">
    <h1>Effettua un ordine al bar</h1>
    <h5>*Selezionare la </h5>
    <div class="table-responsive text-center">
    <table class="table justify-content-center">
        <thead class="thead-light">
        <tr>
            <th scope="col">Piatto</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Quantità</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Menu> menu = (List<Menu>) request.getAttribute("ordineDalMenu");
            for( Menu m: menu){
                String idQuantita= "quantita_"+m.getId();
                String idRow= "row_"+m.getId();
        %>
        <tr id="<%=idRow%>">
            <td>
                <%= m.getNomePiatto() %>
            </td>
            <td>
                 € <%= m.getCosto() %>
            </td>
            <td>
                <div class="col-3">
                <input  type="number"  min="0" max="10" class="form-control quantita" id="<%=idQuantita%>" required>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    </div>
    <button  id = "effettuaOrdine" class="btn btn-primary offset-2">Effettua Ordine</button>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/effettuaOrdineBar.js"
        crossorigin="anonymous"></script>
</html>
