import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SolarSystem2 extends JFrame implements ActionListener {
    private int sunX;
    private int sunY;
    private int planetRadius = 30;
    private int orbitRadius = 200;
    private int angle = 0;
    private int rotations = 0;
    private Timer timer;
    private JLabel rotationLabel;

    SolarSystem2() {
        addWindowListener(new MyFinishWindow());
        timer = new Timer(20, this);
        timer.start();

        rotationLabel = new JLabel("Rotations: " + rotations);
        rotationLabel.setHorizontalAlignment(JLabel.CENTER);
        add(rotationLabel, BorderLayout.SOUTH);

        setTitle("Orbit System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        angle += 2;
        if (angle >= 360) {
            angle = 0;
            rotations++;
            rotationLabel.setText("Rotations: " + rotations);
        }
        repaint();
    }

    public void paint(Graphics g) {
        // Double buffering
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics offg = offscreen.getGraphics();
        super.paint(offg);

        Graphics2D g2d = (Graphics2D) offg;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        int width = getWidth();
        int height = getHeight();

        sunX = width / 2;
        sunY = height / 2;

        g2d.setColor(Color.ORANGE);
        g2d.fillOval(sunX - 30, sunY - 30, 80, 80);

        double planetX = sunX + orbitRadius * Math.cos(Math.toRadians(angle));
        double planetY = sunY + orbitRadius * Math.sin(Math.toRadians(angle));

        g2d.setColor(Color.GREEN);
        g2d.fillOval((int) (planetX - planetRadius), (int) (planetY - planetRadius), 2 * planetRadius,
                2 * planetRadius);

        // Menggambar hasil double buffering ke layar
        g.drawImage(offscreen, 0, 0, this);
    }

    public static void main(String[] argv) {
        SwingUtilities.invokeLater(() -> new No2());
    }
}