package com.example.gestorevini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label scemoChiLegge;

    @FXML
    protected void onHelloButtonClick() {
        if (scemoChiLegge.getOpacity()==0) {
            scemoChiLegge.setOpacity(1);
        } else {
            scemoChiLegge.setOpacity(0);
        }
    }

}

