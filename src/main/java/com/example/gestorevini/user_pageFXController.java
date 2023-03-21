package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class user_pageFXController implements Initializable {
    private String usr_coockie = MainApplicationFXController.getUserUSR();
    private MAIN_LIB lib = new MAIN_LIB();
    private String type;
    //DB access data
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/wineshop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";

    @FXML
    private Label lbl_name, lbl_surname, lbl_user, lbl_email,  lbl_pwd;
    @FXML
    private TextField txt_user, txt_pwd;

    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD); Statement stmt = conn.createStatement()) {
            //Get user data from DB
            String get_data = "SELECT Name, Surname, Email, PSW FROM clienti WHERE clienti.USR = '" + usr_coockie + "';";
            ResultSet rs = stmt.executeQuery(get_data);
            //Set user data
            if (rs.next()) {
                lbl_name.setText(rs.getString("Name"));
                lbl_surname.setText(rs.getString("Surname"));
                lbl_email.setText(rs.getString("Email"));
                lbl_pwd.setText(rs.getString("PSW"));
                lbl_user.setText(usr_coockie);
            }
        } catch (Exception e) { System.out.println("Failed to connect to database " + e); }
    }

    @FXML
    public void btn_change_pwd_is_clicked() {
        String new_pwd = txt_pwd.getText();
        String query = "UPDATE clienti SET PSW = '" + new_pwd + "' WHERE USR = '" + usr_coockie + "'";
        try {
            if (new_pwd.equals("")) {
                System.out.println("Password cannot be empty");
            } else {
                Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                lbl_pwd.setText(new_pwd);
                System.out.println("Password changed successfully to " + new_pwd);
            }
        } catch (SQLException e) { System.out.println("Change PWD, " + e); }
    }

    @FXML
    public void btn_change_user_is_clicked() {
        String new_user = txt_user.getText();
        String query = "UPDATE clienti SET USR = '" + new_user + "' WHERE USR = '" + usr_coockie + "'";
        try {
            if (new_user.equals("")) {
                System.out.println("Password cannot be empty");
            } else {
                Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                lbl_user.setText(new_user);
                System.out.println("Password changed successfully to " + new_user);
            }
        } catch (SQLException e) { System.out.println("Change USR, " + e); }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, usr_coockie, type); }

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type); }
}
