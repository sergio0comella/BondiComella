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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../style/login.css">
<body>
<div class="container">
    <br><br>
    <div class="row">
        <div class="offset-4">
            <h1>Benvenuto su LidoB&C</h1>
        </div>
    </div>
</div>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Login Form -->
        <form action="/login"  method="post">
            <br>
            <h3>Effettua il Login</h3>
            <input type="text" id="email" class="fadeIn second" name="email" placeholder="email" >
            <input type="text" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" id="LoginButton" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Password -->
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
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
