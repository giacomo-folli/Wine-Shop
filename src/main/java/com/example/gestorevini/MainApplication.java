package com.example.gestorevini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException { //TODO: initialize JavaFX stage here

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        System.out.println("1");
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        System.out.println("2");
        stage.setTitle("Home");
        System.out.println("3");
        stage.setScene(scene);
        System.out.println("4");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
