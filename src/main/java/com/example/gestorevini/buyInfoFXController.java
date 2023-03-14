package com.example.gestorevini;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

public class buyInfoFXController implements Initializable {
    private int max;

    @FXML
    private Button btn_home, btn_logout, btn_user, btn_cart, btn_notifications;
    @FXML
    private TextField txt_name, txt_number, txt_cvv, txt_exp;
    @FXML
    public Label lbl_cart_info, lbl_est_cost;
    @FXML
    private Spinner<Integer> spin_quantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //int current_value = spin_quantity.getValue();
    }

    public void setLbl_cart_info(String text) {
        lbl_cart_info.setText(text);
    }

    public void setMaxQuantity(int a) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max);
        valueFactory.setValue(1);
        spin_quantity.setValueFactory(valueFactory);
    }

    @FXML
    private void btn_buy_wine_is_clicked() {
        if (txt_name.getText().isEmpty() || txt_number.getText().isEmpty() || txt_cvv.getText().isEmpty() || txt_exp.getText().isEmpty()) {
            lbl_cart_info.setText("Please fill all the fields");
        } else {
            lbl_cart_info.setText("Purchase completed");
        }
    }

    @FXML
    public void btn_home_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("logged_in.fxml"));
        Stage window = (Stage) btn_home.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Home");
    }

    @FXML
    public void btn_logout_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Stage window = (Stage) btn_logout.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    public void btn_user_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user_page.fxml"));
        Stage window = (Stage) btn_user.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    public void btn_cart_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cart_page.fxml"));
        Stage window = (Stage) btn_cart.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    public void btn_notifications_is_clicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("notifications_page.fxml"));
        Stage window = (Stage) btn_notifications.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }
}
