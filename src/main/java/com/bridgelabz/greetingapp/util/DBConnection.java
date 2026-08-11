package com.bridgelabz.greetingapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection()
            throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "PostgreSQL JDBC Driver not found",
                    e
            );
        }

        String url =
                "jdbc:postgresql://localhost:5432/greetings_app";

        String username =
                "postgres";

        String password =
                "";

        return DriverManager.getConnection(
                url,
                username,
                password
        );
    }
}