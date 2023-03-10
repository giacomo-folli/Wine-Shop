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
    final private Statement stmt;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket, Connection conn, Statement stmt) {
        this.stmt = stmt;
        this.socket = socket;
        this.conn = conn;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            //System.out.println("Socket IO methods created");

            while (true) {
                System.out.println("SERVER ROUND");
                String line = in.readLine();
                System.out.println("SERVER RECEIVED CMD: " + line);

                if (line.equals("SHOW_WINES")) {
                    String query = "SELECT * FROM wine;";
                    ResultSet rs = this.stmt.executeQuery(query);
                    while (rs.next()) {
                        String out_data = rs.getString("Name") + "/" + rs.getString("Producer") + "/" + rs.getString("Origin") + "/" + rs.getString("Data") + "/" + rs.getString("Notes") + "/" + rs.getString("Grape") + "/" + rs.getString("Price") + "/" + rs.getString("Quantity");
                        out.println(out_data);
                    }
                    out.println("null");

                } else if (line.equals("SEARCH_WINE")) {
                    int trovato = 0;
                    String name = in.readLine();
                    System.out.println("Searching for " + name);
                    String query = "SELECT * FROM wine WHERE wine.Name='" + name + "';";
                    ResultSet rs = this.stmt.executeQuery(query);

                    while (rs.next()) {
                        trovato = 1;
                        String out_data = rs.getString("Name") + "/" + rs.getString("Producer") + "/" + rs.getString("Origin") + "/" + rs.getString("Data") + "/" + rs.getString("Grape") + "/" + rs.getString("Quantity");
                        out.println(out_data);
                    }
                    if (trovato == 0) {
                        out.println("La sua ricerca non ha prodotto risultati");
                    }
                    out.println("null");

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
                } else {
                    System.out.println("ServerThread: Feature not added");
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println("ServerThread, " + e);
        }
    }
}


