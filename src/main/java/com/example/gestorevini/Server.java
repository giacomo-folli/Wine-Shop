package com.example.gestorevini;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Scanner;

public class Server extends Application {

    @Override
    public void start(Stage stage) throws IOException { //TODO: initialize Server and connect to DB
        final String DBURL = "jdbc:mysql://127.0.0.1:3306/wineshop";
        //final String ARGS = "createDatabaseIfNotExist=true&serverTimezone=UTC";
        final String LOGIN = "root";
        final String PASSWORD = "";

        try (
                Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
                Statement stmt = conn.createStatement()
        ) { //TODO: if connection is successful, manage client-server connection
            while (true) {
                int port = 1234; //set port number
                try (ServerSocket serverSocket = new ServerSocket(port)) { //create a new server socket
                    System.out.println("Server is listening on port " + port);

                    while (true) {

                        Socket socket = serverSocket.accept();
                        System.out.println("New client connected");
                        new ServerThread(socket).start();

                    }
                } catch (Exception e) { //TODO: handle start() exception;
                }
            }
        } catch (Exception e) { System.out.println(e); }
    }
}

