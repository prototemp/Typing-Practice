package com.bam.typingpractice.typing_practice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Objects;

public class MainTmp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainTmp.class.getResource("landing.fxml")));
        primaryStage.setTitle("Typing Practice");
        primaryStage.setScene(new Scene(root, 600, 460));
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
//        System.out.println(screenBounds.getWidth());
        primaryStage.setX(screenBounds.getWidth()/2-((double) 460 /2));
//        primaryStage.setX(360);
        primaryStage.setY(0);
        primaryStage.show();
    }
}
