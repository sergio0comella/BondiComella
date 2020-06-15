<%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 14/06/2020
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../data/header.jsp" />
<body>

<div class="container mt-4">

<h1>Nuova Prenotazione</h1>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        Inserisci nuova prenotazione
    </button>
    <jsp:include page="nuovaPrenotazione.jsp" />
<hr>
<h1>Ordina al Bar</h1>
<form>

</form>

</div>
</body>
<script>
    function newPrenotazione() {
        console.log("qui")
        $('#modalNuovaPrenotazione').on('shown.bs.modal', function () {
            $('#exampleInputEmail1').trigger('focus')
        })
    }
</script>
</html>
