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
import java.util.ResourceBundle;
import java.net.URL;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class buyInfoFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private String type;
    private String client;
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;
    private String name_wine;
    private int temp_quantity;
    private int tot_price;
    private int price;

    @FXML
    private Button btn_home;
    @FXML
    private TextField txt_name, txt_number, txt_cvv, txt_exp;
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
    private void btn_buy_wine_is_clicked() throws IOException, InterruptedException {
        if (txt_name.getText().isEmpty() || txt_number.getText().isEmpty() || txt_cvv.getText().isEmpty() || txt_exp.getText().isEmpty()) {
            lbl_cart_info.setText("Please fill all the fields");
        } else {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("BUY_WINE");
            out.println(client+"/"+name_wine+"/"+temp_quantity+"/"+tot_price+"/"+txt_name.getText()+"/"+txt_number.getText());
            if (in.readLine().equals("SUCCESS")) {
                lbl_cart_info.setText("Purchase successful");
                sleep(2000);
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
                Stage window = (Stage) btn_home.getScene().getWindow();
                window.setScene(new Scene(fxmlLoader.load()));
                window.setTitle("Home");
            } else if (in.readLine().equals("FAILED")) {
                lbl_cart_info.setText("Something went wrong");
            }
        }
    }

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type); }
}
