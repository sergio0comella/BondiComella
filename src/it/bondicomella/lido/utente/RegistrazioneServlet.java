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
        PrintWriter out = response.getWriter();
        String nome, cognome, email, password, ruolo;
        Boolean fromBgt;

        fromBgt = Boolean.valueOf(request.getParameter("fromBgt"));
        nome = request.getParameter("nome");
        cognome = request.getParameter("cognome");
        email = request.getParameter("email");


            try {
                UtenteController nuovoUtente = new UtenteController();
                if (nuovoUtente.checkEmail(email)) {
                    response.setStatus(400);
                    out.print("NOTVALIDEMAIL");
                    out.flush();
                } else {
                    if(fromBgt){
                        ruolo=request.getParameter("ruolo");
                        nuovoUtente.creaUtenteFromBigliettaio(nome,cognome,email,ruolo);
                    }else{
                        password = request.getParameter("password");
                        ruolo = "CLT";
                        nuovoUtente.creaUtente(nome, cognome, email, password, ruolo);
                        request.login(email, password);
                    }
                }
            } catch (Exception throwables) {
                response.setStatus(400);
                out.print("ERROR");
                out.flush();
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = " ";
        if(request.isUserInRole("BGT")){
            address= "WEB-INF/home/registrazioneBGT.jsp";
        }else {
            address = "WEB-INF/view/registrazione.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
