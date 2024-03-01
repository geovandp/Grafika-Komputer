import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class SolarSystem24 extends Frame implements ActionListener {
    private int sunX;
    private int sunY;
    private int planetRadius = 20;
    private int orbitRadius = 150;
    private int angle = 0;
    private Timer timer;
    private Point closestPointToSun;

    SolarSystem24() {
        addWindowListener(new MyFinishWindow());
        timer = new Timer(20, this);  // Increased speed to 20 milliseconds
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        angle++;
        updateClosestPoint();
        repaint();
    }

    private void updateClosestPoint() {
        double closestX = sunX + orbitRadius * Math.cos(Math.toRadians(angle - 120));
        double closestY = sunY + orbitRadius * Math.sin(Math.toRadians(angle - 120));
        closestPointToSun = new Point((int) closestX, (int) closestY);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        int width = getWidth();
        int height = getHeight();

        sunX = width / 2;
        sunY = height / 2;

        // Draw the sun in orange
        g2d.setColor(new Color(255, 165, 0));
        g2d.fillOval(sunX - 20, sunY - 20, 60, 60);

        double planetX = sunX + orbitRadius * Math.cos(Math.toRadians(angle));
        double planetY = sunY + orbitRadius * Math.sin(Math.toRadians(angle));

        // Draw the Earth-like planet in green
        g2d.setColor(new Color(0, 128, 0));
        g2d.fillOval((int) (planetX - planetRadius), (int) (planetY - planetRadius), 2 * planetRadius, 2 * planetRadius);

        // Draw the closest point to the sun on the planet in red
        g2d.setColor(Color.RED);
        g2d.fillOval((int) (closestPointToSun.x - 3), (int) (closestPointToSun.y - 3), 6, 6);

        // Draw Pluto-like smaller planet in light blue with its own orbit
        double plutoX = sunX + 0.5 * orbitRadius * Math.cos(Math.toRadians(2 * angle));
        double plutoY = sunY + 0.5 * orbitRadius * Math.sin(Math.toRadians(2 * angle));

        g2d.setColor(new Color(173, 216, 230));
        g2d.fillOval((int) (plutoX - 0.5 * planetRadius), (int) (plutoY - 0.5 * planetRadius), planetRadius, planetRadius);
    }

    public static void main(String[] argv) {
        No4 f = new No4();
        f.setTitle("Orbit System Animation");
        f.setSize(500, 500);
        f.setVisible(true);
    }
}
