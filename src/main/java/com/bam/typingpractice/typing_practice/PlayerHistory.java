package com.bam.typingpractice.typing_practice;

public class PlayerHistory {

    public String username;
    public int userId = 0;
    public int gameMode = 0;
    public int sessionWords = 0;
    public int sessionTrueWords = 0;
    public int sessionFalseWords = 0;
    public String playTime = "";

    public PlayerHistory(int userId, int gameMode, int sessionWords, int sessionTrueWords, int sessionFalseWords, String playTime) {
        this.username = Player.username;
        this.userId = userId;
        this.gameMode = gameMode;
        this.sessionWords = sessionWords;
        this.sessionTrueWords = sessionTrueWords;
        this.sessionFalseWords = sessionFalseWords;
        this.playTime = playTime;
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
    }
}
