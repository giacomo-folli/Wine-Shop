package com.example.gestorevini;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServerThread extends Thread {
    ArrayList<String> low_wines = new ArrayList<>();
    final private Connection conn;
    final private Socket socket;
    final private Statement stmt;
    private BufferedReader in;
    private PrintWriter out;
    LocalDate date = LocalDate.now();

    public ServerThread(Socket socket, Connection conn, Statement stmt) {
        this.stmt = stmt;
        this.socket = socket;
        this.conn = conn;
    }

    private ArrayList<String> checkAvailability() throws SQLException {
        low_wines.clear();
        ResultSet at = this.stmt.executeQuery("SELECT Name FROM wine WHERE wine.Quantity<=1;");
        while (at.next()) {
            if (!low_wines.contains(at.getString("Name")))
                low_wines.add(at.getString("Name"));
        }
        return low_wines;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            while (true) {
                if (checkAvailability().size()>0) {
                    String today = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
                    for (String i : checkAvailability()) {
                        String query = "INSERT INTO alert(NameWine, Date_alert) VALUES ('"+i+"','"+today+"');";
                        try {
                            int rs = this.stmt.executeUpdate(query);
                        } catch (SQLIntegrityConstraintViolationException e) {}
                    }
                    System.out.println("Wine capacity low handling");
                } else { System.out.println("Wine capacity ok"); }

                //Manage requests
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
                }
                else if (line.equals("SEARCH_WINE_NAME")) {
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

                }
                else if (line.equals("SEARCH_WINE_YEAR")) {
                    int trovato = 0;
                    String year = in.readLine();
                    System.out.println("Searching for " + year);
                    String query = "SELECT * FROM wine WHERE wine.Data='" + year + "';";
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

                }
                else if (line.equals("BUY_WINE")) {
                    //client/name_wine/quantity/tot_price/card_name/card_number
                    String info = in.readLine();
                    String[] temp = info.split("/");
                    String cart_id = temp[0];
                    String card_name = temp[1];
                    String card_number = temp[2];

                    ResultSet rs = this.stmt.executeQuery("SELECT * FROM cart WHERE cart.ID=" + cart_id + ";");
                    if (rs.next()) { //retrieve card item data
                        int id = rs.getInt("IDBuyer");
                        String name_wine = rs.getString("WineName");
                        int quantity = rs.getInt("WineQuantity");
                        int tot_price = rs.getInt("Price");
                        System.out.println("ID: " + id + " Name: " + name_wine + " Quantity: " + quantity + " Price: " + tot_price);
                        String query = "INSERT INTO purchase (IDBuyer, WineName, WineQuantity, Price, CardName, CardNumber) VALUES (" + id + ", '" + name_wine + "', " + quantity + ", " + tot_price + ", '" + card_name + "', '" + card_number + "');";
                        int count = this.stmt.executeUpdate(query);

                        if (count == 1) {
                            int x = this.stmt.executeUpdate("DELETE FROM cart WHERE cart.ID=" + cart_id + ";");
                            if (x == 1)
                                out.println("DONE");
                        } else
                            System.out.println("Error in purchase");
                    } else
                        System.out.println("User not found");
                }
                else if (line.equals("SHOW_PURCH")) {
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
                }
                else if (line.equals("ADD_EMPLOYEE")) {
                    String info = in.readLine();
                    String[] temp = info.split("/");
                    String name = temp[0];
                    String surname = temp[1];
                    String usr = temp[2];
                    String pwd = temp[3];
                    String email = temp[4];
                    String cell = temp[5];
                    String Address = temp[6];
                    String cf = temp[7];

                    String query = "INSERT INTO clienti (Name, Surname, USR, PSW, Email, Cell, Addres, Cf, type) VALUES ('" + name + "', '" + surname + "', '" + usr + "', '" + pwd + "', '" + email + "', '" + cell + "', '" + Address + "', '" + cf + "', 'employee');";
                    int count = this.stmt.executeUpdate(query);
                }
                else if (line.equals("UPDATE_EMPLOYEE")) {
                    String info = in.readLine();
                    String[] temp = info.split("/");
                    String name = temp[0];
                    String surname = temp[1];
                    String usr = temp[2];
                    String pwd = temp[3];
                    String email = temp[4];
                    String cell = temp[5];
                    String Address = temp[6];
                    String cf = temp[7];
                    String ID_CLIENT = temp[8];

                    String query = "UPDATE clienti SET USR='"+usr+"', PSW='"+pwd+"', Name='"+name+"', Surname='"+surname+"', Email='"+email+"', Cf='"+cf+"', Cell='"+cell+"', Addres='"+Address+"' WHERE ID="+ ID_CLIENT +";";
                    int count = this.stmt.executeUpdate(query);
                }
                else if (line.equals("GET_EMPLOYEE")) {
                    String query = "SELECT * FROM clienti WHERE type='employee';";
                    ResultSet rs = this.stmt.executeQuery(query);
                    while (rs.next()) {
                        String out_data = rs.getString("ID") + "/" + rs.getString("Name") + "/" + rs.getString("Surname");
                        out.println(out_data);
                    }
                    out.println("null");
                }
                else if (line.equals("DELETE_EMPLOYEE")) {
                    String employee = in.readLine();
                    int rs = this.stmt.executeUpdate("DELETE FROM clienti WHERE ID="+ employee +";");
                }
                else if (line.equals("GET_CLIENT")) {
                    String query = "SELECT * FROM clienti WHERE type='null';";
                    ResultSet rs = this.stmt.executeQuery(query);
                    while (rs.next()) {
                        String out_data = rs.getString("ID") + "/" + rs.getString("Name") + "/" + rs.getString("Surname") + "/" + rs.getString("Email")  + "/" + rs.getString("Addres");
                        out.println(out_data);
                    }
                    out.println("null");
                }
                else if (line.equals("GET_ID_CART")) {
                    String user = in.readLine();
                    String query = "SELECT * FROM cart JOIN clienti ON cart.IDBuyer = clienti.ID WHERE clienti.USR = '" + user + "';";
                    ResultSet rs = this.stmt.executeQuery(query);
                    System.out.println("Getting ID cart for " + user);

                    while (rs.next()) {
                        String out_data = String.valueOf(rs.getInt("ID"));
                        out.println(out_data);
                    }

                    System.out.println("ID cart retrieved");
                    out.println("null");
                }
                else if (line.equals("ADD_TO_CART")) {
                    String info = in.readLine();
                    String[] temp = info.split("/");
                    String name_wine = temp[0];
                    String user = temp[1];
                    int quantity = Integer.parseInt(temp[2]);
                    int tot_price = Integer.parseInt(temp[3]);
                    String name_producer = "Error";
                    int year = 000;
                    int current_q= 0;
                    int id = 0;

                    ResultSet r = this.stmt.executeQuery("SELECT Producer, Data FROM wine WHERE wine.Name='"+name_wine+"';");
                    if (r.next()) {
                        name_producer = r.getString("Producer");
                        year = r.getInt("Data");}

                    ResultSet a = this.stmt.executeQuery("SELECT * FROM wine WHERE Name='"+name_wine+"'");
                    if (a.next())
                        current_q = a.getInt("Quantity");

                    ResultSet rs = this.stmt.executeQuery("SELECT ID FROM clienti WHERE clienti.USR='" + user + "';");
                    if (rs.next()) //if user exists, get his ID and insert the purchase
                        id = rs.getInt("ID");

                    System.out.println(tot_price + " : " + quantity + " : " + current_q);
                    int count = this.stmt.executeUpdate("INSERT INTO cart (IDBuyer, WineName, NameProducer, WineQuantity, Price, Year) VALUES (" + id + ", '" + name_wine + "', '" + name_producer + "', " + quantity + ", " + tot_price + ", " + year + ");");
                    if (count == 1) {
                        System.out.println("Wine added");
                        if (current_q >= quantity) {
                            int new_q = current_q - quantity;
                            System.out.println(new_q + " bottiglie disponibili");
                            String query1 = "UPDATE wine SET Quantity=" + new_q + " WHERE Name='" + name_wine + "';";
                            int z = this.stmt.executeUpdate(query1);
                            if (z == 1) {
                                System.out.println("Wine CApacity updated");
                                out.println("ADDED");
                            } else { out.println("FAILED"); }
                        } else {out.println("LOW_WINE_CAPACITY");}
                    } else { out.println("FAILED_ADD"); }

                }
                else if (line.equals("REGISTER")) {
                    String info = in.readLine();
                    String[] temp = info.split("/");
                    String name = temp[0];
                    String surname = temp[1];
                    String cf = temp[2];
                    String email = temp[3];
                    String address = temp[4];
                    String cell = temp[5];
                    String pssw = temp[6];
                    String usr = temp[7];

                    System.out.println(name + " " + surname + " " + cf + " " + email + " " + address + " " + cell + " " + pssw + " " + usr);
                    int count = this.stmt.executeUpdate("INSERT INTO clienti (USR, PSW, Name, Surname, Email, Cf, Cell, Addres) VALUES ('"+usr+"','"+pssw+"','"+name+"','"+surname+"','"+email+"','"+cf+"','"+cell+"','"+address+"');");
                    if (count == 1) {
                        out.println("REGISTERED");
                    } else { out.println("FAILED_ADD"); }
                }
                else if (line.equals("SHOW_CART")) {
                    String user = in.readLine();
                    System.out.println("Showing cart for " + user);
                    String query = "SELECT * FROM cart JOIN clienti ON cart.IDBuyer=clienti.ID WHERE USR='" + user + "';";
                    ResultSet rs = this.stmt.executeQuery(query);
                    while (rs.next()) {
                        String out_data =   rs.getString("ID")
                                + "/" +     rs.getString("IDBuyer")
                                + "/" +     rs.getString("WineName")
                                + "/" +     rs.getString("NameProducer")
                                + "/" +     rs.getString("WineQuantity")
                                + "/" +     rs.getString("Price")
                                + "/" +     rs.getString("Year");
                        out.println(out_data);
                    }
                    out.println("null");
                }
                else if (line.equals("GET_WEEKLY_SALES")) {
                    String year = in.readLine();
                    String month = in.readLine();
                    String start = in.readLine();
                    String end = in.readLine();
                    String query = "SELECT * FROM purchase WHERE year(PurchDate)=" + year + " AND month(PurchDate)=" + month + " AND day(PurchDate)>=" + start + " AND day(PurchDate)<=" + end + ";";
                    ResultSet rs = this.stmt.executeQuery(query);
                    int sum = 0;
                    while (rs.next()) {
                        sum++;
                    }
                    out.println(sum);
                }
                else if (line.equals("SEND_REPORT")) {
                    String REPORT = in.readLine();
                    String today = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
                    String query = "INSERT INTO reports(ReportDate, text) VALUES ('" + today + "', '" + REPORT + "');";
                    int count = this.stmt.executeUpdate(query);
                    if (count == 1)
                        out.println("REPORT_SENT");
                    else
                        out.println("FAILED_ADD");
                }
                else if (line.equals("GET_MOST_SOLD")) {
                    String query = "SELECT WineName, COUNT(WineQuantity) AS Num FROM purchase GROUP BY WineName ORDER BY Num DESC";
                    ResultSet rs = this.stmt.executeQuery(query);
                    String names = "";
                    String nums = "";
                    while (rs.next()) {
                        names += rs.getString("WineName") + "/";
                        nums += rs.getString("Num") + "/";
                    }
                    out.println(names);
                    out.println(nums);
                }
                else if (line.equals("ADD_PDA")) {
                    String info = in.readLine();
                    String[] temp = info.split("/");
                    String USR = temp[0];
                    String wine_name = temp[1];
                    String wine_producer = temp[2];
                    String wine_year = temp[3];
                    String notes = temp[4];
                    int quantity = Integer.parseInt(temp[5]);

                    int id = 0;

                    ResultSet rs = this.stmt.executeQuery("SELECT ID FROM clienti WHERE clienti.USR='" + USR + "';");
                    if (rs.next()) //if user exists, get his ID and insert the purchase
                        id = rs.getInt("ID");

                    int count = this.stmt.executeUpdate("INSERT INTO pda (IDClient, WineName, WineProducer, WineYear, Quantity, Notes) VALUES (" + id + ", '" + wine_name + "', '" + wine_producer + "', '" + wine_year + "', " + quantity + ", '" + notes + "');");
                    if (count == 1) {
                        System.out.println("Wine added");
                    } else { out.println("FAILED_ADD"); }
                }
                else if (line.equals("GET_PDA")) {
                    ResultSet rs = this.stmt.executeQuery("SELECT * FROM pda;");
                    while (rs.next()) {
                        String out_data = rs.getString("ID") + "/" + rs.getString("IDClient") + "/" + rs.getString("WineName") + "/" + rs.getString("WineProducer") + "/" + rs.getString("WineYear") + "/" + rs.getString("Quantity") + "/" + rs.getString("Notes");
                        out.println(out_data);
                    }
                    out.println("null");
                }
                else if (line.equals("GET_ALERT")) {
                    ResultSet rs = this.stmt.executeQuery("SELECT * FROM alert;");
                    while (rs.next()) {
                        String out_data = rs.getInt("ID") + "/" + rs.getString("NameWine") + "/" + rs.getString("Date_alert");
                        out.println(out_data);
                    }
                    out.println("null");
                }
                else if (line.equals("DELETE_ALERT")) {
                    String id = in.readLine();
                    int count = this.stmt.executeUpdate("DELETE FROM alert WHERE ID="+ id + ";");
                }
                else if (line.equals("CHECK_DISCOUNTS")) {
                    String query = "SELECT * FROM discount WHERE ID=1;";
                    ResultSet rs = this.stmt.executeQuery(query);
                    if (rs.next())
                        out.println(rs.getString("small") + "/" + rs.getString("medium") + "/" + rs.getString("large") + "/" + rs.getString("max"));
                    else
                        out.println("error");
                }
                else if (line.equals("UPDATE_DISCOUNTS")) {
                    String discounts = in.readLine();
                    String[] temp = discounts.split("/");
                    String small = temp[0];
                    String medium = temp[1];
                    String large = temp[2];
                    String max = temp[3];
                    int count = this.stmt.executeUpdate("UPDATE discount SET small=" + small + ", medium=" + medium + ", large=" + large + ", max=" + max + " WHERE ID=1;");
                }
                else if (line.equals("SET_QUANTITY")) {
                    String wineName = in.readLine();
                    if (!wineName.equals("null")) {
                        int quantity = Integer.parseInt(in.readLine());
                        int count = this.stmt.executeUpdate("UPDATE wine SET Quantity=" + quantity + " WHERE Name='" + wineName + "';");
                    } else {
                        int quantity = Integer.parseInt(in.readLine());
                    }
                }
                else { System.out.println("ServerThread: Feature not added"); }
            }
        } catch (IOException | SQLException e) { System.out.println("ServerThread, " + e); }
    }
}


