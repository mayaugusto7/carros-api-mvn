package br.com.livro.rest;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.Response;
import br.com.livro.domain.UploadService;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class CarrosResource {

    private final CarroService carroService;
    private final UploadService uploadService;

    @Autowired
    public CarrosResource(CarroService carroService, UploadService uploadService) {
        this.carroService = carroService;
        this.uploadService = uploadService;
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

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postFoto(final FormDataMultiPart multiPart) {
        if (multiPart != null && multiPart.getFields() != null) {
            Set<String> keys = multiPart.getFields().keySet();

            for (String key: keys) {
                // Obtem o InputStream para ler o arquivo
                FormDataBodyPart field = multiPart.getField(key);
                InputStream in = field.getValueAs(InputStream.class);

                try {
                    String fileName = field.getFormDataContentDisposition().getFileName();
                    String path = this.uploadService.upload(fileName, in);
                    System.out.println("Arquivo: " + path);
                    return Response.ok("Arquivo recebido com sucesso.");
                } catch (IOException e) {
                    e.printStackTrace();
                    return Response.error("Erro ao enviar o arquivo.");
                }
            }
        }

        return Response.ok("Requisição inválida.");
    }

    @POST
    @Path("/toBase64")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String toBase64(final FormDataMultiPart multiPart) {
        if (multiPart != null) {
            Set<String> keys = multiPart.getFields().keySet();

            for (String key : keys) {
                try {
                    FormDataBodyPart field = multiPart.getField(key);
                    InputStream in = field.getValueAs(InputStream.class);
                    byte[] bytes = IOUtils.toByteArray(in);
                    String base64 = Base64.getEncoder().encodeToString(bytes);
                    return base64;
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Erro: " + e.getMessage();
                }
            }
        }

        return "Requisição inválida.";
    }

    @POST
    @Path("/postFotoBase64")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postFotoBase64(@FormParam("fileName") String fileName, @FormParam("base64") String base64) {
        if (fileName != null && base64 != null) {
            try {
                // Decode: converte o Base64 para Array de Bytes
                byte[] bytes = Base64.getDecoder().decode(base64);
                InputStream in = new ByteArrayInputStream(bytes);

                // Faz upload e salva o arquivo em uma pasta
                String path = this.uploadService.upload(fileName, in);
                System.out.println("Arquivo: " + path);
                return Response.ok("Arquivo recebido com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("Erro ao enviar o arquivo.");
            }
        }

        return Response.error("Requisição inválida");
    }

}
