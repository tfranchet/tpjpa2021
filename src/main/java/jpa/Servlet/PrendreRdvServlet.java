package jpa.Servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.Service.*;

@WebServlet(name="takeRdv",
urlPatterns={"/rdv/take/"})
public class PrendreRdvServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                RdvService rdvService = new RdvService();
                rdvService.prendreRdv(request.getParameter("idRdv"), request.getParameter("idEtudiant"));
                RequestDispatcher view = request.getRequestDispatcher("/user/list");
                view.forward(request, response);
        }
public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
     throws ServletException, IOException {
             System.out.println(request.getParameter("jour"));
        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("/user/list");
                view.forward(request, response);
}
}
