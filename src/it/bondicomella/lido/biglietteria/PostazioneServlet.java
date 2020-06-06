package it.bondicomella.lido.biglietteria;

import it.bondicomella.lido.biglietteria.controller.PostazioneController;
import it.bondicomella.lido.biglietteria.model.Postazione;
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

@WebServlet("/postazioni")
public class PostazioneServlet extends HttpServlet {
    private PostazioneController controller;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.controller = new PostazioneController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        try {
            List<Postazione> postazioni = this.controller.getSchemaPostazioni();
            request.setAttribute("postazioni", postazioni);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/postazioni.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            out.println(e);
        }
    }
}
