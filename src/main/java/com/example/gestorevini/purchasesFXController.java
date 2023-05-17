package com.example.gestorevini;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class purchasesFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<Purchase> list = FXCollections.observableArrayList();
    private String client;
    private String type;

    @FXML
    private TableView<Purchase> purchaseTableView;
    @FXML
    private TableColumn<Purchase, String> wine_name_col, card_col;
    @FXML
    private TableColumn<Purchase, Integer> id_col, quantity_col, price_col;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

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
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type, client); }

    private Socket getSocket() throws Exception {
        return new Socket("localhost", 1234);
    }

}
