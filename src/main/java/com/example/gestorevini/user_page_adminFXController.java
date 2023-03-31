package com.example.gestorevini;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class user_page_adminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private PrintWriter out;
    private BufferedReader in;
    private String client;
    private String type;
    private ObservableList<String[]> data = FXCollections.observableArrayList();
    private boolean visibility_pda = false;
    private boolean visibility_info = false;

    @FXML
    private TableView<String[]> pda_table;
    @FXML
    private TableColumn<String[], String> col1 = new TableColumn<>("ID");
    @FXML
    private TableColumn<String[], String> col2 = new TableColumn<>("Name");
    @FXML
    private TableColumn<String[], String> col3 = new TableColumn<>("Surname");
    @FXML
    private TextField c1, c2, c3, c4;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.clear();
        col1.setCellValueFactory(stringCellDataFeatures -> new SimpleStringProperty(stringCellDataFeatures.getValue()[0]));
        col2.setCellValueFactory(stringCellDataFeatures -> new SimpleStringProperty(stringCellDataFeatures.getValue()[1]));
        col3.setCellValueFactory(stringCellDataFeatures -> new SimpleStringProperty(stringCellDataFeatures.getValue()[2]));

        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("GET_EMPLOYEE");

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            while ((line = in.readLine())!="null") {
                String[] temp = line.split("/");
                String ID = temp[0];
                String Name = temp[1];
                String Surname = temp[2];

                data.add(new String[]{ID, Name, Surname});
                pda_table.setItems(data);
            }

            pda_table.getColumns().addAll(col1, col2, col3);

        } catch (Exception e) {
            System.out.println("userPageADMIN, " + e);
        }
    }

    @FXML
    public void btn_send_is_clicked() {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("ADD_EMPLOYEE");
            out.println(c1.getText() + "/" + c2.getText() + "/" + c3.getText() + "/" + c4.getText());

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;



            pda_table.getColumns().addAll(col1, col2, col3);
        } catch (IOException e) {
            System.out.println("userPageADMIN, sendBTN: " + e.getMessage());
        }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    public Socket getSocket() throws IOException { return new Socket("localhost", 1234); }
}
