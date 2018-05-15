package com.SupNews.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    /**
     * initialize and create a login window
     * @param primaryStage stage
     * @throws Exception throws any exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("login");
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * main that starts java fx app
     * @param args arguments passed
     */
    public static void main(String[] args) {

        launch(args);
    }
}



