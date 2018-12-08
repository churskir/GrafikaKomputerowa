import geometry.*;
import geometry.Point;
import light.PointWithBrightness;
import light.Source;
import light.Sphere;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import static geometry.Axis.X;
import static geometry.Axis.Y;
import static geometry.Axis.Z;

public class MainPanel extends JPanel implements KeyListener {

    private ArrayList<Polygon> polygonsToView;
    private ArrayList<Point2D> pointsToView;

    private ArrayList<Face> faces = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();
    private Sphere sphere;


    private Camera camera;
    private Source light = new Source(new Point(1000,600,0));

    private final double rotationStep = Math.PI / 180;
    private final int width = 1000;
    private final int height = 600;

    public MainPanel() {
        System.out.println(Color.BLUE.getRed());
        System.out.println(Color.BLUE.getGreen());
        System.out.println(Color.BLUE.getBlue());

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);

        this.camera = new Camera(new Point(width / 2, height / 2, 0));

        // this.faces.addAll(Town.getFaces(new Point(width / 2, height / 1.5, 150)));
        this.sphere = new Sphere(new Point(width / 2, height / 2, 150), 60);
        this.points.addAll(this.sphere.getPoints());
        this.camera.countLight(sphere, light);

        this.polygonsToView = camera.getViewFaces(faces);
        this.pointsToView = camera.getViewPoint(points);
    }

    public void keyPressed(KeyEvent event) {
    }

    public void keyTyped(KeyEvent event) {
        this.interpreteKey(event.getKeyChar());
        this.polygonsToView = camera.getViewFaces(faces);
        this.repaint();
    }

    public void keyReleased(KeyEvent event) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        if (!this.polygonsToView.isEmpty())
            for (Polygon polygon: this.polygonsToView)
                this.paintPolygon(g2d, polygon);
        if (!this.pointsToView.isEmpty())
            for (PointWithBrightness point: this.camera.getPointsWithBrightness())
                this.paintPoint(g2d, point);
    }

    private void paintPolygon(Graphics2D g2d, Polygon polygon) {
        g2d.setColor(Color.BLACK);
        g2d.draw(polygon);
        g2d.setColor(Color.GREEN);
        g2d.fillPolygon(polygon);
    }

    private void paintPoint(Graphics2D g2d, PointWithBrightness point) {
        g2d.setColor(new Color(point.getRelativeBrightness(), point.getRelativeBrightness(), 255));
        g2d.drawOval((int) point.getPoint().getX(), (int) point.getPoint().getY(), 10, 10);
    }

    private void interpreteKey(char key) {
        switch (key) {
            case 'w':
                move(0, 1, 0);
                break;
            case 's':
                move(0, -1, 0);
                break;
            case 'a':
                move(1, 0, 0);
                break;
            case 'd':
                move(-1, 0, 0);
                break;
            case 'q':
                move(0, 0, 0.5);
                break;
            case 'e':
                move(0, 0, -0.5);
                break;
            case '+':
                this.camera.zoomIn();
                break;
            case '-':
                this.camera.zoomOut();
                break;
            case 'i':
                this.rotate(-1 * rotationStep, X);
                break;
            case 'k':
                this.rotate(rotationStep, X);
                break;
            case 'j':
                this.rotate(rotationStep, Y);
                break;
            case 'l':
                this.rotate(-1 * rotationStep, Y);
                break;
            case 'u':
                this.rotate(-1 * rotationStep, Z);
                break;
            case 'o':
                this.rotate(rotationStep, Z);
                break;
            default:
                break;
        }
    }

    private void move(double x, double y, double z) {
        for (Face face: this.faces) {
            face.move(new Vector(x, y, z));
        }
    }

    private void rotate(double angle, Axis axis) {
        Point cameraLocation;
        for (Face face: this.faces) {
            cameraLocation = camera.getLocation().getCopy();
            face.move(new Vector(
                    -1 * cameraLocation.getX(),
                    -1 * cameraLocation.getY(),
                    -1 * cameraLocation.getZ()
            ));
            face.rotate(angle, axis);
            face.move(new Vector(
                    cameraLocation.getX(),
                    cameraLocation.getY(),
                    cameraLocation.getZ()
            ));
        }
    }
}