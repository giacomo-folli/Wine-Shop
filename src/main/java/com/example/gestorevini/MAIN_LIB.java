package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;

public class MAIN_LIB {
    public void getHome(ActionEvent event, String client, String type) throws IOException {
        if (type == null) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
            Parent root = loader.load();
            LoggedInFXController LFXC = loader.getController();
            LFXC.setUser(client);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("Home");
        } else {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("logged_in_AE.fxml"));
            Parent root = loader.load();
            LoggedInAEFXController LAEFX = loader.getController();
            LAEFX.setUser(client);
            LAEFX.setUserType(type);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("Home");
        }
    }

    public void getLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Login");
    }

    public void getUser(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("user_page.fxml"));
        Parent root = loader.load();
        user_pageFXController UPFX = loader.getController();
        UPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("User Page");
    }

    public void getCart(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("cart_page.fxml"));
        Parent root = loader.load();
        cart_pageFXController CPFX = loader.getController();
        CPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Cart");
    }

    public void getNotifications(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("notifications_page.fxml"));
        Parent root = loader.load();
        notifications_pageFXController NPFX = loader.getController();
        NPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Notifications");
    }

    public void getReport(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("report_page.fxml"));
        Parent root = loader.load();
        report_pageFXController RPFX = loader.getController();
        RPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Report");
    }

    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }
}
