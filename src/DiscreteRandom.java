import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class DiscreteRandom {

    private final double[] probability;
    private final double[] probabilityDistribution;
    private static boolean SIGNED = false;

    public DiscreteRandom(double[] probability) {
        this.probability = probability;
        this.probabilityDistribution = makeDistribution(probability);
    }

    public DiscreteRandom(int size) {
        this(generateProbability(size));
    }

    private double[] makeDistribution(double[] probs) {
        double[] distribution = new double[probs.length];
        double sum = 0;
        for (int i = 0; i < probs.length; i++) {
            sum += probs[i];
            distribution[i] = sum;
        }

        System.out.println(Arrays.toString(distribution));

        return distribution;
    }

    public int nextInt() {
        double rand = Math.random();
        int i = 0;

        while (rand > probabilityDistribution[i]) {
            i++;
        }

        if (DiscreteRandom.SIGNED && Math.random() < 0.5) {
            i = -1 * i;
        }

        return i;
    }

    public int[] generateList(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = this.nextInt();
        }

        return array;
    }

    private static double[] generateProbability(int size) {
        BigDecimal step = new BigDecimal(size);
        BigDecimal last = step.pow(2);
        BigDecimal[] before = new BigDecimal[size];
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 0; i < size; i++) {
            before[i] = last;
            sum = sum.add(last);
            last = last.subtract(step);
        }

        double[] after = new double[size];
        for (int i = 0; i < size; i++) {
            String str = before[i].divide(sum, 50, RoundingMode.DOWN).toString();
            after[i] = Double.parseDouble(str);
        }

        return after;
    }

    public String toString() {
        return "DiscreteRandom\n probability=" + Arrays.toString(probability) + "\nprobabilityDistribution=" + Arrays.toString(probabilityDistribution) + "]";
    }

    public static int[] test(int j) {
        int size = j, temp;
        int[] list = new int[size];

        DiscreteRandom randGen = new DiscreteRandom(size);

        for (int i = 0; i < size * size; i++) {
            temp = randGen.nextInt();
            list[temp]++;
        }

        System.out.println(Arrays.toString(list));
        return list;
    }

}
