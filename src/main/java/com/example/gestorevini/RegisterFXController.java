package com.example.gestorevini;

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
    private void getBack_is_clicked() throws IOException {
        System.out.println("Get back button pressed");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Stage window = (Stage) img_getBack.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }
}
