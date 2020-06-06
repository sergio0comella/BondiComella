<%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 02/06/2020
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="WEB-INF/data/header.jsp" />
<body>
<div class="container mt-5">
    <button class="btn-info btn btn-lg btn-block" value="Utenti" onclick="redirectPage('utenti')">
    Utenti
    </button>
</div>
</body>

<script>
    function redirectPage(pageName){
        window.location.href=pageName;
    }
</script>
</html>
