package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public CheckBox checkCapital;
    @FXML
    public CheckBox checkNumber;
    @FXML
    public CheckBox checkReverse;
    @FXML
    public CheckBox checkSymbol;
    @FXML
    public CheckBox checkDouble;
    @FXML
    public CheckBox checkRandom;
    @FXML
    public CheckBox checkOsk;
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
    private final String[] level = {"Mudah", "Sedang", "Sulit", "Random"};

    public static String levelPilihan;
    public static Integer waktuPilihan;
    public static boolean modCapital;
    public static boolean modNumber;
    public static boolean modReverse;
    public static boolean modSymbol;
    public static boolean modDouble;
    public static boolean modRandom;
    public static boolean enableOsk;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkOsk.setSelected(true);
        pilihWaktu.getItems().addAll(waktu);
        pilihLevel.getItems().addAll(level);
        DBUtils.getSumWords(Player.userId);
        displayUsername.setText("Welcome, "+ Player.username);
        // set the day
        Locale locale = new Locale("id");
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm:ss",locale);
        String formattedDate = myDateObj.format(myFormatObj);

        timeLabel.setText(formattedDate);

        // we need to display data
        total.setText(String.valueOf(Player.sumWords));
        wpm.setText(String.valueOf(Math.round(Player.trueWords*1.0/Player.countHist)));
        invalid.setText(String.valueOf(Player.falseWords));
    }


    public void playGame(ActionEvent ddd) {
        levelPilihan = pilihLevel.getValue();
        waktuPilihan = pilihWaktu.getValue();

        if (pilihLevel.getValue().equals("Random")){
            Random random = new Random();
            int levelRand = random.nextInt(3);
            levelPilihan = level[levelRand];
        }

        if (levelPilihan == null) {
            DBUtils.changeScene(ddd,"empty.fxml","Typing Practice",null,null);
        }

        if (waktuPilihan == null) {
            waktuPilihan = 100000;
        }

        if (levelPilihan != null) {
            modCapital = checkCapital.isSelected();
            modNumber = checkNumber.isSelected();
            modReverse = checkReverse.isSelected();
            modSymbol = checkSymbol.isSelected();
            modDouble = checkDouble.isSelected();
            modRandom = checkRandom.isSelected();
            enableOsk = checkOsk.isSelected();
            DBUtils.changeScene(ddd, "game.fxml", "Typing Practice", null, null);
            if (waktuPilihan == 10) Player.gameMode = DBUtils.getModeId("10 Seconds");
            else if (waktuPilihan == 30) Player.gameMode = DBUtils.getModeId("30 Seconds");
            else if (waktuPilihan == 60) Player.gameMode = DBUtils.getModeId("60 Seconds");
            else if (waktuPilihan == 120) Player.gameMode = DBUtils.getModeId("120 Seconds");
            else Player.gameMode = DBUtils.getModeId("Endless");
        }

    }
    public void wikiGame(ActionEvent ddd) {
        DBUtils.changeScene(ddd,"wiki.fxml","Wiki",null,null);
    }
    public void settings(ActionEvent ddd) {
        DBUtils.changeScene(ddd,"settings.fxml","Settings",null,null);
    }

    public void historyTable(ActionEvent ddd) {
        DBUtils.changeScene(ddd,"history.fxml","History",null,null);
    }

}
