package com.example.gestorevini;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class cart_pageFXController implements Initializable {
    private String client = MainApplicationFXController.getUserUSR();
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<CartItem> list = FXCollections.observableArrayList();

    @FXML
    private Button btn_home, btn_logout, btn_cart, btn_notifications, btn_user;
    @FXML
    private TableView<CartItem> cart_table;
    @FXML
    private TableColumn<CartItem, String> name_col, prod_col;
    @FXML
    private TableColumn<CartItem, Integer> year_col, price_col, quantity_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_col.setCellValueFactory(new PropertyValueFactory<>("WineName"));
        prod_col.setCellValueFactory(new PropertyValueFactory<>("NameProducer"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("Year"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("Price"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("WineQuantity"));

        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("SHOW_CART");
            out.println(client);
            System.out.println("Show cart of " + client);

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            while (!(line = in.readLine()).equals("null")) {
                String[] temp = line.split("/");
                int IDCart = Integer.parseInt(temp[0]);
                int IDBuyer = Integer.parseInt(temp[1]);
                String WineName = temp[2];
                String NameProducer = temp[3];
                int WineQuantity = Integer.parseInt(temp[4]);
                int Price = Integer.parseInt(temp[5]);
                int Year = Integer.parseInt(temp[6]);

                CartItem i = new CartItem(IDCart, IDBuyer, WineName, NameProducer, WineQuantity, Price, Year);
                list.add(i);
                cart_table.getItems().add(i);
            }
        } catch (Exception e) {
            System.out.println("cartPageFXController, " + e);
        }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logged_in.fxml"));
        Parent root = loader.load();
        LoggedInFXController LFXC = loader.getController();
        LFXC.setUser(client);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
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
