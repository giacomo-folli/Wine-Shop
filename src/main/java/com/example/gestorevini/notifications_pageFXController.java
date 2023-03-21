package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

public class notifications_pageFXController implements Initializable {
    private String client;
    private String type;
    private MAIN_LIB lib = new MAIN_LIB();

    public void setUser(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(client + ", " + type);
        //TODO: implementare la funzione di notifica(?)
    }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type);}

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type); }
}
