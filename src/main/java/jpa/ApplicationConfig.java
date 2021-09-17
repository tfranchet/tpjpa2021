package jpa;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;
import rest.PersonneRest;
import rest.RdvRest;
import rest.SwaggerRessource;

@ApplicationPath("/")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<>();


		// SWAGGER endpoints
		resources.add(OpenApiResource.class);

        //Your own resources. 
        resources.add(RdvRest.class);
        resources.add(PersonneRest.class);
        resources.add(SwaggerRessource.class);
		return resources;
	}
}
