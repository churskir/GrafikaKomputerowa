import geometry.Axis;
import geometry.Cube;
import geometry.Line;
import geometry.Point;
import geometry.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import static geometry.Axis.X;
import static geometry.Axis.Y;
import static geometry.Axis.Z;

public class MainPanel extends JPanel implements KeyListener {

    private ArrayList<geometry.Line2D> linesToView;
    private ArrayList<Line> edges = new ArrayList<>();
    private Camera camera;

    private final int moveStep = 1;
    private final double rotationStep = Math.PI / 180;
    private final int width = 1000;
    private final int height = 600;

    public MainPanel() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);

        this.camera = new Camera(new Point(width / 2, height / 2, 0));
        this.edges.addAll(new Cube(new Point(width / 2, height / 2, 100), new Vector(50, 50, 50)).getEdges());
        this.edges.addAll(new Cube(new Point(width / 2, height / 2, 150), new Vector(10, 10, 10)).getEdges());
        this.linesToView = camera.getView(edges);
    }

    public void keyPressed(KeyEvent event) {
    }

    public void keyTyped(KeyEvent event) {
        this.interpreteKey(event.getKeyChar());
        this.linesToView = this.camera.getView(this.edges);
        this.repaint();
    }

    public void keyReleased(KeyEvent event) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (geometry.Line2D line: this.linesToView) {
            g2d.draw(line.toAwtLine2D());
        }
    }

    private void interpreteKey(char key) {
        switch (key) {
            case 'w':
                moveLines(0, 1, 0);
                break;
            case 's':
                moveLines(0, -1, 0);
                break;
            case 'a':
                moveLines(1, 0, 0);
                break;
            case 'd':
                moveLines(-1, 0, 0);
                break;
            case 'q':
                moveLines(0, 0, 1);
                break;
            case 'e':
                moveLines(0, 0, -1);
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

    private void moveLines(int x, int y, int z) {
        for (Line line: this.edges) {
            line.move(x, y, z);
        }
    }

    private void rotate(double angle, Axis axis) {
        Point cameraLocation;
        for (Line edge: this.edges) {
            cameraLocation = camera.getLocation().getCopy();
            edge.move(
                    -1 * cameraLocation.getX(),
                    -1 * cameraLocation.getY(),
                    -1 * cameraLocation.getZ()
            );
            edge.rotate(angle, axis);
            edge.move(
                    cameraLocation.getX(),
                    cameraLocation.getY(),
                    cameraLocation.getZ()
            );
        }
    }
}