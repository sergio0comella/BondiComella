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

    <div class="row mt-5 justify-content-between">
        <div class="col">
            <img src="resources/spiaggia_home.jpg" style="width: 30rem; height: 40rem" alt="...">
        </div>


        <div class="col align-self-center">
            <div class="card">
                <div class="card-title text-center">
                    <h1>Menu</h1>
                </div>
                <div class="card-body">
                    <button class="btn btn-block btn-success" onclick="redirectPage('listaPostazioni')">Situazione Postazioni</button>
                    <button class="btn btn-block btn-danger" onclick="redirectPage('menu')">Visualizza Menù del giorno </button>
                    <button class="btn btn-block btn-danger" onclick="redirectPage('/ordinazione')">Stato ordinazione Bar </button>
                    <%if(request.getRemoteUser() == null){%>
                    <button class="btn btn-block btn-info mt-5" onclick="redirectPage('home')">Login</button>
                    <button class="btn btn-block btn-info" onclick="redirectPage('/apiRegistrazione')">Registrati</button>
                    <%}else{%>
                        <button class="btn btn-block btn-info mt-5" onclick="redirectPage('home')">Home</button>
                    <%}%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    function redirectPage(pageName){
        window.location.href=pageName;
    }
</script>
</html>
