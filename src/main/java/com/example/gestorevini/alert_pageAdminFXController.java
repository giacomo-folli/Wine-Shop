package com.example.gestorevini;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class alert_pageAdminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private PrintWriter out;
    private BufferedReader in;
    private String client;
    private String type;
    private ObservableList<AlertMessage> alert_Message_list = FXCollections.observableArrayList();
    private AlertMessage alert_Message_temp;

    @FXML
    private TableView<AlertMessage> alert_table;
    @FXML
    private TableColumn<AlertMessage, String> col1, col2, col3;
    @FXML
    private TextField txt_name, txt_number, txt_notes;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert_Message_list.clear();
        col1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Date"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Availability"));

        alert_table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount()!=0)
                alert_Message_temp = alert_table.getSelectionModel().getSelectedItem();
        });

        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("GET_ALERT");

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            while ((line = in.readLine())!="null") {
                String[] temp = line.split("/");
                int ID = Integer.parseInt(temp[0]);
                int ID_Wine = Integer.parseInt(temp[1]);
                String Name = temp[2];
                String Date = temp[3];
                int Avail = Integer.parseInt(temp[4]);

                alert_Message_list.add(new AlertMessage(ID, ID_Wine, Name, Date, Avail));
                alert_table.setItems(alert_Message_list);
            }
        } catch (Exception e) { System.out.println("AlertPage_INITIALIZE, " + e); }
    }

    @FXML
    public void btn_send_is_clicked(ActionEvent event) {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            String data = alert_Message_temp.getID() + "/" + txt_name.getText() + "/" + txt_number.getText() + "/" + txt_notes.getText();
            out.println("DELETE_ALERT");
            out.println(alert_Message_temp.getID());
            out.println("SET_QUANTITY");
            out.println(alert_Message_temp.getID_Wine());
            out.println(txt_number.getText());
            lib.getAlertADMIN(event, type, client);
        } catch (Exception e) { System.out.println("AlertPage_SEND_BTN, btn_send " + e); }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    public Socket getSocket() throws IOException { return new Socket("localhost", 1234); }
}
