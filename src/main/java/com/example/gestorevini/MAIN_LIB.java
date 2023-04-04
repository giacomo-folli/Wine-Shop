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
    private Parent root;

    public void getHome(ActionEvent event, String client, String type) throws IOException {
        if (type == null) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
            root = loader.load();
            LoggedInFXController LFXC = loader.getController();
            LFXC.setUser(client);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("Home");
        } else {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("logged_in_AE.fxml"));
            root = loader.load();
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
        root = loader.load();
        user_pageFXController UPFX = loader.getController();
        UPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("User Page");
    }

    public void showWines(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("wine_list_page.fxml"));
        root = loader.load();
        wineListFXController WLFXC = loader.getController();
        WLFXC.SetUserID(client);
        WLFXC.SetUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Search Wine");
    }

    public void getHelp(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help_page.fxml"));
        root = loader.load();
        helpFXController helpFX = loader.getController();
        helpFX.setClient(client);
        helpFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Help");
    }

    public void getPurchases(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("purchases_page.fxml"));
        root = loader.load();
        purchasesFXController purchases_page = loader.getController();
        purchases_page.setClient(client);
        purchases_page.setUserType(type);
        purchases_page.setTableView();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Purchases");
    }

    public void searchWine(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("search_wine_page.fxml"));
        root = loader.load();
        search_wine_pageFXController search_wine_page = loader.getController();
        search_wine_page.setUserID(client);
        search_wine_page.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Search Wine");
    }

    public void getCart(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("cart_page.fxml"));
        root = loader.load();
        cart_pageFXController CPFX = loader.getController();
        CPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Cart");
    }

    public void getNotifications(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("notifications_page.fxml"));
        root = loader.load();
        notifications_pageFXController NPFX = loader.getController();
        NPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Notifications");
    }

    public void getReport(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("report_page.fxml"));
        root = loader.load();
        report_pageFXController RPFX = loader.getController();
        RPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Report");
    }

    public void getMail(ActionEvent event, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("pda_page_admin.fxml"));
        root = loader.load();
        pda_pageAdminFXController PPFX = loader.getController();
        PPFX.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Support");
    }

    public void getUserADMIN(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("user_page_admin.fxml"));
        root = loader.load();
        user_page_adminFXController UPFX = loader.getController();
        UPFX.setUserType(type);
        UPFX.setClient(client);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("User Page");
    }

    public void getClientADMIN(ActionEvent event, String type, String client) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("client_page_admin.fxml"));
        root = loader.load();
        client_page_adminFXController CPFX = loader.getController();
        CPFX.setUserType(type);
        CPFX.setClient(client);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Client List");
    }

    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }
}