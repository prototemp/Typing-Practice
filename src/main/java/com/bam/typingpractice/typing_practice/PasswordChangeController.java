package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordChangeController implements Initializable {
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_newPassword;
    @FXML
    private TextField tf_frasa;
    @FXML
    private Button btn_change;
    @FXML
    private Button btn_signup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_change.setOnAction(actionEvent -> DBUtils.changePassword(actionEvent,tf_username.getText(),tf_newPassword.getText(),tf_frasa.getText()));
        btn_signup.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sign-up.fxml","Sign up!", null, null));
    }
}
