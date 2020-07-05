package br.com.livro.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarroService {

    private final CarroDAO db;

    @Autowired
    public CarroService(CarroDAO db) {
        this.db = db;
    }

    public List<Carro> getCarros() {
        try {
            List<Carro> carros = db.getCarros();
            return carros;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Carro getCarro(Long id) {
        try {
            Carro carro = db.getCarroOrderById(id);
            return carro;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Long id) {
        try {
            return  db.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save(Carro carro) {
        try {
            db.save(carro);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Carro> findByName(String name) {
        try {
            return db.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Carro> findByTipo(String tipo) {
        try {
            return db.findByTipo(tipo);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
