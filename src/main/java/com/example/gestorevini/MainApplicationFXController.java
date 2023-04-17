package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainApplicationFXController implements Initializable {
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/wineshop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static String usr;
    private static String user_type;
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
        try {
            Socket s = getSocket();
        } catch (IOException e) {
            System.out.println("MainApplicationFXController, " + e);
        }

        password.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER ) btn_login_is_clicked();
        });
    }

    @FXML
    private void btn_login_is_clicked() { //implement your LOGIN button handler here
        String username = this.username.getText();
        String password = this.password.getText();

        try ( Connection conn = DriverManager.getConnection(DBURL, LOGIN, PASSWORD); Statement stmt = conn.createStatement()) {
            String query = "SELECT PSW FROM clienti WHERE clienti.USR = '" + username + "';";
            String true_psw;

            try { //LOGIN LOGIC
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    true_psw = rs.getString("PSW");

                    if (true_psw.equals(password)) { //if password correct
                        usr = username;
                        String type = "SELECT type FROM clienti WHERE clienti.USR = '" + usr + "';";
                        ResultSet s  = stmt.executeQuery(type);
                        if (s.next()) {
                            user_type = s.getString("type");
                            if (user_type.equals("admin") || user_type.equals("employee")) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("logged_in_AE.fxml"));
                                Parent root = loader.load();
                                //inject username in LoggedInAEFXController
                                LoggedInAEFXController LIAE = loader.getController();
                                LIAE.setUser(usr);
                                LIAE.setUserType(user_type);
                                Stage window = (Stage) btn_login.getScene().getWindow();
                                window.setScene(new Scene(root));
                                window.setTitle("Home");
                                System.out.println(usr + " logged in as admin");
                            } else if (user_type.equals("client")) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("logged_in.fxml"));
                                Parent root = loader.load();
                                //inject username in LoggedInFXController
                                LoggedInFXController loggedIn = loader.getController();
                                loggedIn.setUser(usr);
                                loggedIn.setUserType(user_type);
                                Stage window = (Stage) btn_login.getScene().getWindow();
                                window.setScene(new Scene(root));
                                window.setTitle("Home");
                                System.out.println(usr +" logged in as customer");
                            } else { System.out.println("User type not recognized"); }
                        }
                    } else { System.out.println("Wrong password"); }
                } else { System.out.println("Username doesn't exist"); }
            } catch (Exception e) { System.out.println(e); }
        } catch (Exception e) { System.out.println("MainApplicationFXC, Failed to connect to database"); }
    }

    public static String getUserUSR() { return usr; }
    public static String getUserTYPE() { return user_type; }

    @FXML
    private void btn_register_is_clicked() throws IOException {//implement your REGISTER button handler here
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("register.fxml"));
        Stage window = (Stage) btn_register.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    public Socket getSocket() throws IOException{
        return new Socket("localhost", 1234);
    }
}
