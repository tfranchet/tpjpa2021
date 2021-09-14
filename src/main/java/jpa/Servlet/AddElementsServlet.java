package jpa.Servlet;
import java.io.IOException;
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
        UsersService usersService = new UsersService();
        usersService.ajoutUser(request.getParameter("name"), request.getParameter("email"), request.getParameter("fonction"));
        RequestDispatcher view = request.getRequestDispatcher("/user/list");
                view.forward(request, response);
}
}
