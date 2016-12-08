import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartSample extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");

        Axis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart bc = new BarChart(xAxis, yAxis);
        Scene scene = new Scene(bc, 1920, 1080);

       /*
        * use the Gaussian class, the other one (DiscreteRandom class) is an example failed code ... 
        */
        
        //int[] test = DiscreteRandom.test(344);
        int[] test = Gaussian.test();
        
        XYChart.Series[] toShow = new XYChart.Series[test.length];

        for (int i = 0; i < test.length; i++) {
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data<String, Integer>(Integer.toString(i), test[i]));
            toShow[i] = series;
        }

        bc.getData().addAll(toShow);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}