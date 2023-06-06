package com.bam.typingpractice.typing_practice;

public class PlayerHistory {

    public String username;
    public int userId;
    public int gameMode;
    public int sessionWords;
    public int sessionTrueWords;
    public int sessionFalseWords;
    public int playId;
    public String playTime;
    public static String[] waktu = {"10 Seconds","30 Seconds","60 Seconds","120 Seconds","Endless", "All"};
    public static String historyState;
    public static int historyStateInt;

    public PlayerHistory(int userId, int gameMode, int sessionWords, int sessionTrueWords, int sessionFalseWords, String playTime, int playId) {
        this.username = Player.username;
        this.userId = userId;
        this.gameMode = gameMode;
        this.sessionWords = sessionWords;
        this.sessionTrueWords = sessionTrueWords;
        this.sessionFalseWords = sessionFalseWords;
        this.playTime = playTime;
        this.playId = playId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getSessionWords() {
        return sessionWords;
    }

    public void setSessionWords(int sessionWords) {
        this.sessionWords = sessionWords;
    }

    public int getSessionTrueWords() {
        return sessionTrueWords;
    }

    public void setSessionTrueWords(int sessionTrueWords) {
        this.sessionTrueWords = sessionTrueWords;
    }

    public int getSessionFalseWords() {
        return sessionFalseWords;
    }

    public void setSessionFalseWords(int sessionFalseWords) {
        this.sessionFalseWords = sessionFalseWords;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }   public int getPlayId() {
        return playId;
    }
}