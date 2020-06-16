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

@WebServlet("/apiRegistrazione")
public class RegistrazioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


      /*  String nome, cognome, email, password;

        System.out.print("sono qui");

        nome = request.getParameter("nome");
        cognome = request.getParameter("cognome");
        email = request.getParameter("email");
        password = request.getParameter("password");
        System.out.println(nome + cognome + email + password);
        try {
            UtenteController nuovoUtente = new UtenteController();
            nuovoUtente.creaUtente(nome,cognome,email,password);
        } catch (Exception e) {
            e.printStackTrace();
        } */

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("I miss you ha ha!");
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/registrazione.jsp");
        dispatcher.forward(request, response);
    }
}
