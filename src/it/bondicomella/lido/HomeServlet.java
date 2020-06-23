package it.bondicomella.lido;

import it.bondicomella.lido.biglietteria.controller.PrenotazioneController;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
import it.bondicomella.lido.cucina.controller.OrdinazioneController;
import it.bondicomella.lido.cucina.model.Ordine;
import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;

/**
 * Servlet implementation class HomeAuth
 */
@WebServlet("/homeAuth")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"CLT", "BGN","BGT","CCN"}))
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

        /** Se ho un utente loggato allora ne creo il bean**/
        if(request.getRemoteUser() != null){
            try {

                UtenteController utController = new UtenteController();
                Utente ut = utController.getUtenteByEmail(request.getRemoteUser());

                HttpSession session = request.getSession();
                session.setAttribute("utente", ut);
                request.setAttribute("utente", ut);
                session.setMaxInactiveInterval(30 * 60);


                /** Una volta autenticato faccio il redirects secondo il ruolo **/
                if (request.isUserInRole("CLT"))
                    request.getRequestDispatcher("WEB-INF/home/homeUtente.jsp").forward(request, response);
                else if (request.isUserInRole("BGN"))
                    request.getRequestDispatcher("WEB-INF/home/homeBagnino.jsp").forward(request, response);
                else if (request.isUserInRole("CCN")) {
                    try {
                        OrdinazioneController  controller = new OrdinazioneController();
                        List<Ordine> ordinazioni = controller.getListOrdini();
                        request.setAttribute("ordinazioni", ordinazioni);
                        request.getRequestDispatcher("WEB-INF/home/homeCucina.jsp").forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if (request.isUserInRole("BGT"))
                    request.getRequestDispatcher("WEB-INF/home/homeBigliettaio.jsp").forward(request, response);
                else
                    request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (Exception e) {
                request.getRequestDispatcher("WEB-INF/view/errore.jsp").forward(request, response);
                System.out.println("Errore in getHomeAuth");
            }
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
