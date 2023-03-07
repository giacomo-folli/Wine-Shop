package com.example.gestorevini;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerThread extends Thread {
    final private Connection conn;
    final private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket, Connection conn) {
        this.socket = socket;
        this.conn = conn;
        //System.out.println("ServerThread Created");
    }

    public void run() {
        try ( Statement stmt = conn.createStatement()) {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            //System.out.println("Socket IO methods created");

            while (true) {//TODO: implement server logic here
                out.println("SERVER ROUND");
                //String line;
                String query;

                if (in.readLine().equals("SHOW_WINES")) {
                    query = "SELECT * FROM wine;";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String out_data = rs.getString("Name") + "/" +
                                        rs.getString("Producer") + "/" +
                                        rs.getString("Origin") + "/" +
                                        rs.getString("Data") + "/" +
                                        rs.getString("Notes") + "/" +
                                        rs.getString("Grape") + "/" +
                                        rs.getString("Price") + "/" +
                                        rs.getString("Quantity");
                        out.println(out_data);
                    }
                    out.println("null");

                } else if (in.readLine().equals("SEARCH_WINE")) {
                    //TODO: Search wine
                } else if (in.readLine().equals("SEARCH_ORDER")) {
                    //TODO: Search order
                } else if (in.readLine().equals("SEARCH_CUSTOMER")) {
                    //TODO: Search customer
                } else if (in.readLine().equals("GET_HELP")) {
                    //TODO: Get help
                } else if (in.readLine().equals("UPDATE_CUSTOMER_DATA")) {
                    //TODO: Update customer data
                } else if (in.readLine().equals("UPDATE_WINE_DATA")) {
                    //TODO: Update wine data
                } else {
                    System.out.println("ServerThread: Feature not added");
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println("ServerThread, " + e);
        }
    }
}


