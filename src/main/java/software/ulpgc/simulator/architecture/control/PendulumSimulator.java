package software.ulpgc.simulator.architecture.control;

import software.ulpgc.simulator.architecture.model.Pendulum;

public class PendulumSimulator {
    private final double dt;
    private static final int THETA = 0;
    private static  final int OMEGA = 1;

    public PendulumSimulator(double dt) {
        this.dt = dt;
    }

    public Pendulum simulate(Pendulum pendulum) {
        double[] newParameters = rungeKutta(pendulum);
        return new Pendulum(
                pendulum.L(),
                pendulum.r(),
                pendulum.g(),
                newParameters[THETA],
                newParameters[OMEGA]
        );
    }

    private double[] rungeKutta(Pendulum pendulum) {

        double k1Theta = dt * pendulum.omega();
        double k1Omega = dt * acceleration(pendulum.theta(), pendulum.g(), pendulum.L());

        double k2Theta = dt * (pendulum.omega() + k1Omega / 2.0);
        double k2Omega = dt * acceleration(pendulum.theta() + k1Theta / 2.0, pendulum.g(), pendulum.L());

        double k3Theta = dt * (pendulum.omega() + k2Omega / 2.0);
        double k3Omega = dt * acceleration(pendulum.theta() + k2Theta / 2.0, pendulum.g(), pendulum.L());

        double k4Theta = dt * (pendulum.omega() + k3Omega);
        double k4Omega = dt * acceleration(pendulum.theta() + k3Theta, pendulum.g(), pendulum.L());

        double newTheta = pendulum.theta() + (k1Theta + 2 * k2Theta + 2 * k3Theta + k4Theta) / 6.0;
        double newOmega = pendulum.omega() + (k1Omega + 2 * k2Omega + 2 * k3Omega + k4Omega) / 6.0;


        return new double[]{newTheta, newOmega};
    }

    private double acceleration(double theta, double g, double L) {
        return (g /  L) * Math.sin(theta);
    }
}
