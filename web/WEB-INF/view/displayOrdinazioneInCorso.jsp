<%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 22/06/2020
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.bondicomella.lido.cucina.model.Ordine" %>
<jsp:include page="../../WEB-INF/data/header.jsp" />
<html>
<body>
<% Ordine o = (Ordine) request.getAttribute("ordine");%>
<div class="container mt-5 text-center">
    <h1> STIAMO SERVENDO L'UTENTE: </h1>
    <h1 style="font-size: 10em">#<%= o.getIdUtente()%></h1>
    <img src="../../resources/panino2.jpg">
</div>


</body>
</html>
