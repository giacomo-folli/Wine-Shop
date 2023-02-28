package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFXController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;



}
