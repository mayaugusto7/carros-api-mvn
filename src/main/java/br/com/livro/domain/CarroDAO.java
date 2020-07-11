package br.com.livro.domain;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarroDAO extends HibernateDAO<Carro> {

    public CarroDAO() {
        super(Carro.class);
    }

    public Carro getCarroById(Long id) {
        return super.get(id);
    }

    public List<Carro> findByName(String nome) {
        Query q = getSession().createQuery("FROM Carro WHERE lower(nome) like lower(?)");
        q.setString(0, "%" + nome + "%");
        return q.list();
    }

    public List<Carro> findByTipo(String tipo) {
        Query q = getSession().createQuery("FROM Carro WHERE tipo=?");
        q.setString(0, tipo);
        List<Carro> carros = q.list();
        return carros;
    }

    public List<Carro> getCarros() {
        Query q = getSession().createQuery("FROM Carro");
        List<Carro> carros = q.list();
        return carros;
    }

    public void salvar(Carro c) {
        getSession().save(c);
    }

    public boolean delete(Long id) {
        Carro c = get(id);
        delete(c);
        return true;
    }


}
