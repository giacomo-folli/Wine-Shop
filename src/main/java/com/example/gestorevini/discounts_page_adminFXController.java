package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class discounts_page_adminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private String type;
    private String client;
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;


    @FXML
    private TextField txt_name, txt_number, txt_cvv, txt_exp;
    @FXML
    public Label lbl_cart_info, lbl_est_cost;

    public void setUserID(String c) { client = c; }
    public void setUserType(String c) { type = c; }
    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //...
    }

    @FXML
    private void btn_home_clicked(ActionEvent event) throws IOException { lib.getHome(event, type, client); }
}
