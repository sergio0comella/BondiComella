<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.sql.*" %>
<% String connectionURL = "jdbc:mysql://localhost:3306/App?useLegacyDatetimeCode=false&serverTimezone=Europe/Rome";
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    try {
        connection = DriverManager.getConnection(connectionURL,"root","");
        statement= connection.createStatement();
        rs=statement.executeQuery("SELECT * FROM student");
        while(rs.next()) {
            out.print(rs.getString("ID") + ") ");
            out.println(rs.getString("Nome") + ", ");
            out.println(rs.getString("Cognome") + "<br>");
        }
        rs.close();
        statement.close();
        connection.close();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
%>
</body>
</html>