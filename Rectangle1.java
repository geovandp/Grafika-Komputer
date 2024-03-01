import java.awt.*;
import java.awt.geom.*;

public class Rectangle1 extends Frame {

    Rectangle1() {
        addWindowListener(new MyFinishWindow());
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Use of antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // The lines should have a thickness of 3.0 instead of 1.0.
        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        GeneralPath gp = new GeneralPath();

        gp.moveTo(60, 80);

        gp.lineTo(300, 80);  // Adjusted the x-coordinate to make the rectangle wider
        gp.quadTo(320, 80, 320, 100);

        gp.lineTo(320, 200);  // Adjusted the y-coordinate to make the rectangle taller
        gp.quadTo(320, 220, 300, 220);

        gp.lineTo(60, 220);
        gp.quadTo(40, 220, 40, 200);

        gp.lineTo(40, 100);
        gp.quadTo(40, 80, 60, 80);

        g2d.draw(gp);

        g2d.setStroke(new BasicStroke(1.0f));

        // Draw a coordinate system.
        drawSimpleCoordinateSystem(350, 250, g2d);
    }

    public static void drawSimpleCoordinateSystem(int xmax, int ymax, Graphics2D g2d) {
        int xOffset = 30;
        int yOffset = 50;
        int step = 20;
        String s;
        // Remember the actual font.
        Font fo = g2d.getFont();
        // Use a small font.
        g2d.setFont(new Font("inter", Font.PLAIN, 9));
        // x-axis.
        g2d.drawLine(xOffset, yOffset, xmax, yOffset);
        // Marks and labels for the x-axis.
        for (int i = xOffset + step; i <= xmax; i = i + step) {
            g2d.drawLine(i, yOffset - 2, i, yOffset + 2);
            g2d.drawString(String.valueOf(i), i - 7, yOffset - 7);
        }

        // y-axis.
        g2d.drawLine(xOffset, yOffset, xOffset, ymax);

        s = "  "; // for indention of numbers < 100
        for (int i = yOffset + step; i <= ymax; i = i + step) {
            g2d.drawLine(xOffset - 2, i, xOffset + 2, i);
            if (i > 99) {
                s = "";
            }
            g2d.drawString(s + String.valueOf(i), xOffset - 25, i + 5);
        }

        // Reset to the original font.
        g2d.setFont(fo);
    }

    public static void main(String[] argv) {
        Rectangle1 f = new Rectangle1();
        f.setTitle("Rectangle");
        f.setSize(500, 300);  // Adjusted the window size
        f.setVisible(true);
    }
}