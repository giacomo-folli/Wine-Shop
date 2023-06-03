package com.example.gestorevini;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServerThread extends Thread {
    ArrayList<String> low_wines = new ArrayList<>();
    ArrayList<String> temp_wines = new ArrayList<>();
    /** The connection to the database. */
    final private Connection conn;
    /** The socket to communicate with the client. */
    final private Socket socket;
    /** The statement to execute queries. */
    final private Statement stmt;
    private BufferedReader in;
    private PrintWriter out;
    LocalDate date = LocalDate.now();

    /**
     * Instantiates a server child
     * @param socket Socket to communicate with the client
     * @param conn Connection to the database
     * @param stmt Statement to execute queries
     * @throws IOException If an error occured
     */
    public ServerThread(Socket socket, Connection conn, Statement stmt) throws IOException
    {
        this.socket = socket;
        this.stmt = stmt;
        this.conn = conn;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new PrintWriter(this.socket.getOutputStream(), true);
    }

    private ArrayList<String> checkAvailability() throws SQLException {
        low_wines.clear();
        ResultSet lol = this.stmt.executeQuery("SELECT Name FROM wine WHERE wine.Quantity<=1;");
        while (lol.next()) {
            low_wines.add(lol.getString("Name"));
        }
        return low_wines;
    }

    public void run() {
        try
        {
            while (true) {
                low_wines.clear();
                low_wines = checkAvailability();
                //check wines availability
                if (low_wines!=null && low_wines.size()>0) {
                    String today = date.getYear() + ":" + date.getMonthValue() + ":" + date.getDayOfMonth();

                    //download alerts already fired
                    temp_wines.clear();
                    ResultSet as = this.stmt.executeQuery("SELECT NameWine FROM alert;");
                    while (as.next()) {
                        temp_wines.add(as.getString("NameWine"));
                    }

                    //check for duplicates and fire new alerts
                    for (String i : low_wines) {
                        if (!temp_wines.contains(i)) {
                            try {
                                int rss = this.stmt.executeUpdate("INSERT INTO alert(NameWine, Date_alert) VALUES ('"+i+"','"+today+"');");
                            } catch (Exception e) {
                                System.out.println("ERROR CheckAvailability() UPDATE");
                            }
                        }
                    }
                    low_wines.clear();
                }

                //Manage requests
                String line = in.readLine();
                System.out.println("SERVER RECEIVED CMD: " + line);

                switch (line) {
                    case "SHOW_WINES" -> showWines();
                    case "SEARCH_WINE_NAME" -> searchWineName();
                    case "SEARCH_WINE_YEAR" -> searchWineYear();
                    case "BUY_WINE" -> buyWine();
                    case "SHOW_PURCH" -> showPurch();
                    case "ADD_EMPLOYEE" -> addEmployee();
                    case "UPDATE_EMPLOYEE" -> updateEmployee();
                    case "GET_EMPLOYEE" -> getEmployee();
                    case "DELETE_EMPLOYEE" -> deleteEmployee();
                    case "GET_CLIENT" -> getClient();
                    case "GET_ID_CART" -> getIdCart();
                    case "ADD_TO_CART" -> addToCart();
                    case "REGISTER" -> register();
                    case "SHOW_CART" -> showCart();
                    case "GET_WEEKLY_SALES" -> getWeeklySales();
                    case "SEND_REPORT" -> sendReport();
                    case "GET_MOST_SOLD" -> getMostSold();
                    case "ADD_PDA" -> addPDA();
                    case "GET_PDA" -> getPDA();
                    case "GET_ALERT" -> getAlert();
                    case "DELETE_ALERT" -> deleteAlert();
                    case "CHECK_DISCOUNTS" -> checkDiscounts();
                    case "UPDATE_DISCOUNTS" -> updateDiscounts();
                    case "SET_QUANTITY" -> setQuantity();
                    default -> System.out.println("ServerThread: Feature not added");
                }
            }
        } catch (IOException | SQLException e) { System.out.println("ServerThread, " + e); }
    }

    /** Show all wines in the database */
    private void showWines() throws SQLException
    {
            String query = "SELECT * FROM wine;";
            ResultSet rs = this.stmt.executeQuery(query);
            while (rs.next()) {
                String out_data = rs.getString("Name") + "/" + rs.getString("Producer") + "/" + rs.getString("Origin") + "/" + rs.getString("Data") + "/" + rs.getString("Notes") + "/" + rs.getString("Grape") + "/" + rs.getString("Price") + "/" + rs.getString("Quantity");
                out.println(out_data);
            }
            out.println("null");
    }

    /** Search a wine by name */
    private void searchWineName() throws IOException, SQLException
    {
        int trovato = 0;
        String name = in.readLine();
        System.out.println("Searching for " + name);
        String query = "SELECT * FROM wine WHERE wine.Name='" + name + "';";
        ResultSet rs = this.stmt.executeQuery(query);

        while (rs.next())
        {
            trovato = 1;
            String out_data = rs.getString("Name") + "/" + rs.getString("Producer") + "/" + rs.getString("Origin") + "/" + rs.getString("Data") + "/" + rs.getString("Price") + "/" + rs.getString("Quantity");
            out.println(out_data);
        }

        out.println((trovato == 0) ? "La sua ricerca non ha prodotto risultati" : "null");

    }

    /** Search a wine by year */
    private void searchWineYear() throws IOException, SQLException
    {
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

    /** Buy a wine */
    private void buyWine() throws IOException, SQLException
    {
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

    /** Show all purchases of a client */
    private void showPurch() throws IOException, SQLException
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
    }

    /** Add a new employee */
    private void addEmployee() throws IOException, SQLException
    {
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

    /** Update an employee */
    private void updateEmployee() throws IOException, SQLException
    {
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

        String query = "UPDATE clienti SET USR='" + usr + "', PSW='" + pwd + "', Name='" + name + "', Surname='" + surname + "', Email='" + email + "', Cf='" + cf + "', Cell='" + cell + "', Addres='" + Address + "' WHERE ID=" + ID_CLIENT + ";";
        int count = this.stmt.executeUpdate(query);
    }

    /** Get all employees */
    private void getEmployee() throws IOException, SQLException
    {
        String query = "SELECT * FROM clienti WHERE type='employee';";
        ResultSet rs = this.stmt.executeQuery(query);
        while (rs.next()) {
            String out_data = rs.getString("ID") + "/" + rs.getString("Name") + "/" + rs.getString("Surname");
            out.println(out_data);
        }
        out.println("null");
    }

    /** Delete an employee */
    private void deleteEmployee() throws IOException, SQLException
    {
        String employee = in.readLine();
        int rs = this.stmt.executeUpdate("DELETE FROM clienti WHERE ID=" + employee + ";");
    }

    /** Get all clients */
    private void getClient() throws IOException, SQLException
    {
        String query = "SELECT * FROM clienti WHERE type='client';";
        ResultSet rs = this.stmt.executeQuery(query);
        while (rs.next()) {
            String out_data = rs.getString("ID") + "/" + rs.getString("Name") + "/" + rs.getString("Surname") + "/" + rs.getString("Email") + "/" + rs.getString("Addres");
            out.println(out_data);
        }
        out.println("null");
    }

    /** Get ID cart of a client */
    private void getIdCart() throws IOException, SQLException
    {
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

    /** Add item to cart */
    private void addToCart() throws IOException, SQLException
    {
        String info = in.readLine();
        String[] temp = info.split("/");
        String name_wine = temp[0];
        String user = temp[1];
        int quantity = Integer.parseInt(temp[2]);
        int tot_price = Integer.parseInt(temp[3]);
        String name_producer = "Error";
        int year = 000;
        int current_q = 0;
        int id = 0;

        ResultSet r = this.stmt.executeQuery("SELECT Producer, Data FROM wine WHERE wine.Name='" + name_wine + "';");
        if (r.next()) {
            name_producer = r.getString("Producer");
            year = r.getInt("Data");
        }

        ResultSet a = this.stmt.executeQuery("SELECT * FROM wine WHERE Name='" + name_wine + "'");
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
                } else {
                    out.println("FAILED");
                }
            } else {
                out.println("LOW_WINE_CAPACITY");
            }
        } else {
            out.println("FAILED_ADD");
        }

    }

    /** Register a new client */
    private void register() throws IOException, SQLException
    {
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
        int count = this.stmt.executeUpdate("INSERT INTO clienti (USR, PSW, Name, Surname, Email, Cf, Cell, Addres) VALUES ('" + usr + "','" + pssw + "','" + name + "','" + surname + "','" + email + "','" + cf + "','" + cell + "','" + address + "');");
        if (count == 1) {
            out.println("REGISTERED");
        } else {
            out.println("FAILED_ADD");
        }
    }

    /** Show cart of a client */
    private void showCart() throws IOException, SQLException
    {
        String user = in.readLine();
        System.out.println("Showing cart for " + user);
        String query = "SELECT * FROM cart JOIN clienti ON cart.IDBuyer=clienti.ID WHERE USR='" + user + "';";
        ResultSet rs = this.stmt.executeQuery(query);
        while (rs.next()) {
            String out_data = rs.getString("ID")
                    + "/" + rs.getString("IDBuyer")
                    + "/" + rs.getString("WineName")
                    + "/" + rs.getString("NameProducer")
                    + "/" + rs.getString("WineQuantity")
                    + "/" + rs.getString("Price")
                    + "/" + rs.getString("Year");
            out.println(out_data);
        }
        out.println("null");
    }

    /** Get weekly sales */
    private void getWeeklySales() throws IOException, SQLException
    {
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

    /** Send a report */
    private void sendReport() throws IOException, SQLException
    {
        String REPORT = in.readLine();
        String today = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
        String query = "INSERT INTO reports(ReportDate, text) VALUES ('" + today + "', '" + REPORT + "');";
        int count = this.stmt.executeUpdate(query);
        System.out.println(count == 1 ? "REPORT_SENT" : "FAILED_ADD");
    }

    /** Get most sold wines */
    private void getMostSold() throws IOException, SQLException
    {
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

    /** Add a new PDA */
    private void addPDA() throws IOException, SQLException
    {
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
        System.out.println(count == 1 ? "SUCCESSFULL_ADD" : "FAILED_ADD");
    }

    /** Get PDA */
    private void getPDA() throws IOException, SQLException
    {
        ResultSet rs = this.stmt.executeQuery("SELECT * FROM pda;");
        while (rs.next()) {
            String out_data = rs.getString("ID") + "/" + rs.getString("IDClient") + "/" + rs.getString("WineName") + "/" + rs.getString("WineProducer") + "/" + rs.getString("WineYear") + "/" + rs.getString("Quantity") + "/" + rs.getString("Notes");
            out.println(out_data);
        }
        out.println("null");
    }

    /** Get alerts */
    private void getAlert() throws IOException, SQLException
    {
        ResultSet rs = this.stmt.executeQuery("SELECT * FROM alert;");
        while (rs.next()) {
            String out_data = rs.getInt("ID") + "/" + rs.getString("NameWine") + "/" + rs.getString("Date_alert");
            out.println(out_data);
        }
        out.println("null");
    }

    /** Delete an alert */
    private void deleteAlert() throws IOException, SQLException
    {
        String id = in.readLine();
        int count = this.stmt.executeUpdate("DELETE FROM alert WHERE ID=" + id + ";");
    }

    /** Check discounts */
    private void checkDiscounts() throws IOException, SQLException
    {
        String query = "SELECT * FROM discount WHERE ID=1;";
        ResultSet rs = this.stmt.executeQuery(query);
        if (rs.next())
            out.println(rs.getString("small") + "/" + rs.getString("medium") + "/" + rs.getString("large") + "/" + rs.getString("max"));
        else
            out.println("error");
    }

    /** Update discounts */
    private void updateDiscounts() throws IOException, SQLException
    {
        String discounts = in.readLine();
        String[] temp = discounts.split("/");
        String small = temp[0];
        String medium = temp[1];
        String large = temp[2];
        String max = temp[3];
        int count = this.stmt.executeUpdate("UPDATE discount SET small=" + small + ", medium=" + medium + ", large=" + large + ", max=" + max + " WHERE ID=1;");
    }

    /** Set quantity */
    private void setQuantity() throws IOException, SQLException
    {
        String wineName = in.readLine();
        if (!wineName.equals("null")) {
            int quantity = Integer.parseInt(in.readLine());
            int count = this.stmt.executeUpdate("UPDATE wine SET Quantity=" + quantity + " WHERE Name='" + wineName + "';");
        }
    }

}

