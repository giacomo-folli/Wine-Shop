package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainApplicationFXController implements Initializable {
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/wineshop";
    private static final String LOGIN = "root";
    final String PASSWORD = "";

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_register;

    public void initialize(URL url, ResourceBundle resourceBundle) { //TODO: implement your JavaFX initialization code here
        try (Socket s = new Socket("localhost", 1234)) {
            System.out.println("Socket port: " + s.getPort());
        } catch (Exception e) { System.out.println(e); }
    }

    @FXML
    private void btn_login_is_clicked() { //TODO: implement your LOGIN button handler here
        String username = this.username.getText();
        String password = this.password.getText();

        try (
                Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
                Statement stmt = conn.createStatement();
                ) {
            String query = "SELECT PWD FROM client WHERE client.USR = '" + username + "';";
            String true_psw;

            try { //LOGIN LOGIC
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next()){
                    true_psw = rs.getString("PWD");
                    System.out.println(true_psw + " " + password + " " + username);

                    if (true_psw.equals(password)) { //start "LoggedIn" JavaFX scene
                        System.out.println(username + " logged in");
                        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
                        Stage window = (Stage) btn_login.getScene().getWindow();
                        window.setScene(new Scene(fxmlLoader.load()));
                    } else {
                        System.out.println("Wrong password");
                    }
                } else {
                    System.out.println("Wrong username");
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            //TODO: handle CLIENT_SIDE exception
        }

    }

    @FXML
    private void btn_register_is_clicked() throws IOException { //TODO: implement your REGISTER button handler here

        System.out.println("Register button is clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("register.fxml"));
        Stage window = (Stage) btn_register.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));

    }

    public Socket getSocket() throws IOException{ //TODO: implement your CLIENT_SOCKET connection
        Socket s = new Socket("localhost", 1234);
        return s;
    }


}
