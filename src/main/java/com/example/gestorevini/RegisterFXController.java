package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private BufferedReader in;
    private PrintWriter out;

    @FXML
    private TextField field_nome, field_cognome, field_email, field_cf, field_res, field_cell, field_psd, field_user;
    @FXML
    private Button btn_register;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Socket s = getSocket();
        System.out.println("start");
    }

    @FXML
    private void register_btn_is_clicked(ActionEvent event) {
        String name = field_nome.getText();
        String surname = field_cognome.getText();
        String cf = field_cf.getText();
        String email = field_email.getText();
        String address = field_res.getText();
        String cell = field_cell.getText();
        String password = field_psd.getText();
        String username = field_user.getText();

        if (username.isEmpty() || name.isEmpty() || surname.isEmpty() || cf.isEmpty() || email.isEmpty() || address.isEmpty() || cell.isEmpty() || password.isEmpty()) {
            System.out.println("Empty field, cannot proceed");
        } else {
            try (Socket s = getSocket()) {
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new PrintWriter(s.getOutputStream(), true);

                out.println("REGISTER");
                out.println(name+"/"+surname+"/"+cf+"/"+email+"/"+address+"/"+cell+"/"+password+"/"+username);

                if (in.readLine().equals("REGISTERED")) {
                    System.out.println("Registration successful");
                    lib.getLogout(event);
                } else {
                    System.out.println("Registration failed");
                }

            } catch (IOException e) { System.out.println("RegisterFX, " + e); }
        }
    }

    @FXML
    private void get_back_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    public Socket getSocket() throws IOException {
        return new Socket("localhost", 1234);
    }
}
