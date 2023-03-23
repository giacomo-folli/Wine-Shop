package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import static java.lang.Thread.sleep;

public class addCartFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private String client;
    private String type;
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;
    private String name_wine;
    private int temp_quantity = 1;
    private int tot_price;
    private int price;
    private String wine_name, user_buyer, name_producer;
    private int quantity, wines_price, year;

    @FXML
    private Button btn_home, btn_logout, btn_user, btn_cart, btn_notifications, btn_add_to_cart;
    @FXML
    public Label lbl_cart_info, lbl_est_cost;
    @FXML
    private Spinner<Integer> spin_quantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spin_quantity.setOnMouseClicked((MouseEvent event) -> {
            temp_quantity = spin_quantity.getValue();
            tot_price = price * temp_quantity;
            lbl_est_cost.setText(tot_price + "€");
        });
    }

    public void setUserID(String c) { client = c; }
    public void setUserType(String c) { type = c; }

    public void getSocket(Socket socket) { s = socket; }

    public void setLbl_cart_info(String nameWine, String nameProducer) {
        name_wine = nameWine;
        String text = "You are buying " + name_wine + " from " + nameProducer;
        lbl_cart_info.setText(text);
    }

    public void setPrice(int i) {
        price = i;
    }

    public void setMaxQuantity(int a) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, a);
        valueFactory.setValue(1);
        spin_quantity.setValueFactory(valueFactory);
        lbl_est_cost.setText(String.valueOf(price) + "€");
    }

    @FXML
    private void btn_add_to_cart_clicked(ActionEvent event) throws IOException, InterruptedException {
        out = new PrintWriter(s.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        wine_name = name_wine;
        user_buyer = client;
        quantity = spin_quantity.getValue();
        wines_price = price * temp_quantity;

        out.println("ADD_TO_CART");
        out.println(name_wine+"/"+client+"/"+quantity+"/"+wines_price);
        System.out.println(name_wine+"/"+client+"/"+quantity+"/"+wines_price+":"+temp_quantity+":"+price);
        if (in.readLine().equals("ADDED")) {
            lbl_cart_info.setText("Added to cart");
            sleep(1000);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search_wine_page.fxml"));
            Parent root = loader.load();
            search_wine_pageFXController search_wine_page = loader.getController();
            search_wine_page.setUserID(client);
            search_wine_page.setUserType(type);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("Search Wine");
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
}
