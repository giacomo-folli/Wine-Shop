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
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class wine_list_adminFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private PrintWriter out;
    private BufferedReader in;
    private String client;
    private String type;
    private ObservableList<Wine> data = FXCollections.observableArrayList();
    private Wine temp_wine;

    @FXML
    private TableView<Wine> wine_table;
    @FXML
    private TableColumn<Wine, String> name, origin, producer, grape;
    @FXML
    private TableColumn<Wine, Integer> quantity, year, price;
    @FXML
    private TextField txt_name, txt_producer, txt_origin, txt_year, txt_notes, txt_grape, txt_price, txt_quantity;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    private void setTable() {
        try (Socket s = getSocket())
        {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("SHOW_WINES");

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            data.clear();
            wine_table.getItems().clear();

            while (!(line = in.readLine()).equals("null")) {
                String[] temp = line.split("/");
                int id = Integer.parseInt(temp[0]);
                String name = temp[1];
                String prod =temp[2];
                String orig = temp[3];
                String year = temp[4];
                String notes = temp[5];
                String grapes = temp[6];
                int price = Integer.parseInt(temp[7]);
                int num = Integer.parseInt(temp[8]);

                Wine w = new Wine(id, name, prod, orig, (Integer.parseInt(year)), notes, grapes, price, num);
                data.add(w);
                wine_table.getItems().add(w);
            }
        } catch (IOException e) { System.out.println("Wine_List_ADMIN, SetTable " + e); }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Wine, String>("nome"));
        producer.setCellValueFactory(new PropertyValueFactory<Wine, String>("produttore"));
        origin.setCellValueFactory(new PropertyValueFactory<Wine, String>("provenienza"));
        year.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("anno"));
        grape.setCellValueFactory(new PropertyValueFactory<Wine, String>("vitigniProvenienza"));
        price.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("prezzo"));
        quantity.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("num"));

        setTable();

        wine_table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount()!=0)
                temp_wine = wine_table.getSelectionModel().getSelectedItem();
        });
    }

    @FXML
    public void btn_send_is_clicked()
    {
        try (Socket s = getSocket())
        {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out.println("ADD_WINE");
            out.println(txt_name.getText()
                    + "/" + txt_producer.getText()
                    + "/" + txt_origin.getText()
                    + "/" + txt_year.getText()
                    + "/" + txt_grape.getText()
                    + "/" + txt_price.getText()
                    + "/" + txt_quantity.getText());

            String result = in.readLine();
            if (result.equals("DONE"))
            {
                temp_wine = null;
                setTable();
            } else { System.out.println("ERROR MESSAGE RECEIVED FROM SERVER"); }
        } catch (Exception e) { System.out.println("userPageADMIN, SendBTN: " + e.getMessage()); }
    }

    @FXML
    public void btn_update_is_clicked()
    {
        try (Socket s = getSocket())
        {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("UPDATE_WINE");
            String temp_data = temp_wine.getIDWine()
                    + "/" + txt_name.getText()
                    + "/" + txt_producer.getText()
                    + "/" + txt_origin.getText()
                    + "/" + txt_year.getText()
                    + "/" + txt_grape.getText()
                    + "/" + txt_price.getText()
                    + "/" + txt_quantity.getText();
            out.println(temp_data);

            String result = in.readLine();
            if (result.equals("DONE"))
            {
                temp_wine = null;
                sleep(500);
                setTable();
            } else { System.out.println("ERROR MESSAGE RECEIVED FROM SERVER"); }
        } catch (Exception e) { System.out.println("userPageADMIN, SendBTN: " + e.getMessage()); }
    }

    @FXML
    public void btn_delete_is_clicked()
    {
        try (Socket s = getSocket())
        {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out.println("DELETE_WINE");
            out.println(temp_wine.getIDWine());

            //this way client waits confirm from server
            String result = in.readLine();
            if (result.equals("DONE"))
            {
                temp_wine = null;
                setTable();
            } else { System.out.println("ERROR MESSAGE RECEIVED FROM SERVER"); }
        } catch (Exception e) { System.out.println("userPageADMIN, DeleteBTN: " + e.getMessage()); }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }
    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    public Socket getSocket() throws IOException { return new Socket("localhost", 1234); }
}