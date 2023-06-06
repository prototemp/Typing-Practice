package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<XYChart.Series<String, Integer>> seriesList = DBUtils.userHistoryChart(Player.userId);
        btn_back.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null));
        btn_table.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"history.fxml","Main Menu",null,null));
        btn_lineChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"line-chart.fxml","Main Menu",null,null));
        btn_areaChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"area-chart.fxml","Main Menu",null,null));
        histBarChart.getData().addAll(seriesList.get(0),seriesList.get(1),seriesList.get(2));
    }
}
