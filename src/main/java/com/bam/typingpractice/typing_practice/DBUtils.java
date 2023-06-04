package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class DBUtils {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final int PORT = 3306;
    private static final String HOST = "localhost";
    private static final String DBNAME = "typing-javafx";

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String frasa){
        Parent root = null;
        if (username != null && frasa != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInfo(username, frasa);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlFile)));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,460));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String frasa){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psCheckExist = connection.prepareStatement("SELECT * FROM users where username = ?");
            psCheckExist.setString(1,username);
            resultSet = psCheckExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Cannot enter this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users(username,password,frasa) VALUES(?,?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,frasa);
                psInsert.executeUpdate();
                changeScene(event,"landing.fxml","Log-In",null,null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckExist != null){
                try{
                    psCheckExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement psLogin = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psLogin = connection.prepareStatement("SELECT password, frasa from users WHERE username = ?");
            psLogin.setString(1,username);
            resultSet = psLogin.executeQuery();
            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided cridentials are incorrect!");
                alert.show();

            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedFrasa = resultSet.getString("frasa");
                    if (retrievedPassword.equals(password)){
                        changeScene(event,"logged-in.fxml","Log-in",username,retrievedFrasa);
                        Player.frasa = retrievedFrasa;
                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided cridentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psLogin != null){
                try {
                    psLogin.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void changePassword(ActionEvent event, String username, String newPassword, String frasa){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psCheckExist = connection.prepareStatement("SELECT * FROM users where username = ?");
            psCheckExist.setString(1,username);
            resultSet = psCheckExist.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User don't exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Insert correct username");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedFrasa = resultSet.getString("frasa");
                    if (retrievedFrasa.equals(frasa)){
                        psInsert = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
                        psInsert.setString(1,newPassword);
                        psInsert.setString(2,username);
                        psInsert.executeUpdate();
                        changeScene(event,"landing.fxml","Log-in",null,null);
                    } else {
                        System.out.println("Frasa did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided cridentials are incorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckExist != null){
                try{
                    psCheckExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void changeFrasa(ActionEvent event, String username, String newPassword, String oldFrasa, String newFrasa){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psCheckExist = connection.prepareStatement("SELECT * FROM users where username = ?");
            psCheckExist.setString(1,username);
            resultSet = psCheckExist.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User don't exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Insert correct username");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedFrasa = resultSet.getString("frasa");
                    if (retrievedFrasa.equals(oldFrasa) && retrievedPassword.equals(newPassword)){
                        psInsert = connection.prepareStatement("UPDATE users SET frasa = ? WHERE username = ?");
                        psInsert.setString(1,newFrasa);
                        psInsert.setString(2,username);
                        psInsert.executeUpdate();
                        changeScene(event,"settings.fxml","Settings",null,null);
                    } else {
                        System.out.println("Frasa or Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided cridentials are incorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckExist != null){
                try{
                    psCheckExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deleteUser(ActionEvent event, String username){
        Connection connection = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psCheckExist = connection.prepareStatement("SELECT * FROM users where username = ?");
            psCheckExist.setString(1,username);
            resultSet = psCheckExist.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User don't exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Insert correct username");
                alert.show();
            } else {
                while (resultSet.next()){
                    psDelete = connection.prepareStatement("DELETE FROM users WHERE username = ?");
                    psDelete.setString(1,username);
                    psDelete.executeUpdate();
                    changeScene(event,"landing.fxml","Log-in",null,null);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckExist != null){
                try{
                    psCheckExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psDelete != null){
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void insertScore(int sumWords,int trueWords,int userId,int mode){
        Connection connection = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psInsert = connection.prepareStatement("INSERT INTO players_score(sum_words,true_words,false_words,history,user_id,mode_id) VALUES(?,?,?,now(),?,?)");
            psInsert.setInt(1,sumWords);
            psInsert.setInt(2,trueWords);
            psInsert.setInt(3, (sumWords-trueWords));
            psInsert.setInt(4,userId);
            psInsert.setInt(5,mode);
            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static int getUserId(String username){
        Connection connection = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;
        int ret = 0;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psInsert = connection.prepareStatement("SELECT user_id FROM users WHERE username = ?");
            psInsert.setString(1, String.valueOf(username));

            resultSet = psInsert.executeQuery();
            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided cridentials are incorrect!");
                alert.show();

            } else {
                while (resultSet.next()){
                    ret = resultSet.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
    public static int getModeId(String modeName){
        Connection connection = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;
        int ret = 0;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psInsert = connection.prepareStatement("SELECT id_mode FROM game_mode WHERE mode_name = ?");
            psInsert.setString(1, String.valueOf(modeName));
            resultSet = psInsert.executeQuery();
            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided cridentials are incorrect!");
                alert.show();

            } else {
                while (resultSet.next()){
                    ret = resultSet.getInt("id_mode");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
    public static void userHistory(int userid, TableView<PlayerHistory> histTable){
        Connection connection = null;
        PreparedStatement psLogin = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psLogin = connection.prepareStatement("SELECT * from players_score WHERE user_id = ?");
            psLogin.setInt(1, userid);
            resultSet = psLogin.executeQuery();
            if (!resultSet.isBeforeFirst()){
                System.out.println("User never playing");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided cridentials are incorrect!");
                alert.show();

            } else {
                while (resultSet.next()){
                    int retrievedSumWords = Integer.parseInt(resultSet.getString("sum_words"));
                    int retrievedTrueWords = Integer.parseInt(resultSet.getString("true_words"));
                    int retrievedFalseWords = Integer.parseInt(resultSet.getString("false_words"));
                    String retrievedHistory = resultSet.getString("history");
                    int retrievedUserId = Integer.parseInt(resultSet.getString("user_id"));
                    int retrievedMode = Integer.parseInt(resultSet.getString("mode_id"));
                    histTable.getItems().add(new PlayerHistory(retrievedUserId,retrievedMode,retrievedSumWords,retrievedTrueWords,retrievedFalseWords,retrievedHistory));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psLogin != null){
                try {
                    psLogin.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void getSumWords(int userid){
        Connection connection = null;
        PreparedStatement psLogin = null;
        PreparedStatement psCount = null;
        ResultSet resultSet = null;
        ResultSet countSet = null;
        int sumWords = 0;
        int trueWords = 0;
        int falseWords = 0;
        int countHistory = 0;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DBNAME),USERNAME,PASSWORD);
            psLogin = connection.prepareStatement("SELECT * from players_score WHERE user_id = ?");
            psLogin.setInt(1, userid);
            resultSet = psLogin.executeQuery();

            psCount = connection.prepareStatement("SELECT COUNT(*) from players_score WHERE user_id = ?");
            psCount.setInt(1, userid);
            countSet = psCount.executeQuery();

            while (countSet.next()){
                countHistory = Integer.parseInt(countSet.getString(1));
            }
            if (!resultSet.isBeforeFirst()){
                System.out.println("User never playing");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided cridentials are incorrect!");
                alert.show();

            } else {
                while (resultSet.next()){
                    sumWords += Integer.parseInt(resultSet.getString("sum_words"));
                    trueWords += Integer.parseInt(resultSet.getString("true_words"));
                    falseWords += Integer.parseInt(resultSet.getString("false_words"));
                }
            }
            Player.sumWords = sumWords;
            Player.trueWords = trueWords;
            Player.falseWords = falseWords;
            Player.countHist = countHistory;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (countSet != null){
                try{
                    countSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psLogin != null){
                try {
                    psLogin.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCount != null){
                try{
                    psCount.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
