package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFXController implements Initializable {

    @FXML
    private TextField field_nome;

    @FXML
    private TextField field_cognome;

    @FXML
    private TextField field_email;

    @FXML
    private TextField field_cf;

    @FXML
    private TextField field_res;

    @FXML
    private TextField field_cell;

    @FXML
    private TextField field_psd;

    @FXML
    private Button btn_register;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Socket s = getSocket();
        System.out.println("start");
    }

    @FXML
    private void register_btn_is_clicked() {
        if (field_nome.getText().isEmpty()) {
            System.out.println("Register button");
        }
    }
}
