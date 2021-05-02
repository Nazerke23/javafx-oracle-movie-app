package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static  final String CURRENCY = "...";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/market.fxml"));
        primaryStage.setTitle("Netflix1");
        primaryStage.setScene(new Scene(root, 1500, 810));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
