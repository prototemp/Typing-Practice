package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        sumCol.setCellValueFactory(new PropertyValueFactory<>("sessionWords"));
        trueCol.setCellValueFactory(new PropertyValueFactory<>("sessionTrueWords"));
        falseCol.setCellValueFactory(new PropertyValueFactory<>("sessionFalseWords"));
        playtimeCol.setCellValueFactory(new PropertyValueFactory<>("playTime"));
        modeCol.setCellValueFactory(new PropertyValueFactory<>("gameMode"));
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent,"sample.fxml","Main Menu",null,null);
            }
        });
        DBUtils.userHistory(Player.userId,histTable);
    }
}
