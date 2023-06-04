package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    public Button btn_lanjutkan;
    @FXML
    public Button btn_erase;
    @FXML
    public Button btn_gantiPassword;
    public Label label_frasaUser;
    @FXML
    public Button btn_gantiFrasa;
    @FXML
    private Button btn_logout;
    @FXML
    private Label label_welcome;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_welcome.setText("Welcome! " + Player.username);
        label_frasaUser.setText(Player.frasa);
        btn_logout.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"landing.fxml","Logged in!", null, null));
        btn_gantiFrasa.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"frasa-change.fxml","Frasa Change!", null, null));
        btn_lanjutkan.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sample.fxml","Typing Practice",null,null));
        btn_erase.setOnAction(actionEvent -> DBUtils.deleteUser(actionEvent,Player.username));
        btn_gantiPassword.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"password-change.fxml","Password Change!", null, null));
    }
}
