package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainApplicationFXController implements Initializable {
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/wineshop";
    private static final String LOGIN = "root";
    final String PASSWORD = "";
    private BufferedReader in;
    private PrintWriter out;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_register;

    public void initialize(URL url, ResourceBundle resourceBundle) { //JavaFX initialization code here
        try (Socket s = getSocket()) {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String message = in.readLine();
            System.out.println(message);

        } catch (Exception e) { System.out.println("MainApplicationFXController, " + e); }
    }

    @FXML
    private void btn_login_is_clicked() { //TODO: implement your LOGIN button handler here
        String username = this.username.getText();
        String password = this.password.getText();

        try (
                Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
                Statement stmt = conn.createStatement();)
        {
            String query = "SELECT PSW FROM clienti WHERE clienti.USR = '" + username + "';";
            String true_psw;

            try { //LOGIN LOGIC
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    true_psw = rs.getString("PSW");

                    if (true_psw.equals(password)) { //start "LoggedIn" JavaFX scene
                        System.out.println(username + " logged in");
                        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
                        Stage window = (Stage) btn_login.getScene().getWindow();
                        window.setScene(new Scene(fxmlLoader.load()));
                    } else { System.out.println("Wrong password"); }
                } else { System.out.println("Username doesn't exist"); }
            } catch (Exception e) { System.out.println(e); }
        } catch (Exception e) { System.out.println("Failed to connect to database"); }
    }

    @FXML
    private void btn_register_is_clicked() throws IOException {//implement your REGISTER button handler here
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("register.fxml"));
        Stage window = (Stage) btn_register.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    public Socket getSocket() throws IOException{
        Socket s = new Socket("localhost", 1234);
        return s;
    }

}
