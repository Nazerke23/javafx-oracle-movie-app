package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.ResourceBundle;

public class NewLineChartController {

    private static XYChart.Series dataSeries1;

    @FXML
    private LineChart<  ?,? > bar_id;

    @FXML
    private Button btnMovie;

    @FXML
    private Button btnTvShpw;

    @FXML
    private Button btnLineChartNazad;

    @FXML
    void handleButtonLineChartNazad(ActionEvent event) throws IOException {
        //handleBtnStages("../views/market.fxml", btnLineChartNazad);
        MarketController.stage.close();
    }

    @FXML
    void handleButtonMovie(ActionEvent event) {
        if(dataSeries1.getData() == null){
            dataSeries1.getData().removeAll(Collections.singleton(bar_id.getData().setAll()));
        }

        bar("Movie");

    }

    @FXML
    void handleButtonTvShow(ActionEvent event) {
        //dataSeries1.getData().removeAll(Collections.singleton(bar_id.getData().setAll()));
        bar("TV Show");
    }


    void bar(String column_name) {
        String q = "\n" +
                "select  release_year, count(*) as t from netflix_Dilyara \n" +
                "where type  like '%"+column_name+"%'\n" +
                "group by release_year ORDER BY release_year\n";

        dataSeries1 = new XYChart.Series();
        try{
            Connection conn = getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery(q);
            while (resultSet.next()) {
                dataSeries1.getData().add(new XYChart.Data( resultSet.getString("release_year"),
                        resultSet.getInt("t")));
            }
            bar_id.getData().add(dataSeries1);
            bar_id.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
            dataSeries1.getNode().setStyle("-fx-stroke: #FFD6C");
        }catch (Exception e) {
            System.out.println("Try again!");
        }
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        try {
//
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                ItemController itemController = fxmlLoader.getController();
//                itemController.setData(netflixes.get(i), myListener);
//
//
//                grid.add(anchorPane,column++, row);//(child, column, row)
//                //set item grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set item grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(10));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "system", "oracle");
            System.out.println("Succesfully connected!");
            return connection;
        } catch (Exception e) {
            System.out.println("Oops, error!");
            e.printStackTrace();
            return null;
        }

    }
}
