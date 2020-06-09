package it.bondicomella.lido;

import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ApiLogin/*")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean checkLogin = false;
        UtenteController controller = null;
        try {
            controller = new UtenteController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utente utente = new Utente();
        response.setContentType("text/html");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + password);
        try {
            checkLogin = controller.doLogin(email,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(checkLogin) System.out.println("giusto");
        else System.out.println("sbagliato");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
