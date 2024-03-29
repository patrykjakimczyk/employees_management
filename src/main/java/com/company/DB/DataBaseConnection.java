package com.company.DB;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;

@Getter
public final class DataBaseConnection {
    private static final String url = "jdbc:postgresql://127.0.0.1:5432/company";
    private static final String user = "postgres";
    private static final String password = "admin";
    public Connection connection;

    public DataBaseConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Couldn't connect to database");
        }
    }
}
