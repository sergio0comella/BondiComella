package it.bondicomella.lido.cucina;
import it.bondicomella.lido.cucina.controller.MenuController;
import it.bondicomella.lido.cucina.model.Menu;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AggiungiPiattoServlet")
public class AggiungiPiattoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomePiatto = request.getParameter("nomePiatto");
        String costo = request.getParameter("costo");
        PrintWriter out = response.getWriter();
        try {
            MenuController controller = new MenuController();
            if(!controller.controlloPiattoDuplicato(nomePiatto)) {
                controller.aggiungiPiatto(nomePiatto, costo);
            }else{
                response.setStatus(400);
                out.print("NOTVALIDNOME");
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
