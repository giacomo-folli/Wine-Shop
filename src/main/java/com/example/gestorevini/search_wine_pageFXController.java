package com.example.gestorevini;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class search_wine_pageFXController implements Initializable {
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<Wine> list = FXCollections.observableArrayList();
    private Wine temp_wine;
    private String client;

    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_user, btn_cart, btn_notifications, btn_logout, btn_home, btn_buy_wine;
    @FXML
    private TableView<Wine> search_table;
    @FXML
    private TableColumn<Wine, String> name_col, prod_col, origin_col;
    @FXML
    private TableColumn<Wine, Integer> year_col, avl_col, price_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_col.setCellValueFactory(new PropertyValueFactory<>("nome"));
        prod_col.setCellValueFactory(new PropertyValueFactory<>("produttore"));
        origin_col.setCellValueFactory(new PropertyValueFactory<>("provenienza"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("anno"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        avl_col.setCellValueFactory(new PropertyValueFactory<>("num"));
        search_table.getItems().clear();
        list.clear();

        search_table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount()!=0) {
                temp_wine = search_table.getSelectionModel().getSelectedItem();
            }
        });

        txt_search.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER ) btn_search_is_clicked();
        });
    }

    public void setUserID(String c) { client = c; }

    @FXML
    public void btn_search_is_clicked() {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String search = txt_search.getText();
            if (search != "") {
                out.println("SEARCH_WINE");
                out.println(search);
                String line;

                while ((line = in.readLine())!="null") {
                    String[] temp = line.split("/");
                    String name = temp[0];
                    String prod = temp[1];
                    String orig = temp[2];
                    String year = temp[3];
                    int price = Integer.parseInt(temp[4]);
                    int num = Integer.parseInt(temp[5]);

                    list.clear();
                    list.add(new Wine(name, prod, orig, (Integer.parseInt(year)), "", "", price, num));
                    search_table.setItems(list);
                }
            }
        } catch (Exception t) {
            System.out.println("wineListFXController, " + t);
        }
    }

    public void btn_buy_wine_is_clicked(ActionEvent event) throws Exception {
        if (temp_wine==null) {
            System.out.println("You have to select a wine");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buy_info_page.fxml"));
            Parent root = loader.load();
            //Set the wine to buy in new scene
            buyInfoFXController buyInfo = loader.getController();
            buyInfo.setLbl_cart_info(temp_wine.getNome(), temp_wine.getProduttore());
            buyInfo.setUserID(client);
            buyInfo.setPrice(temp_wine.getPrezzo());
            buyInfo.setMaxQuantity(temp_wine.getNum());
            buyInfo.getSocket(getSocket());
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
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
