package software.ulpgc.simulator.app;

import software.ulpgc.simulator.architecture.control.PendulumPresenter;
import software.ulpgc.simulator.architecture.control.PendulumSimulator;
import software.ulpgc.simulator.architecture.model.Pendulum;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Pendulum pendulum = new Pendulum(1, 1, -9.8, Math.PI/4, 0.0);
        PendulumSimulator simulator = new PendulumSimulator(0.001);
        SwingPendulumDisplay view = new SwingPendulumDisplay();

        PendulumPresenter presenter = new PendulumPresenter(view, simulator, pendulum);

        JFrame frame = new JFrame("Pendulum Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        presenter.execute();
    }
}
