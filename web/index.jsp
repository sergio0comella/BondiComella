<%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 13/06/2020
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="WEB-INF/data/header.jsp" />

<body>
<div class="container mt-4">
    <button class="btn-info btn btn-lg btn-block" value="Prenotazioni" onclick="redirectPage('home')">
        Home
    </button>
</div>
</body>

<script>
    function redirectPage(pageName){
        window.location.href=pageName;
    }
</script>
</html>
