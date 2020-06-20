package it.bondicomella.lido.biglietteria;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.bondicomella.lido.biglietteria.controller.PrenotazioneController;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;
import it.bondicomella.lido.util.Mailer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet("/apiPrenotazioni")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"BGN", "BGT"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"CLT", "BGN", "BGT"}),
        }
)
public class PrenotazioneServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
            PrenotazioneController prController = new PrenotazioneController();
            UtenteController utController = new UtenteController();

            String datas = data.get("dataPrenotazione").getAsString().replace('/', '-');
            Date dataPrenotazione = this.convertStringToSqlDate(datas);
            Time oraInizio = this.convertStringToTime(data.get("oraInizio").getAsString());
            Time oraFine = this.convertStringToTime(data.get("oraFine").getAsString());

            int postazione = data.get("postazione").getAsInt();
            boolean isPagato = data.get("isPagato").getAsBoolean();
            String emailCliente = data.get("emailUtente").getAsString();

            if (emailCliente == null || emailCliente.equals("")) {
                emailCliente = request.getRemoteUser();
            }

            Utente ut = utController.getUtenteByEmail(emailCliente);

            Prenotazione prTemp = new Prenotazione(ut.getId(), postazione, isPagato, dataPrenotazione, oraInizio, oraFine, false);
            Prenotazione prFinal = prController.addNewPrenotazione(prTemp);

            PrintWriter out = response.getWriter();
            JsonObject obj = new JsonObject();
            obj.addProperty("message", "Prenotazione inserita con successo");
            out.print(obj.toString());

            Mailer mailer = new Mailer();
            mailer.sendMailNewPrenotazione(prFinal, emailCliente);

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in PostPrenotazione");
        }

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

    private Date convertStringToSqlDate(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String tempDate = LocalDate.parse(data, formatter).format(formatter2);
        return Date.valueOf(tempDate);
    }

    private Time convertStringToTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long ms = sdf.parse(time).getTime();
        return new Time(ms); //TODO
    }
}
