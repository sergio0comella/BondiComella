package it.bondicomella.lido;

import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeAuth
 */
@WebServlet("/home")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"CLT", "BGN"}))
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home/homeDispatcher.jsp");

        try {

            UtenteController utController = new UtenteController();
            Utente ut = utController.getUtenteByEmail(request.getRemoteUser());
            request.setAttribute("utente", ut);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in GetPostazione");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
