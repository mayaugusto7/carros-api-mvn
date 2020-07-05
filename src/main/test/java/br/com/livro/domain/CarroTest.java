package br.com.livro.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CarroTest {

    private CarroService carroService = new CarroService();

    @Test
    public void listarCarros() {
        List<Carro> carros = carroService.getCarros();
        assertNotNull(carros);
        assertTrue(carros.size() > 0);

        Carro tucker = carroService.findByName("Tucker 1948").get(0);
        assertEquals("Tucker 1948", tucker.getNome());

        Carro ferrari = carroService.findByName("Ferrari FF").get(0);
        assertEquals("Ferrari FF", ferrari.getNome());

        Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
        assertEquals("Bugatti Veyron", bugatti.getNome());
    }

    @Test
    public void salvarCarro() {
        Carro c = new Carro();
        c.setNome("Palio");
        c.setDesc("Palio Fire 1.0");
        c.setUrlFoto("http url foto");
        c.setUrlVideo("http url video");
        c.setLatitude("lat");
        c.setLongitude("lng");
        c.setTipo("popular");
        carroService.save(c);

        Long id = c.getId();
        assertNotNull(id);

        c = carroService.getCarro(id);
        assertEquals("Palio", c.getNome());
        assertEquals("Palio Fire 1.0", c.getDesc());
        assertEquals("http url foto", c.getUrlFoto());
        assertEquals("http url video", c.getUrlVideo());
        assertEquals("lat", c.getLatitude());
        assertEquals("lng", c.getLongitude());
        assertEquals("popular", c.getTipo());

        // update
        c.setNome("Palio Fire");
        carroService.save(c);

        // busca carro
        c = carroService.getCarro(id);
        assertEquals("Palio Fire", c.getNome());

        // deleta carro
        carroService.delete(id);
        c = carroService.getCarro(id);
        assertNull(c);
    }
}
