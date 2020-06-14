<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html>
<jsp:include page="../data/header.jsp" />

<body>
<div class="container mt-5">

    <div class="row">
        <%
            String show = request.getParameter("show");
            if (show != null && show.equals("logerror"))
                out.println("<p>Wrong username or password</p>");
        %>
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

</body>
</html>
