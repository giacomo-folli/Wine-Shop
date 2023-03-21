package com.example.gestorevini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.Scanner;

public class helpFXController implements Initializable {
    private PrintWriter out;
    private String client;
    private boolean visibility_pda = false;
    private boolean visibility_info = false;

    @FXML
    private Button btn_logout, btn_user, btn_cart, btn_notifications, btn_support, btn_pda, btn_send;
    @FXML
    private AnchorPane pane_pda, pane_contact_info;
    @FXML
    private TextField txt_name, txt_year, txt_notes, txt_producer;
    @FXML
    private Spinner spin_quantity;

    public void setClient(String i) { client = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane_pda.setVisible(visibility_pda);
        pane_contact_info.setVisible(visibility_info);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        valueFactory.setValue(1);
        spin_quantity.setValueFactory(valueFactory);

        btn_support.onActionProperty().set(e -> {
            if (visibility_info) {
                pane_contact_info.setVisible(false);
                visibility_info = false;
            } else {
                pane_contact_info.setVisible(true);
                visibility_info = true;
            }
        });

        btn_pda.onActionProperty().set(e -> {
            if (visibility_pda) {
                pane_pda.setVisible(false);
                visibility_pda = false;
            } else {
                pane_pda.setVisible(true);
                visibility_pda = true;
            }
        });
    }

    @FXML
    public void btn_send_is_clicked() {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            String pda = client + "/" + txt_name.getText() + "/" + txt_year.getText() + "/" + txt_producer.getText() + "/" + txt_notes.getText() + "/" + spin_quantity.getValue();
            out.println("ADD_PDA");
            out.println(pda);
            //Hide the pane when the request is sent
            pane_pda.setVisible(false);
        } catch (IOException e) {
            System.out.println("HelpFXCotroller: " + e.getMessage());
        }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logged_in.fxml"));
        Parent root = loader.load();
        LoggedInFXController LFXC = loader.getController();
        LFXC.setUser(client);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Home");
    }


    @FXML
    public void btn_logout_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Stage window = (Stage) btn_logout.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Login");
    }

    @FXML
    public void btn_user_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_page.fxml"));
        Stage window = (Stage) btn_user.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("User");
    }

    @FXML
    public void btn_cart_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cart_page.fxml"));
        Stage window = (Stage) btn_cart.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Cart");
    }

    @FXML
    public void btn_notifications_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("notifications_page.fxml"));
        Stage window = (Stage) btn_notifications.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Notifications");
    }

    public Socket getSocket() throws IOException {
        return new Socket("localhost", 1234);
    }
}
