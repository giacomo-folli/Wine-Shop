package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInAEFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private String client;
    private String type = MainApplicationFXController.getUserTYPE();

    @FXML
    private Label lbl_user_type;
    @FXML
    private Button btn_set_discount, btn_report, btn_user;
    @FXML
    private ImageView img_users, img_discounts, img_reports;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //btn_search_wine.onMouseEnteredProperty().set(e -> mouse_enters_img(img1));
        //btn_search_wine.onMouseExitedProperty().set(e -> mouse_exits_img(img1));
        System.out.println("Logged in as: " + type);

        if (type.equals("admin"))
            lbl_user_type.setText("ADMIN");
        else {
            lbl_user_type.setText("EMPLOYEE");
            btn_set_discount.setVisible(false);
            btn_report.setVisible(false);
            btn_user.setVisible(false);
            img_discounts.setOpacity(0.3);
            img_users.setOpacity(0.3);
            img_reports.setOpacity(0.3);
        }
    }

    public void setUser(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @FXML
    private void btn_alert_is_clicked(ActionEvent event) throws IOException { lib.getAlertADMIN(event, type, client); }
    @FXML
    private void btn_show_wines_clicked(ActionEvent event) throws IOException { lib.showWinesADMIN(event, type, client); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }
    @FXML
    public void btn_report_is_clicked(ActionEvent event) throws IOException { lib.getReport(event, type); }
    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUserADMIN(event, type, client); }
    @FXML
    public void btn_mail_is_clicked(ActionEvent event) throws IOException { lib.getMail(event, type); }
    @FXML
    public void btn_show_client_clicked(ActionEvent event) throws IOException { lib.getClientADMIN(event, type, client); }
    @FXML
    public void btn_set_discount_clicked(ActionEvent event) throws IOException { lib.getDiscounts(event, type, client); }
}
