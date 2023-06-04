package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    private Label timeLabel;
    @FXML
    private Label displayUsername;

    @FXML
    private Text total;
    @FXML
    private Text wpm;
    @FXML
    private Text invalid;

    @FXML
    private ChoiceBox<Integer> pilihWaktu;

    @FXML
    private ChoiceBox<String> pilihLevel;
    private final Integer[] waktu = {10,30,60,120};
    private final String[] level = {"Mudah", "Sedang", "Sulit"};

    public static String levelPilihan;
    public static Integer waktuPilihan;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File newFile = new File("username.txt");
        pilihWaktu.getItems().addAll(waktu);
        pilihLevel.getItems().addAll(level);
        DBUtils.getSumWords(Player.userId);
        if (newFile.length() != 0) {
            Scanner reader = null;
            try {
                reader = new Scanner(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            String data = reader.nextLine();
//            displayUsername.setText("Welcome, "+data);
            displayUsername.setText("Welcome, "+ Player.username);
        }

        // set the day
        Date date = new Date();
        Locale locale = new Locale("id");
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        String strDay = formatter.format(date);

        timeLabel.setText("Hari ini " + strDay);

        // we need to display data
//        int[] data = FileHandling.sumUpNumbers("src/data");
//        total.setText(String.valueOf(data[0]));
//        wpm.setText(String.valueOf(Math.round(data[1]*1.0/data[3])));
//        invalid.setText(String.valueOf(data[2]));
        total.setText(String.valueOf(Player.sumWords));
        wpm.setText(String.valueOf(Math.round(Player.trueWords*1.0/Player.countHist)));
        invalid.setText(String.valueOf(Player.falseWords));
    }


    public void playGame(ActionEvent ddd) throws IOException {
        File newFile = new File("username.txt");
        if (newFile.length() == 0) {
            DBUtils.changeScene(ddd,"popup.fxml","Typing Practice",null,null);
        }
        else {
            levelPilihan = pilihLevel.getValue();
            waktuPilihan = pilihWaktu.getValue();

            if (waktuPilihan == null || levelPilihan == null) {
                DBUtils.changeScene(ddd,"empty.fxml","Typing Practice",null,null);
            } else {
                DBUtils.changeScene(ddd, "game.fxml", "Typing Practice", null, null);
                if (waktuPilihan == 10) Player.gameMode = DBUtils.getModeId("10 Seconds");
                else if (waktuPilihan == 30) Player.gameMode = DBUtils.getModeId("30 Seconds");
                else if (waktuPilihan == 60) Player.gameMode = DBUtils.getModeId("60 Seconds");
                else Player.gameMode = DBUtils.getModeId("120 Seconds");
            }
        }

    }
    public void wikiGame(ActionEvent ddd) throws IOException {
        DBUtils.changeScene(ddd,"wiki.fxml","Wiki",null,null);
    }
    public void settings(ActionEvent ddd) throws IOException {
        DBUtils.changeScene(ddd,"settings.fxml","Settings",null,null);
    }

    public void historyTable(ActionEvent ddd) throws IOException {
        DBUtils.changeScene(ddd,"history.fxml","History",null,null);
    }

}
