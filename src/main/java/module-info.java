module com.example.gestorevini {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.example.gestorevini to javafx.fxml;
    exports com.example.gestorevini;
}