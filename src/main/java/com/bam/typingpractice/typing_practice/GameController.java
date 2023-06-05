package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController implements Initializable {
    private int wordCounter = 0;
    private int first = 1;
    private int countAll = 0;
    private int counter = 0;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @FXML
    public AnchorPane pane_basis;
    @FXML
    public Text seconds;
    @FXML
    private Text wordsPerMin;
    @FXML
    private Text accuracy;
    @FXML
    private Text programWord;
    @FXML
    private Text secondProgramWord;

    @FXML
    private TextField userWord;

    @FXML
    private ImageView correct;
    @FXML
    private ImageView wrong;

    @FXML
    private Button playAgain;
    @FXML
    public Button btn_stop;
    @FXML
    public Button btn_mainMenu;


    ArrayList<String> words = new ArrayList<>();

    // add words to array list
    public void addToList() {
        char[] symbol = {'~' ,'`' ,'!' ,'@' ,'#' ,'$' ,'%' ,'^' ,'&' ,'*' ,'(' ,')' ,'-' ,'_' ,'+' ,'=' ,'{' ,'}' ,'[' ,']' ,'|' ,'\\' ,'/' ,':' ,';' ,'"' ,'\'' ,'<' ,'>' ,',' ,'.' ,'?'};
        BufferedReader reader;
        try {
            if(Objects.equals(Controller.levelPilihan, "Sulit")){
                reader = new BufferedReader(new FileReader("wordsList_sulit"));
            } else if (Objects.equals(Controller.levelPilihan, "Sedang")) {
                reader = new BufferedReader(new FileReader("wordsList_sedang"));
            } else reader = new BufferedReader(new FileReader("wordsList_mudah"));

            String line = reader.readLine();

            while (line != null) {
                if (Controller.modCapital){
                    StringBuilder strTmp = new StringBuilder(line);
                    for (int i = 0; i < line.length(); i++) {
                        Random random = new Random();
                        char c = line.charAt(i);
                        if (random.nextBoolean()) {
                            c = Character.toUpperCase(c);
                        }
                        strTmp.setCharAt(i,c);
                    }
                    line = String.valueOf(strTmp);
                }
                if (Controller.modNumber){
                    StringBuilder strTmp = new StringBuilder(line);
                    for (int i = 0; i <= line.length(); i++) {
                        Random random = new Random();
                        int c = random.nextInt(10);
                        if (random.nextBoolean()) {
                            strTmp.insert(i,c);
                        }
                    }
                    line = String.valueOf(strTmp);
                }
                if (Controller.modReverse){
                    StringBuilder strTmp = new StringBuilder(line);
                        Random random = new Random();
                        if (random.nextBoolean()) {
                            strTmp.reverse();
                        }
                    line = String.valueOf(strTmp);
                }
                if (Controller.modSymbol){
                    StringBuilder strTmp = new StringBuilder(line);
                    for (int i = 0; i <= line.length(); i++) {
                        Random random = new Random();
                        int c = random.nextInt(32);
                        if (random.nextBoolean()) {
                            strTmp.insert(i,symbol[c]);
                        }
                    }
                    line = String.valueOf(strTmp);
                }
                words.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toMainMenu(ActionEvent ae) {
        DBUtils.changeScene(ae,"sample.fxml","Main Menu",null,null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playAgain.setVisible(false);
        playAgain.setDisable(true);
        btn_mainMenu.setVisible(false);
        btn_mainMenu.setDisable(true);
        seconds.setText(String.valueOf(Controller.waktuPilihan));
        addToList();
        Collections.shuffle(words);
        programWord.setText(words.get(wordCounter));
        secondProgramWord.setText(words.get(wordCounter+1));
        wordCounter++;

        btn_stop.setOnAction(actionEvent -> timer = -1);
        playAgain.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"game.fxml","Typing Practice",null,null));

        userWord.textProperty().addListener((observable, oldValue, newValue) -> {
            String real = programWord.getText();
            String ss;
            int maxIdx = real.trim().length();
            if(newValue.trim().length() <= maxIdx){
                ss = real.substring(0,newValue.trim().length());
            }
            else {
                ss = real.substring(0,maxIdx);
            }
            // if correct
            if (newValue.trim().equals(ss)) {
                pane_basis.setStyle("-fx-background-color: #1cfc03;");
            }
            else {
                pane_basis.setStyle("-fx-background-color: #e04646;");
            }
            programWord.setFill(Color.DARKBLUE);
            secondProgramWord.setFill(Color.DARKBLUE);
        });

    }

    private int timer = Controller.waktuPilihan;
    Runtime runTime = Runtime.getRuntime();
    String executablePath = "osk";
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (timer > -1) {
                seconds.setText(String.valueOf(timer));
                try {
                    runTime.exec(executablePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            else {
                if (timer == -1) {
                    userWord.setDisable(true);
                    userWord.setText("Game over");
                    programWord.setFill(Color.WHEAT);
                    secondProgramWord.setFill(Color.WHEAT);
                    pane_basis.setStyle("-fx-background-color: #0a1931;");

                    try {
                        Player.sessionWords = countAll;
                        Player.sessionTrueWords = counter;
                        Player.sessionFalseWords = countAll - counter;
                        DBUtils.insertScore(Player.sessionWords,Player.sessionTrueWords,Player.userId,Player.gameMode);
                        Runtime.getRuntime().exec("taskkill /F /IM osk.exe");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (timer <= -2) {
                    playAgain.setVisible(true);
                    playAgain.setDisable(false);
                    btn_mainMenu.setVisible(true);
                    btn_mainMenu.setDisable(false);
                    btn_stop.setVisible(false);
                    btn_stop.setDisable(true);
                    executor.shutdown();
                }

            }
            timer -= 1;
        }
    };

    Runnable fadeCorrect = new Runnable() {
        @Override
        public void run() {
            correct.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(0);

        }
    };

    Runnable fadeWrong = new Runnable() {
        @Override
        public void run() {
            wrong.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(0);
        }
    };




    public void startGame(KeyEvent ke) {
        // only gets called once
        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }

        if (ke.getCode().equals(KeyCode.ENTER) || ke.getCode().equals(KeyCode.SPACE)) {

            String s = userWord.getText().trim();
            String real = programWord.getText();
            countAll++;

            // if correct
            if (s.equals(real)) {
                counter++;
                wordsPerMin.setText(String.valueOf(counter));

                Thread t = new Thread(fadeCorrect);
                t.start();

            }
            else {
                Thread t = new Thread(fadeWrong);
                t.start();
            }
            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)));
            programWord.setText(words.get(wordCounter));
            secondProgramWord.setText(words.get(wordCounter+1));
            wordCounter++;
        }


    }
}
