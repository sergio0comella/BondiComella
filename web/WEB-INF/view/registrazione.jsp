
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../WEB-INF/data/header.jsp" />
<div id="file"></div>
<form>
    <div class="form-row">
        <div class="col-md-4 mb-3 offset-2">
            <label for="nome">Nome</label>
            <input type="text" class="form-control" id="nome" placeholder="nome" name="nome" required>
        </div>
        <div class="col-md-4 mb-3">
            <label for="cognome">Cognome</label>
            <input type="text" class="form-control" id="cognome" placeholder="cognome"  name="cognome" required>
        </div>
    </div>

    <div class="form-row">
        <div class="col-md-6 mb-3 offset-2" >
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" placeholder="example@example.com"  name="email" required>
        </div>
    </div>

    <div class="form-row">
        <div class="col-md-4 mb-3 offset-2">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="password"  name="password" required>
        </div>
        <div class="col-md-4 mb-3">
            <label for="re-password">Conferma Passowrd</label>
            <input type="password" class="form-control" id="re-password" placeholder="conferma password" required>
        </div>
    </div>


    <button  id = "bottone" class="btn btn-primary offset-2">Registrati</button>
</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/registrazione.js" crossorigin="anonymous"></script>
</html>
