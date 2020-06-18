package it.bondicomella.lido.biglietteria;

import it.bondicomella.lido.biglietteria.controller.PrenotazioneController;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import javax.servlet.RequestDispatcher;
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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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
     * data: {
     * dataPrenotazione: dataPrenotazione,
     * oraInizio: oraInizioPrenotazione,
     * oraFine: oraFinePrenotazione,
     * postazione: postazione,
     * isPagato: isPagato,
     * emailCliente: emailCliente
     * },
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrenotazioneController prController = new PrenotazioneController();
            UtenteController utController = new UtenteController();

            String data = request.getParameter("dataPrenotazione").replace('/', '-');
            Date dataPrenotazione = this.convertStringToSqlDate(data);
            Time oraInizio = this.convertStringToTime(request.getParameter("oraInizio"));
            Time oraFine = this.convertStringToTime(request.getParameter("oraFine"));

            int postazione = Integer.parseInt(request.getParameter("postazione"));
            boolean isPagato = Boolean.parseBoolean(request.getParameter("isPagato"));
            String emailCliente = request.getParameter("emailCliente");

            if (emailCliente == null) {
                emailCliente = request.getRemoteUser();
            }

            Utente ut = utController.getUtenteByEmail(emailCliente);

            Prenotazione prTemp = new Prenotazione(ut.getId(), postazione, isPagato, dataPrenotazione, oraInizio, oraFine, false);
            prController.addNewPrenotazione(prTemp, emailCliente);

            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.print("SUCCESS");
            out.flush();

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
