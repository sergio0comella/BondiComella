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

<h1 class="text-center mb-5">Riepilogo postazioni</h1>

    <div class="dropdown-divider"></div>
    <div class="row justify-content-center font-italic">
        <div class="col-1">
            Legenda:
        </div>
        <div class="col-2">
            Bianco = Libero
        </div>
        <div class="col-2" style="color: #d9515e;">
            Rosso = Occupato
        </div>
        <div class="col-2" style="color: #eacd78;">
            Giallo = Prenotato
        </div>
    </div>
    <div class="dropdown-divider"></div>

    <div class="row row-cols-3 mt-4 justify-content-center">
        <div class="col-3">
            <p class="nPostazione">Postazione: #<span id="postSelected"></span></p>
        </div>
        <div class="col-3">
            <button class="btn btn-block btn-info" onclick="occupaPostazione()">Occupa</button>
        </div>
        <div class="col-3">
            <button class="btn btn-block btn-info" onclick="liberaPostazione()">Libera</button>
        </div>
    </div>

    <div class="row row-cols-5 align-content-center">
    <%
        List<Postazione> postazioni = (List<Postazione>) request.getAttribute("postazioni");
        for(Postazione postazione : postazioni){
    %>
            <div class="col">
                <%switch (postazione.getStato()) {
                    case "P":%>
                    <button class="btn btn-warning btn-circle btn-lg m-5" id="post_<%=postazione.getId()%>">
                 <% break;
                     case "O":%>
                    <button class="btn btn-danger btn-circle btn-lg m-5" id="post_<%=postazione.getId()%>">
                <% break;
                     default:%>
                    <button class="btn btn-default btn-circle btn-lg m-5" id="post_<%=postazione.getId()%>" onclick="setOptionsPostazione(this)">
                <% break;
                }%>
                <i class="fas fa-umbrella-beach"></i><p class="small text-center">#<%=postazione.getId()%></p></button>
            </div>
        <%}%>

    </div>

    <div class="dropdown-menu" id="postazioniOptions">
        <a class="dropdown-item" href="#">Action</a>
        <a class="dropdown-item" href="#">Another action</a>
        <a class="dropdown-item" href="#">Something else here</a>
    </div>

</div>


</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/postazioni.js" crossorigin="anonymous"></script>

</html>
