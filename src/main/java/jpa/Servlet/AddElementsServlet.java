package jpa.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.Service.*;

@WebServlet(name="addUser",
urlPatterns={"/user/add"})
public class AddElementsServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                RequestDispatcher view = request.getRequestDispatcher("/add_user_form.html");
                view.forward(request, response);
        }
public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
     throws ServletException, IOException {
        System.out.println(request.getParameter("email"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UsersService usersService = new UsersService();
        usersService.ajoutUser(request.getParameter("name"), request.getParameter("email"), request.getParameter("fonction"));
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +            
        " <LI>Nom: "
                + request.getParameter("name") + "\n" +
                " <LI>Email: "
                + request.getParameter("email") + "\n" +
                " <LI>Fonction: "
                + request.getParameter("fonction") + "\n" +
                "</UL>\n" +                
        "</BODY></HTML>");
}
}
