package software.ulpgc.simulator.app;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.simulator.architecture.DataView.PendulumData;

import javax.swing.*;
import java.util.List;

public class PendulumGraph {

    public static void plotPendulumData(PendulumData data) {
        XYSeries thetaSeries = new XYSeries("Theta (rad)");
        XYSeries omegaSeries = new XYSeries("Omega (rad/s)");

        List<Double> time = data.getTime();
        List<Double> theta = data.getTheta();
        List<Double> omega = data.getOmega();

        for (int i = 0; i < time.size(); i++) {
            thetaSeries.add(time.get(i), theta.get(i));
            omegaSeries.add(time.get(i), omega.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(thetaSeries);
        dataset.addSeries(omegaSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Pendulum Simulation",
                "Time (s)",
                "Value",
                dataset
        );

        NumberAxis yAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false); // For better scaling

        JFrame frame = new JFrame("Pendulum Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
