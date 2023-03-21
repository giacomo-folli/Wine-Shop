package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInFXController implements Initializable {
    private String client;
    private String type;
    private MAIN_LIB lib = new MAIN_LIB();

    @FXML
    private ImageView img1, img2, img3, img4;
    @FXML
    private Button btn_logout, btn_user, btn_cart, btn_notifications, btn_show_wines, btn_search_wine, btn_get_help, btn_show_purch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_search_wine.onMouseEnteredProperty().set(e -> mouse_enters_img(img1));
        btn_search_wine.onMouseExitedProperty().set(e -> mouse_exits_img(img1));
        btn_show_wines.onMouseEnteredProperty().set(e -> mouse_enters_img(img2));
        btn_show_wines.onMouseExitedProperty().set(e -> mouse_exits_img(img2));
        btn_show_purch.onMouseEnteredProperty().set(e -> mouse_enters_img(img3));
        btn_show_purch.onMouseExitedProperty().set(e -> mouse_exits_img(img3));
        btn_get_help.onMouseEnteredProperty().set(e -> mouse_enters_img(img4));
        btn_get_help.onMouseExitedProperty().set(e -> mouse_exits_img(img4));
    }

    private void mouse_enters_img(ImageView a) { a.setVisible(false); }
    private void mouse_exits_img(ImageView a) { a.setVisible(true); }
    public void setUser(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @FXML
    public void btn_search_wine_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_wine_page.fxml"));
        Parent root = loader.load();
        search_wine_pageFXController search_wine_page = loader.getController();
        search_wine_page.setUserID(client);
        search_wine_page.setUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Search Wine");
    }

    @FXML
    private void btn_show_wines_clicked(ActionEvent event) throws IOException {
        //TODO: maybe add way to change wine's data
        FXMLLoader loader = new FXMLLoader(getClass().getResource("wine_list_page.fxml"));
        Parent root = loader.load();
        wineListFXController WLFXC = loader.getController();
        WLFXC.SetUserID(client);
        WLFXC.SetUserType(type);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Search Wine");
    }

    @FXML
    public void btn_help_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help_page.fxml"));
        Parent root = loader.load();
        //send username to purchase list page
        helpFXController helpFX = loader.getController();
        helpFX.setClient(client);
        helpFX.setUserType(type);
        //continue with the scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Help");
    }

    @FXML
    public void btn_show_purch_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("purchases_page.fxml"));
        Parent root = loader.load();
        //send username to purchase list page
        purchasesFXController purchases_page = loader.getController();
        purchases_page.setClient(client);
        purchases_page.setUserType(type);
        purchases_page.setTableView();
        //continue with the scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Purchases");
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
