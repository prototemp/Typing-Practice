package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {

    @FXML
    public Button btn_back;
    @FXML
    public BarChart<String,Integer> histBarChart;
    @FXML
    public Button btn_table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_back.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null));
        btn_table.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"history.fxml","Main Menu",null,null));
        DBUtils.userHistoryChart(Player.userId,histBarChart);
    }
}
