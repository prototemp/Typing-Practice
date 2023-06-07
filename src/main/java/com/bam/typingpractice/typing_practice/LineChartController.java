package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LineChartController implements Initializable {

    @FXML
    public Button btn_back;
    @FXML
    public Button btn_table;
    @FXML
    public LineChart<String,Integer> histLineChart;
    @FXML
    public Button btn_barChart;
    @FXML
    public Button btn_areaChart;
    @FXML
    public ChoiceBox<String> pilihWaktu;

    public List<XYChart.Series<String, Integer>> seriesList = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pilihWaktu.getItems().addAll(PlayerHistory.waktu);
        btn_back.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null));
        btn_table.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"history.fxml","Main Menu",null,null));
        btn_barChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"bar-chart.fxml","Main Menu",null,null));
        btn_areaChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"area-chart.fxml","Main Menu",null,null));
        if (PlayerHistory.historyState == null || PlayerHistory.historyState.equals("All")) {
            seriesList = DBUtils.userHistoryChart(Player.userId, 0);
        } else {
            seriesList = DBUtils.userHistoryChart(Player.userId,PlayerHistory.historyStateInt);
        }
        histLineChart.getData().addAll(seriesList.get(0), seriesList.get(1), seriesList.get(2));
        pilihWaktu.setOnAction(actionEvent -> {
            histLineChart.getData().clear();
            int selectedIndex = pilihWaktu.getSelectionModel().getSelectedIndex() + 1;
            PlayerHistory.historyState = pilihWaktu.getValue();
            PlayerHistory.historyStateInt = selectedIndex;
            seriesList = DBUtils.userHistoryChart(Player.userId, selectedIndex);
            histLineChart.getData().addAll(seriesList.get(0),seriesList.get(1),seriesList.get(2));
        });
    }
}
