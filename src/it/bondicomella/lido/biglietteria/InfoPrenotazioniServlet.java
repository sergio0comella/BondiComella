package it.bondicomella.lido.biglietteria;

import com.google.gson.*;
import it.bondicomella.lido.biglietteria.controller.PrenotazioneController;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@WebServlet("/infoPrenotazioni")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"CLT", "BGN", "BGT", "CCN"})
)
public class InfoPrenotazioniServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String codice = request.getParameter("codice");

        try {
            String jsonResponse = "";
            if (id != null) {
                jsonResponse = getPrenotazioniGiornaliere(id);
            } else if (codice != null) {
                jsonResponse = getPrenotazioneByCodice(codice);
            }

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            System.out.println(jsonResponse);
            out.print(jsonResponse);

        } catch (Exception throwables) {
            System.out.println("Errore in get info prenotazioni");
            throwables.printStackTrace();
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String tipo = request.getParameter("tipo");
        if(tipo.equals("PAGA")){
            try {
                pagamentoPrenotazione(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(tipo.equals("CONFERMA")){
            try {
                prenotazioneConfermata(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private String getPrenotazioniGiornaliere(String id) throws SQLException {
        PrenotazioneController prController = new PrenotazioneController();
        ArrayList<Prenotazione> prenotazioni = prController.getPrenotazioniGiornaliereInPostazione(id);

        Gson gsonBuilder = new GsonBuilder().create();
        return gsonBuilder.toJson(prenotazioni);
    }

    private String getPrenotazioneByCodice(String codice) throws Exception {
        PrenotazioneController prController = new PrenotazioneController();
        Prenotazione prenotazione = prController.getPrenotazioneByCodice(codice);

        UtenteController utController = new UtenteController();
        String nomeUtente = utController.getUtenteById(prenotazione.getFkIdUtente()).getCognomeNome();

        JsonObject jo = new JsonObject();

        jo.add("prenotazione", new Gson().toJsonTree(prenotazione));
        jo.addProperty("utente", nomeUtente);
        Gson gsonBuilder = new GsonBuilder().create();

        return gsonBuilder.toJson(jo);
    }

    private void pagamentoPrenotazione(String id) throws SQLException {
        PrenotazioneController prController = new PrenotazioneController();
        prController.pagaPrenotazione(id);
    }

    private void prenotazioneConfermata(String id) throws SQLException {
        PrenotazioneController prController = new PrenotazioneController();
        prController.confermaPrenotazione(id);
    }
}
