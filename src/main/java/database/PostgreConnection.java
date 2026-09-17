package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class PostgreConnection {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    // Bloque estático: se ejecuta UNA sola vez al cargar la clase
    static {
        try {
            Properties props = new Properties();

            // Cargar db.properties desde resources
            InputStream input = PostgreConnection.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (input == null) {
                throw new RuntimeException("No se encontró db.properties en resources/");
            }

            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            // Cargar driver JDBC
            Class.forName(driver);

        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración de la base de datos", e);
        }
    }

    // Método estático: devuelve una conexión nueva cada vez
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
