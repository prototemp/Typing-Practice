package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_signup;
    @FXML
    private Button btn_forgot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(actionEvent -> DBUtils.logInUser(actionEvent,tf_username.getText(),tf_password.getText()));
        btn_signup.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sign-up.fxml","Sign up!", null, null));
        btn_forgot.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"password-change.fxml","Password Change!", null, null));
    }
}
