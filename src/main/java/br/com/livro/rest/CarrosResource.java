package br.com.livro.rest;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class CarrosResource {

    private final CarroService carroService;

    @Autowired
    public CarrosResource(CarroService carroService) {
        this.carroService = carroService;
    }

    @GET
    public List<Carro> getCarros() {
        List<Carro> carros = carroService.getCarros();
        return carros;
    }

    @GET
    @Path("{id}")
    public Carro get(@PathParam("id") Long id) {
        Carro carro = carroService.getCarro(id);
        return carro;
    }

    @GET
    @Path("/tipo/{tipo}")
    public List<Carro> getByTipo(@PathParam("tipo") String tipo) {
        List<Carro> carros = carroService.findByTipo(tipo);
        return carros;
    }

    @GET
    @Path("/nome/{nome}")
    public List<Carro> getByNome(@PathParam("nome") String nome) {
        List<Carro> carros = carroService.findByName(nome);
        return carros;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        carroService.delete(id);
        return Response.ok("Carro deletado com sucesso");
    }

    @POST
    public Response post(Carro carro) {
        carroService.save(carro);
        return Response.ok("Carro salvo com sucesso");
    }

    @PUT
    public Response put(Carro carro) {
        carroService.save(carro);
        return Response.ok("Carro atualizado com sucesso");
    }
}
