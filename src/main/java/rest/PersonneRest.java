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
import jpa.Entity.Personne;
import jpa.Entity.Rdv;
import jpa.Service.RdvService;
import jpa.Service.UsersService;

@Path("/user")
public class PersonneRest {

  @GET
  @Path("/list")
  @Operation(summary = "List all existing users",
    tags = {"rdv"},
    description = "Return all users",
    responses = {
      @ApiResponse(description = "The user", content = @Content(
              schema = @Schema(implementation = Personne.class)
      )),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Pet not found")
})
    public Response listRdv()  {
      String out = "";
    UsersService usersService = new UsersService();
    String[][] usersinfo = usersService.getAllUsers();
        out += ("<HTML>\n<BODY>\n" +
                "<H1>Liste des utilisateurs</H1>\n");
                out += ("<h3>Professeurs</h3>");
        for (String string : usersinfo[0]) {
                if(string != null)
                out += ("<LI>" + string + "</LI>");
        };
        out += ("<h3>Etudiants</h3>");
        for (String string : usersinfo[1]) {
                if(string != null)
                out += ("<LI>" + string + "</LI>");
        };
        out += ("</UL>\n" +                
        "</BODY></HTML>" 
        );
    return Response.ok(out).build();
  }

}