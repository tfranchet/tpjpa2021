package jpa.Servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.Service.*;

@WebServlet(name="createRdv",
urlPatterns={"/rdv/create/"})
public class AddRdvServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                RequestDispatcher view = request.getRequestDispatcher("/add_rdv_form.html");
                view.forward(request, response);
        }
public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
     throws ServletException, IOException {
             System.out.println(request.getParameter("jour"));
        response.setContentType("text/html");
        RdvService rdvService = new RdvService();
        rdvService.createPossibleRdv(request.getParameter("jour"), request.getParameter("heureDebut"), request.getParameter("heureFin"), request.getParameter("prof"));
        RequestDispatcher view = request.getRequestDispatcher("/user/list");
                view.forward(request, response);
}
}
