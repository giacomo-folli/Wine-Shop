package com.example.gestorevini;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerThread extends Thread{
    private Connection conn;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket, Connection conn) {
        this.socket = socket;
        this.conn = conn;
    }

    public void run() {
        try ( Statement stmt = conn.createStatement()) {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            while (true) {//TODO: implement server logic here
                out.println("Hello from server");
                String line = in.readLine();
                String query;

                if (line.equals("SHOW_WINES")) {
                    query = "SELECT * FROM wine;";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        out.println(rs.getString("NAME"));
                    }
                    System.out.println("Show wines handled");
                } else if (line.equals("SEARCH_WINE")) {
                    //TODO: Search wine
                } else if (line.equals("SEARCH_ORDER")) {
                    //TODO: Search order
                } else if (line.equals("SEARCH_CUSTOMER")) {
                    //TODO: Search customer
                } else if (line.equals("GET_HELP")) {
                    //TODO: Get help
                } else if (line.equals("UPDATE_CUSTOMER_DATA")) {
                    //TODO: Update customer data
                } else if (line.equals("UPDATE_WINE_DATA")) {
                    //TODO: Update wine data
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println("ServerThread, " + e);
        }
    }
}


