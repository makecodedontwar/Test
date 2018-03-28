package com.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    public static synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1","sa","");
            connection.setAutoCommit(true);
            connection.createStatement().execute("create table names (name varchar, count int)");
            return connection;
        } else {
            return connection;
        }
    }
}
