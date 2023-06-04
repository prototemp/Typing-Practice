package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    public TableView<PlayerHistory> histTable = new TableView<>();
    @FXML
    public TableColumn<PlayerHistory,String> nameCol;
    @FXML
    public TableColumn<PlayerHistory,Integer> sumCol;
    @FXML
    public TableColumn<PlayerHistory,Integer> trueCol;
    @FXML
    public TableColumn<PlayerHistory,Integer> falseCol;
    @FXML
    public TableColumn<PlayerHistory,String> playtimeCol;
    @FXML
    public TableColumn<PlayerHistory,Integer> modeCol;
    @FXML
    public Button btn_back;
    @FXML
    public Button btn_barChart;
    @FXML
    public Button btn_lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        sumCol.setCellValueFactory(new PropertyValueFactory<>("sessionWords"));
        trueCol.setCellValueFactory(new PropertyValueFactory<>("sessionTrueWords"));
        falseCol.setCellValueFactory(new PropertyValueFactory<>("sessionFalseWords"));
        playtimeCol.setCellValueFactory(new PropertyValueFactory<>("playTime"));
        modeCol.setCellValueFactory(new PropertyValueFactory<>("gameMode"));
        btn_back.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null));
        btn_barChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"bar-chart.fxml","Bar Chart",null,null));
        btn_lineChart.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"line-chart.fxml","Bar Chart",null,null));
        DBUtils.userHistory(Player.userId,histTable);
    }
}
