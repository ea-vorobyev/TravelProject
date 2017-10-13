package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerPostgresSQL implements IConnectionManager{
    private static final ConnectionManagerPostgresSQL INSTANCE = new ConnectionManagerPostgresSQL();
    private Connection connection;

    private ConnectionManagerPostgresSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TravelDB", "postgres", "root");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionManagerPostgresSQL getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

}


