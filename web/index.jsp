<%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 02/06/2020
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Hello!
    <%
        String pageName = "utenti";
    %>
    <input type="button" value="Click" onclick="redirectPage('<%=pageName%>')" />
</body>

<script>
    function redirectPage(pageName){
        window.location.href=pageName;
    }
</script>
</html>
