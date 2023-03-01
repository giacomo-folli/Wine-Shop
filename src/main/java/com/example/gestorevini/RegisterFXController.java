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
        String name = field_nome.getText();
        String surname = field_cognome.getText();
        String cf = field_cf.getText();
        String email = field_email.getText();
        String address = field_res.getText();
        String cell = field_cell.getText();
        String password = field_psd.getText();

        if (name.isEmpty() || surname.isEmpty() || cf.isEmpty() || email.isEmpty() || address.isEmpty() || cell.isEmpty() || password.isEmpty()) {
            System.out.println("Register button pressed");
        }
    }
}
