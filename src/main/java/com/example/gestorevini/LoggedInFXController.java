package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInFXController implements Initializable {
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
    private void btn_wine_list_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("wine_list_page.fxml"));
        Stage window = (Stage) btn_wine_list.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

}
