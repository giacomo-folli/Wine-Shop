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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInFXController implements Initializable {
    private String client;
    private String type;
    private BufferedReader in;
    private PrintWriter out;
    private MAIN_LIB lib = new MAIN_LIB();

    @FXML
    private ImageView img1, img2, img3, img4;
    @FXML
    private Button btn_show_wines, btn_search_wine, btn_get_help, btn_show_purch;
    @FXML
    private Label lbl1, lbl2, lbl3, lbl4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_search_wine.onMouseEnteredProperty().set(e -> mouse_enters_img(img1));
        btn_search_wine.onMouseExitedProperty().set(e -> mouse_exits_img(img1));
        btn_show_wines.onMouseEnteredProperty().set(e -> mouse_enters_img(img2));
        btn_show_wines.onMouseExitedProperty().set(e -> mouse_exits_img(img2));
        btn_show_purch.onMouseEnteredProperty().set(e -> mouse_enters_img(img3));
        btn_show_purch.onMouseExitedProperty().set(e -> mouse_exits_img(img3));
        btn_get_help.onMouseEnteredProperty().set(e -> mouse_enters_img(img4));
        btn_get_help.onMouseExitedProperty().set(e -> mouse_exits_img(img4));

        check_discount();
    }

    private void mouse_enters_img(ImageView a) { a.setVisible(false); }
    private void mouse_exits_img(ImageView a) { a.setVisible(true); }
    public void setUser(String i) { client = i; }
    public void setUserType(String i) { type = i; }
    private Socket getSocket() throws IOException { return new Socket("localhost", 1234); }

    private void check_discount() {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new java.io.InputStreamReader(s.getInputStream()));

            out.println("CHECK_DISCOUNTS");
            String line = in.readLine();
            String[] discounts = line.split("/");
            if (discounts[0] == "0" && discounts[1] == "0" && discounts[2] == "0" && discounts[3] == "0") {
                lbl1.setText("Nessun sconto disponibile");
                lbl2.setText("");
                lbl3.setText("");
                lbl4.setText("");
            } else {
                lbl1.setText(discounts[0] + "% off for 1-6 bottles");
                lbl2.setText(discounts[1] + "% off for 7-14 bottles");
                lbl3.setText(discounts[2] + "% off for 15-20 bottles");
                lbl4.setText(discounts[3] + "% off for >20 bottles");
            }
        } catch (IOException e) { System.out.println("LoggedIn, check_discounts: " + e); }
    }

    @FXML
    public void btn_search_wine_clicked(ActionEvent event) throws IOException { lib.searchWine(event, type, client); }
    @FXML
    private void btn_show_wines_clicked(ActionEvent event) throws IOException { lib.showWines(event, type, client); }
    @FXML
    public void btn_help_clicked(ActionEvent event) throws IOException { lib.getHelp(event, type, client); }
    @FXML
    public void btn_show_purch_clicked(ActionEvent event) throws IOException { lib.getPurchases(event, type, client); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }
    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }
    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }
    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type, client); }
}