import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class BeatingHeart3 extends Frame implements Runnable {

    int x = 20; // Initial x-coordinate of the dot
    int y = 150; // Initial y-coordinate of the dot
    int DotSize = 15; // Diameter of the dot
    int DELAY = 20; // Interval between updates in milliseconds (controls animation speed)

    Thread speed; //dot's controller

    public BeatingHeart3() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        speed = new Thread(this);
        speed.start(); 
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK); // Set the line's color

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke base = new BasicStroke(1.5f);
        g2d.setStroke(base);

        //drawing the initial Line
        GeneralPath gp2 = new GeneralPath();
        gp2.moveTo(20, 157);
        gp2.lineTo(520, 157);
        g2d.draw(gp2);

        g.setColor(Color.RED); // Set the dot's color
        g.fillOval(x, y, DotSize, DotSize);
    }

    @Override
    public void run() {
        while (true) {
            try {
                x = x + 3;

                //first beat
                if (x + DotSize >= 120 && x + DotSize <= 140 || x +DotSize >=180 && x +DotSize <= 200){
                    y = y -5;
                }

                if(x +DotSize >=140 && x +DotSize <= 180){
                    y = y +5;
                }

                //second beat
                if (x + DotSize >= 300 && x + DotSize <= 320 || x +DotSize >=360 && x +DotSize <= 380){
                    y = y -10;
                }

                if(x +DotSize >=320 && x +DotSize <= 360){
                    y = y +10;
                }

                //resetting location
                if (x + DotSize == 500) {
                    x = 20;
                    y = 150;
                }

                repaint(); // Redraw the panel

                // Sleep for the specified delay
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        ex frame = new ex();
        frame.setTitle("Beating Heart");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}