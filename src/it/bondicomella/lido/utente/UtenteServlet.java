package it.bondicomella.lido.utente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import javax.servlet.*;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/apiUtente")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = { "BGN", "BGT"})
)
public class UtenteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
             UtenteController utController = new UtenteController();

           /* List<Utente> utenti = this.controller.getListaUtenti();
            request.setAttribute("utenti", utenti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home/utenti.jsp");
            dispatcher.forward(request, response);*/

            List<Utente> utenti = utController.getListaUtenti();
            List<String> email = utenti.stream().filter(u -> u.getRuolo().equals("CLT")).map(Utente::getEmail).collect(Collectors.toList());
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(email);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            System.out.println(jsonResponse);
            out.print(jsonResponse);

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in Lista Utenti"); }
    }
}
