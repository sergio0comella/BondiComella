package it.bondicomella.lido.biglietteria;

import it.bondicomella.lido.biglietteria.controller.PostazioneController;
import it.bondicomella.lido.biglietteria.model.Postazione;
import it.bondicomella.lido.utente.controller.UtenteController;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/BondiComella/apiPostazioni")
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
        response.setCharacterEncoding("UTF-8");
        try {
            List<Postazione> postazioni = this.controller.getSchemaPostazioni();
            request.setAttribute("postazioni", postazioni);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/postazioni.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in GetPostazione");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPostazione = request.getParameter("id");
        String statoPostazione = request.getParameter("stato");
        Postazione postazione = null;

        try {
            this.controller = new PostazioneController();
            postazione = this.controller.getPostazioneById(idPostazione);

            switch (statoPostazione) {
                case "O":
                    this.controller.occupaPostazione(postazione);
                    break;
                case "L":
                    this.controller.liberaPostazione(postazione);
                    break;
                default:
                    System.out.println("Case Default");
            }
        }catch (SQLException throwables) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
            dispatcher.forward(request, response);
            System.out.println("Errore in Modifica postazione");
        }
    }

}
