package com.example.gestorevini;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class report_pageFXController implements Initializable {
    private int total_sales = 0;
    LocalDate date = LocalDate.now();
    private String client;
    private String type;
    private MAIN_LIB lib = new MAIN_LIB();
    private BufferedReader in;
    private PrintWriter out;
    ArrayList mostSold_names = new ArrayList<>();
    ArrayList mostSold_nums = new ArrayList<>();

    @FXML
    private LineChart<?, ?> chart_report;
    @FXML
    private Button btn_send_report;
    @FXML
    private PieChart pie_chart;
    @FXML
    private AnchorPane note_pane;
    @FXML
    private TextArea txt_note;

    public void setUser(String i) { client = i; }
    public void setUserType(String i) { type = i; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Socket s = getSocket()){
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

            inlineChart();
            chart_report.getXAxis().setLabel("Giorni");
            chart_report.getYAxis().setLabel("Vendite");
            getMostSold();
            inPieChart();
            btn_lineChart_clicked();

            btn_send_report.setOnAction((ActionEvent event) -> {
                sendReport();
            });

        } catch (Exception e) {
            System.out.println("report_pageFXController: " + e);
        }
    }

    @FXML
    private void btn_lineChart_clicked() {
        chart_report.setVisible(true);
        pie_chart.setVisible(false);
        note_pane.setVisible(false);
    }

    @FXML
    private void btn_notes_clicked() {
        note_pane.setVisible(true);
        pie_chart.setVisible(false);
        chart_report.setVisible(false);
    }

    @FXML
    private void btn_pieChart_clicked() {
        chart_report.setVisible(false);
        pie_chart.setVisible(true);
        note_pane.setVisible(false);
    }

    private int getSales(int start, int end) throws IOException {
        out.println("GET_WEEKLY_SALES");
        out.println(date.getYear());
        out.println(date.getMonthValue());
        out.println(start);
        out.println(end);

        int temp = Integer.parseInt(in.readLine());
        total_sales += temp;
        return temp;
    }

    private void getMostSold() throws IOException {
        out.println("GET_MOST_SOLD");
        String[] temp_names = in.readLine().split("/");
        String[] temp_nums = in.readLine().split("/");

        mostSold_names.addAll(Arrays.asList(temp_names));
        mostSold_nums.addAll(Arrays.asList(temp_nums));
    }

    private void sendReport() {
        out.println("SEND_REPORT");
        String REPORT = "Report mensile del " + date.getMonthValue() + "/" + date.getYear() + ":\n" + "Vendite totali: " + total_sales + "\n" + "Vino più venduto: " + mostSold_names.get(0) + " (" + mostSold_nums.get(0) + ")\n" + "Vino meno venduto: " + mostSold_names.get(mostSold_names.size() - 1) + " (" + mostSold_nums.get(mostSold_nums.size() - 1) + ")\n" + "Ulteriori informazioni: " + txt_note.getText() + "\n";
        out.println(REPORT);
        //lib.getHome(event, client, type);
    }

    private void inlineChart() throws Exception {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("1-5", getSales(1, 5)));
        series.getData().add(new XYChart.Data("5-10", getSales(5, 10)));
        series.getData().add(new XYChart.Data("10-15", getSales(10, 15)));
        series.getData().add(new XYChart.Data("15-20", getSales(15, 20)));
        series.getData().add(new XYChart.Data("20-25", getSales(20, 25)));
        series.getData().add(new XYChart.Data("25-30", getSales(25, 31)));
        series.setName("Vendite Mensili");
        chart_report.getData().add(series);
    }

    private void inPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < mostSold_names.size(); i++)
            pieChartData.add(new PieChart.Data(mostSold_names.get(i).toString(), Integer.parseInt(mostSold_nums.get(i).toString())));
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

    private Socket getSocket() throws Exception { return new Socket("localhost", 1234); }
}
