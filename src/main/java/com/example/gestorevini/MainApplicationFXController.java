package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainApplicationFXController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Socket s = getSocket();
        System.out.println("start");
    }

    @FXML
    private void btn_login_is_clicked() {
        System.out.println("Login button is clicked");
    }

    @FXML
    private void btn_register_is_clicked() throws IOException {

        System.out.println("Register button is clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("register.fxml"));
        Stage window = (Stage) btn_register.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));

    }


}
