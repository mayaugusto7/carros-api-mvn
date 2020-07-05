package br.com.livro.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "carros")
public class ListaCarros implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Carro> carros;

    @XmlElement(name = "carro")
    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public String toString() {
        return "ListaCarros{" +
                "carros=" + carros +
                '}';
    }
}
