<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="WEB-INF/data/header.jsp" />
<body>
<div class="container mt-4">
   <h1>Arrivederci,</h1>
    <%request.logout(); %>
    <p>Speriamo di rivederti presto, <a href="index.jsp">vai alla pagina inziale</a></p>
    <div class="row">
        <img src="../resources/arrivederci-estate-84c8283c-e3e0-43cb-a3a4-2607aa46bdc3.jpg">
    </div>
</div>
</body>
</html>