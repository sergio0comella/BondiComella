<%@ page import="it.bondicomella.lido.cucina.model.Menu" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../data/header.jsp" />
<body>

<div class="container mt-5">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Piatto</th>
            <th scope="col">Costo €</th>
            <th scope="col">Attivo</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Menu> menu = (List<Menu>) request.getAttribute("menu");
            for(Menu m : menu){
                int id =  m.getId();
                String checked = "";
                if (m.isAttivo()) {
                    checked = "checked";
                }
        %>
        <tr>
            <td>
                <%= m.getNomePiatto() %>
            </td>
            <td>
                <%= m.getCosto() %>
            </td>
            <td>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input piatto" id="<%=id%>" <%=checked%> >
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <button  id = "modificaMenu" class="btn btn-primary offset-2">Modifica il Menu</button>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/modificaMenu.js" crossorigin="anonymous"></script>
</html>
