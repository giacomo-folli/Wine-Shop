package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();

    @FXML
    private TextField field_nome, field_cognome, field_email, field_cf, field_res, field_cell, field_psd;
    @FXML
    private Button btn_register;
    @FXML
    private ImageView img_getBack;

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
            System.out.println("Empty field, cannot proceed");
        } else {
            //TODO: send data to server
        }
    }

    @FXML
    private void getBack_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }
}
