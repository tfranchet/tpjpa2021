package jpa.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.Service.*;

@WebServlet(name="listUser",
urlPatterns={"/user/list"})
public class GetUserListServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        System.out.println(request.getParameter("email"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UsersService usersService = new UsersService();
        String[][] usersinfo = usersService.getAllUsers();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Liste des utilisateurs</H1>\n");
                out.println("<h3>Professeurs</h3>");
        for (String string : usersinfo[0]) {
                if(string != null)
                out.println("<LI>" + string + "</LI>");
        };
        out.println("<h3>Etudiants</h3>");
        for (String string : usersinfo[1]) {
                if(string != null)
                out.println("<LI>" + string + "</LI>");
        };
        out.println("</UL>\n" +                
        "</BODY></HTML>");
}

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                doGet(request, response);
        }
}
