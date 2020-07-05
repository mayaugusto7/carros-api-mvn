package br.com.livro.rest;

import br.com.livro.domain.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String helloHTML() {
        return "<b>Olá mundo HTML!</b>";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloTextPlain() {
        return "Olá mundo texto";
    }

    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Response helloXML() {
        return Response.ok("Olá mundo XML!");
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloJSON() {
        return Response.ok("Olá mundo JSON!");
    }

    @GET
    public String get() {
        return "HTTP GET";
    }

    @POST
    public String post() {
        return "HTTP POST";
    }

    @PUT
    public String put() {
        return "HTTP PUT";
    }

    @DELETE
    public String delete() {
        return "HTTP DELETE";
    }
}
