package jpa.Servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.Service.*;

@WebServlet(name="rmUser",
urlPatterns={"/user/rm/"})
public class RemoveUserServlet extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println(request.getParameter("id"));
        UsersService usersService = new UsersService();
        usersService.removeUser(request.getParameter("id"));
        RequestDispatcher view = request.getRequestDispatcher("/user/list");
                view.forward(request, response);
}
}
