package it.bondicomella.lido.utente;

import it.bondicomella.lido.utente.controller.UtenteController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UtenteServlet")
public class UtenteServlet extends HttpServlet {
    private it.bondicomella.lido.utente.controller.UtenteController UtenteController;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteController = new UtenteController();


    }
}
