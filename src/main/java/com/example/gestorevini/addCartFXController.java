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
    private BufferedReader in;
    private PrintWriter out;
    private String name_wine;
    private String user_buyer;
    private int max_quantity = 0;
    private int temp_quantity = 1;
    private int price;
    private int tot_price;
    private int temp_discount = 0;
    private int discounted_price;
    private int quantity;

    @FXML
    public Label lbl_cart_info, lbl_discount, lbl_tot_price, lbl_max1;
    @FXML
    private Spinner<Integer> spin_quantity;
    @FXML
    private Button btn_send_PDA;

    public void setUserID(String c) { client = c; }
    public void setUserType(String c) { type = c; }
    public void setPrice(int i) {
        price = i;
    }
    public void setLbl_cart_info(String nameWine, String nameProducer) {
        name_wine = nameWine;
        String text = "You are buying " + name_wine + " from " + nameProducer;
        lbl_cart_info.setText(text);
    }
    private int disc_price(int price, int discount) {
        return price - (price * discount) / 100;
    }
    public void setMaxQuantity(int a) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, a, 1);
        spin_quantity.setValueFactory(valueFactory);
        max_quantity = a;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getDiscount();
        tot_price = 15 * temp_quantity;
        discounted_price = disc_price(tot_price, temp_discount);
        lbl_tot_price.setText(discounted_price + "€");

        spin_quantity.setOnMouseClicked((MouseEvent event) -> {
            getDiscount();
            temp_quantity = spin_quantity.getValue();
            tot_price = price * temp_quantity;
            discounted_price = disc_price(tot_price, temp_discount);
            lbl_tot_price.setText(discounted_price + "€");
        });

        spin_quantity.setOnMouseClicked((MouseEvent event) -> {
            if (spin_quantity.getValue() == max_quantity) {
                lbl_max1.setText("Send a PDA to buy more wine");
                btn_send_PDA.setDisable(false);
            }
            else {
                lbl_max1.setText("");
                btn_send_PDA.setDisable(true);
            }
        });
    }

    private void getDiscount() {
        try (Socket s = lib.getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("CHECK_DISCOUNTS");

            String discount = in.readLine();
            String[] discount_array = discount.split("/");

            if (temp_quantity >= 1 && temp_quantity < 7) {
                lbl_discount.setText(discount_array[0] + "%");
                temp_discount = Integer.parseInt(discount_array[0]);
            }
            else if (temp_quantity >= 7 && temp_quantity < 15) {
                lbl_discount.setText(discount_array[1] + "%");
                temp_discount = Integer.parseInt(discount_array[1]);
            }
            else if (temp_quantity >= 15 && temp_quantity < 20) {
                lbl_discount.setText(discount_array[2] + "%");
                temp_discount = Integer.parseInt(discount_array[2]);
            }
            else if (temp_quantity >= 20) {
                lbl_discount.setText(discount_array[3] + "%");
                temp_discount = Integer.parseInt(discount_array[3]);
            }
            else
                lbl_discount.setText("0%");
        } catch (Exception e) { System.out.println("addCartFXC, " + e); }
    }

    @FXML
    private void btn_add_to_cart_clicked(ActionEvent event)
    {
        try (Socket s = lib.getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            user_buyer = client;
            quantity = spin_quantity.getValue();

            out.println("ADD_TO_CART");
            out.println(name_wine + "/" + client + "/" + quantity + "/" + discounted_price);
            if (in.readLine().equals("ADDED")) {
                lbl_cart_info.setText("Added to cart");
                lib.searchWine(event, type, client);
            }
        } catch (Exception e) { System.out.println("addCartFXC, " + e); }
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
    @FXML
    public void btn_send_to_PDA_clicked(ActionEvent event) throws IOException  { lib.getHelp(event, type, client); }
}