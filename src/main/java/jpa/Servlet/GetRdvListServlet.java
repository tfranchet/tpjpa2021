package jpa.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.Entity.Rdv;
import jpa.Service.*;

@WebServlet(name="listServlet",
urlPatterns={"/rdv/list/"})
public class GetRdvListServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RdvService rdvService = new RdvService();
        List<Rdv> rdvinfo = rdvService.getAllRdvs();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Liste des rdv</H1>\n");
                out.println("<h3>rdvs</h3>");
        for (Rdv rdv : rdvinfo) {
                if(rdv.getEtudiant() == null)
                out.println("<LI>" + rdv.toString() + "<a href=\"http://localhost:8080/rdv/take/?idRdv=" + rdv.getId() + "\" id=\"redirect\"> Prendre rdv </a>" + "</LI>");
                else
                out.println("<LI>" + rdv.toString() + "</LI>");      
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
