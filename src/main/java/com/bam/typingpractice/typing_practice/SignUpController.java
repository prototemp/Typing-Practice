package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    public PasswordField tf_confirmPassword;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_frasa;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_signup;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_signup.setOnAction(actionEvent -> {
            if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_confirmPassword.getText().trim().isEmpty() && !tf_frasa.getText().trim().isEmpty()){
                if (tf_password.getText().equals(tf_confirmPassword.getText())) DBUtils.signUpUser(actionEvent,tf_username.getText(),tf_password.getText(),tf_frasa.getText());
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill password confirmation with the same password!");
                    alert.show();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in Information to sign up!");
                alert.show();
            }
        });

        btn_login.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"landing.fxml","Log in!", null, null));
    }
}
