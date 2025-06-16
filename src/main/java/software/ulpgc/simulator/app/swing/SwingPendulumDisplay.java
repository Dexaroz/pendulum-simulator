package software.ulpgc.simulator.app.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingPendulumDisplay extends JPanel {
    private final int originX = 400;
    private final int originY = 100;
    private double pendulumX;
    private double pendulumY;
    private boolean dragging = false;
    private final JLabel accelerationLabel;

    public SwingPendulumDisplay() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        accelerationLabel = new JLabel("Acceleration: 0.0 m/s²");
        accelerationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        accelerationLabel.setForeground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(accelerationLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isNearPendulum(e.getX(), e.getY())) {
                    dragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    updatePendulumPosition(e.getX(), e.getY());
                }
            }
        });
    }

    private boolean isNearPendulum(int x, int y) {
        double distance = Math.hypot(x - pendulumX, y - pendulumY);
        return distance < 20;
    }

    private double calculateRopeLength(double x, double y) {
        return Math.hypot(x - originX, y - originY) / 200.0;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void updatePendulumPosition(double x, double y) {
        this.pendulumX = x;
        this.pendulumY = y;
        repaint();
    }

    public void updateAcceleration(double acceleration) {
        accelerationLabel.setText(String.format("Acceleration: %.2f m/s²", acceleration));
    }

    public double getNewRopeLength() {
        return calculateRopeLength(pendulumX, pendulumY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(originX, originY, (int) pendulumX, (int) pendulumY);

        g2d.setColor(Color.RED);
        int ballRadius = 35;
        g2d.fillOval((int) pendulumX - ballRadius, (int) pendulumY - ballRadius, 2 * ballRadius, 2 * ballRadius);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(originX - 4, originY - 4, 8, 8);
    }

    public boolean isDragging() {
        return dragging;
    }

    public double getPendulumX() {
        return pendulumX;
    }

    public double getPendulumY() {
        return pendulumY;
    }
}
