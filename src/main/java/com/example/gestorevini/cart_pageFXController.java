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
    private MAIN_LIB lib = new MAIN_LIB();
    private String client = MainApplicationFXController.getUserUSR();
    private String type;
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<CartItem> list = FXCollections.observableArrayList();

    @FXML
    private TableView<CartItem> cart_table;
    @FXML
    private TableColumn<CartItem, String> name_col, prod_col;
    @FXML
    private TableColumn<CartItem, Integer> year_col, price_col, quantity_col;

    public void setUserType(String i) { type = i; }

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
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type); }

    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }

}
