package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<XYChart.Series<String, Integer>> seriesList = DBUtils.userHistoryChart(Player.userId);
        btn_back.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null));
        btn_table.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"history.fxml","Main Menu",null,null));
        btn_barChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"bar-chart.fxml","Main Menu",null,null));
        btn_areaChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"area-chart.fxml","Main Menu",null,null));
        histLineChart.getData().addAll(seriesList.get(0),seriesList.get(1),seriesList.get(2));
    }
}
