package Controller;

import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import model.Netflix;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class NewSortController implements Initializable {
    private ObservableList<Netflix> ls = FXCollections.observableArrayList();
    @FXML
    private LineChart<  ?,? > bar_id;

    @FXML
    private TableView<Netflix> tableNetflix;

    @FXML
    private TableColumn<Netflix, String> colId;

    @FXML
    private TableColumn<Netflix, String> colType;

    @FXML
    private TableColumn<Netflix, String> colTitle;

    @FXML
    private TableColumn<Netflix, String> colDirector;

    @FXML
    private TableColumn<Netflix, String> colCast;

    @FXML
    private TableColumn<Netflix, String> colCountry;

    @FXML
    private TableColumn<Netflix, String> colDate;

    @FXML
    private TableColumn<Netflix, Integer> colYear;

    @FXML
    private TableColumn<Netflix, String> colRating;

    @FXML
    private TableColumn<Netflix, String> colDuration;

    @FXML
    private TableColumn<Netflix, String> colGender;

    @FXML
    private TableColumn<Netflix, String> colDescription;

    @FXML
    private TextField tf;

    @FXML
    private MenuButton dropdown_type;

    @FXML
    private MenuButton drop;

    @FXML
    private TextField date_from;

    @FXML
    private TextField date_to;

    @FXML
    private Button find_btn;

    @FXML
    private Button btnSortNazad;


    @FXML
    void handleButtonSortNazad(ActionEvent event) {
        MarketController.stage.close();
    }

    @FXML
    void sor_by_year(ActionEvent event) {
        String query = "";
        if(date_from.getText().equals("") || date_to.getText().equals("")) {
            query = "select * from netflix_Dilyara";
        } else {
            query = "select * from netflix_Dilyara " +
                    "where release_year between "+date_from.getText()+" and "+date_to.getText()+" order by release_year";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_brazil(ActionEvent event) {
        drop.setText("Brazil");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_france(ActionEvent event) {
        drop.setText("France");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_india(ActionEvent event) {
        drop.setText("India");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_japan(ActionEvent event) {
        drop.setText("Japan");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_spain(ActionEvent event) {
        drop.setText("Spain");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_states(ActionEvent event) {
        drop.setText("United States");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_united_kingdom(ActionEvent event) {
        drop.setText("United Kingdom");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_korea(ActionEvent event) {
        drop.setText("South Korea");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_china(ActionEvent event) {
        drop.setText("China");
        String query = "";
        if(dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara \n" +
                    "where country = '"+ drop.getText()+"'";
        }else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_def_type(ActionEvent event) {
        dropdown_type.setText("Type");
        String query = "";
        if(drop.getText().equals("Country")) {
            query = "select * from netflix_Dilyara";
        } else {
            query = "select * from netflix_Dilyara where country = '"+drop.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_def_country(ActionEvent event) {
        drop.setText("Country");
        String query = "";
        if (dropdown_type.getText().equals("Type")) {
            query = "select * from netflix_Dilyara";
        } else {
            query = "select * from netflix_Dilyara where type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_movies(ActionEvent event) {
        dropdown_type.setText("Movie");
        String query = "";
        if (drop.getText().equals("Country")) {
            query = "select * from netflix_Dilyara where type = 'Movie'";
        } else {
            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    @FXML
    void show_shows(ActionEvent event) {
        dropdown_type.setText("TV Show");
        String query = "";
        if (drop.getText().equals("Country")) {
            query = "select * from netflix_Dilyara where type = 'TV Show'";
        } else {

            query = "select * from netflix_Dilyara\n" +
                    "\twhere  country not like '%,%' and country = '"+drop.getText()+"' and type = '"+dropdown_type.getText()+"'";
            System.out.println(query);
        }
        ObservableList<Netflix> list = getByQuery(query);
        set_data();
        tableNetflix.setItems(list);
    }

    //TEMIRLAN-musql
//    Connection getConnection() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/lab4?autoReconnect=true&useSSL=false";
//            Connection conn = DriverManager.getConnection(url, "root", "1234");
////            System.out.println("Connection created!");
//            return conn;
//        }
//        catch(ClassNotFoundException e) {
//            System.out.println("Error!! No class! " + e.getMessage());
//            return null;
//        }
//        catch(SQLException e) {
//            System.out.println("Error!! No connection! " + e.getMessage());
//            return null;
//        }
//    }

    //Connnection-ORACLE
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

    ObservableList<Netflix> getByQuery (String q) {
        ObservableList<Netflix> ol = FXCollections.observableArrayList();
        Connection conn = getConnection();
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
                        resultSet.getString("image_url"),
                        resultSet.getString("color"),
                        resultSet.getString("continent"),
                        resultSet.getString("category"),
                        resultSet.getInt("is_popular")
                );

                ol.add(netflix);
            }
        }catch (Exception e) {
            System.out.println("Try again! get net");
        }
        return ol;
    }

    public void showNetflix() {
        ObservableList<Netflix> list = getByQuery("select * from netflix_Dilyara");
        set_data();
        tableNetflix.setItems(list);
    }

    void set_data() {
        colId.setCellValueFactory(new PropertyValueFactory<>("show_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colCast.setCellValueFactory(new PropertyValueFactory<>("cast"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_added"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("release_year"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("listed_in"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    void bar() {
        String q = "\n" +
                "select  release_year, count(*) as t from netflix_Dilyara \n" +
                "where type  like '%Movie%'\n" +
                "group by release_year\n";
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");
        LineChart lineChart = new LineChart(xAxis, yAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        try{
            Connection conn = getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery(q);
            while (resultSet.next()) {
                dataSeries1.getData().add(new XYChart.Data( resultSet.getString("release_year"),
                        resultSet.getInt("t")));
            }
            bar_id.getData().add(dataSeries1);
        }catch (Exception e) {
            System.out.println("Try again!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getConnection();
        showNetflix();
    }
}