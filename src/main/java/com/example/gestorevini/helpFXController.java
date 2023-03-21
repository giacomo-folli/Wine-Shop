package com.example.gestorevini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ResourceBundle;
import java.net.URL;

public class helpFXController implements Initializable {
    private MAIN_LIB lib = new MAIN_LIB();
    private PrintWriter out;
    private String client;
    private String type;
    private boolean visibility_pda = false;
    private boolean visibility_info = false;

    @FXML
    private Button btn_support, btn_pda;
    @FXML
    private AnchorPane pane_pda, pane_contact_info;
    @FXML
    private TextField txt_name, txt_year, txt_notes, txt_producer;
    @FXML
    private Spinner spin_quantity;

    public void setClient(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("HelpFXController: " + client + " " + type);
        pane_pda.setVisible(visibility_pda);
        pane_contact_info.setVisible(visibility_info);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        valueFactory.setValue(1);
        spin_quantity.setValueFactory(valueFactory);

        btn_support.onActionProperty().set(e -> {
            if (visibility_info) {
                pane_contact_info.setVisible(false);
                visibility_info = false;
            } else {
                pane_contact_info.setVisible(true);
                visibility_info = true;
            }
        });

        btn_pda.onActionProperty().set(e -> {
            if (visibility_pda) {
                pane_pda.setVisible(false);
                visibility_pda = false;
            } else {
                pane_pda.setVisible(true);
                visibility_pda = true;
            }
        });
    }

    @FXML
    public void btn_send_is_clicked() {
        try (Socket s = getSocket()) {
            out = new PrintWriter(s.getOutputStream(), true);
            String pda = client + "/" + txt_name.getText() + "/" + txt_year.getText() + "/" + txt_producer.getText() + "/" + txt_notes.getText() + "/" + spin_quantity.getValue();
            out.println("ADD_PDA");
            out.println(pda);
            //Hide the pane when the request is sent
            pane_pda.setVisible(false);
        } catch (IOException e) {
            System.out.println("HelpFXCotroller: " + e.getMessage());
        }
    }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type); }

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_notifications_is_clicked(ActionEvent event) throws IOException { lib.getNotifications(event, type); }

    public Socket getSocket() throws IOException { return new Socket("localhost", 1234); }
}
