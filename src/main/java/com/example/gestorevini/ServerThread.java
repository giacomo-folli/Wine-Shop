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

            while (true) {
                System.out.println("SERVER ROUND");
                String line = in.readLine();
                System.out.println("SERVER RECEIVED CMD: " + line);

                if (line.equals("SHOW_WINES"))
                {
                    String query = "SELECT * FROM wine;";
                    ResultSet rs = this.stmt.executeQuery(query);
                    while (rs.next()) {
                        String out_data = rs.getString("Name") + "/" + rs.getString("Producer") + "/" + rs.getString("Origin") + "/" + rs.getString("Data") + "/" + rs.getString("Notes") + "/" + rs.getString("Grape") + "/" + rs.getString("Price") + "/" + rs.getString("Quantity");
                        out.println(out_data);
                    }
                    out.println("null");

                } else if (line.equals("SEARCH_WINE"))
                {
                    int trovato = 0;
                    String name = in.readLine();
                    System.out.println("Searching for " + name);
                    String query = "SELECT * FROM wine WHERE wine.Name='" + name + "';";
                    ResultSet rs = this.stmt.executeQuery(query);

                    while (rs.next()) {
                        trovato = 1;
                        String out_data = rs.getString("Name") + "/" + rs.getString("Producer") + "/" + rs.getString("Origin") + "/" + rs.getString("Data") + "/" + rs.getString("Price") + "/" + rs.getString("Quantity");
                        out.println(out_data);
                    }
                    if (trovato == 0) {
                        out.println("La sua ricerca non ha prodotto risultati");
                    }
                    out.println("null");

                } else if (line.equals("BUY_WINE"))
                {
                    //client/name_wine/quantity/tot_price/card_name/card_number
                    String info = in.readLine();
                    String[] temp = info.split("/");
                    String user = temp[0];
                    String name_wine = temp[1];
                    int quantity = Integer.parseInt(temp[2]);
                    int tot_price = Integer.parseInt(temp[3]);
                    String card_name = temp[4];
                    String card_number = temp[5];
                    ResultSet rs = this.stmt.executeQuery("SELECT ID FROM clienti WHERE clienti.USR='" + user + "';");
                    if (rs.next()) { //if user exists, get his ID and insert the purchase
                        int id = rs.getInt("ID");
                        String query = "INSERT INTO purchase (IDBuyer, WineName, WineQuantity, Price, CardName, CardNumber) VALUES (" + id + ", '" + name_wine + "', " + quantity + ", " + tot_price + ", '" + card_name + "', '" + card_number + "');";
                        int count = this.stmt.executeUpdate(query);
                        if (count == 1) {
                            out.println("SUCCESS");
                        } else {
                            out.println("FAILED");
                        }
                    } else {
                        System.out.println("User not found");
                    }

                } else if (line.equals("SHOW_PURCH"))
                {
                    String user = in.readLine();
                    System.out.println("Showing purchases for " + user);
                    String query = "SELECT * FROM purchase JOIN clienti ON purchase.IDBuyer=clienti.ID WHERE USR='" + user + "';";
                    ResultSet rs = this.stmt.executeQuery(query);
                    while (rs.next()) {
                        String out_data = rs.getString("ID") + "/" + rs.getString("WineName") + "/" + rs.getString("WineQuantity") + "/" + rs.getString("Price") + "/" + rs.getString("CardName");
                        System.out.println(out_data);
                        out.println(out_data);
                    }
                    out.println("null");

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


