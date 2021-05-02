package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import main.MyListener;
import model.Netflix;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {


    @FXML
    private Label fruitNameLabel;

    @FXML
    private ImageView fruitImage;

    @FXML
    private Label fruitCountryLabel;

    @FXML
    private Label fruitYearLabel;

    @FXML
    private Label fruitContinentLabel;

    @FXML
    private Label fruitStyleLabel;

    @FXML
    private Label fruitDescripsionLabel;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private VBox chosenFruidCard;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Button btnMenuHome;

    @FXML
    private Button btnMenuCrud;

    @FXML
    private Button btnMenuLineChart;

    @FXML
    private Button btnMenuPieChart;

    @FXML
    private Button btnMenuSearch;

    @FXML
    private Label labelPopularMovies;

    static Stage stage;
    static ArrayList<String> imagesUrl;

    @FXML
    void handleButtonCollection1(ActionEvent event) {
       labelPopularMovies.setText("RECOMMENDED MOVIES WITH R RATING");
        System.out.println("Collection1-1");
        netflixes.clear();
        System.out.println(netflixes);
        System.out.println("Collection2");
        System.out.println();
        netflixes.addAll(getNetflixesFromCollection1());
        grid.getChildren().clear();
        setNetflixesToGrid();
    }

    @FXML
    void handleButtonCollection2(ActionEvent event) {
        labelPopularMovies.setText("LATEST DRAMA TV SHOWS");
        netflixes.clear();
        netflixes.addAll(getNetflixesFromCollection2());
        grid.getChildren().clear();
        setNetflixesToGrid();
    }


    @FXML
    void handleButtonLineChart(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/newLineChart.fxml"));
        stage.setTitle("Netflix1");
        stage.setScene(new Scene(root, 1500, 810));
        stage.show();
    }

    @FXML
    void handleButtonMenuCrud(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/newCrud.fxml"));
        stage.setTitle("Netflix1");
        stage.setScene(new Scene(root, 1500, 810));
        stage.show();
    }

    @FXML
    void handleButtonMenuHome(ActionEvent event) throws IOException {
        netflixes.addAll(getData());
        setNetflixesToGrid();
    }

    @FXML
    void handleButtonPieChart(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/newPieChart.fxml"));
        stage.setTitle("Netflix1");
        stage.setScene(new Scene(root, 1500, 810));
        stage.show();

    }

    @FXML
    void handleButtonMenuSearch(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/newSort.fxml"));
        stage.setTitle("Netflix1");
        stage.setScene(new Scene(root, 1500, 810));
        stage.show();
    }


    private static List<Netflix> netflixes = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    @FXML
    void handleSearchButton(ActionEvent event) {
        System.out.println("Iamhere1");
        netflixes.clear();
        System.out.println(netflixes);
        System.out.println("Iamhere1");
        System.out.println();
        netflixes.addAll( getDataSearch(tfSearch.getText().toLowerCase()));
        grid.getChildren().clear();
        setNetflixesToGrid();
    }


    public void setNetflixesToGrid(){
        if(netflixes.size() > 0){
            setChosenFruit(netflixes.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Netflix netflix) {
                    setChosenFruit(netflix);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for(int i = 0; i < netflixes.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(netflixes.get(i), myListener);

                if(column == 4){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane,column++, row);//(child, column, row)
                //set item grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set item grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //1-variant
//    private List<Netflix> getData(){
//        List<Netflix> netflixes = new ArrayList<>();
//        Netflix netflix;
//
//        for(int i = 0; i < 50; i++){
//            netflix = new Netflix();
//            netflix.setTitle("Naruto");
//            netflix.setCountry("JAPAN");
//            netflix.setImageUrl("/img/naruto.png");
//            netflix.setColor("#fec5e5");
//            netflixes.add(netflix);
//        }
//
//        return netflixes;
//    }


    private List<Netflix> getData() {

        List<Netflix> netflixes = new ArrayList<>();

        Connection conn = getConnection();
        //String q = "select * from netflix_Dilyara where is_popular = 1 and image_url is not null";
        String q = "select * from netflix_Dilyara where is_popular = 1";
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(q);
            Netflix netflix;

//            netflix = new Netflix();
//            netflix.setTitle("Nazerke2");
//            netflix.setCountry("JAPAN1");
//            netflix.setImageUrl("/img/kiwi.png");
//            netflix.setColor("#fff");
//            netflixes.add(netflix);

            imagesUrl = new ArrayList<>();

            while (resultSet.next()) {
                netflix = new Netflix(resultSet.getString("show_id"),
                        resultSet.getString("type"),
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("cast"),
                        resultSet.getString("country"),
                        resultSet.getString("date_added"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("rating"),
                        resultSet.getString("duration"),
                        resultSet.getString("listed_in"),
                        resultSet.getString("description"),
                        resultSet.getString("image_url"),
                        resultSet.getString("color"),
                        resultSet.getString("continent"),
                        resultSet.getString("category"),
                        resultSet.getInt("is_popular")
                );


                imagesUrl.add(netflix.getImageUrl());


                netflixes.add(netflix);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Try again!--get Data Method");
        }
        return netflixes;
    }

    private void setChosenFruit(Netflix netflix){
        fruitNameLabel.setText(netflix.getTitle());
        fruitCountryLabel.setText(netflix.getCountry() + Main.CURRENCY);
        fruitContinentLabel.setText(netflix.getContinent());
        fruitDescripsionLabel.setText(netflix.getListed_in());
        fruitYearLabel.setText(netflix.getRelease_year() + "");
        fruitStyleLabel.setText(netflix.getCategory());

        if(netflix.getImageUrl() == null){
            int random = (int)(Math.random() * MarketController.imagesUrl.size());
            System.out.println(random);
            System.out.println(MarketController.imagesUrl.get(random));
            image = new Image(MarketController.imagesUrl.get(random));
        }
        else{
            image = new Image(netflix.getImageUrl());
        }

        //image = new Image(getClass().getResourceAsStream(netflix.getImageUrl()));
        fruitImage.setImage(image);
        chosenFruidCard.setStyle("   " +
                "    -fx-background-color:" + netflix.getColor() +";\n" +
                "    -fx-background-radius: 20;");
    }

    //ORACLE
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        netflixes.addAll(getData());
        setNetflixesToGrid();
    }

    private List<Netflix> getDataSearch(String findMe) {

        List<Netflix> netflixes = new ArrayList<>();

        Connection conn = getConnection();
        String q = "select * from netflix_Dilyara " +
                "WHERE lower(country) like '%"+findMe+"%' or lower(show_id) like '%"+findMe+"%' or lower(title) like '%"+findMe+"%' or " +
                "lower(type) like '%"+findMe+"%' or lower(director) like '%"+findMe+"%' or lower(cast) like '%"+findMe+"%' or " +
                "lower(date_added) like '%"+findMe+"%' or lower(rating) like '%"+findMe+"%' or lower(duration) like '%"+findMe+"%' or " +
                "lower(listed_in) like '%"+findMe+"%' or lower(description) like '%"+findMe+"%' or lower(image_url) like '%"+findMe+"%' or " +
                "lower(color) like '%"+findMe+"%'";
        //String q = "select * from netflix_Dilyara WHERE title like '%Candyman%'";
        System.out.println(q);
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(q);
            Netflix netflix;

            while (resultSet.next()) {
                netflix = new Netflix(resultSet.getString("show_id"),
                        resultSet.getString("type"),
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("cast"),
                        resultSet.getString("country"),
                        resultSet.getString("date_added"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("rating"),
                        resultSet.getString("duration"),
                        resultSet.getString("listed_in"),
                        resultSet.getString("description"),
                        resultSet.getString("image_Url"),
                        resultSet.getString("color"),
                        resultSet.getString("continent"),
                        resultSet.getString("category"),
                        resultSet.getInt("is_popular")
                );

                netflixes.add(netflix);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Try again!--get Data MethodSearch");
        }

        return netflixes;
    }


    private List<Netflix> getNetflixesFromCollection1() {

        List<Netflix> netflixes = new ArrayList<>();

        Connection conn = getConnection();
        String q = "select * from netflixCollection1 OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY";
        //String q = "select * from netflix_Dilyara WHERE title like '%Candyman%'";
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(q);
            Netflix netflix;

            while (resultSet.next()) {
                netflix = new Netflix(resultSet.getString("show_id"),
                        resultSet.getString("type"),
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("cast"),
                        resultSet.getString("country"),
                        resultSet.getString("date_added"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("rating"),
                        resultSet.getString("durating"),
                        resultSet.getString("listed_in"),
                        resultSet.getString("description")
                );

                netflixes.add(netflix);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Try again!-- getNetflixesFromCollection1");
        }

        return netflixes;
    }


    private List<Netflix> getNetflixesFromCollection2() {
        List<Netflix> netflixes = new ArrayList<>();

        Connection conn = getConnection();
        String q = "select * from netflixCollection2 OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY";
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(q);
            Netflix netflix;

            while (resultSet.next()) {
                netflix = new Netflix(resultSet.getString("show_id"),
                        resultSet.getString("type"),
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("cast"),
                        resultSet.getString("country"),
                        resultSet.getString("date_added"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("rating"),
                        resultSet.getString("durating"),
                        resultSet.getString("listed_in"),
                        resultSet.getString("description")
                );

                netflixes.add(netflix);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Try again!-- getNetflixesFromCollection2");
        }

        return netflixes;

    }

}
