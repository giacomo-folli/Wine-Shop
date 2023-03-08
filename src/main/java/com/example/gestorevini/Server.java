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

    public void start(Stage stage) throws IOException { //TODO: initialize Server and connect to DB
        final String DBURL = "jdbc:mysql://localhost:3306/wineshop";
        final String LOGIN = "root";
        final String PASSWORD = "";

        try (
                Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
                Statement stmt = conn.createStatement()
        ) { //TODO: if connection is successful, manage client-server connection
            while (true) {
                int port = 1234; //set port number
                try (ServerSocket serverSocket = new ServerSocket(port))
                { //create a new server socket
                    while (true) {
                        final Socket socket = serverSocket.accept();
                        System.out.println("New client connected at port " + socket.getPort());
                        new ServerThread(socket, conn, stmt).start(); //create a new thread for each client
                    }
                } catch (Exception e) { System.out.println("Server connection failed, " + e); }
            }
        } catch (Exception e) { System.out.println("DB connection failed, " + e); }
    }
}

