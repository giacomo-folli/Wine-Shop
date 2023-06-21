package com.example.gestorevini;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class client_page_adminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private PrintWriter out;
    private BufferedReader in;
    private String client;
    private String type;
    private ObservableList<Client> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Client> client_table;
    @FXML
    private TableColumn<Client, String> name_col, surn_col, email_col, add_col;
    @FXML
    private TableColumn<Client, Integer> id_col;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.clear();
        id_col.setCellValueFactory(new PropertyValueFactory<Client, Integer>("IDClient"));
        name_col.setCellValueFactory(new PropertyValueFactory<Client, String>("Name"));
        surn_col.setCellValueFactory(new PropertyValueFactory<Client, String>("Surname"));
        email_col.setCellValueFactory(new PropertyValueFactory<Client, String>("Email"));
        add_col.setCellValueFactory(new PropertyValueFactory<Client, String>("Address"));

        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("GET_CLIENT");

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            data.clear();

            while ((line = in.readLine())!="null") {
                String[] temp = line.split("/");
                int ID = Integer.parseInt(temp[0]);
                String Name = temp[1];
                String Surname = temp[2];
                String Email = temp[3];
                String Address = temp[4];

                data.add(new Client("", ID, Name, Surname, "", Email, 0, Address, ""));
                client_table.setItems(data);
            }

        } catch (Exception e) { System.out.println("userPageADMIN, " + e); }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    public Socket getSocket() throws IOException { return new Socket("localhost", 1234); }
}
