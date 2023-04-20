package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class MAIN_LIB {
    private Parent root;
    Stage window;

    public void getHome(ActionEvent event, String client, String type) throws IOException {
        if (type.equals("client")) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
            root = loader.load();
            LoggedInFXController LFXC = loader.getController();
            LFXC.setUser(client);
            LFXC.setUserType(type);
            window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setResizable(false);
            window.setTitle("Home");
        } else if (type.equals("admin") || type.equals("employee")) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("logged_in_AE.fxml"));
            root = loader.load();
            LoggedInAEFXController LAEFX = loader.getController();
            LAEFX.setUser(client);
            LAEFX.setUserType(type);
            window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setResizable(false);
            window.setTitle("Home");
        } else {
            System.out.println("Error: Invalid user type");
        }
    }

    public void getLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setResizable(false);
        window.setTitle("Login");
    }

    public void getUser(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("user_page.fxml"));
        root = loader.load();
        user_pageFXController UPFX = loader.getController();
        UPFX.setUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("User Page");
    }

    public void showWines(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("wine_list_page.fxml"));
        root = loader.load();
        wineListFXController WLFXC = loader.getController();
        WLFXC.SetUserID(client);
        WLFXC.SetUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Search Wine");
    }

    public void getHelp(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help_page.fxml"));
        root = loader.load();
        helpFXController helpFX = loader.getController();
        helpFX.setClient(client);
        helpFX.setUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Help");
    }

    public void getPurchases(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("purchases_page.fxml"));
        root = loader.load();
        purchasesFXController purchases_page = loader.getController();
        purchases_page.setClient(client);
        purchases_page.setUserType(type);
        purchases_page.setTableView();
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Purchases");
    }

    public void searchWine(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("search_wine_page.fxml"));
        root = loader.load();
        search_wine_pageFXController search_wine_page = loader.getController();
        search_wine_page.setUserID(client);
        search_wine_page.setUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Search Wine");
    }

    public void getCart(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("cart_page.fxml"));
        root = loader.load();
        cart_pageFXController CPFX = loader.getController();
        CPFX.setUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Cart");
    }

    public void getNotifications(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("notifications_page.fxml"));
        root = loader.load();
        notifications_pageFXController NPFX = loader.getController();
        NPFX.setUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Notifications");
    }

    public void getReport(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("report_page.fxml"));
        root = loader.load();
        report_pageFXController RPFX = loader.getController();
        RPFX.setUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Report");
    }

    public void getMail(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("pda_page_admin.fxml"));
        root = loader.load();
        pda_pageAdminFXController PPFX = loader.getController();
        PPFX.setUserType(type);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Support");
    }

    public void getAlertADMIN(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("alert_page_admin.fxml"));
        root = loader.load();
        alert_pageAdminFXController APFX = loader.getController();
        APFX.setUserType(type);
        APFX.setClient(client);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Alerts");
    }

    public void getUserADMIN(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("user_page_admin.fxml"));
        root = loader.load();
        user_page_adminFXController UPFX = loader.getController();
        UPFX.setUserType(type);
        UPFX.setClient(client);
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("User Page");
    }

    public void getClientADMIN(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("client_page_admin.fxml"));
        root = loader.load();
        client_page_adminFXController CPFX = loader.getController();
        CPFX.setUserType(type);
        CPFX.setClient(client);
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Client List");
    }

    public void getDiscounts(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("discounts_page_admin.fxml"));
        root = loader.load();
        discounts_page_adminFXController DPFX = loader.getController();
        DPFX.setUserType(type);
        DPFX.setUserID(client);
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Discounts");
    }

    public Socket getSocket() throws Exception { return new Socket("localhost", 1234); }
}