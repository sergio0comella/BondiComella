package it.bondicomella.lido.cucina;

import it.bondicomella.lido.cucina.controller.MenuController;
import it.bondicomella.lido.cucina.model.Menu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    private MenuController controller;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idsActive = request.getParameter("ItemsActive");
        String idsNotActive = request.getParameter("ItemsNotActive");
        idsActive = idsActive.replaceAll("[\"\" \"\\p{Z}\\s]+", "");
        idsNotActive = idsNotActive.replaceAll("[\"\" \"\\p{Z}\\s]+", "");
        String[] idsConvertActive = idsActive.substring(1, idsActive.length() - 1).split(",");
        String[] idsConvertNotActive = idsNotActive.substring(1, idsNotActive.length() - 1).split(",");
        try {
            controller.modificaMenu(idsConvertActive, idsConvertNotActive);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.controller = new MenuController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (request.isUserInRole("CCN")) {
            try {
                List<Menu> menu = this.controller.getMenu();
                request.setAttribute("menu", menu);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home/modificaMenu.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
                dispatcher.forward(request, response);
                System.out.println("Errore nella modifica del Menu");
            }
        } else {
            try {
                List<Menu> menuDelGiorno = this.controller.getMenuDelGiorno();;
                request.setAttribute("menuDelGiorno", menuDelGiorno);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/visualizzaMenu.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/errore.jsp");
                dispatcher.forward(request, response);
                System.out.println("Errore nel caricamento del Menu");
            }
        }

    }
}
