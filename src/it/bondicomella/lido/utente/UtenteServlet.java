package it.bondicomella.lido.utente;

import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/utenti")
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
            Utente ut = utenti.get(0);
            request.setAttribute("utenti", ut);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/utente.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            out.println(e);
        }
    }
}
