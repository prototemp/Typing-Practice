package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AreaChartController implements Initializable {

    @FXML
    public Button btn_back;
    @FXML
    public Button btn_table;
    @FXML
    public Button btn_barChart;
    @FXML
    public AreaChart<String,Integer> histAreaChart;
    @FXML
    public Button btn_lineChart;
    @FXML
    public ChoiceBox<String> pilihWaktu;

    public List<XYChart.Series<String, Integer>> seriesList = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pilihWaktu.getItems().addAll(PlayerHistory.waktu);
        btn_back.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null));
        btn_table.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"history.fxml","Main Menu",null,null));
        btn_barChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"bar-chart.fxml","Main Menu",null,null));
        btn_lineChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"line-chart.fxml","Bar Chart",null,null));
        if(PlayerHistory.historyState == null || PlayerHistory.historyState.equals("All")){
            seriesList = DBUtils.userHistoryChart(Player.userId,0);
        }
        else {
            seriesList = DBUtils.userHistoryChart(Player.userId,PlayerHistory.historyStateInt);
        }
        histAreaChart.getData().addAll(seriesList.get(0),seriesList.get(1),seriesList.get(2));
        pilihWaktu.setOnAction(actionEvent -> {
            histAreaChart.getData().clear();
            int selectedIndex = pilihWaktu.getSelectionModel().getSelectedIndex() + 1;
            PlayerHistory.historyState = pilihWaktu.getValue();
            PlayerHistory.historyStateInt = selectedIndex;
            if (selectedIndex != 5) {
                seriesList = DBUtils.userHistoryChart(Player.userId, selectedIndex);
                histAreaChart.getData().addAll(seriesList.get(0),seriesList.get(1),seriesList.get(2));
            }
        });
    }
}
