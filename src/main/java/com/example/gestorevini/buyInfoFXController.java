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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class buyInfoFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private StringMatch match = new StringMatch();
    private String type;
    private String client;
    private ArrayList temp_id;
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;
    private String name_wine;
    private int price;

    @FXML
    private TextField txt_name, txt_number, txt_cvv, txt_exp;
    @FXML
    public Label lbl_cart_info, lbl_est_cost;

    public void setUserID(String c) { client = c; }
    public void setUserType(String c) { type = c; }
    public void setCartID(ArrayList l) { temp_id = l; }
    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }
    public void setPrice(int i) {
        price = i;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //...
    }

    @FXML
    private void btn_buy_wine_is_clicked(ActionEvent event)
    {
        if (txt_name.getText().isEmpty() || txt_number.getText().isEmpty() || txt_cvv.getText().isEmpty() || txt_exp.getText().isEmpty()) //check input fields
        {
            lbl_cart_info.setText("Please fill all the fields");
        } else
        {
            if (match.globalCardCheck(txt_name.getText(), txt_number.getText(), txt_cvv.getText())) //check card info misspellings
            {
                try (Socket s = getSocket())
                {
                    out = new PrintWriter(s.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    for (int i = 0; i < temp_id.size(); i++)
                    {
                        out.println("BUY_WINE");
                        out.println(temp_id.get(i) + "/" + txt_name.getText() + "/" + txt_number.getText());
                        String next;
                        if ((next = in.readLine()) == "DONE")
                            System.out.println(txt_name + " bought");
                    }

                    //get back to main page
                    lib.getHome(event, client, type);

                } catch (Exception e) { System.out.println("buyInfoFX, " + e); }
            } else { match.ErrorDialog(); }
        }
    }

    @FXML
    private void btn_home_is_clicked(ActionEvent event) throws IOException
    {
        lib.getHome(event, client, type);
    }
}
