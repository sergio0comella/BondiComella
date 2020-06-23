<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html>
<jsp:include page="../data/header.jsp" />
<body>
<div id="login">
    <h3 class="text-center text-white pt-5">Login form</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login_form" action='j_security_check' method="post">
                        <h3 class="text-center text-info">Login</h3>
                        <div class="row offset-3">
                        <%
                            String show = request.getParameter("show");
                            if (show != null && show.equals("logerror"))
                                out.println("<p>" +"Email o password errati</p>");
                        %>
                        </div>
                        <div class="form-group row">
                            <label for="j_username">Email</label>
                            <input id="j_username" type="email" name="j_username" maxlength="30" size="18" class="form-control">
                        </div>
                        <div class="form-group row">
                            <label for="j_password">Password</label>
                            <input id="j_password" type="password" name="j_password" maxlength="30" class="form-control" size="18">
                        </div>
                        <div class="align-content-center">
                            <button class='btn btn-success align-content-center' type=submit>Accedi</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--
<div class="container mt-5">

    <div class="row">





    </div>
    <div class="row">
        <form id="login_form" action='j_security_check' method="post" >
            <div class="form-group row">
            <label for="j_username">Email</label>
                <input id="j_username" type="email" name="j_username" maxlength="30" size="18" class="form-control">
            </div>
            <div class="form-group row">
            <label for="j_password">Password</label>
                <input id="j_password" type="password" name="j_password" maxlength="30" class="form-control" size="18">
            </div>
            <div class="align-content-center">
                <button class='btn btn-success align-content-center' type=submit>Accedi</button>
            </div>
        </form>
    </div>

</div>
-->
</body>
</html>
