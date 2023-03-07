package com.example.gestorevini;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class wineListFXController implements Initializable {
    private BufferedReader in;
    private PrintWriter out;

    @FXML
    private TableView<Wine> table_view;
    @FXML
    private TableColumn<Wine, String> name_col;
    @FXML
    private TableColumn<Wine, String> prod_col;
    @FXML
    private TableColumn<Wine, String> origin_col;
    @FXML
    private TableColumn<Wine, Integer> year_col;
    @FXML
    private TableColumn<Wine, String> notes_col;
    @FXML
    private TableColumn<Wine, String> grape_col;
    @FXML
    private TableColumn<Wine, String> price_col;
    //@FXML
    //private TableColumn<Wine, String> avl_col;

    ObservableList<Wine> list = FXCollections.observableArrayList(
            new Wine("Gutturnio", "Tommaso Zorzi", "Francia", 2001, "...", "Bianchi", "30£"),
            new Wine("Gatto", "Stonks Datome", "Francia", 2001, "...", "Bianchi", "30£"),
            new Wine("Colori", "Luca Zzo", "Francia", 2001, "...", "Bianchi", "30£")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*{
            try (Socket s = getSocket()) {
                out = new PrintWriter(s.getOutputStream(), true);
                out.println("SHOW_WINES");

                in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                while (!in.readLine().equals("/DONE")) {
                    String line = in.readLine();
                    System.out.println(line);
                }

                //String[] wine_list = {"","","","","","","","","",""};

            } catch (Exception e) {
                System.out.println("wineListFXController, " + e);
            }
        }*/

        name_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("nome"));
        prod_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("produttore"));
        origin_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("provenienza"));
        year_col.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("anno"));
        notes_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("note"));
        grape_col.setCellValueFactory(new PropertyValueFactory<>("vitigniProvenienza"));
        price_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("prezzo"));
        //avl_col.setCellValueFactory(new PropertyValueFactory<Wine, String>("Avail"));

        table_view.setItems(list);
    }

    private Socket getSocket() throws Exception {
        return new Socket("localhost", 1234);
    }

}
