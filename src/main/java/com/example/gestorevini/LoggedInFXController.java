package com.example.gestorevini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInFXController implements Initializable {
    @FXML
    private Button btn_show_wines;
    @FXML
    private Button btn_search_wine;
    @FXML
    private Button btn_get_help;
    @FXML
    private Button btn_show_purch;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //...
    }

    @FXML
    private void btn_show_wines_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("wine_list_page.fxml"));
        Stage window = (Stage) btn_show_wines.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }
}
