package it.bondicomella.lido.utente;

import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/apiUtente")
public class UtenteServlet extends HttpServlet {

    private UtenteController controller;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
             this.controller = new UtenteController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        try {
            List<Utente> utenti = this.controller.getListaUtenti();
            request.setAttribute("utenti", utenti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/utenti.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in Lista Utenti");        }
    }
}
