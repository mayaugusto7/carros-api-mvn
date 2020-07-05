package br.com.livro.servlets;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.Response;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.RegexUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/carros/*")
public class CarroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CarroService carroService = new CarroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();
        Long id = RegexUtil.matchId(requestUri);

        if (id != null) {
            Carro carro = carroService.getCarro(id);

            if (carro != null) {
                String json = JAXBUtil.toJSONUsingGson(carro);
                ServletUtil.writeJSON(resp, json);
            } else {
                resp.sendError(404, "Carro não encontrado");
            }
        } else {
            List<Carro> carros = carroService.getCarros();
            String json = JAXBUtil.toJSONUsingGson(carros);
            ServletUtil.writeJSON(resp, json);
        }

        //List<Carro> carros = carroService.getCarros();
        //ListaCarros lista = new ListaCarros();
        //lista.setCarros(carros);

        // converte lista de carros em xml
        //String xml = JAXBUtil.toXML(lista);

        // envia resposta para o browser
        //ServletUtil.writeXML(resp, xml);

        // converte lista de carros em json usando jettison
        //String json = JAXBUtil.toJSON(lista);

        // envia resposta para o browser
        //ServletUtil.writeJSON(resp, json);

        // converte lista de carros em json usando gson do google
        //String json = JAXBUtil.toJSONUsingGson(lista);

        // envia resposta para o browser
        //ServletUtil.writeJSON(resp, json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Carro carro = getCarroFromRequest(req);

        carroService.save(carro);

        String json = JAXBUtil.toJSONUsingGson(carro);
        ServletUtil.writeJSON(resp, json);
    }

    private Carro getCarroFromRequest(HttpServletRequest req) {

        Carro c = new Carro();
        String id = req.getParameter("id");

        if (id != null) {
            c = carroService.getCarro(Long.parseLong(id));
        }

        c.setNome(req.getParameter("nome"));
        c.setDesc(req.getParameter("descricao"));
        c.setUrlFoto(req.getParameter("url_foto"));
        c.setUrlVideo(req.getParameter("url_video"));
        c.setLatitude(req.getParameter("latitude"));
        c.setLongitude(req.getParameter("longitude"));
        c.setTipo(req.getParameter("tipo"));

        return c;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        Long id = RegexUtil.matchId(requestUri);

        if (id != null) {
            carroService.delete(id);
            Response r = Response.ok("Carro excluído com sucesso");
            String json = JAXBUtil.toJSONUsingGson(r);
            ServletUtil.writeJSON(resp, json);
        } else {
            resp.sendError(404, "URL inválida");
        }
    }
}
