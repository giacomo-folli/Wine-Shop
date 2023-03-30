package com.example.gestorevini;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class list_pda_pageFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<PDA> list = FXCollections.observableArrayList();
    private PDA temp_pda;
    private String client;
    private String type;
    private String search_type = "SEARCH_WINE_NAME";

    @FXML
    private TableView<PDA> search_table;
    @FXML
    private TableColumn<PDA, String> name_col, prod_col, origin_col, avl_col;
    @FXML
    private TableColumn<PDA, Integer> year_col, price_col;

    public void setUserID(String c) { client = c; }
    public void setUserType(String c) { type = c; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.clear();
        name_col.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        prod_col.setCellValueFactory(new PropertyValueFactory<>("wineName"));
        origin_col.setCellValueFactory(new PropertyValueFactory<>("wineProducer"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        avl_col.setCellValueFactory(new PropertyValueFactory<>("notes"));
        search_table.getItems().clear();
        list.clear();

        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("GET_PDA");

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            while ((line = in.readLine())!="null") {
                String[] temp = line.split("/");
                int idClient = Integer.parseInt(temp[0]);
                String wineName = temp[1];
                String wineProd = temp[2];
                int wineYear = Integer.parseInt(temp[3]);
                int quantity = Integer.parseInt(temp[4]);
                String notes = temp[5];

                list.add(new PDA(idClient, wineName, wineProd, wineYear, quantity, notes));
                search_table.setItems(list);
            }

        } catch (Exception t) { System.out.println("PDA_list, " + t); }

        search_table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount()!=0)
                temp_pda = search_table.getSelectionModel().getSelectedItem();
        });
    }

    @FXML
    public void btn_forward_to_is_clicked() {
        //...
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type); }

    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }
}
