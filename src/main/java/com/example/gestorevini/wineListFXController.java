package com.example.gestorevini;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class wineListFXController implements Initializable {
    private BufferedReader in;
    private PrintWriter out;

    @FXML
    private TableColumn<Wine, Integer> id_col;
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
    @FXML
    private TableColumn<Wine, String> avl_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            //in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out.println("SHOW_WINES");

            //String line;
            //String[] wine_list = {"","","","","","","","","",""};
            //while (in.readLine() != null) { line = in.readLine(); }

            //String message = in.readLine();
            //System.out.println(message);

        } catch (Exception e) { System.out.println("wineListFXController, " + e); }
    }

    private Socket getSocket() throws Exception {
        return new Socket("localhost", 1234);
    }

}
