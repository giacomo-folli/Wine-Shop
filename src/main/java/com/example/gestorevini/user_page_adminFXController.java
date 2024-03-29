package com.example.gestorevini;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class user_page_adminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private StringMatch match = new StringMatch();
    private PrintWriter out;
    private BufferedReader in;
    private String client;
    private String type;
    private ObservableList<Client> data = FXCollections.observableArrayList();
    private Client temp_user;

    @FXML
    private TableView<Client> user_table;
    @FXML
    private TableColumn<Client, String> col1 = new TableColumn<>("ID");
    @FXML
    private TableColumn<Client, String> col2 = new TableColumn<>("Name");
    @FXML
    private TableColumn<Client, String> col3 = new TableColumn<>("Surname");
    @FXML
    private TextField txt_name, txt_surname, txt_user, txt_pwd, txt_email, txt_cell, txt_address, txt_cf;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    private void setTable() {
        try (Socket s = getSocket())
        {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("GET_EMPLOYEE");
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            data.clear();

            while (!Objects.equals(line = in.readLine(), "null")) {
                String[] temp = line.split("/");
                int ID = Integer.parseInt(temp[0]);
                String Name = temp[1];
                String Surname = temp[2];

                data.add(new Client("", ID, Name, Surname, "", "", 0, "", ""));
                user_table.setItems(data);
            }
        } catch (IOException e) { System.out.println("userPageADMIN, SetTable " + e); }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col1.setCellValueFactory(new PropertyValueFactory<>("IDClient"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        setTable();

        user_table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount()!=0)
                temp_user = user_table.getSelectionModel().getSelectedItem();
        });
    }

    @FXML
    public void btn_send_is_clicked() {
        try (Socket s = getSocket())
        {
            if (txt_name.getText().isEmpty() || txt_surname.getText().isEmpty() || txt_user.getText().isEmpty() || txt_pwd.getText().isEmpty() || txt_cell.getText().isEmpty() || txt_cf.getText().isEmpty() || txt_email.getText().isEmpty() || txt_address.getText().isEmpty())
            {
                match.ErrorDialog();
            } else
            {
                if (match.globalUserCheck(txt_name.getText(), txt_surname.getText(), txt_cell.getText(), txt_cf.getText(), txt_email.getText())) {
                    out = new PrintWriter(s.getOutputStream(), true);
                    out.println("ADD_EMPLOYEE");
                    out.println(txt_name.getText() + "/" + txt_surname.getText() + "/" + txt_user.getText() + "/" + txt_pwd.getText() + "/" + txt_email.getText() + "/" + txt_cell.getText() + "/" + txt_address.getText() + "/" + txt_cf.getText());
                    temp_user = null;
                    setTable();
                } else {
                    match.ErrorDialog();
                }
            }
        } catch (Exception e) { System.out.println("userPageADMIN, SendBTN: " + e.getMessage()); }
    }

    @FXML
    public void btn_update_is_clicked() {
        try (Socket s = getSocket())
        {
            if (txt_name.getText().isEmpty() || txt_surname.getText().isEmpty() || txt_user.getText().isEmpty() || txt_pwd.getText().isEmpty() || txt_cell.getText().isEmpty() || txt_cf.getText().isEmpty() || txt_email.getText().isEmpty() || txt_address.getText().isEmpty())
            {
                match.ErrorDialog();
            } else {
                if (match.globalUserCheck(txt_name.getText(), txt_surname.getText(), txt_cell.getText(), txt_cf.getText(), txt_email.getText())) {
                    out = new PrintWriter(s.getOutputStream(), true);
                    out.println("UPDATE_EMPLOYEE");
                    out.println(txt_name.getText() + "/" + txt_surname.getText() + "/" + txt_user.getText() + "/" + txt_pwd.getText() + "/" + txt_email.getText() + "/" + txt_cell.getText() + "/" + txt_address.getText() + "/" + txt_cf.getText() + "/" + temp_user.getIDClient());
                    setTable();
                } else {
                    match.ErrorDialog();
                }
            }

        } catch (Exception e) { System.out.println("userPageADMIN, SendBTN: " + e.getMessage()); }
    }

    @FXML
    public void btn_delete_is_clicked() {
        try (Socket s = getSocket())
        {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out.println("DELETE_EMPLOYEE");
            out.println(temp_user.getIDClient());

            //this way client waits confirm from server
            String result = in.readLine();
            if (result.equals("DONE"))
            {
                temp_user = null;
                setTable();
            } else {
                System.out.println("ERROR MESSAGE RECEIVED FROM SERVER");
            }


        } catch (Exception e) { System.out.println("userPageADMIN, DeleteBTN: " + e.getMessage()); }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    public Socket getSocket() throws IOException { return new Socket("localhost", 1234); }
}