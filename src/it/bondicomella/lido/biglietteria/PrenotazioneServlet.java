package it.bondicomella.lido.biglietteria;

import it.bondicomella.lido.biglietteria.controller.PrenotazioneController;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
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
import java.util.List;
import java.util.Map;

@WebServlet("/apiPrenotazioni/*")
public class PrenotazioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrenotazioneController controller = new PrenotazioneController();
            response.setCharacterEncoding("UTF-8");
            Map<Prenotazione, String> prenotazioni = controller.getListaPrenotazioni();
            request.setAttribute("prenotazioni", prenotazioni);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/prenotazioni.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.out.println("Errore in GetListaPrenotazioni");
        }
    }
}
