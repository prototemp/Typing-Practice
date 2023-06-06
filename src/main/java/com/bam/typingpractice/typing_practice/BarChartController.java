package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {

    @FXML
    public Button btn_back;
    @FXML
    public BarChart<String,Integer> histBarChart;
    @FXML
    public Button btn_table;
    @FXML
    public Button btn_lineChart;
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
        btn_lineChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"line-chart.fxml","Main Menu",null,null));
        btn_areaChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"area-chart.fxml","Main Menu",null,null));
        if(PlayerHistory.historyState == null || PlayerHistory.historyState.equals("All")){
            seriesList = DBUtils.userHistoryChart(Player.userId,0);
        }
        else {
            seriesList = DBUtils.userHistoryChart(Player.userId,DBUtils.getModeId(PlayerHistory.historyState));
        }
        histBarChart.getData().addAll(seriesList.get(0),seriesList.get(1),seriesList.get(2));
        pilihWaktu.setOnAction(actionEvent -> {
            histBarChart.getData().clear();
            int selectedIndex = pilihWaktu.getSelectionModel().getSelectedIndex() + 1;
            PlayerHistory.historyState = pilihWaktu.getValue();
            if (selectedIndex != 5) {
                seriesList = DBUtils.userHistoryChart(Player.userId, selectedIndex);
                histBarChart.getData().addAll(seriesList.get(0),seriesList.get(1),seriesList.get(2));
            }
        });
    }
}
