package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.datatransfer.FlavorEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class discounts_page_adminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private String type;
    private String client;
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    @FXML
    private Spinner<Integer> small_spin, med_spin, large_spin, max_spin;
    @FXML
    private Label lbl1, lbl2, lbl3, lbl4;

    public void setUserID(String c) { client = c; }
    public void setUserType(String c) { type = c; }
    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> vFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 90, 0, 5);
        SpinnerValueFactory<Integer> vFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 90, 0, 5);
        SpinnerValueFactory<Integer> vFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 90, 0, 5);
        SpinnerValueFactory<Integer> vFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 90, 0, 5);
        small_spin.setValueFactory(vFactory1);
        med_spin.setValueFactory(vFactory2);
        large_spin.setValueFactory(vFactory3);
        max_spin.setValueFactory(vFactory4);

        small_spin.setOnMouseClicked((MouseEvent event) -> {
            lbl1.setText(small_spin.getValue() + "% off for 1-6 bottles");
        });

        med_spin.setOnMouseClicked((MouseEvent event) -> {
            lbl2.setText(med_spin.getValue() + "% off for 7-14 bottles");
        });

        large_spin.setOnMouseClicked((MouseEvent event) -> {
            lbl3.setText(large_spin.getValue() + "% off for 15-20 bottles");
        });

        max_spin.setOnMouseClicked((MouseEvent event) -> {
            lbl4.setText(max_spin.getValue() + "% off for >20 bottles");
        });
    }

    @FXML
    private void btn_discounts_is_clicked(ActionEvent event) throws Exception {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("UPDATE_DISCOUNTS");
            out.println(small_spin.getValue() + "/" + med_spin.getValue() + "/" + large_spin.getValue() + "/" + max_spin.getValue());
            lib.getHome(event, type, client);
        } catch (Exception e) { System.out.println("discount_page, " + e); }
    }

    @FXML
    private void btn_home_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }
}
