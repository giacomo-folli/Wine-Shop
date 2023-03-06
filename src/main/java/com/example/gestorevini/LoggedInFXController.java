package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInFXController implements Initializable {
    //public MainApplicationFXController mfxc = new MainApplicationFXController();
    //public Socket socket = mfxc.getSocket();
    //private PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    //private BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    @FXML
    private ImageView btn_logout;
    @FXML
    private ImageView btn_wine_list;
    @FXML
    private ImageView btn_search_wine;
    @FXML
    private ImageView btn_help;
    @FXML
    private ImageView btn_info;
    @FXML
    private ImageView btn_cart;
    @FXML
    private ImageView btn_user;
    @FXML
    private ImageView btn_show_purch;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //...
    }

    @FXML
    private void btn_wine_list_clicked() {
        System.out.println("SHOW_WINES");
    }

}
