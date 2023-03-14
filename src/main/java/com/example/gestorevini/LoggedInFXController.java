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
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String client;

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

    @FXML
    private void btn_show_wines_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("wine_list_page.fxml"));
        Stage window = (Stage) btn_show_wines.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Wine List");
    }

    @FXML
    public void btn_logout_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Stage window = (Stage) btn_logout.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Login");
    }

    @FXML
    public void btn_user_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_page.fxml"));
        Stage window = (Stage) btn_user.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("User Page");
    }

    @FXML
    public void btn_cart_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cart_page.fxml"));
        Stage window = (Stage) btn_cart.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Cart");
    }

    @FXML
    public void btn_notifications_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("notifications_page.fxml"));
        Stage window = (Stage) btn_notifications.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Notifications");
    }

    @FXML
    public void btn_search_wine_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_wine_page.fxml"));
        Parent root = loader.load();
        search_wine_pageFXController search_wine_page = loader.getController();
        search_wine_page.setUserID(client);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Search Wine");
    }

    @FXML
    public void btn_help_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("help_page.fxml"));
        Stage window = (Stage) btn_get_help.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Help");
    }

    @FXML
    public void btn_show_purch_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("purchases_page.fxml"));
        Stage window = (Stage) btn_show_purch.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Purchase");
    }

}
