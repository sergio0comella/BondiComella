package it.bondicomella.lido.biglietteria;

import com.google.gson.*;
import it.bondicomella.lido.biglietteria.controller.PrenotazioneController;
import it.bondicomella.lido.biglietteria.model.Prenotazione;

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
        @HttpConstraint(rolesAllowed = {"CLT", "BGN","BGT","CCN"})
)
public class InfoPrenotazioniServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            PrenotazioneController prController = new PrenotazioneController();
            ArrayList<Prenotazione> prenotazioni = prController.getPrenotazioniGiornaliereInPostazione(id);

            Gson gsonBuilder = new GsonBuilder().create();
            String jsonFromJavaArrayList = gsonBuilder.toJson(prenotazioni);

            PrintWriter out = response.getWriter();
            out.print(jsonFromJavaArrayList);

        } catch (SQLException throwables) {
            System.out.println("Errore in get info prenotazioni");
            throwables.printStackTrace();
        }
    }
}
