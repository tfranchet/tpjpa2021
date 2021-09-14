package jpa.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.Service.*;

@WebServlet(name="listServlet",
urlPatterns={"/rdv/list/"})
public class GetRdvListServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RdvService rdvService = new RdvService();
        String[] rdvinfo = rdvService.getAllRdvs();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Liste des rdv</H1>\n");
                out.println("<h3>rdvs</h3>");
        for (String string : rdvinfo) {
                if(string != null)
                out.println("<LI>" + string + "</LI>");
        };
        out.println("</UL>\n" +                
        "</BODY></HTML>" +
        "<script>" + 
        "const params = new URLSearchParams(document.location.search);" + 
        "const s = params.get(\"id\");" + 
        "var myf = document.getElementById(\"redirect\");" +
        "myf.href += \"&idEtudiant=\" + s;" +
        "</script>"
        );
}

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                doGet(request, response);
        }
}
