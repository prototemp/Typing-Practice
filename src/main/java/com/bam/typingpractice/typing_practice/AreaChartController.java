package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_back.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null));
        btn_table.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"history.fxml","Main Menu",null,null));
        btn_barChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"bar-chart.fxml","Main Menu",null,null));
        btn_lineChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"line-chart.fxml","Bar Chart",null,null));
        DBUtils.userHistoryAreaChart(Player.userId,histAreaChart);
    }
}
