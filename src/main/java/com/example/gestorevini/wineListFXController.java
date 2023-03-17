package com.example.gestorevini;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class wineListFXController implements Initializable {
    private BufferedReader in;
    private PrintWriter out;
    private ObservableList<Wine> list = FXCollections.observableArrayList();
    private String client;

    @FXML
    private Button btn_home, btn_logout, btn_cart, btn_notifications, btn_user;
    @FXML
    private TableView<Wine> table_view;
    @FXML
    private TableColumn<Wine, String> name_col, prod_col, origin_col, notes_col, grape_col;
    @FXML
    private TableColumn<Wine, Integer> year_col, price_col, avl_col;

    public void SetUserID(String i) { client = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("nome"));
        prod_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("produttore"));
        origin_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("provenienza"));
        year_col.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("anno"));
        notes_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("note"));
        grape_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("vitigniProvenienza"));
        price_col.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("prezzo"));
        avl_col.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("num"));

        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            out.println("SHOW_WINES");

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;

            while (!(line = in.readLine()).equals("null")) {
                String[] temp = line.split("/");
                String name = temp[0];
                String prod =temp[1];
                String orig = temp[2];
                String year = temp[3];
                String notes = temp[4];
                String grapes = temp[5];
                int price = Integer.parseInt(temp[6]);
                int num = Integer.parseInt(temp[7]);

                Wine w = new Wine(name, prod, orig, (Integer.parseInt(year)), notes, grapes, price, num);
                list.add(w);
                table_view.getItems().add(w);
            }

        } catch (Exception e) {
            System.out.println("wineListFXController, " + e);
        }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logged_in.fxml"));
        Parent root = loader.load();
        LoggedInFXController LFXC = loader.getController();
        LFXC.setUser(client);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Home");
    }

    private Socket getSocket() throws Exception {
        return new Socket("localhost", 1234);
    }

}
