package it.bondicomella.lido.biglietteria;

import it.bondicomella.lido.biglietteria.controller.PrenotazioneController;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
import it.bondicomella.lido.utente.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/apiPrenotazioni")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"CCN", "BGN", "BGL"}))
public class PrenotazioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrenotazioneController controller = new PrenotazioneController();
            response.setCharacterEncoding("UTF-8");
            Map<Prenotazione, Utente> prenotazioni = controller.getListaPrenotazioni();
            request.setAttribute("prenotazioni", prenotazioni);
            RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/home/prenotazioni.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in GetListaPrenotazioni");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrenotazioneController controller = new PrenotazioneController();
            String idPrenotazione = request.getParameter("idPrenotazione");
            controller.annullaPrenotazione(idPrenotazione);

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in AnnullaPrenotazione");
        }
    }
}
