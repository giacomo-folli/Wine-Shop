package com.example.gestorevini;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class purchasesFXController implements Initializable {
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<Purchase> list = FXCollections.observableArrayList();
    private String client;

    @FXML
    private Button btn_user, btn_cart, btn_notifications, btn_logout, btn_home;
    @FXML
    private TableView<Purchase> purchaseTableView;
    @FXML
    private TableColumn<Purchase, String> wine_name_col, card_col;
    @FXML
    private TableColumn<Purchase, Integer> id_col, quantity_col, price_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        wine_name_col.setCellValueFactory(new PropertyValueFactory<>("wineName"));
        card_col.setCellValueFactory(new PropertyValueFactory<>("cardName"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("ID"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("wineQuantity"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("winePrice"));

        purchaseTableView.getItems().clear();
        list.clear();
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setTableView() {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("SHOW_PURCH");
            out.println(this.client);
            System.out.println("Requesting purchases of client: " + this.client);

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            while (!(line = in.readLine()).equals("null")) {
                String[] temp = line.split("/");
                int id = Integer.parseInt(temp[0]);
                String wineName =temp[1];
                int wineQuantity = Integer.parseInt(temp[2]);
                int totPrice = Integer.parseInt(temp[3]);
                String cardName = temp[4];

                Purchase p = new Purchase(id, 0, wineName, wineQuantity, totPrice, cardName, "");
                list.add(p);
                purchaseTableView.getItems().add(p);
            }
        } catch (Exception e) {
            System.out.println("purchasesFXController, " + e);
        }
    }

    @FXML
    public void btn_home_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
        Stage window = (Stage) btn_home.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Home");
    }

    @FXML
    public void btn_logout_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Stage window = (Stage) btn_logout.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Login");
    }

    @FXML
    public void btn_user_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_page.fxml"));
        Stage window = (Stage) btn_user.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("User Page");
    }

    @FXML
    public void btn_cart_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cart_page.fxml"));
        Stage window = (Stage) btn_cart.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Cart");
    }

    @FXML
    public void btn_notifications_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("notifications_page.fxml"));
        Stage window = (Stage) btn_notifications.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Notifications");
    }

    private Socket getSocket() throws Exception {
        return new Socket("localhost", 1234);
    }

}
