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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class report_pageFXController implements Initializable {
    private String client;
    private String type;
    private MAIN_LIB lib = new MAIN_LIB();

    @FXML
    private LineChart<?, ?> chart_report;
    @FXML
    private PieChart pie_chart;
    @FXML
    private Button btn_pieChart, btn_lineChart;

    public void setUser(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inlineChart();
        inPieChart();
        btn_lineChart_clicked();
    }

    @FXML
    private void btn_lineChart_clicked() {
        chart_report.setVisible(true);
        pie_chart.setVisible(false);
    }

    @FXML
    private void btn_pieChart_clicked() {
        chart_report.setVisible(false);
        pie_chart.setVisible(true);
    }

    private void inlineChart() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Monday", 23));
        series.getData().add(new XYChart.Data("Tuesday", 14));
        series.getData().add(new XYChart.Data("Wednesday", 15));
        series.getData().add(new XYChart.Data("Thursday", 24));
        series.getData().add(new XYChart.Data("Friday", 34));
        series.getData().add(new XYChart.Data("Saturday", 36));
        series.getData().add(new XYChart.Data("Sunday", 22));
        chart_report.getData().add(series);
    }

    private void inPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Grape 1", 13),
                new PieChart.Data("Grape 2", 25),
                new PieChart.Data("Grape 3", 10),
                new PieChart.Data("Grape 4", 22),
                new PieChart.Data("Grape 5", 30));
        pie_chart.setData(pieChartData);
    }

    @FXML
    public void btn_cart_is_clicked(ActionEvent event) throws IOException { lib.getCart(event, type); }

    @FXML
    public void btn_home_is_clicked(ActionEvent event) throws IOException { lib.getHome(event, client, type);}

    @FXML
    public void btn_logout_is_clicked(ActionEvent event) throws IOException { lib.getLogout(event); }

    @FXML
    public void btn_user_is_clicked(ActionEvent event) throws IOException { lib.getUser(event, type); }

    @FXML
    public void btn_view_chart_is_clicked(ActionEvent actionEvent) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("chart_page.fxml"));
        Parent root = loader.load();
        //chart_pageFXController CPFXC = loader.getController();
        //CPFXC.setUser(client);
        //CPFXC.setUserType(type);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();*/
    }
}
