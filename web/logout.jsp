<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
</head>
<body>
Goodbye <%= request.getRemoteUser() %>
<%request.logout(); %>
<p>You successfully logged out (<%= request.getRemoteUser() %>)</p>
<a href="home">Go to reserved area</a>
</body>
</html>