package com.example.gestorevini;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;

public class Server extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        final String DBURL = "jdbc:mysql://localhost:3306/wineshop";
        //final String ARGS = "createDatabaseIfNotExist=true&serverTimezone=UTC";
        final String LOGIN = "root";
        final String PASSWORD = "root";

        try (

                Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
                Statement stmt = conn.createStatement();

        ) {
            //ServerSocket serverSocket = new ServerSocket(4444, 20);
            ResultSet rs = stmt.executeQuery("SELECT * FROM client");

            while (rs.next()) {
                System.out.println(rs.getString("Nome"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}