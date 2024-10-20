package util;
/**
 * @author Alejandro Serrano
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //Basde de datos "libreria"
    private static final String URL = "jdbc:mysql://localhost:3306/libreria";//BBDD
    private static final String USER = "root";//Usuario root
    private static final String PASSWORD = "1234";//Contraseña
    //Establece la conexión en cada uno de los métodos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
