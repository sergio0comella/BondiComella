<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../WEB-INF/data/header.jsp" />
<body>
<div class="container mt-4">
    
<div class="alert alert-danger" role="alert" id = "Error" style="display: none" >
    <p id = "message" style="text-align:center"></p>
</div>
<form method="post">
    <div class="form-row">
        <div class="col-md-6 mb-3">
            <label for="nome">Nome</label>
            <input type="text" class="form-control" id="nome" placeholder="Mario" name="nome" required>
        </div>
        <div class="col-md-6 mb-3">
            <label for="cognome">Cognome</label>
            <input type="text" class="form-control" id="cognome" placeholder="Rossi"  name="cognome" required>
        </div>
    </div>

    <div class="form-row">
        <div class="col-md-6 mb-3">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" placeholder="example@example.com"  name="email" required>
        </div>
        <div class="col-md-3 align-self-center">
            <div class="form-group">
                <label for="roleOption">Scegli il ruolo</label>
                <select class="form-control" id="roleOption">
                    <option value="CLT">Cliente</option>
                    <option value="BGN">Bagnino</option>
                    <option value="CCN">Cuoco</option>
                </select>
            </div>
        </div>
    </div>
</form>
<button  id = "registraUtente" class="btn btn-primary  ">Registra Utente</button>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/registrazione.js" crossorigin="anonymous"></script>
</html>