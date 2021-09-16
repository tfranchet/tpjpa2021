package rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jpa.Entity.Rdv;
import jpa.Service.RdvService;

@Path("/rdv")
public class RdvRest {

  @GET
  @Path("/list")
  public Response listRdv()  {
      String out = "";
    RdvService rdvService = new RdvService();
    List<Rdv> rdvinfo = rdvService.getAllRdvs();
    out += ("<HTML>\n<BODY>\n" +
            "<H1>Liste des rdv</H1>\n");
            out += ("<h3>rdvs</h3>");
    for (Rdv rdv : rdvinfo) {
            if(rdv.getEtudiant() == null)
            out += ("<LI>" + rdv.toString() + "<a href=\"http://localhost:8080/rdv/take/?idRdv=" + rdv.getId() + "\" id=\"redirect\"> Prendre rdv </a>" + "</LI>");
            else
            out += ("<LI>" + rdv.toString() + "</LI>");      
    };
    out += ("</UL>\n" +                
    "</BODY></HTML>" +
    "<script>" + 
    "const params = new URLSearchParams(document.location.search);" + 
    "const s = params.get(\"id\");" + 
    "var myf = document.getElementById(\"redirect\");" +
    "myf.href += \"&idEtudiant=\" + s;" +
    "</script>"
    );
    return Response.ok(rdvinfo.get(1)).build();
  }

}