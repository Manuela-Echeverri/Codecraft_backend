package config;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_db {

    private static final String URL = "jdbc:mysql://localhost:3306/proacademic_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Passc0de*";

    public static java.sql.Connection getConnection() {
        java.sql.Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }

        return conn;
    }

}
