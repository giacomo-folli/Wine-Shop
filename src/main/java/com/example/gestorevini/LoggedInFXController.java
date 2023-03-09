package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInFXController implements Initializable {
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_cart;
    @FXML
    private Button btn_notifications;
    @FXML
    private Button btn_show_wines;
    @FXML
    private Button btn_search_wine;
    @FXML
    private Button btn_get_help;
    @FXML
    private Button btn_show_purch;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //...
    }

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
    public void btn_search_wine_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("search_wine_page.fxml"));
        Stage window = (Stage) btn_search_wine.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
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
        window.setTitle("Purchases");
    }

}
