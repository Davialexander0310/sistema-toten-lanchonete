package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL     = "jdbc:mysql://localhost:3306/quickbite?useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA   = "";  // padrão XAMPP é sem senha

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // driver do conector 5.1
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado.", e);
        }
    }
}