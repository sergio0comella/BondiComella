package it.bondicomella.lido.utente;

import it.bondicomella.lido.utente.controller.UtenteController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/utenti")
public class UtenteServlet extends HttpServlet {

    private UtenteController controller;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
             this.controller = new UtenteController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/text");
        response.setCharacterEncoding("UTF-8");
        try {
            out.print(this.controller.getListaUtenti());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        out.flush();

    }
}
