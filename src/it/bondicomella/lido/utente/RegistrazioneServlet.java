package it.bondicomella.lido.utente;

import it.bondicomella.lido.utente.controller.UtenteController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/apiRegistrazione")
public class RegistrazioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String nome, cognome, email, password,ruolo;

        nome = request.getParameter("nome");
        cognome = request.getParameter("cognome");
        email = request.getParameter("email");
        password = request.getParameter("password");
        ruolo = "CLT";
        try {
            UtenteController nuovoUtente = new UtenteController();
            if(nuovoUtente.checkEmail(email)){
                 response.setStatus(400);
                 out.print("NOTVALIDEMAIL");
                 out.flush();
            }
            else {
                 nuovoUtente.creaUtente(nome, cognome, email, password, ruolo);
                 request.login(email,password);
            }
        } catch (Exception throwables) {
            response.setStatus(400);
            out.print("ERROR");
            out.flush();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/registrazione.jsp");
        dispatcher.forward(request, response);
    }
}
