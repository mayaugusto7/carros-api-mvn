package br.com.livro.domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends BaseDAO {

    public Carro getCarroOrderById(Long id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM carro WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Carro c = createCarro(rs);
                rs.close();
                return c;
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return null;
    }

    public List<Carro> findByName(String nome) throws SQLException {
        List<Carro> carros = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM carro WHERE lower(nome) like ?");
            stmt.setString(1, "%" + nome.toLowerCase() + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carro c = createCarro(rs);
                carros.add(c);
            }

            rs.close();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return carros;
    }

    public List<Carro> findByTipo(String tipo) throws SQLException {
        List<Carro> carros = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM carro WHERE tipo = ?");
            stmt.setString(1, tipo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carro c = createCarro(rs);
                carros.add(c);
            }

            rs.close();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return carros;
    }

    public List<Carro> getCarros() throws SQLException {
        List<Carro> carros = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM carro");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carro c = createCarro(rs);
                carros.add(c);
            }

            rs.close();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return carros;
    }


    public Carro createCarro(ResultSet rs) throws SQLException {

        Carro c = new Carro();
        c.setId(rs.getLong("id"));
        c.setNome(rs.getString("nome"));
        c.setDesc(rs.getString("descricao"));
        c.setUrlFoto(rs.getString("url_foto"));
        c.setUrlVideo(rs.getString("url_video"));
        c.setLatitude(rs.getString("latitude"));
        c.setLongitude(rs.getString("longitude"));
        c.setTipo(rs.getString("tipo"));

        return c;
    }

    public void save(Carro c) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            if (c.getId() == null) {
                stmt = conn.
                        prepareStatement("INSERT INTO carro(nome, descricao, url_foto, url_video, latitude, longitude, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)",
                                Statement.RETURN_GENERATED_KEYS);
            } else {
                stmt = conn.prepareStatement("UPDATE carro SET nome=?, descricao=?, url_foto=?, url_video=?, latitude=?, longitude=?, tipo=? WHERE id=?");
            }

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDesc());
            stmt.setString(3, c.getUrlFoto());
            stmt.setString(4, c.getUrlVideo());
            stmt.setString(5, c.getLatitude());
            stmt.setString(6, c.getLongitude());
            stmt.setString(7, c.getTipo());

            if (c.getId() != null) {
                stmt.setLong(8, c.getId());
            }

            int count = stmt.executeUpdate();

            if (count == 0) {
                throw new SQLException("Erro ao inserir carro!");
            }

            if (c.getId() == null) {
                Long id = getGenerateId(stmt);
                c.setId(id);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public static Long getGenerateId(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();

        if (rs.next()) {
            Long id = rs.getLong(1);
            return id;
        }

        return 0L;
    }

    public boolean delete(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("DELETE FROM carro WHERE id=?");
            stmt.setLong(1, id);
            int count = stmt.executeUpdate();
            boolean ok = count > 0;
            return ok;
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }
}
