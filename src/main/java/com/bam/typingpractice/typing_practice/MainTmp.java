package com.bam.typingpractice.typing_practice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Objects;

public class MainTmp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(Objects.requireNonNull(MainTmp.class.getResource("landing.fxml")));
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainTmp.class.getResource("db-settings.fxml")));
        primaryStage.setTitle("Typing Practice");
        primaryStage.setScene(new Scene(root, 600+100, 460+100));
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        primaryStage.setX(screenBounds.getWidth()/2-((double) 660 /2));
        primaryStage.setY(0);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(MainTmp.class.getResourceAsStream("icon.png"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
