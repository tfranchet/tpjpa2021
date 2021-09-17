package rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import jpa.Entity.Rdv;
import jpa.Service.RdvService;

@Path("/rdv")
public class RdvRest {

  @GET
  @Path("/list")
  @Operation(summary = "List all existing RDV",
    tags = {"rdv"},
    description = "Return all rdvs",
    responses = {
      @ApiResponse(description = "The pet", content = @Content(
              schema = @Schema(implementation = Rdv.class)
      )),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Pet not found")
})
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
    return Response.ok(out).build();
  }

  @GET
  @Path("/create")
  @Operation(summary = "Create a RDV",
    tags = {"rdv"},
    description = "create a rdvs",
    responses = {
      @ApiResponse(description = "The rdv", content = @Content(
              schema = @Schema(implementation = Rdv.class)
      )),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Pet not found")
})
  public Response createRdv(HttpServletRequest request){
    RequestDispatcher view = request.getRequestDispatcher("/add_rdv_form.html");
    return Response.ok(view.toString()).build();
  }
}