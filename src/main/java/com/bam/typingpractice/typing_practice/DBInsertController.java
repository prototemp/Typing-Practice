package com.bam.typingpractice.typing_practice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DBInsertController implements Initializable {
    @FXML
    public TextField tf_confirmPassword;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Button btn_signup;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_signup.setOnAction(actionEvent -> {
            if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_confirmPassword.getText().trim().isEmpty()){
                DBUtils.USERNAME = tf_username.getText();
                DBUtils.PASSWORD = tf_password.getText();
                DBUtils.DBNAME = tf_confirmPassword.getText();
                DBUtils.changeScene(actionEvent,"landing.fxml","Login",null,null);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in Database Information to sign up!");
                alert.show();
            }
        });

    }
}
