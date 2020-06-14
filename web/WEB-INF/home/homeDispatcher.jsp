<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%
        if(request.isUserInRole("CLT"))
            request.getRequestDispatcher("homeUtente.jsp").forward(request, response);
        else if (request.isUserInRole("BGN"))
            request.getRequestDispatcher("homedue.jsp").forward(request, response);
        else if (request.isUserInRole("CCN"))
            request.getRequestDispatcher("prenotazioni.jsp").forward(request, response);
        else
            request.getRequestDispatcher("index.jsp").forward(request, response);
    %>

