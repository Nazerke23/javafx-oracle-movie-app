package Controller;

import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.paint.Color;
import model.NetflixCrud;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class NewCrudController implements Initializable {
    private ObservableList<NetflixCrud> ls = FXCollections.observableArrayList();
    @FXML
    private LineChart<  ?,? > bar_id;

    @FXML
    private TableView<NetflixCrud> tableNetflix;

    @FXML
    private TableColumn<NetflixCrud, String> colId;

    @FXML
    private TableColumn<NetflixCrud, String> colType;

    @FXML
    private TableColumn<NetflixCrud, String> colTitle;

    @FXML
    private PieChart pie_id;

    @FXML
    private TableColumn<NetflixCrud, String> colDirector;

    @FXML
    private TableColumn<NetflixCrud, String> colCast;

    @FXML
    private TableColumn<NetflixCrud, String> colCountry;

    @FXML
    private TableColumn<NetflixCrud, String> colDate;

    @FXML
    private TableColumn<NetflixCrud, Integer> colYear;

    @FXML
    private TableColumn<NetflixCrud, String> colRating;

    @FXML
    private TableColumn<NetflixCrud, String> colDuration;

    @FXML
    private TableColumn<NetflixCrud, String> colGender;

    @FXML
    private TableColumn<NetflixCrud, String> colDescription;

    @FXML
    private TextField tf;

    @FXML
    private Button find_btn;

    @FXML
    private TextField txId;

    @FXML
    private TextField txDirector;

    @FXML
    private TextField txDescription;

    @FXML
    private TextField txCountry;

    @FXML
    private TextField txRating;

    @FXML
    private TextField txGender;

    @FXML
    private TextField txDate;

    @FXML
    private TextField txType;

    @FXML
    private TextField txTitle;

    @FXML
    private TextField txCast;

    @FXML
    private TextField txYear;

    @FXML
    private TextField txDuration;

    @FXML
    private Button btnCrudNazad;

    @FXML
    void handleButtonCrudNazad(ActionEvent event) {
        MarketController.stage.close();
    }


    @FXML
    void deleteAction(ActionEvent event) {
        delete();
        txId.clear();
        txType.clear();
        txTitle.clear();
        txDuration.clear();
        txDescription.clear();
        txDate.clear();
        txDirector.clear();
        txGender.clear();
        txRating.clear();
        txCountry.clear();
        txCountry.clear();
        txYear.clear();
        txCast.clear();
    }

    @FXML
    void insertAction(ActionEvent event) {
        insert();
        txId.clear();
        txType.clear();
        txTitle.clear();
        txDuration.clear();
        txDescription.clear();
        txDate.clear();
        txDirector.clear();
        txGender.clear();
        txRating.clear();
        txCountry.clear();
        txCountry.clear();
        txYear.clear();
        txCast.clear();
    }

    @FXML
    void updateAction(ActionEvent event) {
        update();
        txId.clear();
        txType.clear();
        txTitle.clear();
        txDuration.clear();
        txDescription.clear();
        txDate.clear();
        txDirector.clear();
        txGender.clear();
        txRating.clear();
        txCountry.clear();
        txCountry.clear();
        txYear.clear();
        txCast.clear();
    }

    @FXML
    void showValue() {
        NetflixCrud netflix = tableNetflix.getSelectionModel().getSelectedItem();
        txId.setText(netflix.getShow_id());
        txType.setText(netflix.getType());
        txTitle.setText(netflix.getTitle());
        txDirector.setText(netflix.getDirector());
        txCast.setText(netflix.getCast());
        txCountry.setText(netflix.getCountry());
        txDate.setText(netflix.getDate_added());
        txRating.setText(netflix.getRating());
        txDuration.setText(netflix.getDuration());
        txYear.setText(""+netflix.getRelease_year());
        txGender.setText(netflix.getListed_in());
        txDescription.setText(netflix.getDescription());
    }


//
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

    ObservableList<NetflixCrud> getByQuery (String q) {
        ObservableList<NetflixCrud> ol = FXCollections.observableArrayList();
        Connection conn = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(q);
            NetflixCrud netflix;
            while (resultSet.next()) {
                netflix = new NetflixCrud(resultSet.getString("show_id"),
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
                        resultSet.getString("description")
                );
                ol.add(netflix);
            }
        }catch (Exception e) {
            System.out.println("Try again! get net");
        }
        return ol;
    }

    public void showNetflix() {
        ObservableList<NetflixCrud> list = getByQuery("select * from netflix_Dilyara");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showNetflix();
    }




//    public void insert() {
//
//        String query = "call insertProcedure('"+txId.getText()+"','"+txType.getText()+"','"+txTitle.getText()+"','"+txDirector.getText()+"'," +
//                "'"+txCast.getText()+"','"+txCountry.getText()+"','"+txDate.getText()+"',"+Integer.parseInt(txYear.getText())+",'"+txRating.getText()+"'," +
//                "'"+txDuration.getText()+"','"+txGender.getText()+"','"+txDescription.getText()+"')";
//        //String query =    "insert into netflix_temirlan (show_id) values ('s101')";
//        executing(query);
//        showNetflix();
//    }


    public void insert() {
        String query = "";
        if(txYear.getText().equals("")) {
            query = "call insertProcedure('"+txId.getText()+"','"+txType.getText()+"','"+txTitle.getText()+"','"+txDirector.getText()+"'," +
                    "'"+txCast.getText()+"','"+txCountry.getText()+"','"+txDate.getText()+"','"+txYear.getText()+"','"+txRating.getText()+"'," +
                    "'"+txDuration.getText()+"','"+txGender.getText()+"','"+txDescription.getText()+"')";
        } else {
            query = "call insertProcedure('" + txId.getText() + "','" + txType.getText() + "','" + txTitle.getText() + "','" + txDirector.getText() + "'," +
                    "'" + txCast.getText() + "','" + txCountry.getText() + "','" + txDate.getText() + "'," + Integer.parseInt(txYear.getText()) + ",'" + txRating.getText() + "'," +
                    "'" + txDuration.getText() + "','" + txGender.getText() + "','" + txDescription.getText() + "')";
        }
        executing(query);
        showNetflix();
    }

    public void update() {
        //String query = "update netflix_temirlan set type = 'movieeee' where show_id = 's1106'";
//        String query = "call updateProcedure('"+txId.getText()+"','"+txType.getText()+"','"+txTitle.getText()+"','"+txDirector.getText()+"'," +
//                "'"+txCast.getText()+"','"+txCountry.getText()+"','"+txDate.getText()+"',"+Integer.parseInt(txYear.getText())+",'"+txRating.getText()+"'," +
//                "'"+txDuration.getText()+"','"+txGender.getText()+"','"+txDescription.getText()+"')";

        System.out.println("update method");


        String query = "";
        if(txYear.getText().equals("")) {
            query = "call updateProcedure('"+txId.getText()+"','"+txType.getText()+"','"+txTitle.getText()+"','"+txDirector.getText()+"'," +
                    "'"+txCast.getText()+"','"+txCountry.getText()+"','"+txDate.getText()+"','"+txYear.getText()+"','"+txRating.getText()+"'," +
                    "'"+txDuration.getText()+"','"+txGender.getText()+"','"+txDescription.getText()+"')";
        } else {
            query = "call updateProcedure('" + txId.getText() + "','" + txType.getText() + "','" + txTitle.getText() + "','" + txDirector.getText() + "'," +
                    "'" + txCast.getText() + "','" + txCountry.getText() + "','" + txDate.getText() + "'," + Integer.parseInt(txYear.getText()) + ",'" + txRating.getText() + "'," +
                    "'" + txDuration.getText() + "','" + txGender.getText() + "','" + txDescription.getText() + "')";
        }

        System.out.println(query);
        executing(query);
        showNetflix();

    }

    public void delete() {


//        -----
        String query = "call deleteProcedure('"+txId.getText()+"')";
        //String query = "call deleteProcedure('s1080')";
        //String query = "delete from netflix_temirlan where show_id = 's1068'";
        executing(query);
        showNetflix();
        System.out.println("delete method");
//        ----

    }

    public void executing(String q) {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(q);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Try again!--executing");
        }
    }
}
