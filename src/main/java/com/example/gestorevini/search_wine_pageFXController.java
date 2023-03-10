package com.example.gestorevini;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class search_wine_pageFXController implements Initializable {
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<Wine> list = FXCollections.observableArrayList();

    @FXML
    private Button btn_search;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_cart;
    @FXML
    private Button btn_notifications;
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_home;
    @FXML
    private TableView<Wine> search_table;
    @FXML
    private TableColumn<Wine, String> name_col;
    @FXML
    private TableColumn<Wine, String> prod_col;
    @FXML
    private TableColumn<Wine, String> origin_col;
    @FXML
    private TableColumn<Wine, Integer> year_col;
    @FXML
    private TableColumn<Wine, String> grape_col;
    //@FXML
    //private TableColumn<Wine, Integer> price_col;
    @FXML
    private TableColumn<Wine, Integer> avl_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("nome"));
        prod_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("produttore"));
        origin_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("provenienza"));
        year_col.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("anno"));
        grape_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("vitigniProvenienza"));
        avl_col.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("num"));
        search_table.getItems().clear();
        list.clear();
    }

    @FXML
    public void btn_search_is_clicked() {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String search = txt_search.getText();
            if (search != "") {
                out.println("SEARCH_WINE");
                out.println(search);
                String line;

                while ((line = in.readLine())!="null") {
                    String[] temp = line.split("/");
                    String name = temp[0];
                    String prod = temp[1];
                    String orig = temp[2];
                    String year = temp[3];
                    String grapes = temp[4];
                    int num = Integer.parseInt(temp[5]);

                    list.add(new Wine(name, prod, orig, (Integer.parseInt(year)), "", grapes, 0, num));
                    search_table.setItems(list);
                }
            }
        } catch (Exception t) {
            System.out.println("wineListFXController, " + t);
        }
    }

    @FXML
    public void btn_home_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
        Stage window = (Stage) btn_home.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Home");
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

    private Socket getSocket() throws Exception {
        return new Socket("localhost", 1234);
    }

}
