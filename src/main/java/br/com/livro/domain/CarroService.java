package br.com.livro.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        List<Carro> carros = db.getCarros();
        return carros;
    }

    public Carro getCarro(Long id) {
        return db.getCarroById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return db.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Carro carro) {
        db.saveOrUpdate(carro);
        return true;
    }

    public List<Carro> findByName(String name) {
        return db.findByName(name);
    }

    public List<Carro> findByTipo(String tipo) {
        return db.findByTipo(tipo);
    }
}
