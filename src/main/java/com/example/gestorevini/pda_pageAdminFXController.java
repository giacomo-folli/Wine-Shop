package com.example.gestorevini;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class pda_pageAdminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private PrintWriter out;
    private BufferedReader in;
    private String client;
    private String type;
    private ObservableList<PDA> pda_list = FXCollections.observableArrayList();;
    private boolean visibility_pda = false;
    private boolean visibility_info = false;

    @FXML
    private TableView<PDA> pda_table;
    @FXML
    private TableColumn<PDA, String> col1, col2, col3;
    @FXML
    private TableColumn<PDA, Integer> pda_type;
    @FXML
    private TextField c1, c2, c3, c4;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pda_list.clear();
        col1.setCellValueFactory(new PropertyValueFactory<PDA, String>("wineName"));
        col2.setCellValueFactory(new PropertyValueFactory<PDA, String>("wineProducer"));
        col3.setCellValueFactory(new PropertyValueFactory<PDA, String>("quantity"));

        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("GET_PDA");

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            while ((line = in.readLine())!="null") {
                String[] temp = line.split("/");
                int idClient = Integer.parseInt(temp[0]);
                String wineName = temp[1];
                String wineProd = temp[2];
                int wineYear = Integer.parseInt(temp[3]);
                int quantity = Integer.parseInt(temp[4]);
                String notes = temp[5];

                pda_list.add(new PDA(idClient, wineName, wineProd, wineYear, quantity, notes));
                pda_table.setItems(pda_list);
            }

        } catch (Exception e) {
            System.out.println("wineListFXController, " + e);
        }
    }

    @FXML
    public void btn_send_is_clicked() {
        try (Socket s = getSocket()) {
            //TODO: send the data to the courier
        } catch (IOException e) {
            System.out.println("HelpFXCotroller: " + e.getMessage());
        }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    public Socket getSocket() throws IOException { return new Socket("localhost", 1234); }
}
