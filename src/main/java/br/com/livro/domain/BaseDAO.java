package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    public BaseDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/livro?useTimezone=true&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(url, "livro", "livro123");
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        BaseDAO db = new BaseDAO();
        Connection conn = db.getConnection();
        System.out.println(conn);
    }
}
