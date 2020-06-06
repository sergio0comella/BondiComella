<%@ page import="it.bondicomella.lido.biglietteria.model.Postazione" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 06/06/2020
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../data/header.jsp"/>
<body>
<div class="container mt-5">
<h1>Situazione postazioni</h1>
    <div class="row">
    <%
        List<Postazione> postazioni = (List<Postazione>) request.getAttribute("postazioni");
        for(Postazione postazione : postazioni){
    %>
            <%if(postazione.getStato().equals("L")) {%>
                <button class="btn btn-default btn-circle btn-lg m-5 "><i class="fas fa-umbrella-beach"></i></button>
            <%}else if(postazione.getStato().equals("P")) {%>
                <button class="btn btn-warning btn-circle btn-lg m-5 "><i class="fas fa-umbrella-beach"></i></button>
             <%}else if(postazione.getStato().equals("O")){ %>
                <button class="btn btn-danger btn-circle btn-lg m-5 "><i class="fas fa-umbrella-beach"></i></button>
             <%}%>

            <%if(postazioni.indexOf(postazione) % 5 == 0)%>
                <br>
            <%}%>
        <%}%>
    </div>

</div>

</body>
</html>
