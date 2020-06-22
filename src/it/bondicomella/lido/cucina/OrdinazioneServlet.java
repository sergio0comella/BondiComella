package it.bondicomella.lido.cucina;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import it.bondicomella.lido.cucina.controller.OrdinazioneController;
import it.bondicomella.lido.cucina.model.Menu;
import it.bondicomella.lido.cucina.model.Ordine;
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
import java.util.Set;

@WebServlet("/OrdinazioneServlet")
public class OrdinazioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        try {
            Utente utenteInSessione = (Utente) request.getSession().getAttribute("utente");
            int id = utenteInSessione.getId();
            OrdinazioneController controller = new OrdinazioneController();
            controller.effettuaOrdinazione(data, id);
            PrintWriter out = response.getWriter();
            JsonObject obj = new JsonObject();
            obj.addProperty("message", "Ordinazione inserita con successo, il tuo codice è:" + "#" + id);
            out.print(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            OrdinazioneController controller = new OrdinazioneController();
            Ordine ordine = controller.getTheFirstOrdine();
            request.setAttribute("ordine",ordine);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/displayOrdinazioneInCorso.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            OrdinazioneController controller = new OrdinazioneController();
            controller.completaOrdinazione(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
