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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInAEFXController implements Initializable {
    private String client;
    private String type = MainApplicationFXController.getUserTYPE();

    @FXML
    private Label lbl_user_type;
    @FXML
    private ImageView img1, img2, img3, img4;
    @FXML
    private Button btn_search_wine, btn_show_wines, btn_show_client, btn_mail, btn_logout, btn_report;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //btn_search_wine.onMouseEnteredProperty().set(e -> mouse_enters_img(img1));
        //btn_search_wine.onMouseExitedProperty().set(e -> mouse_exits_img(img1));
        System.out.println("Logged in as: " + type);

        if (type.equals("admin")) {
            lbl_user_type.setText("ADMIN");
        } else {
            lbl_user_type.setText("EMPLOYEE");
        }
    }

    //private void mouse_enters_img(ImageView a) { a.setVisible(false); }
    //private void mouse_exits_img(ImageView a) { a.setVisible(true); }
    public void setUser(String i) { client = i; }
    public void setUserType(String i) { type = i; }

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
    public void btn_report_is_clicked(ActionEvent event) throws IOException {
        //TODO: open report page
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
        //TODO: open to user page. Admin actions are different from employee actions.
    }

    @FXML
    public void btn_search_wine_clicked(ActionEvent event) throws IOException {
        //TODO: maybe add method to choose between search a client or search a wine
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
    public void btn_mail_is_clicked(ActionEvent event) throws IOException {
        //TODO: open to mail page with messages from clients
    }

    @FXML
    public void btn_show_client_clicked(ActionEvent event) throws IOException {
        //TODO: open client list page
    }

}
