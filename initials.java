import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.Date;

/**
 * An example for an animation based on on convex combinations of
 * control points of two objects (the letters D and C).
 *
 * @author Frank Klawonn
 * Last change 04.02.2005
 */
public class initials extends Frame {

    //Constructor
    public initials() {
        //Enables the closing of the window.
        addWindowListener(new MyFinishWindow());
    }


    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //Use of antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //The lines should have a thickness of 3.0 instead of 1.0.
        BasicStroke bs = new BasicStroke(5.0f);
        g2d.setStroke(bs);


        //Control points for the letter G.
        int[] xd = new int[11]; //The index 0 is not used.
        int[] yd = new int[11]; //The index 0 is not used.
        xd[1] = 270;
        yd[1] = 80;

        xd[2] = 270;
        yd[2] = 300;

        xd[3] = 0;
        yd[3] = 200;

        xd[4] = 270;
        yd[4] = 300;

        xd[5] = 270;
        yd[5] = 190;

        xd[6] = 270;
        yd[6] = 190;

        xd[7] = 270;
        yd[7] = 190;

        xd[8] = 210;
        yd[8] = 190;

        xd[9] = 210;
        yd[9] = 190;


        //Control points for the letter D.
        int[] xc = new int[11]; //The index 0 is not used.
        int[] yc = new int[11]; //The index 0 is not used.

        xc[1] = 50;
        yc[1] = 50;

        xc[2] = 50;
        yc[2] = 450;

        xc[3] = 400;
        yc[3] = 250;

        xc[4] = 50;
        yc[4] = 450;

        xc[5] = 50;
        yc[5] = 350;

        //Both letters are composed of two quadratic curves. The first curve has
        //the first, third and second as control points, the second curve the
        //first, fifth and fourth point.
        QuadCurve2D.Double q1;
        QuadCurve2D.Double q2;
        QuadCurve2D.Double q3;


        //Draw the letter D.
        q1 = new QuadCurve2D.Double(xd[1], yd[1], xd[3], yd[3], xd[2], yd[2]);
        q2 = new QuadCurve2D.Double(xd[4], yd[4], xd[6], yd[6], xd[5], yd[5]);
        q3 = new QuadCurve2D.Double(xd[7], yd[7], xd[9], yd[9], xd[8], yd[8]);

        g2d.draw(q1);
        g2d.draw(q2);
        g2d.draw(q3);

        //Wait 1 second before starting the transformation from D to C.
        sustain(1000);


        //Arrays for the convex combinations of the control point of D and C
        double x[] = new double[xc.length];
        double y[] = new double[x.length];
        double alpha;

        //Number of steps (frames) that the transform from D to C should take.
        int steps = 50;
        double stepsDouble = steps; //Number of steps as a Double-value to avoid
        //repeated casting.
        for (int i = 1; i <= steps; i++) {
            alpha = i / stepsDouble;

            //Computation of the convex combinations for the five pairs of points.
            for (int j = 1; j < x.length; j++) {
                x[j] = (1 - alpha) * xd[j] + alpha * xc[j];
                y[j] = (1 - alpha) * yd[j] + alpha * yc[j];
            }

            //Generation of the two quadratic curves defined by the control points
            //given by the convex combinations.
            q1 = new QuadCurve2D.Double(x[1], y[1], x[3], y[3], x[2], y[2]);
            q2 = new QuadCurve2D.Double(x[1], y[1], x[5], y[5], x[4], y[4]);
            q3 = new QuadCurve2D.Double(x[1], y[1], x[5], y[5], x[4], y[4]);

            //Clear the window.
            clearWindow(g2d);

            //Draw the two quadratic curves.
            g2d.draw(q1);
            g2d.draw(q2);
            g2d.draw(q3);

            //A short waiting time until the next frame is drawn.
            sustain(100);

        }

    }


    /**
     * A method for clearing a window (drawing all white) of fixed size (600x300).
     *
     * @param g2d Graphics2D object used for drawing.
     */
    public static void clearWindow(Graphics2D g) {
        g.setPaint(Color.white);
        g.fill(new Rectangle(0, 0, 500, 500));
        g.setPaint(Color.black);
    }


    /**
     * A method for a delay of t milliseconds.
     * This method is used here only to keep the program simple.
     * This method involves active waiting, consuming unnecessary processor capicity.
     * For real applications threads should be used.
     *
     * @param t Waiting time in milliseconds
     */
    public static void sustain(long t) {
        long finish = (new Date()).getTime() + t;
        while ((new Date()).getTime() < finish) {
        }
    }


    public static void main(String[] argv) {
        initials f = new initials();
        f.setTitle("Merubah G ke D :(");

        f.setSize(500, 500);
        f.setVisible(true);
    }

}