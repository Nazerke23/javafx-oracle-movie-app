package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
public class NewPieChartController implements Initializable {

    @FXML
    private Button btnPieChartNazad;

    @FXML
    private PieChart pie_id;

    @FXML
    void handleButtonPieChartNazad(ActionEvent event)  {
        MarketController.stage.close();
    }

    void pieChart() {
        String q = "select distinct country, COUNT(*) as c from netflix_Dilyara where country not like '%,%' group by country";
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            Connection conn = getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery(q);
            while (resultSet.next()) {
                pieChartData.add(new PieChart.Data(resultSet.getString("country"), resultSet.getInt("c")));
            }
            pie_id.setData(pieChartData);
//            pie_id.setMinSize(1000, 700);
        } catch (Exception ex) {
            System.out.println("Oops. Try again!");
        }

//        applyCustomColorSequence(pieChartData,
//                "turquoise", "aquamarine", "cornflowerblue",
//                "blue", "cadetblue", "navy", "deepskyblue",
//                "steelblue", "teal", "royalblue", "dodgerblue");
//        for (final PieChart.Data data : pie_id.getData()) {
//            data.getNode().addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                    e -> {
//                        double total = 0;
//                        for (PieChart.Data d : pie_id.getData()) {
//                            total += d.getPieValue();
//                        }
//                        caption.setTranslateX(e.getSceneX());
//                        caption.setTranslateY(e.getSceneY());
//                        String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
//                        caption.setText(text);
//                    }
//            );
//        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pieChart();
    }


    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
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
}
